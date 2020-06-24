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

/** Hierarchy: x648 -> x739 -> x75 **/
/** BEGIN None x648_inr_UnitPipe **/
class x648_inr_UnitPipe_kernel(
  list_b636: List[Bool],
  list_x387_dram_result2D: List[FixedPoint],
  list_x637: List[DecoupledIO[AppCommandDense]],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Sequenced, false   , latency = 2.2.toInt, myName = "x648_inr_UnitPipe_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x648_inr_UnitPipe_iiCtr"))
  
  abstract class x648_inr_UnitPipe_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_b636 = Input(Bool())
      val in_x637 = Decoupled(new AppCommandDense(ModuleParams.getParams("x637_p").asInstanceOf[(Int,Int)] ))
      val in_x387_dram_result2D = Input(new FixedPoint(true, 64, 0))
      val in_b635 = Input(new FixedPoint(true, 32, 0))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def b636 = {io.in_b636} 
    def x637 = {io.in_x637} 
    def x387_dram_result2D = {io.in_x387_dram_result2D} 
    def b635 = {io.in_b635} 
  }
  def connectWires0(module: x648_inr_UnitPipe_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_b636 <> b636
    module.io.in_x637 <> x637
    module.io.in_x387_dram_result2D <> x387_dram_result2D
    module.io.in_b635 <> b635
  }
  val b636 = list_b636(0)
  val x387_dram_result2D = list_x387_dram_result2D(0)
  val b635 = list_x387_dram_result2D(1)
  val x637 = list_x637(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x648_inr_UnitPipe")
    implicit val stack = ControllerStack.stack.toList
    class x648_inr_UnitPipe_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x648_inr_UnitPipe_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val x914 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x914""")
      val ensig0 = Wire(Bool())
      ensig0 := x637.ready
      x914.r := Math.arith_left_shift(b635, 7, Some(0.2), ensig0,"x914").r
      val x642 = Wire(new FixedPoint(true, 64, 0)).suggestName("""x642""")
      x642.r := Math.fix2fix(x914, true, 64, 0, Some(0.0), ensig0, Truncate, Wrapping, "x642").r
      val x643 = x387_dram_result2D
      val x644_sum = Wire(new FixedPoint(true, 64, 0)).suggestName("""x644_sum""")
      x644_sum.r := Math.add(x642,x643,Some(2.0), ensig0, Truncate, Wrapping, "x644_sum").r
      val x645_tuple = Wire(UInt(97.W)).suggestName("""x645_tuple""")
      x645_tuple.r := Cat(false.B,128L.FP(true, 32, 0).r,x644_sum.r)
      val x646 = true.B
      val x956 = Wire(Bool()).suggestName("x956_x646_D2") 
      x956.r := getRetimed(x646.r, 2.toInt, io.sigsIn.backpressure)
      x637.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(2.2.toInt.toInt, rr, io.sigsIn.backpressure) & x956 & io.sigsIn.backpressure
      x637.bits.addr := x645_tuple(63,0)
      x637.bits.size := x645_tuple(95,64)
    }
    val module = Module(new x648_inr_UnitPipe_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnitPipe x648_inr_UnitPipe **/
