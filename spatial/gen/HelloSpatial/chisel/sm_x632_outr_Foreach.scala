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

/** Hierarchy: x632 -> x75 **/
/** BEGIN None x632_outr_Foreach **/
class x632_outr_Foreach_kernel(
  list_x382_dram5D: List[FixedPoint],
  list_x592: List[DecoupledIO[AppCommandDense]],
  list_x593: List[DecoupledIO[AppLoadData]],
  list_x391_sram5D_0: List[StandardInterface],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new OuterControl(Streaming, 2, isFSM = false   , latency = 0.0.toInt, myName = "x632_outr_Foreach_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x632_outr_Foreach_iiCtr"))
  
  abstract class x632_outr_Foreach_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x593 = Flipped(Decoupled(new AppLoadData(ModuleParams.getParams("x593_p").asInstanceOf[(Int, Int)] )))
      val in_x391_sram5D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x391_sram5D_0_p").asInstanceOf[MemParams] ))
      val in_x382_dram5D = Input(new FixedPoint(true, 64, 0))
      val in_x592 = Decoupled(new AppCommandDense(ModuleParams.getParams("x592_p").asInstanceOf[(Int,Int)] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(2, 2, List(1, 1, 1, 1), List(32, 32, 32, 32)))
      val sigsOut = Output(new OutputKernelSignals(2, 2))
      val rr = Input(Bool())
    })
    def x593 = {io.in_x593} 
    def x391_sram5D_0 = {io.in_x391_sram5D_0} ; io.in_x391_sram5D_0 := DontCare
    def x382_dram5D = {io.in_x382_dram5D} 
    def x592 = {io.in_x592} 
  }
  def connectWires0(module: x632_outr_Foreach_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x593 <> x593
    x391_sram5D_0.connectLedger(module.io.in_x391_sram5D_0)
    module.io.in_x382_dram5D <> x382_dram5D
    module.io.in_x592 <> x592
  }
  val x382_dram5D = list_x382_dram5D(0)
  val x592 = list_x592(0)
  val x593 = list_x593(0)
  val x391_sram5D_0 = list_x391_sram5D_0(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x632_outr_Foreach")
    implicit val stack = ControllerStack.stack.toList
    class x632_outr_Foreach_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x632_outr_Foreach_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val b584_copyx608_inr_UnitPipe = io.sigsIn.cchainOutputs(0).counts(0).FP(true, 32, 0)
      val b585_copyx608_inr_UnitPipe = io.sigsIn.cchainOutputs(0).counts(1).FP(true, 32, 0)
      val b586_copyx608_inr_UnitPipe = io.sigsIn.cchainOutputs(0).counts(2).FP(true, 32, 0)
      val b587_copyx608_inr_UnitPipe = io.sigsIn.cchainOutputs(0).counts(3).FP(true, 32, 0)
      val b588_copyx608_inr_UnitPipe = ~io.sigsIn.cchainOutputs(0).oobs(0)
      val b589_copyx608_inr_UnitPipe = ~io.sigsIn.cchainOutputs(0).oobs(1)
      val b590_copyx608_inr_UnitPipe = ~io.sigsIn.cchainOutputs(0).oobs(2)
      val b591_copyx608_inr_UnitPipe = ~io.sigsIn.cchainOutputs(0).oobs(3)
      val b584_copyx631_inr_Foreach = io.sigsIn.cchainOutputs(1).counts(0).FP(true, 32, 0)
      val b585_copyx631_inr_Foreach = io.sigsIn.cchainOutputs(1).counts(1).FP(true, 32, 0)
      val b586_copyx631_inr_Foreach = io.sigsIn.cchainOutputs(1).counts(2).FP(true, 32, 0)
      val b587_copyx631_inr_Foreach = io.sigsIn.cchainOutputs(1).counts(3).FP(true, 32, 0)
      val b588_copyx631_inr_Foreach = ~io.sigsIn.cchainOutputs(1).oobs(0)
      val b589_copyx631_inr_Foreach = ~io.sigsIn.cchainOutputs(1).oobs(1)
      val b590_copyx631_inr_Foreach = ~io.sigsIn.cchainOutputs(1).oobs(2)
      val b591_copyx631_inr_Foreach = ~io.sigsIn.cchainOutputs(1).oobs(3)
      val x608_inr_UnitPipe = new x608_inr_UnitPipe_kernel(List(b591_copyx608_inr_UnitPipe,b590_copyx608_inr_UnitPipe,b589_copyx608_inr_UnitPipe,b588_copyx608_inr_UnitPipe), List(b587_copyx608_inr_UnitPipe,b586_copyx608_inr_UnitPipe,b584_copyx608_inr_UnitPipe,x382_dram5D,b585_copyx608_inr_UnitPipe), List(x592) , Some(me), List(), 0, 1, 1, List(1), List(32), breakpoints, rr)
      x608_inr_UnitPipe.sm.io.ctrDone := risingEdge(x608_inr_UnitPipe.sm.io.ctrInc)
      x608_inr_UnitPipe.backpressure := x592.ready | x608_inr_UnitPipe.sm.io.doneLatch
      x608_inr_UnitPipe.forwardpressure := true.B | x608_inr_UnitPipe.sm.io.doneLatch
      x608_inr_UnitPipe.sm.io.enableOut.zip(x608_inr_UnitPipe.smEnableOuts).foreach{case (l,r) => r := l}
      x608_inr_UnitPipe.sm.io.break := false.B
      x608_inr_UnitPipe.mask := true.B & b588_copyx608_inr_UnitPipe & b589_copyx608_inr_UnitPipe & b590_copyx608_inr_UnitPipe & b591_copyx608_inr_UnitPipe
      x608_inr_UnitPipe.configure("x608_inr_UnitPipe", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x608_inr_UnitPipe.kernel()
      val x610_ctr = new CtrObject(Left(Some(0)), Left(Some(16)), Left(Some(1)), 1, 32, false)
      val x611_ctrchain = (new CChainObject(List[CtrObject](x610_ctr), "x611_ctrchain")).cchain.io 
      x611_ctrchain.setup.isStream := false.B
      ModuleParams.addParams("x611_ctrchain_p", (x611_ctrchain.par, x611_ctrchain.widths))
      val x631_inr_Foreach = new x631_inr_Foreach_kernel(List(b591_copyx631_inr_Foreach,b590_copyx631_inr_Foreach,b589_copyx631_inr_Foreach,b588_copyx631_inr_Foreach), List(b587_copyx631_inr_Foreach,b586_copyx631_inr_Foreach,b584_copyx631_inr_Foreach,b585_copyx631_inr_Foreach), List(x593), List(x391_sram5D_0) , Some(me), List(x611_ctrchain), 1, 1, 1, List(1), List(32), breakpoints, rr)
      x631_inr_Foreach.sm.io.ctrDone := (x631_inr_Foreach.cchain.head.output.done).DS(1.toInt, rr, io.sigsIn.backpressure)
      x631_inr_Foreach.backpressure := true.B | x631_inr_Foreach.sm.io.doneLatch
      x631_inr_Foreach.forwardpressure := x593.valid | x631_inr_Foreach.sm.io.doneLatch
      x631_inr_Foreach.sm.io.enableOut.zip(x631_inr_Foreach.smEnableOuts).foreach{case (l,r) => r := l}
      x631_inr_Foreach.sm.io.break := false.B
      x631_inr_Foreach.mask := ~x631_inr_Foreach.cchain.head.output.noop & b588_copyx631_inr_Foreach & b589_copyx631_inr_Foreach & b590_copyx631_inr_Foreach & b591_copyx631_inr_Foreach
      x631_inr_Foreach.configure("x631_inr_Foreach", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x631_inr_Foreach.kernel()
    }
    val module = Module(new x632_outr_Foreach_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnrolledForeach x632_outr_Foreach **/
