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

/** Hierarchy: x780 -> x75 **/
/** BEGIN None x780_outr_UnitPipe **/
class x780_outr_UnitPipe_kernel(
  list_x380_dram1D_longer: List[FixedPoint],
  list_x742: List[DecoupledIO[Vec[UInt]]],
  list_x389_sram1D_0: List[StandardInterface],
  list_x741: List[DecoupledIO[AppCommandSparse]],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new OuterControl(Streaming, 2, isFSM = false   , latency = 0.0.toInt, myName = "x780_outr_UnitPipe_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x780_outr_UnitPipe_iiCtr"))
  
  abstract class x780_outr_UnitPipe_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x741 = Decoupled(new AppCommandSparse(ModuleParams.getParams("x741_p").asInstanceOf[(Int,Int)] ))
      val in_x389_sram1D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x389_sram1D_0_p").asInstanceOf[MemParams] ))
      val in_x380_dram1D_longer = Input(new FixedPoint(true, 64, 0))
      val in_x742 = Flipped(Decoupled(Vec(1,UInt(32.W))))
      val in_x740_gathered_sram_0 = Flipped(new StandardInterface(ModuleParams.getParams("x740_gathered_sram_0_p").asInstanceOf[MemParams] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(2, 2, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(2, 2))
      val rr = Input(Bool())
    })
    def x741 = {io.in_x741} 
    def x389_sram1D_0 = {io.in_x389_sram1D_0} ; io.in_x389_sram1D_0 := DontCare
    def x380_dram1D_longer = {io.in_x380_dram1D_longer} 
    def x742 = {io.in_x742} 
    def x740_gathered_sram_0 = {io.in_x740_gathered_sram_0} ; io.in_x740_gathered_sram_0 := DontCare
  }
  def connectWires0(module: x780_outr_UnitPipe_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x741 <> x741
    x389_sram1D_0.connectLedger(module.io.in_x389_sram1D_0)
    module.io.in_x380_dram1D_longer <> x380_dram1D_longer
    module.io.in_x742 <> x742
    x740_gathered_sram_0.connectLedger(module.io.in_x740_gathered_sram_0)
  }
  val x380_dram1D_longer = list_x380_dram1D_longer(0)
  val x742 = list_x742(0)
  val x389_sram1D_0 = list_x389_sram1D_0(0)
  val x740_gathered_sram_0 = list_x389_sram1D_0(1)
  val x741 = list_x741(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x780_outr_UnitPipe")
    implicit val stack = ControllerStack.stack.toList
    class x780_outr_UnitPipe_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x780_outr_UnitPipe_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val x743_ctr = new CtrObject(Left(Some(0)), Left(Some(64)), Left(Some(1)), 1, 32, false)
      val x744_ctrchain = (new CChainObject(List[CtrObject](x743_ctr), "x744_ctrchain")).cchain.io 
      x744_ctrchain.setup.isStream := false.B
      ModuleParams.addParams("x744_ctrchain_p", (x744_ctrchain.par, x744_ctrchain.widths))
      val x767_inr_Foreach = new x767_inr_Foreach_kernel(List(x380_dram1D_longer), List(x389_sram1D_0), List(x741) , Some(me), List(x744_ctrchain), 0, 1, 1, List(1), List(32), breakpoints, rr)
      x767_inr_Foreach.sm.io.ctrDone := (x767_inr_Foreach.cchain.head.output.done).DS(1.toInt, rr, io.sigsIn.backpressure)
      x767_inr_Foreach.backpressure := x741.ready | x767_inr_Foreach.sm.io.doneLatch
      x767_inr_Foreach.forwardpressure := true.B | x767_inr_Foreach.sm.io.doneLatch
      x767_inr_Foreach.sm.io.enableOut.zip(x767_inr_Foreach.smEnableOuts).foreach{case (l,r) => r := l}
      x767_inr_Foreach.sm.io.break := false.B
      x767_inr_Foreach.mask := ~x767_inr_Foreach.cchain.head.output.noop & true.B
      x767_inr_Foreach.configure("x767_inr_Foreach", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x767_inr_Foreach.kernel()
      val x769_ctr = new CtrObject(Left(Some(0)), Left(Some(64)), Left(Some(1)), 1, 32, false)
      val x770_ctrchain = (new CChainObject(List[CtrObject](x769_ctr), "x770_ctrchain")).cchain.io 
      x770_ctrchain.setup.isStream := false.B
      ModuleParams.addParams("x770_ctrchain_p", (x770_ctrchain.par, x770_ctrchain.widths))
      val x779_inr_Foreach = new x779_inr_Foreach_kernel(List(x742), List(x740_gathered_sram_0) , Some(me), List(x770_ctrchain), 1, 1, 1, List(1), List(32), breakpoints, rr)
      x779_inr_Foreach.sm.io.ctrDone := (x779_inr_Foreach.cchain.head.output.done).DS(1.toInt, rr, io.sigsIn.backpressure)
      x779_inr_Foreach.backpressure := true.B | x779_inr_Foreach.sm.io.doneLatch
      x779_inr_Foreach.forwardpressure := x742.valid | x779_inr_Foreach.sm.io.doneLatch
      x779_inr_Foreach.sm.io.enableOut.zip(x779_inr_Foreach.smEnableOuts).foreach{case (l,r) => r := l}
      x779_inr_Foreach.sm.io.break := false.B
      x779_inr_Foreach.mask := ~x779_inr_Foreach.cchain.head.output.noop & true.B
      x779_inr_Foreach.configure("x779_inr_Foreach", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x779_inr_Foreach.kernel()
    }
    val module = Module(new x780_outr_UnitPipe_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnitPipe x780_outr_UnitPipe **/
