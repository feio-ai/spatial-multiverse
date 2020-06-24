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

/** Hierarchy: x409 -> x410 -> x75 **/
/** BEGIN None x409_inr_Foreach **/
class x409_inr_Foreach_kernel(
  list_x393: List[DecoupledIO[AppLoadData]],
  list_x389_sram1D_0: List[StandardInterface],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Pipelined, false   , latency = 2.0.toInt, myName = "x409_inr_Foreach_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x409_inr_Foreach_iiCtr"))
  
  abstract class x409_inr_Foreach_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x389_sram1D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x389_sram1D_0_p").asInstanceOf[MemParams] ))
      val in_x393 = Flipped(Decoupled(new AppLoadData(ModuleParams.getParams("x393_p").asInstanceOf[(Int, Int)] )))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def x389_sram1D_0 = {io.in_x389_sram1D_0} ; io.in_x389_sram1D_0 := DontCare
    def x393 = {io.in_x393} 
  }
  def connectWires0(module: x409_inr_Foreach_module)(implicit stack: List[KernelHash]): Unit = {
    x389_sram1D_0.connectLedger(module.io.in_x389_sram1D_0)
    module.io.in_x393 <> x393
  }
  val x393 = list_x393(0)
  val x389_sram1D_0 = list_x389_sram1D_0(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x409_inr_Foreach")
    implicit val stack = ControllerStack.stack.toList
    class x409_inr_Foreach_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x409_inr_Foreach_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val b402 = io.sigsIn.cchainOutputs.head.counts(0).FP(true, 32, 0); b402.suggestName("b402")
      val b403 = ~io.sigsIn.cchainOutputs.head.oobs(0); b403.suggestName("b403")
      val x404 = Wire(Vec(1, new FixedPoint(true, 32, 0))).suggestName("""x404""")
      x393.ready := b403 & (io.sigsIn.datapathEn) 
      (0 until 1).map{ i => x404(i).r := x393.bits.rdata(i).r }
      val x923 = Wire(Vec(1, new FixedPoint(true, 32, 0))).suggestName("x923_x404_D1") 
      (0 until 1).foreach{i => x923(i).r := getRetimed(x404(i).r, 1.toInt, io.sigsIn.backpressure)}
      val x405_elem_0 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x405_elem_0""")
      x405_elem_0.r := x923(0).r
      val x924 = Wire(new FixedPoint(true, 32, 0)).suggestName("x924_b402_D1") 
      x924.r := getRetimed(b402.r, 1.toInt, io.sigsIn.backpressure)
      val x925 = Wire(Bool()).suggestName("x925_b403_D1") 
      x925.r := getRetimed(b403.r, 1.toInt, io.sigsIn.backpressure)
      val x408_wr_banks = List[UInt](0L.FP(true, 32, 0).r)
      val x408_wr_ofs = List[UInt](x924.r)
      val x408_wr_en = List[Bool](true.B)
      val x408_wr_data = List[UInt](x405_elem_0.r)
      x389_sram1D_0.connectWPort(408, x408_wr_banks, x408_wr_ofs, x408_wr_data, x408_wr_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(1.0.toInt, rr, io.sigsIn.backpressure) & ~io.sigsIn.break && io.sigsIn.backpressure && x925))
    }
    val module = Module(new x409_inr_Foreach_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnrolledForeach x409_inr_Foreach **/
