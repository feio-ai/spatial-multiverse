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

/** Hierarchy: x825 -> x75 **/
/** BEGIN None x825_outr_UnitPipe **/
class x825_outr_UnitPipe_kernel(
  list_x388_dram_scatter1D: List[FixedPoint],
  list_x781: List[DecoupledIO[ScatterCmdStream]],
  list_x782: List[DecoupledIO[Bool]],
  list_x389_sram1D_0: List[StandardInterface],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new OuterControl(Streaming, 2, isFSM = false   , latency = 0.0.toInt, myName = "x825_outr_UnitPipe_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x825_outr_UnitPipe_iiCtr"))
  
  abstract class x825_outr_UnitPipe_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x782 = Flipped(Decoupled(Bool()))
      val in_x389_sram1D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x389_sram1D_0_p").asInstanceOf[MemParams] ))
      val in_x388_dram_scatter1D = Input(new FixedPoint(true, 64, 0))
      val in_x781 = Decoupled(new ScatterCmdStream(ModuleParams.getParams("x781_p").asInstanceOf[StreamParInfo] ))
      val in_x740_gathered_sram_0 = Flipped(new StandardInterface(ModuleParams.getParams("x740_gathered_sram_0_p").asInstanceOf[MemParams] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(2, 2, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(2, 2))
      val rr = Input(Bool())
    })
    def x782 = {io.in_x782} 
    def x389_sram1D_0 = {io.in_x389_sram1D_0} ; io.in_x389_sram1D_0 := DontCare
    def x388_dram_scatter1D = {io.in_x388_dram_scatter1D} 
    def x781 = {io.in_x781} 
    def x740_gathered_sram_0 = {io.in_x740_gathered_sram_0} ; io.in_x740_gathered_sram_0 := DontCare
  }
  def connectWires0(module: x825_outr_UnitPipe_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x782 <> x782
    x389_sram1D_0.connectLedger(module.io.in_x389_sram1D_0)
    module.io.in_x388_dram_scatter1D <> x388_dram_scatter1D
    module.io.in_x781 <> x781
    x740_gathered_sram_0.connectLedger(module.io.in_x740_gathered_sram_0)
  }
  val x388_dram_scatter1D = list_x388_dram_scatter1D(0)
  val x781 = list_x781(0)
  val x782 = list_x782(0)
  val x389_sram1D_0 = list_x389_sram1D_0(0)
  val x740_gathered_sram_0 = list_x389_sram1D_0(1)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x825_outr_UnitPipe")
    implicit val stack = ControllerStack.stack.toList
    class x825_outr_UnitPipe_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x825_outr_UnitPipe_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val x783_ctr = new CtrObject(Left(Some(0)), Left(Some(64)), Left(Some(1)), 1, 32, false)
      val x784_ctrchain = (new CChainObject(List[CtrObject](x783_ctr), "x784_ctrchain")).cchain.io 
      x784_ctrchain.setup.isStream := false.B
      ModuleParams.addParams("x784_ctrchain_p", (x784_ctrchain.par, x784_ctrchain.widths))
      val x816_inr_Foreach = new x816_inr_Foreach_kernel(List(x388_dram_scatter1D), List(x781), List(x389_sram1D_0,x740_gathered_sram_0) , Some(me), List(x784_ctrchain), 0, 1, 1, List(1), List(32), breakpoints, rr)
      x816_inr_Foreach.sm.io.ctrDone := (x816_inr_Foreach.cchain.head.output.done).DS(1.toInt, rr, io.sigsIn.backpressure)
      x816_inr_Foreach.backpressure := x781.ready | x816_inr_Foreach.sm.io.doneLatch
      x816_inr_Foreach.forwardpressure := true.B | x816_inr_Foreach.sm.io.doneLatch
      x816_inr_Foreach.sm.io.enableOut.zip(x816_inr_Foreach.smEnableOuts).foreach{case (l,r) => r := l}
      x816_inr_Foreach.sm.io.break := false.B
      x816_inr_Foreach.mask := ~x816_inr_Foreach.cchain.head.output.noop & true.B
      x816_inr_Foreach.configure("x816_inr_Foreach", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x816_inr_Foreach.kernel()
      val x818_ctr = new CtrObject(Left(Some(0)), Left(Some(64)), Left(Some(1)), 1, 32, false)
      val x819_ctrchain = (new CChainObject(List[CtrObject](x818_ctr), "x819_ctrchain")).cchain.io 
      x819_ctrchain.setup.isStream := false.B
      ModuleParams.addParams("x819_ctrchain_p", (x819_ctrchain.par, x819_ctrchain.widths))
      val x824_inr_Foreach = new x824_inr_Foreach_kernel(List(x782) , Some(me), List(x819_ctrchain), 1, 1, 1, List(1), List(32), breakpoints, rr)
      x824_inr_Foreach.sm.io.ctrDone := (x824_inr_Foreach.cchain.head.output.done).DS(1.toInt, rr, io.sigsIn.backpressure)
      x824_inr_Foreach.backpressure := true.B | x824_inr_Foreach.sm.io.doneLatch
      x824_inr_Foreach.forwardpressure := x782.valid | x824_inr_Foreach.sm.io.doneLatch
      x824_inr_Foreach.sm.io.enableOut.zip(x824_inr_Foreach.smEnableOuts).foreach{case (l,r) => r := l}
      x824_inr_Foreach.sm.io.break := false.B
      x824_inr_Foreach.mask := ~x824_inr_Foreach.cchain.head.output.noop & true.B
      x824_inr_Foreach.configure("x824_inr_Foreach", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x824_inr_Foreach.kernel()
    }
    val module = Module(new x825_outr_UnitPipe_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnitPipe x825_outr_UnitPipe **/
