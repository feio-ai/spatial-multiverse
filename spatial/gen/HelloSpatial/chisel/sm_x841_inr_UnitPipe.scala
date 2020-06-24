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

/** Hierarchy: x841 -> x75 **/
/** BEGIN None x841_inr_UnitPipe **/
class x841_inr_UnitPipe_kernel(
  list_x360: List[MultiArgOut],
  list_x826_reg1_0: List[StandardInterface],
  list_x361_argin2: List[UInt],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Sequenced, false   , latency = 3.0.toInt, myName = "x841_inr_UnitPipe_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x841_inr_UnitPipe_iiCtr"))
  
  abstract class x841_inr_UnitPipe_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x361_argin2 = Input(UInt(64.W))
      val in_x360 = new MultiArgOut(1)
      val in_x826_reg1_0 = Flipped(new StandardInterface(ModuleParams.getParams("x826_reg1_0_p").asInstanceOf[MemParams] ))
      val in_x827_reg1_1 = Flipped(new StandardInterface(ModuleParams.getParams("x827_reg1_1_p").asInstanceOf[MemParams] ))
      val in_x359_argout1 = new MultiArgOut(1)
      val in_x358_argin1 = Input(UInt(64.W))
      val in_x828_reg2_0 = Flipped(new StandardInterface(ModuleParams.getParams("x828_reg2_0_p").asInstanceOf[MemParams] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def x361_argin2 = {io.in_x361_argin2} 
    def x360 = {io.in_x360} ; io.in_x360 := DontCare
    def x826_reg1_0 = {io.in_x826_reg1_0} ; io.in_x826_reg1_0 := DontCare
    def x827_reg1_1 = {io.in_x827_reg1_1} ; io.in_x827_reg1_1 := DontCare
    def x359_argout1 = {io.in_x359_argout1} ; io.in_x359_argout1 := DontCare
    def x358_argin1 = {io.in_x358_argin1} 
    def x828_reg2_0 = {io.in_x828_reg2_0} ; io.in_x828_reg2_0 := DontCare
  }
  def connectWires0(module: x841_inr_UnitPipe_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x361_argin2 <> x361_argin2
    x360.connectLedger(module.io.in_x360)
    module.io.in_x360.port.zip(x360.port).foreach{case (l,r) => l.ready := r.ready}
    x826_reg1_0.connectLedger(module.io.in_x826_reg1_0)
    x827_reg1_1.connectLedger(module.io.in_x827_reg1_1)
    x359_argout1.connectLedger(module.io.in_x359_argout1)
    module.io.in_x359_argout1.port.zip(x359_argout1.port).foreach{case (l,r) => l.ready := r.ready}
    module.io.in_x358_argin1 <> x358_argin1
    x828_reg2_0.connectLedger(module.io.in_x828_reg2_0)
  }
  val x360 = list_x360(0)
  val x359_argout1 = list_x360(1)
  val x826_reg1_0 = list_x826_reg1_0(0)
  val x827_reg1_1 = list_x826_reg1_0(1)
  val x828_reg2_0 = list_x826_reg1_0(2)
  val x361_argin2 = list_x361_argin2(0)
  val x358_argin1 = list_x361_argin2(1)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x841_inr_UnitPipe")
    implicit val stack = ControllerStack.stack.toList
    class x841_inr_UnitPipe_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x841_inr_UnitPipe_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val x829_rd_x358 = Wire(new FixedPoint(true, 32, 0))
      x829_rd_x358.r := x358_argin1.r
      val x830_wr_x827_banks = List[UInt]()
      val x830_wr_x827_ofs = List[UInt]()
      val x830_wr_x827_en = List[Bool](true.B)
      val x830_wr_x827_data = List[UInt](x829_rd_x358.r)
      x827_reg1_1.connectWPort(830, x830_wr_x827_banks, x830_wr_x827_ofs, x830_wr_x827_data, x830_wr_x827_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(0.0.toInt, rr, io.sigsIn.backpressure) & ~io.sigsIn.break && io.sigsIn.backpressure && true.B))
      val x831_wr_x826_banks = List[UInt]()
      val x831_wr_x826_ofs = List[UInt]()
      val x831_wr_x826_en = List[Bool](true.B)
      val x831_wr_x826_data = List[UInt](x829_rd_x358.r)
      x826_reg1_0.connectWPort(831, x831_wr_x826_banks, x831_wr_x826_ofs, x831_wr_x826_data, x831_wr_x826_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(0.0.toInt, rr, io.sigsIn.backpressure) & ~io.sigsIn.break && io.sigsIn.backpressure && true.B))
      val x832_rd_x361 = Wire(new FixedPoint(false, 16, 16))
      x832_rd_x361.r := x361_argin2.r
      val x833_wr_x828_banks = List[UInt]()
      val x833_wr_x828_ofs = List[UInt]()
      val x833_wr_x828_en = List[Bool](true.B)
      val x833_wr_x828_data = List[UInt](x832_rd_x361.r)
      x828_reg2_0.connectWPort(833, x833_wr_x828_banks, x833_wr_x828_ofs, x833_wr_x828_data, x833_wr_x828_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(0.0.toInt, rr, io.sigsIn.backpressure) & ~io.sigsIn.break && io.sigsIn.backpressure && true.B))
      val x834_rd_x828 = Wire(new FixedPoint(false, 16, 16)).suggestName("""x834_rd_x828""")
      val x834_rd_x828_banks = List[UInt]()
      val x834_rd_x828_ofs = List[UInt]()
      val x834_rd_x828_en = List[Bool](true.B)
      x834_rd_x828.toSeq.zip(x828_reg2_0.connectRPort(834, x834_rd_x828_banks, x834_rd_x828_ofs, io.sigsIn.backpressure, x834_rd_x828_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(1.0.toInt, rr, io.sigsIn.backpressure) && true.B), true)).foreach{case (a,b) => a := b}
      val x835 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x835""")
      x835.r := Math.fix2fix(x834_rd_x828, true, 32, 0, Some(0.0), true.B, Truncate, Wrapping, "x835").r
      val x836_rd_x826 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x836_rd_x826""")
      val x836_rd_x826_banks = List[UInt]()
      val x836_rd_x826_ofs = List[UInt]()
      val x836_rd_x826_en = List[Bool](true.B)
      x836_rd_x826.toSeq.zip(x826_reg1_0.connectRPort(836, x836_rd_x826_banks, x836_rd_x826_ofs, io.sigsIn.backpressure, x836_rd_x826_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(1.0.toInt, rr, io.sigsIn.backpressure) && true.B), true)).foreach{case (a,b) => a := b}
      val x837_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x837_sum""")
      x837_sum.r := Math.add(x836_rd_x826,x835,Some(1.0), true.B, Truncate, Wrapping, "x837_sum").r
      x359_argout1.connectWPort(0, util.Cat(util.Fill(32, x837_sum.msb), x837_sum.r), true.B & getRetimed(io.sigsIn.datapathEn & io.sigsIn.iiDone, 2.0.toInt, io.sigsIn.backpressure))
      val x839_rd_x827 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x839_rd_x827""")
      val x839_rd_x827_banks = List[UInt]()
      val x839_rd_x827_ofs = List[UInt]()
      val x839_rd_x827_en = List[Bool](true.B)
      x839_rd_x827.toSeq.zip(x827_reg1_1.connectRPort(839, x839_rd_x827_banks, x839_rd_x827_ofs, io.sigsIn.backpressure, x839_rd_x827_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(1.0.toInt, rr, io.sigsIn.backpressure) && true.B), true)).foreach{case (a,b) => a := b}
      x360.connectWPort(0, util.Cat(util.Fill(32, x839_rd_x827.msb), x839_rd_x827.r), true.B & getRetimed(io.sigsIn.datapathEn & io.sigsIn.iiDone, 1.0.toInt, io.sigsIn.backpressure))
    }
    val module = Module(new x841_inr_UnitPipe_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnitPipe x841_inr_UnitPipe **/
