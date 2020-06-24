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

/** Hierarchy: x410 -> x75 **/
/** BEGIN None x410_outr_UnitPipe **/
class x410_outr_UnitPipe_kernel(
  list_x379_dram1D: List[FixedPoint],
  list_x392: List[DecoupledIO[AppCommandDense]],
  list_x393: List[DecoupledIO[AppLoadData]],
  list_x389_sram1D_0: List[StandardInterface],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new OuterControl(Streaming, 2, isFSM = false   , latency = 0.0.toInt, myName = "x410_outr_UnitPipe_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x410_outr_UnitPipe_iiCtr"))
  
  abstract class x410_outr_UnitPipe_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x379_dram1D = Input(new FixedPoint(true, 64, 0))
      val in_x389_sram1D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x389_sram1D_0_p").asInstanceOf[MemParams] ))
      val in_x392 = Decoupled(new AppCommandDense(ModuleParams.getParams("x392_p").asInstanceOf[(Int,Int)] ))
      val in_x393 = Flipped(Decoupled(new AppLoadData(ModuleParams.getParams("x393_p").asInstanceOf[(Int, Int)] )))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(2, 2, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(2, 2))
      val rr = Input(Bool())
    })
    def x379_dram1D = {io.in_x379_dram1D} 
    def x389_sram1D_0 = {io.in_x389_sram1D_0} ; io.in_x389_sram1D_0 := DontCare
    def x392 = {io.in_x392} 
    def x393 = {io.in_x393} 
  }
  def connectWires0(module: x410_outr_UnitPipe_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x379_dram1D <> x379_dram1D
    x389_sram1D_0.connectLedger(module.io.in_x389_sram1D_0)
    module.io.in_x392 <> x392
    module.io.in_x393 <> x393
  }
  val x379_dram1D = list_x379_dram1D(0)
  val x392 = list_x392(0)
  val x393 = list_x393(0)
  val x389_sram1D_0 = list_x389_sram1D_0(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x410_outr_UnitPipe")
    implicit val stack = ControllerStack.stack.toList
    class x410_outr_UnitPipe_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x410_outr_UnitPipe_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val x398_inr_UnitPipe = new x398_inr_UnitPipe_kernel(List(x379_dram1D), List(x392) , Some(me), List(), 0, 1, 1, List(1), List(32), breakpoints, rr)
      x398_inr_UnitPipe.sm.io.ctrDone := risingEdge(x398_inr_UnitPipe.sm.io.ctrInc)
      x398_inr_UnitPipe.backpressure := x392.ready | x398_inr_UnitPipe.sm.io.doneLatch
      x398_inr_UnitPipe.forwardpressure := true.B | x398_inr_UnitPipe.sm.io.doneLatch
      x398_inr_UnitPipe.sm.io.enableOut.zip(x398_inr_UnitPipe.smEnableOuts).foreach{case (l,r) => r := l}
      x398_inr_UnitPipe.sm.io.break := false.B
      x398_inr_UnitPipe.mask := true.B & true.B
      x398_inr_UnitPipe.configure("x398_inr_UnitPipe", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x398_inr_UnitPipe.kernel()
      val x400_ctr = new CtrObject(Left(Some(0)), Left(Some(64)), Left(Some(1)), 1, 32, false)
      val x401_ctrchain = (new CChainObject(List[CtrObject](x400_ctr), "x401_ctrchain")).cchain.io 
      x401_ctrchain.setup.isStream := false.B
      ModuleParams.addParams("x401_ctrchain_p", (x401_ctrchain.par, x401_ctrchain.widths))
      val x409_inr_Foreach = new x409_inr_Foreach_kernel(List(x393), List(x389_sram1D_0) , Some(me), List(x401_ctrchain), 1, 1, 1, List(1), List(32), breakpoints, rr)
      x409_inr_Foreach.sm.io.ctrDone := (x409_inr_Foreach.cchain.head.output.done).DS(1.toInt, rr, io.sigsIn.backpressure)
      x409_inr_Foreach.backpressure := true.B | x409_inr_Foreach.sm.io.doneLatch
      x409_inr_Foreach.forwardpressure := x393.valid | x409_inr_Foreach.sm.io.doneLatch
      x409_inr_Foreach.sm.io.enableOut.zip(x409_inr_Foreach.smEnableOuts).foreach{case (l,r) => r := l}
      x409_inr_Foreach.sm.io.break := false.B
      x409_inr_Foreach.mask := ~x409_inr_Foreach.cchain.head.output.noop & true.B
      x409_inr_Foreach.configure("x409_inr_Foreach", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x409_inr_Foreach.kernel()
    }
    val module = Module(new x410_outr_UnitPipe_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnitPipe x410_outr_UnitPipe **/
