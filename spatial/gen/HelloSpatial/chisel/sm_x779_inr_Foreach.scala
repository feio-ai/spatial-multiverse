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

/** Hierarchy: x779 -> x780 -> x75 **/
/** BEGIN None x779_inr_Foreach **/
class x779_inr_Foreach_kernel(
  list_x742: List[DecoupledIO[Vec[UInt]]],
  list_x740_gathered_sram_0: List[StandardInterface],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Pipelined, false   , latency = 2.0.toInt, myName = "x779_inr_Foreach_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x779_inr_Foreach_iiCtr"))
  
  abstract class x779_inr_Foreach_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x742 = Flipped(Decoupled(Vec(1,UInt(32.W))))
      val in_x740_gathered_sram_0 = Flipped(new StandardInterface(ModuleParams.getParams("x740_gathered_sram_0_p").asInstanceOf[MemParams] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def x742 = {io.in_x742} 
    def x740_gathered_sram_0 = {io.in_x740_gathered_sram_0} ; io.in_x740_gathered_sram_0 := DontCare
  }
  def connectWires0(module: x779_inr_Foreach_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x742 <> x742
    x740_gathered_sram_0.connectLedger(module.io.in_x740_gathered_sram_0)
  }
  val x742 = list_x742(0)
  val x740_gathered_sram_0 = list_x740_gathered_sram_0(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x779_inr_Foreach")
    implicit val stack = ControllerStack.stack.toList
    class x779_inr_Foreach_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x779_inr_Foreach_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val b771 = io.sigsIn.cchainOutputs.head.counts(0).FP(true, 32, 0); b771.suggestName("b771")
      val b772 = ~io.sigsIn.cchainOutputs.head.oobs(0); b772.suggestName("b772")
      val x773 = Wire(Vec(1, new FixedPoint(true, 32, 0))).suggestName("""x773""")
      x742.ready := b772 & (io.sigsIn.datapathEn) 
      (0 until 1).map{ i => x773(i).r := x742.bits(i).r }
      val x992 = Wire(Vec(1, new FixedPoint(true, 32, 0))).suggestName("x992_x773_D1") 
      (0 until 1).foreach{i => x992(i).r := getRetimed(x773(i).r, 1.toInt, io.sigsIn.backpressure)}
      val x774_elem_0 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x774_elem_0""")
      x774_elem_0.r := x992(0).r
      val x775 = Wire(Bool()).suggestName("""x775""")
      x775.r := Math.lt(b771, 64L.FP(true, 32, 0), Some(0.4), true.B,"x775").r
      val x993 = Wire(Bool()).suggestName("x993_x775_D1") 
      x993.r := getRetimed(x775.r, 1.toInt, io.sigsIn.backpressure)
      val x994 = Wire(new FixedPoint(true, 32, 0)).suggestName("x994_b771_D1") 
      x994.r := getRetimed(b771.r, 1.toInt, io.sigsIn.backpressure)
      val x995 = Wire(Bool()).suggestName("x995_b772_D1") 
      x995.r := getRetimed(b772.r, 1.toInt, io.sigsIn.backpressure)
      val x778_wr_banks = List[UInt](0L.FP(true, 32, 0).r)
      val x778_wr_ofs = List[UInt](x994.r)
      val x778_wr_en = List[Bool](true.B)
      val x778_wr_data = List[UInt](x774_elem_0.r)
      x740_gathered_sram_0.connectWPort(778, x778_wr_banks, x778_wr_ofs, x778_wr_data, x778_wr_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(1.0.toInt, rr, io.sigsIn.backpressure) & ~io.sigsIn.break && io.sigsIn.backpressure && x993 & x995))
    }
    val module = Module(new x779_inr_Foreach_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnrolledForeach x779_inr_Foreach **/
