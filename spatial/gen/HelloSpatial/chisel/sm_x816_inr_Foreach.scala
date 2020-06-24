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

/** Hierarchy: x816 -> x825 -> x75 **/
/** BEGIN None x816_inr_Foreach **/
class x816_inr_Foreach_kernel(
  list_x388_dram_scatter1D: List[FixedPoint],
  list_x781: List[DecoupledIO[ScatterCmdStream]],
  list_x389_sram1D_0: List[StandardInterface],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Pipelined, false   , latency = 6.1.toInt, myName = "x816_inr_Foreach_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x816_inr_Foreach_iiCtr"))
  
  abstract class x816_inr_Foreach_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x389_sram1D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x389_sram1D_0_p").asInstanceOf[MemParams] ))
      val in_x388_dram_scatter1D = Input(new FixedPoint(true, 64, 0))
      val in_x781 = Decoupled(new ScatterCmdStream(ModuleParams.getParams("x781_p").asInstanceOf[StreamParInfo] ))
      val in_x740_gathered_sram_0 = Flipped(new StandardInterface(ModuleParams.getParams("x740_gathered_sram_0_p").asInstanceOf[MemParams] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def x389_sram1D_0 = {io.in_x389_sram1D_0} ; io.in_x389_sram1D_0 := DontCare
    def x388_dram_scatter1D = {io.in_x388_dram_scatter1D} 
    def x781 = {io.in_x781} 
    def x740_gathered_sram_0 = {io.in_x740_gathered_sram_0} ; io.in_x740_gathered_sram_0 := DontCare
  }
  def connectWires0(module: x816_inr_Foreach_module)(implicit stack: List[KernelHash]): Unit = {
    x389_sram1D_0.connectLedger(module.io.in_x389_sram1D_0)
    module.io.in_x388_dram_scatter1D <> x388_dram_scatter1D
    module.io.in_x781 <> x781
    x740_gathered_sram_0.connectLedger(module.io.in_x740_gathered_sram_0)
  }
  val x388_dram_scatter1D = list_x388_dram_scatter1D(0)
  val x781 = list_x781(0)
  val x389_sram1D_0 = list_x389_sram1D_0(0)
  val x740_gathered_sram_0 = list_x389_sram1D_0(1)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x816_inr_Foreach")
    implicit val stack = ControllerStack.stack.toList
    class x816_inr_Foreach_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x816_inr_Foreach_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val b785 = io.sigsIn.cchainOutputs.head.counts(0).FP(true, 32, 0); b785.suggestName("b785")
      val b786 = ~io.sigsIn.cchainOutputs.head.oobs(0); b786.suggestName("b786")
      val x787_reg = (new x787_reg).m.io.asInstanceOf[StandardInterface]
      val x788_reg = (new x788_reg).m.io.asInstanceOf[StandardInterface]
      val x789 = Wire(Bool()).suggestName("""x789""")
      val ensig0 = Wire(Bool())
      ensig0 := x781.ready
      x789.r := Math.lt(b785, 64L.FP(true, 32, 0), Some(0.4), ensig0,"x789").r
      val x792_rd = Wire(Vec(1, new FixedPoint(true, 32, 0))).suggestName("""x792_rd""")
      val x792_rd_banks = List[UInt](0L.FP(true, 32, 0).r)
      val x792_rd_ofs = List[UInt](b785.r)
      val x792_rd_en = List[Bool](true.B)
      x792_rd.toSeq.zip(x389_sram1D_0.connectRPort(792, x792_rd_banks, x792_rd_ofs, io.sigsIn.backpressure, x792_rd_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(0.4.toInt, rr, io.sigsIn.backpressure) && x789 & true.B & b786), true)).foreach{case (a,b) => a := b}
      val x793_elem_0 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x793_elem_0""")
      x793_elem_0.r := x792_rd(0).r
      val x794 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x794""")
      x794.r := Math.arith_left_shift(x793_elem_0, 2, Some(0.2), ensig0,"x794").r
      val x795 = Wire(new FixedPoint(true, 64, 0)).suggestName("""x795""")
      x795.r := Math.fix2fix(x794, true, 64, 0, Some(0.0), ensig0, Truncate, Wrapping, "x795").r
      val x796 = x388_dram_scatter1D
      val x996 = Wire(new FixedPoint(true, 64, 0)).suggestName("x996_x796_D2") 
      x996.r := getRetimed(x796.r, 2.toInt, io.sigsIn.backpressure)
      val x797_sum = Wire(new FixedPoint(true, 64, 0)).suggestName("""x797_sum""")
      x797_sum.r := Math.add(x795,x996,Some(2.0), ensig0, Truncate, Wrapping, "x797_sum").r
      val x798_rd = Wire(Vec(1, new FixedPoint(true, 32, 0))).suggestName("""x798_rd""")
      val x798_rd_banks = List[UInt](0L.FP(true, 32, 0).r)
      val x798_rd_ofs = List[UInt](b785.r)
      val x798_rd_en = List[Bool](true.B)
      x798_rd.toSeq.zip(x740_gathered_sram_0.connectRPort(798, x798_rd_banks, x798_rd_ofs, io.sigsIn.backpressure, x798_rd_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(0.4.toInt, rr, io.sigsIn.backpressure) && x789 & b786), true)).foreach{case (a,b) => a := b}
      val x997 = Wire(Bool()).suggestName("x997_x789_D4") 
      x997.r := getRetimed(x789.r, 4.toInt, io.sigsIn.backpressure)
      val x801_wr_x787_banks = List[UInt]()
      val x801_wr_x787_ofs = List[UInt]()
      val x801_wr_x787_en = List[Bool](true.B)
      val x801_wr_x787_data = List[UInt](x797_sum.r)
      x787_reg.connectWPort(801, x801_wr_x787_banks, x801_wr_x787_ofs, x801_wr_x787_data, x801_wr_x787_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(4.6.toInt, rr, io.sigsIn.backpressure) & ~io.sigsIn.break && io.sigsIn.backpressure && x997))
      val x911_elem_0 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x911_elem_0""")
      x911_elem_0.r := x798_rd(0).r
      val x998 = Wire(Bool()).suggestName("x998_x789_D2") 
      x998.r := getRetimed(x789.r, 2.toInt, io.sigsIn.backpressure)
      val x805_wr_x788_banks = List[UInt]()
      val x805_wr_x788_ofs = List[UInt]()
      val x805_wr_x788_en = List[Bool](true.B)
      val x805_wr_x788_data = List[UInt](x911_elem_0.r)
      x788_reg.connectWPort(805, x805_wr_x788_banks, x805_wr_x788_ofs, x805_wr_x788_data, x805_wr_x788_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(2.4.toInt, rr, io.sigsIn.backpressure) & ~io.sigsIn.break && io.sigsIn.backpressure && x998))
      val x809_rd_x787 = Wire(new FixedPoint(true, 64, 0)).suggestName("""x809_rd_x787""")
      val x809_rd_x787_banks = List[UInt]()
      val x809_rd_x787_ofs = List[UInt]()
      val x809_rd_x787_en = List[Bool](true.B)
      x809_rd_x787.toSeq.zip(x787_reg.connectRPort(809, x809_rd_x787_banks, x809_rd_x787_ofs, io.sigsIn.backpressure, x809_rd_x787_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(5.6.toInt, rr, io.sigsIn.backpressure) && true.B), true)).foreach{case (a,b) => a := b}
      val x999 = Wire(Bool()).suggestName("x999_x789_D5") 
      x999.r := getRetimed(x789.r, 5.toInt, io.sigsIn.backpressure)
      val x1000 = Wire(new FixedPoint(true, 64, 0)).suggestName("x1000_x797_sum_D1") 
      x1000.r := getRetimed(x797_sum.r, 1.toInt, io.sigsIn.backpressure)
      val x810 = Wire(new FixedPoint(true, 64, 0)).suggestName("""x810""")
      x810.r := Mux((x999), x1000.r, x809_rd_x787.r)
      val x811_rd_x788 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x811_rd_x788""")
      val x811_rd_x788_banks = List[UInt]()
      val x811_rd_x788_ofs = List[UInt]()
      val x811_rd_x788_en = List[Bool](true.B)
      x811_rd_x788.toSeq.zip(x788_reg.connectRPort(811, x811_rd_x788_banks, x811_rd_x788_ofs, io.sigsIn.backpressure, x811_rd_x788_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(3.4.toInt, rr, io.sigsIn.backpressure) && true.B), true)).foreach{case (a,b) => a := b}
      val x1001 = Wire(new FixedPoint(true, 32, 0)).suggestName("x1001_x911_elem_0_D1") 
      x1001.r := getRetimed(x911_elem_0.r, 1.toInt, io.sigsIn.backpressure)
      val x1002 = Wire(Bool()).suggestName("x1002_x789_D3") 
      x1002.r := getRetimed(x789.r, 3.toInt, io.sigsIn.backpressure)
      val x812 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x812""")
      x812.r := Mux((x1002), x1001.r, x811_rd_x788.r)
      val x1003 = Wire(new FixedPoint(true, 32, 0)).suggestName("x1003_x812_D3") 
      x1003.r := getRetimed(x812.r, 3.toInt, io.sigsIn.backpressure)
      val x1004 = Wire(new FixedPoint(true, 64, 0)).suggestName("x1004_x810_D1") 
      x1004.r := getRetimed(x810.r, 1.toInt, io.sigsIn.backpressure)
      val x813_tuple = Wire(UInt(96.W)).suggestName("""x813_tuple""")
      x813_tuple.r := Cat(x1004.r,x1003.r)
      val x814 = true.B
      val x1005 = Wire(Bool()).suggestName("x1005_b786_D6") 
      x1005.r := getRetimed(b786.r, 6.toInt, io.sigsIn.backpressure)
      val x1006 = Wire(Bool()).suggestName("x1006_x814_D6") 
      x1006.r := getRetimed(x814.r, 6.toInt, io.sigsIn.backpressure)
      x781.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(6.1.toInt.toInt, rr, io.sigsIn.backpressure) & x1006&x1005 & io.sigsIn.backpressure
      x781.bits.addr.addr(0) := x813_tuple(95, 32)
      x781.bits.wdata(0) := x813_tuple(31, 0)
    }
    val module = Module(new x816_inr_Foreach_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnrolledForeach x816_inr_Foreach **/
