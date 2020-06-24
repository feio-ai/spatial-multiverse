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

/** Hierarchy: x631 -> x632 -> x75 **/
/** BEGIN None x631_inr_Foreach **/
class x631_inr_Foreach_kernel(
  list_b591: List[Bool],
  list_b587: List[FixedPoint],
  list_x593: List[DecoupledIO[AppLoadData]],
  list_x391_sram5D_0: List[StandardInterface],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Pipelined, false   , latency = 4.2.toInt, myName = "x631_inr_Foreach_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x631_inr_Foreach_iiCtr"))
  
  abstract class x631_inr_Foreach_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_b587 = Input(new FixedPoint(true, 32, 0))
      val in_b591 = Input(Bool())
      val in_x593 = Flipped(Decoupled(new AppLoadData(ModuleParams.getParams("x593_p").asInstanceOf[(Int, Int)] )))
      val in_b590 = Input(Bool())
      val in_b586 = Input(new FixedPoint(true, 32, 0))
      val in_b584 = Input(new FixedPoint(true, 32, 0))
      val in_x391_sram5D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x391_sram5D_0_p").asInstanceOf[MemParams] ))
      val in_b589 = Input(Bool())
      val in_b585 = Input(new FixedPoint(true, 32, 0))
      val in_b588 = Input(Bool())
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def b587 = {io.in_b587} 
    def b591 = {io.in_b591} 
    def x593 = {io.in_x593} 
    def b590 = {io.in_b590} 
    def b586 = {io.in_b586} 
    def b584 = {io.in_b584} 
    def x391_sram5D_0 = {io.in_x391_sram5D_0} ; io.in_x391_sram5D_0 := DontCare
    def b589 = {io.in_b589} 
    def b585 = {io.in_b585} 
    def b588 = {io.in_b588} 
  }
  def connectWires0(module: x631_inr_Foreach_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_b587 <> b587
    module.io.in_b591 <> b591
    module.io.in_x593 <> x593
    module.io.in_b590 <> b590
    module.io.in_b586 <> b586
    module.io.in_b584 <> b584
    x391_sram5D_0.connectLedger(module.io.in_x391_sram5D_0)
    module.io.in_b589 <> b589
    module.io.in_b585 <> b585
    module.io.in_b588 <> b588
  }
  val b591 = list_b591(0)
  val b590 = list_b591(1)
  val b589 = list_b591(2)
  val b588 = list_b591(3)
  val b587 = list_b587(0)
  val b586 = list_b587(1)
  val b584 = list_b587(2)
  val b585 = list_b587(3)
  val x593 = list_x593(0)
  val x391_sram5D_0 = list_x391_sram5D_0(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x631_inr_Foreach")
    implicit val stack = ControllerStack.stack.toList
    class x631_inr_Foreach_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x631_inr_Foreach_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val b612 = io.sigsIn.cchainOutputs.head.counts(0).FP(true, 32, 0); b612.suggestName("b612")
      val b613 = ~io.sigsIn.cchainOutputs.head.oobs(0); b613.suggestName("b613")
      val x614 = Wire(Vec(1, new FixedPoint(true, 32, 0))).suggestName("""x614""")
      x593.ready := b591 & b590 & b589 & b613 & b588 & (io.sigsIn.datapathEn) 
      (0 until 1).map{ i => x614(i).r := x593.bits.rdata(i).r }
      val x948 = Wire(Vec(1, new FixedPoint(true, 32, 0))).suggestName("x948_x614_D1") 
      (0 until 1).foreach{i => x948(i).r := getRetimed(x614(i).r, 1.toInt, io.sigsIn.backpressure)}
      val x615_elem_0 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x615_elem_0""")
      x615_elem_0.r := x948(0).r
      val x621 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x621""")
      x621.r := Math.arith_left_shift(b584, 7, Some(0.2), true.B,"x621").r
      val x622 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x622""")
      x622.r := Math.arith_left_shift(b585, 6, Some(0.2), true.B,"x622").r
      val x623 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x623""")
      x623.r := Math.arith_left_shift(b586, 5, Some(0.2), true.B,"x623").r
      val x624 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x624""")
      x624.r := Math.arith_left_shift(b587, 4, Some(0.2), true.B,"x624").r
      val x625_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x625_sum""")
      x625_sum.r := Math.add(x621,x622,Some(1.0), true.B, Truncate, Wrapping, "x625_sum").r
      val x626_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x626_sum""")
      x626_sum.r := Math.add(x623,x624,Some(1.0), true.B, Truncate, Wrapping, "x626_sum").r
      val x627_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x627_sum""")
      x627_sum.r := Math.add(x625_sum,x626_sum,Some(1.0), true.B, Truncate, Wrapping, "x627_sum").r
      val x949 = Wire(new FixedPoint(true, 32, 0)).suggestName("x949_b612_D2") 
      x949.r := getRetimed(b612.r, 2.toInt, io.sigsIn.backpressure)
      val x628_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x628_sum""")
      x628_sum.r := Math.add(x627_sum,x949,Some(1.0), true.B, Truncate, Wrapping, "x628_sum").r
      val x950 = Wire(Bool()).suggestName("x950_b591_D3") 
      x950.r := getRetimed(b591.r, 3.toInt, io.sigsIn.backpressure)
      val x951 = Wire(new FixedPoint(true, 32, 0)).suggestName("x951_x615_elem_0_D2") 
      x951.r := getRetimed(x615_elem_0.r, 2.toInt, io.sigsIn.backpressure)
      val x952 = Wire(Bool()).suggestName("x952_b590_D3") 
      x952.r := getRetimed(b590.r, 3.toInt, io.sigsIn.backpressure)
      val x953 = Wire(Bool()).suggestName("x953_b589_D3") 
      x953.r := getRetimed(b589.r, 3.toInt, io.sigsIn.backpressure)
      val x954 = Wire(Bool()).suggestName("x954_b613_D3") 
      x954.r := getRetimed(b613.r, 3.toInt, io.sigsIn.backpressure)
      val x955 = Wire(Bool()).suggestName("x955_b588_D3") 
      x955.r := getRetimed(b588.r, 3.toInt, io.sigsIn.backpressure)
      val x630_wr_banks = List[UInt](0L.FP(true, 32, 0).r)
      val x630_wr_ofs = List[UInt](x628_sum.r)
      val x630_wr_en = List[Bool](true.B)
      val x630_wr_data = List[UInt](x951.r)
      x391_sram5D_0.connectWPort(630, x630_wr_banks, x630_wr_ofs, x630_wr_data, x630_wr_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(3.2.toInt, rr, io.sigsIn.backpressure) & ~io.sigsIn.break && io.sigsIn.backpressure && x952 & x950 & x954 & x955 & x953))
    }
    val module = Module(new x631_inr_Foreach_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnrolledForeach x631_inr_Foreach **/
