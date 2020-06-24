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

/** Hierarchy: x767 -> x780 -> x75 **/
/** BEGIN None x767_inr_Foreach **/
class x767_inr_Foreach_kernel(
  list_x380_dram1D_longer: List[FixedPoint],
  list_x389_sram1D_0: List[StandardInterface],
  list_x741: List[DecoupledIO[AppCommandSparse]],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Pipelined, false   , latency = 6.6.toInt, myName = "x767_inr_Foreach_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x767_inr_Foreach_iiCtr"))
  
  abstract class x767_inr_Foreach_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x741 = Decoupled(new AppCommandSparse(ModuleParams.getParams("x741_p").asInstanceOf[(Int,Int)] ))
      val in_x389_sram1D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x389_sram1D_0_p").asInstanceOf[MemParams] ))
      val in_x380_dram1D_longer = Input(new FixedPoint(true, 64, 0))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def x741 = {io.in_x741} 
    def x389_sram1D_0 = {io.in_x389_sram1D_0} ; io.in_x389_sram1D_0 := DontCare
    def x380_dram1D_longer = {io.in_x380_dram1D_longer} 
  }
  def connectWires0(module: x767_inr_Foreach_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x741 <> x741
    x389_sram1D_0.connectLedger(module.io.in_x389_sram1D_0)
    module.io.in_x380_dram1D_longer <> x380_dram1D_longer
  }
  val x380_dram1D_longer = list_x380_dram1D_longer(0)
  val x389_sram1D_0 = list_x389_sram1D_0(0)
  val x741 = list_x741(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x767_inr_Foreach")
    implicit val stack = ControllerStack.stack.toList
    class x767_inr_Foreach_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x767_inr_Foreach_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val b745 = io.sigsIn.cchainOutputs.head.counts(0).FP(true, 32, 0); b745.suggestName("b745")
      val b746 = ~io.sigsIn.cchainOutputs.head.oobs(0); b746.suggestName("b746")
      val x747_reg = (new x747_reg).m.io.asInstanceOf[StandardInterface]
      val x748 = Wire(Bool()).suggestName("""x748""")
      val ensig0 = Wire(Bool())
      ensig0 := x741.ready
      x748.r := Math.lt(b745, 64L.FP(true, 32, 0), Some(0.4), ensig0,"x748").r
      val x751_rd = Wire(Vec(1, new FixedPoint(true, 32, 0))).suggestName("""x751_rd""")
      val x751_rd_banks = List[UInt](0L.FP(true, 32, 0).r)
      val x751_rd_ofs = List[UInt](b745.r)
      val x751_rd_en = List[Bool](true.B)
      x751_rd.toSeq.zip(x389_sram1D_0.connectRPort(751, x751_rd_banks, x751_rd_ofs, io.sigsIn.backpressure, x751_rd_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(0.4.toInt, rr, io.sigsIn.backpressure) && x748 & true.B & b746), true)).foreach{case (a,b) => a := b}
      val x752_elem_0 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x752_elem_0""")
      x752_elem_0.r := x751_rd(0).r
      val x753 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x753""")
      x753.r := Math.arith_left_shift(x752_elem_0, 2, Some(0.2), ensig0,"x753").r
      val x754 = Wire(new FixedPoint(true, 64, 0)).suggestName("""x754""")
      x754.r := Math.fix2fix(x753, true, 64, 0, Some(0.0), ensig0, Truncate, Wrapping, "x754").r
      val x755 = x380_dram1D_longer
      val x983 = Wire(new FixedPoint(true, 64, 0)).suggestName("x983_x755_D2") 
      x983.r := getRetimed(x755.r, 2.toInt, io.sigsIn.backpressure)
      val x756_sum = Wire(new FixedPoint(true, 64, 0)).suggestName("""x756_sum""")
      x756_sum.r := Math.add(x754,x983,Some(2.0), ensig0, Truncate, Wrapping, "x756_sum").r
      val x984 = Wire(new FixedPoint(true, 64, 0)).suggestName("x984_x755_D4") 
      x984.r := getRetimed(x755.r, 4.toInt, io.sigsIn.backpressure)
      val x985 = Wire(Bool()).suggestName("x985_x748_D4") 
      x985.r := getRetimed(x748.r, 4.toInt, io.sigsIn.backpressure)
      val x757 = Wire(new FixedPoint(true, 64, 0)).suggestName("""x757""")
      x757.r := Mux((x985), x756_sum.r, x984.r)
      val x986 = Wire(Bool()).suggestName("x986_x748_D5") 
      x986.r := getRetimed(x748.r, 5.toInt, io.sigsIn.backpressure)
      val x987 = Wire(new FixedPoint(true, 64, 0)).suggestName("x987_x757_D1") 
      x987.r := getRetimed(x757.r, 1.toInt, io.sigsIn.backpressure)
      val x759_wr_x747_banks = List[UInt]()
      val x759_wr_x747_ofs = List[UInt]()
      val x759_wr_x747_en = List[Bool](true.B)
      val x759_wr_x747_data = List[UInt](x987.r)
      x747_reg.connectWPort(759, x759_wr_x747_banks, x759_wr_x747_ofs, x759_wr_x747_data, x759_wr_x747_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(5.1.toInt, rr, io.sigsIn.backpressure) & ~io.sigsIn.break && io.sigsIn.backpressure && x986))
      val x763_rd_x747 = Wire(new FixedPoint(true, 64, 0)).suggestName("""x763_rd_x747""")
      val x763_rd_x747_banks = List[UInt]()
      val x763_rd_x747_ofs = List[UInt]()
      val x763_rd_x747_en = List[Bool](true.B)
      x763_rd_x747.toSeq.zip(x747_reg.connectRPort(763, x763_rd_x747_banks, x763_rd_x747_ofs, io.sigsIn.backpressure, x763_rd_x747_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(6.1.toInt, rr, io.sigsIn.backpressure) && true.B), true)).foreach{case (a,b) => a := b}
      val x988 = Wire(Bool()).suggestName("x988_x748_D6") 
      x988.r := getRetimed(x748.r, 6.toInt, io.sigsIn.backpressure)
      val x989 = Wire(new FixedPoint(true, 64, 0)).suggestName("x989_x757_D2") 
      x989.r := getRetimed(x757.r, 2.toInt, io.sigsIn.backpressure)
      val x764 = Wire(new FixedPoint(true, 64, 0)).suggestName("""x764""")
      x764.r := Mux((x988), x989.r, x763_rd_x747.r)
      val x765 = true.B
      val x990 = Wire(Bool()).suggestName("x990_x765_D6") 
      x990.r := getRetimed(x765.r, 6.toInt, io.sigsIn.backpressure)
      val x991 = Wire(Bool()).suggestName("x991_b746_D6") 
      x991.r := getRetimed(b746.r, 6.toInt, io.sigsIn.backpressure)
      x741.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(6.6.toInt.toInt, rr, io.sigsIn.backpressure) & x990&x991 & io.sigsIn.backpressure
      x741.bits.addr(0) := x764.r
    }
    val module = Module(new x767_inr_Foreach_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnrolledForeach x767_inr_Foreach **/
