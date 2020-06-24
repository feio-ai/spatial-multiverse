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

/** Hierarchy: x608 -> x632 -> x75 **/
/** BEGIN None x608_inr_UnitPipe **/
class x608_inr_UnitPipe_kernel(
  list_b591: List[Bool],
  list_b587: List[FixedPoint],
  list_x592: List[DecoupledIO[AppCommandDense]],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Sequenced, false   , latency = 4.4.toInt, myName = "x608_inr_UnitPipe_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x608_inr_UnitPipe_iiCtr"))
  
  abstract class x608_inr_UnitPipe_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_b587 = Input(new FixedPoint(true, 32, 0))
      val in_b591 = Input(Bool())
      val in_b590 = Input(Bool())
      val in_b586 = Input(new FixedPoint(true, 32, 0))
      val in_b584 = Input(new FixedPoint(true, 32, 0))
      val in_x382_dram5D = Input(new FixedPoint(true, 64, 0))
      val in_b589 = Input(Bool())
      val in_b585 = Input(new FixedPoint(true, 32, 0))
      val in_b588 = Input(Bool())
      val in_x592 = Decoupled(new AppCommandDense(ModuleParams.getParams("x592_p").asInstanceOf[(Int,Int)] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def b587 = {io.in_b587} 
    def b591 = {io.in_b591} 
    def b590 = {io.in_b590} 
    def b586 = {io.in_b586} 
    def b584 = {io.in_b584} 
    def x382_dram5D = {io.in_x382_dram5D} 
    def b589 = {io.in_b589} 
    def b585 = {io.in_b585} 
    def b588 = {io.in_b588} 
    def x592 = {io.in_x592} 
  }
  def connectWires0(module: x608_inr_UnitPipe_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_b587 <> b587
    module.io.in_b591 <> b591
    module.io.in_b590 <> b590
    module.io.in_b586 <> b586
    module.io.in_b584 <> b584
    module.io.in_x382_dram5D <> x382_dram5D
    module.io.in_b589 <> b589
    module.io.in_b585 <> b585
    module.io.in_b588 <> b588
    module.io.in_x592 <> x592
  }
  val b591 = list_b591(0)
  val b590 = list_b591(1)
  val b589 = list_b591(2)
  val b588 = list_b591(3)
  val b587 = list_b587(0)
  val b586 = list_b587(1)
  val b584 = list_b587(2)
  val x382_dram5D = list_b587(3)
  val b585 = list_b587(4)
  val x592 = list_x592(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x608_inr_UnitPipe")
    implicit val stack = ControllerStack.stack.toList
    class x608_inr_UnitPipe_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x608_inr_UnitPipe_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val x594 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x594""")
      val ensig0 = Wire(Bool())
      ensig0 := x592.ready
      x594.r := Math.arith_left_shift(b584, 7, Some(0.2), ensig0,"x594").r
      val x595 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x595""")
      x595.r := Math.arith_left_shift(b585, 6, Some(0.2), ensig0,"x595").r
      val x596 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x596""")
      x596.r := Math.arith_left_shift(b586, 5, Some(0.2), ensig0,"x596").r
      val x597 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x597""")
      x597.r := Math.arith_left_shift(b587, 4, Some(0.2), ensig0,"x597").r
      val x598_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x598_sum""")
      x598_sum.r := Math.add(x594,x595,Some(1.0), ensig0, Truncate, Wrapping, "x598_sum").r
      val x599_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x599_sum""")
      x599_sum.r := Math.add(x596,x597,Some(1.0), ensig0, Truncate, Wrapping, "x599_sum").r
      val x600_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x600_sum""")
      x600_sum.r := Math.add(x598_sum,x599_sum,Some(1.0), ensig0, Truncate, Wrapping, "x600_sum").r
      val x601 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x601""")
      x601.r := Math.arith_left_shift(x600_sum, 2, Some(0.2), ensig0,"x601").r
      val x602 = Wire(new FixedPoint(true, 64, 0)).suggestName("""x602""")
      x602.r := Math.fix2fix(x601, true, 64, 0, Some(0.0), ensig0, Truncate, Wrapping, "x602").r
      val x603 = x382_dram5D
      val x946 = Wire(new FixedPoint(true, 64, 0)).suggestName("x946_x603_D2") 
      x946.r := getRetimed(x603.r, 2.toInt, io.sigsIn.backpressure)
      val x604_sum = Wire(new FixedPoint(true, 64, 0)).suggestName("""x604_sum""")
      x604_sum.r := Math.add(x602,x946,Some(2.0), ensig0, Truncate, Wrapping, "x604_sum").r
      val x605_tuple = Wire(UInt(97.W)).suggestName("""x605_tuple""")
      x605_tuple.r := Cat(true.B,64L.FP(true, 32, 0).r,x604_sum.r)
      val x606 = true.B
      val x947 = Wire(Bool()).suggestName("x947_x606_D4") 
      x947.r := getRetimed(x606.r, 4.toInt, io.sigsIn.backpressure)
      x592.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(4.4.toInt.toInt, rr, io.sigsIn.backpressure) & x947 & io.sigsIn.backpressure
      x592.bits.addr := x605_tuple(63,0)
      x592.bits.size := x605_tuple(95,64)
    }
    val module = Module(new x608_inr_UnitPipe_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnitPipe x608_inr_UnitPipe **/
