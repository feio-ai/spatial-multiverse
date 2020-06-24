package accel
import fringe._
import fringe.templates.memory._
import fringe.templates._
import fringe.Ledger._
import fringe.utils._
import fringe.utils.implicits._
import fringe.templates.math._
import fringe.templates.counters._
import fringe.templates.vector._
import fringe.SpatialBlocks._
import fringe.templates.memory._
import fringe.templates.memory.implicits._
import fringe.templates.retiming._
import emul.ResidualGenerator._
import api._
import chisel3._
import chisel3.util._
import Args._
import scala.collection.immutable._

/** Hierarchy: x739 -> x75 **/
/** BEGIN None x739_outr_Foreach **/
class x739_outr_Foreach_kernel(
  list_x639: List[DecoupledIO[Bool]],
  list_x638: List[DecoupledIO[AppStoreData]],
  list_x390_sram2D_0: List[StandardInterface],
  list_x387_dram_result2D: List[FixedPoint],
  list_x637: List[DecoupledIO[AppCommandDense]],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new OuterControl(Streaming, 3, isFSM = false   , latency = 0.0.toInt, myName = "x739_outr_Foreach_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x739_outr_Foreach_iiCtr"))
  
  abstract class x739_outr_Foreach_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x638 = Decoupled(new AppStoreData(ModuleParams.getParams("x638_p").asInstanceOf[(Int,Int)] ))
      val in_x637 = Decoupled(new AppCommandDense(ModuleParams.getParams("x637_p").asInstanceOf[(Int,Int)] ))
      val in_x387_dram_result2D = Input(new FixedPoint(true, 64, 0))
      val in_x390_sram2D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x390_sram2D_0_p").asInstanceOf[MemParams] ))
      val in_x639 = Flipped(Decoupled(Bool()))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(3, 3, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(3, 3))
      val rr = Input(Bool())
    })
    def x638 = {io.in_x638} 
    def x637 = {io.in_x637} 
    def x387_dram_result2D = {io.in_x387_dram_result2D} 
    def x390_sram2D_0 = {io.in_x390_sram2D_0} ; io.in_x390_sram2D_0 := DontCare
    def x639 = {io.in_x639} 
  }
  def connectWires0(module: x739_outr_Foreach_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x638 <> x638
    module.io.in_x637 <> x637
    module.io.in_x387_dram_result2D <> x387_dram_result2D
    x390_sram2D_0.connectLedger(module.io.in_x390_sram2D_0)
    module.io.in_x639 <> x639
  }
  val x639 = list_x639(0)
  val x638 = list_x638(0)
  val x390_sram2D_0 = list_x390_sram2D_0(0)
  val x387_dram_result2D = list_x387_dram_result2D(0)
  val x637 = list_x637(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x739_outr_Foreach")
    implicit val stack = ControllerStack.stack.toList
    class x739_outr_Foreach_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x739_outr_Foreach_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val b635_copyx648_inr_UnitPipe = io.sigsIn.cchainOutputs(0).counts(0).FP(true, 32, 0)
      val b636_copyx648_inr_UnitPipe = ~io.sigsIn.cchainOutputs(0).oobs(0)
      val b635_copyx734_inr_Foreach = io.sigsIn.cchainOutputs(1).counts(0).FP(true, 32, 0)
      val b636_copyx734_inr_Foreach = ~io.sigsIn.cchainOutputs(1).oobs(0)
      val b635_copyx738_inr_UnitPipe = io.sigsIn.cchainOutputs(2).counts(0).FP(true, 32, 0)
      val b636_copyx738_inr_UnitPipe = ~io.sigsIn.cchainOutputs(2).oobs(0)
      val x648_inr_UnitPipe = new x648_inr_UnitPipe_kernel(List(b636_copyx648_inr_UnitPipe), List(x387_dram_result2D,b635_copyx648_inr_UnitPipe), List(x637) , Some(me), List(), 0, 1, 1, List(1), List(32), breakpoints, rr)
      x648_inr_UnitPipe.sm.io.ctrDone := risingEdge(x648_inr_UnitPipe.sm.io.ctrInc)
      x648_inr_UnitPipe.backpressure := x637.ready | x648_inr_UnitPipe.sm.io.doneLatch
      x648_inr_UnitPipe.forwardpressure := true.B | x648_inr_UnitPipe.sm.io.doneLatch
      x648_inr_UnitPipe.sm.io.enableOut.zip(x648_inr_UnitPipe.smEnableOuts).foreach{case (l,r) => r := l}
      x648_inr_UnitPipe.sm.io.break := false.B
      x648_inr_UnitPipe.mask := true.B & b636_copyx648_inr_UnitPipe
      x648_inr_UnitPipe.configure("x648_inr_UnitPipe", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x648_inr_UnitPipe.kernel()
      val x649_ctr = new CtrObject(Left(Some(0)), Left(Some(32)), Left(Some(1)), 8, 32, false)
      val x650_ctrchain = (new CChainObject(List[CtrObject](x649_ctr), "x650_ctrchain")).cchain.io 
      x650_ctrchain.setup.isStream := false.B
      ModuleParams.addParams("x650_ctrchain_p", (x650_ctrchain.par, x650_ctrchain.widths))
      val x734_inr_Foreach = new x734_inr_Foreach_kernel(List(b636_copyx734_inr_Foreach), List(b635_copyx734_inr_Foreach), List(x390_sram2D_0), List(x638) , Some(me), List(x650_ctrchain), 1, 1, 1, List(8), List(32), breakpoints, rr)
      x734_inr_Foreach.sm.io.ctrDone := (x734_inr_Foreach.cchain.head.output.done).DS(1.toInt, rr, io.sigsIn.backpressure)
      x734_inr_Foreach.backpressure := x638.ready | x734_inr_Foreach.sm.io.doneLatch
      x734_inr_Foreach.forwardpressure := true.B | x734_inr_Foreach.sm.io.doneLatch
      x734_inr_Foreach.sm.io.enableOut.zip(x734_inr_Foreach.smEnableOuts).foreach{case (l,r) => r := l}
      x734_inr_Foreach.sm.io.break := false.B
      x734_inr_Foreach.mask := ~x734_inr_Foreach.cchain.head.output.noop & b636_copyx734_inr_Foreach
      x734_inr_Foreach.configure("x734_inr_Foreach", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x734_inr_Foreach.kernel()
      val x738_inr_UnitPipe = new x738_inr_UnitPipe_kernel(List(b636_copyx738_inr_UnitPipe), List(x639) , Some(me), List(), 2, 1, 1, List(1), List(32), breakpoints, rr)
      x738_inr_UnitPipe.sm.io.ctrDone := risingEdge(x738_inr_UnitPipe.sm.io.ctrInc)
      x738_inr_UnitPipe.backpressure := true.B | x738_inr_UnitPipe.sm.io.doneLatch
      x738_inr_UnitPipe.forwardpressure := x639.valid | x738_inr_UnitPipe.sm.io.doneLatch
      x738_inr_UnitPipe.sm.io.enableOut.zip(x738_inr_UnitPipe.smEnableOuts).foreach{case (l,r) => r := l}
      x738_inr_UnitPipe.sm.io.break := false.B
      x738_inr_UnitPipe.mask := true.B & b636_copyx738_inr_UnitPipe
      x738_inr_UnitPipe.configure("x738_inr_UnitPipe", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x738_inr_UnitPipe.kernel()
    }
    val module = Module(new x739_outr_Foreach_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnrolledForeach x739_outr_Foreach **/
