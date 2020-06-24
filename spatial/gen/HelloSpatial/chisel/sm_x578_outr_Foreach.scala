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

/** Hierarchy: x578 -> x75 **/
/** BEGIN None x578_outr_Foreach **/
class x578_outr_Foreach_kernel(
  list_x381_dram2D: List[FixedPoint],
  list_x415: List[DecoupledIO[AppCommandDense]],
  list_x416: List[DecoupledIO[AppLoadData]],
  list_x390_sram2D_0: List[StandardInterface],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new OuterControl(Streaming, 2, isFSM = false   , latency = 0.0.toInt, myName = "x578_outr_Foreach_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x578_outr_Foreach_iiCtr"))
  
  abstract class x578_outr_Foreach_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x416 = Flipped(Decoupled(new AppLoadData(ModuleParams.getParams("x416_p").asInstanceOf[(Int, Int)] )))
      val in_x381_dram2D = Input(new FixedPoint(true, 64, 0))
      val in_x390_sram2D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x390_sram2D_0_p").asInstanceOf[MemParams] ))
      val in_x415 = Decoupled(new AppCommandDense(ModuleParams.getParams("x415_p").asInstanceOf[(Int,Int)] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(2, 2, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(2, 2))
      val rr = Input(Bool())
    })
    def x416 = {io.in_x416} 
    def x381_dram2D = {io.in_x381_dram2D} 
    def x390_sram2D_0 = {io.in_x390_sram2D_0} ; io.in_x390_sram2D_0 := DontCare
    def x415 = {io.in_x415} 
  }
  def connectWires0(module: x578_outr_Foreach_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x416 <> x416
    module.io.in_x381_dram2D <> x381_dram2D
    x390_sram2D_0.connectLedger(module.io.in_x390_sram2D_0)
    module.io.in_x415 <> x415
  }
  val x381_dram2D = list_x381_dram2D(0)
  val x415 = list_x415(0)
  val x416 = list_x416(0)
  val x390_sram2D_0 = list_x390_sram2D_0(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x578_outr_Foreach")
    implicit val stack = ControllerStack.stack.toList
    class x578_outr_Foreach_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x578_outr_Foreach_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val b413_copyx426_inr_UnitPipe = io.sigsIn.cchainOutputs(0).counts(0).FP(true, 32, 0)
      val b414_copyx426_inr_UnitPipe = ~io.sigsIn.cchainOutputs(0).oobs(0)
      val b413_copyx577_inr_Foreach = io.sigsIn.cchainOutputs(1).counts(0).FP(true, 32, 0)
      val b414_copyx577_inr_Foreach = ~io.sigsIn.cchainOutputs(1).oobs(0)
      val x426_inr_UnitPipe = new x426_inr_UnitPipe_kernel(List(b414_copyx426_inr_UnitPipe), List(x381_dram2D,b413_copyx426_inr_UnitPipe), List(x415) , Some(me), List(), 0, 1, 1, List(1), List(32), breakpoints, rr)
      x426_inr_UnitPipe.sm.io.ctrDone := risingEdge(x426_inr_UnitPipe.sm.io.ctrInc)
      x426_inr_UnitPipe.backpressure := x415.ready | x426_inr_UnitPipe.sm.io.doneLatch
      x426_inr_UnitPipe.forwardpressure := true.B | x426_inr_UnitPipe.sm.io.doneLatch
      x426_inr_UnitPipe.sm.io.enableOut.zip(x426_inr_UnitPipe.smEnableOuts).foreach{case (l,r) => r := l}
      x426_inr_UnitPipe.sm.io.break := false.B
      x426_inr_UnitPipe.mask := true.B & b414_copyx426_inr_UnitPipe
      x426_inr_UnitPipe.configure("x426_inr_UnitPipe", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x426_inr_UnitPipe.kernel()
      val x428_ctr = new CtrObject(Left(Some(0)), Left(Some(32)), Left(Some(1)), 16, 32, false)
      val x429_ctrchain = (new CChainObject(List[CtrObject](x428_ctr), "x429_ctrchain")).cchain.io 
      x429_ctrchain.setup.isStream := false.B
      ModuleParams.addParams("x429_ctrchain_p", (x429_ctrchain.par, x429_ctrchain.widths))
      val x577_inr_Foreach = new x577_inr_Foreach_kernel(List(b414_copyx577_inr_Foreach), List(b413_copyx577_inr_Foreach), List(x416), List(x390_sram2D_0) , Some(me), List(x429_ctrchain), 1, 1, 1, List(16), List(32), breakpoints, rr)
      x577_inr_Foreach.sm.io.ctrDone := (x577_inr_Foreach.cchain.head.output.done).DS(1.toInt, rr, io.sigsIn.backpressure)
      x577_inr_Foreach.backpressure := true.B | x577_inr_Foreach.sm.io.doneLatch
      x577_inr_Foreach.forwardpressure := x416.valid | x577_inr_Foreach.sm.io.doneLatch
      x577_inr_Foreach.sm.io.enableOut.zip(x577_inr_Foreach.smEnableOuts).foreach{case (l,r) => r := l}
      x577_inr_Foreach.sm.io.break := false.B
      x577_inr_Foreach.mask := ~x577_inr_Foreach.cchain.head.output.noop & b414_copyx577_inr_Foreach
      x577_inr_Foreach.configure("x577_inr_Foreach", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x577_inr_Foreach.kernel()
    }
    val module = Module(new x578_outr_Foreach_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnrolledForeach x578_outr_Foreach **/
