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

/** Hierarchy: x426 -> x578 -> x75 **/
/** BEGIN None x426_inr_UnitPipe **/
class x426_inr_UnitPipe_kernel(
  list_b414: List[Bool],
  list_x381_dram2D: List[FixedPoint],
  list_x415: List[DecoupledIO[AppCommandDense]],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Sequenced, false   , latency = 3.2.toInt, myName = "x426_inr_UnitPipe_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x426_inr_UnitPipe_iiCtr"))
  
  abstract class x426_inr_UnitPipe_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_b414 = Input(Bool())
      val in_x381_dram2D = Input(new FixedPoint(true, 64, 0))
      val in_b413 = Input(new FixedPoint(true, 32, 0))
      val in_x415 = Decoupled(new AppCommandDense(ModuleParams.getParams("x415_p").asInstanceOf[(Int,Int)] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def b414 = {io.in_b414} 
    def x381_dram2D = {io.in_x381_dram2D} 
    def b413 = {io.in_b413} 
    def x415 = {io.in_x415} 
  }
  def connectWires0(module: x426_inr_UnitPipe_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_b414 <> b414
    module.io.in_x381_dram2D <> x381_dram2D
    module.io.in_b413 <> b413
    module.io.in_x415 <> x415
  }
  val b414 = list_b414(0)
  val x381_dram2D = list_x381_dram2D(0)
  val b413 = list_x381_dram2D(1)
  val x415 = list_x415(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x426_inr_UnitPipe")
    implicit val stack = ControllerStack.stack.toList
    class x426_inr_UnitPipe_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x426_inr_UnitPipe_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val x417_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x417_sum""")
      val ensig0 = Wire(Bool())
      ensig0 := x415.ready
      x417_sum.r := Math.add(32L.FP(true, 32, 0),b413,Some(1.0), ensig0, Truncate, Wrapping, "x417_sum").r
      val x913 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x913""")
      x913.r := Math.arith_left_shift(x417_sum, 8, Some(0.2), ensig0,"x913").r
      val x420 = Wire(new FixedPoint(true, 64, 0)).suggestName("""x420""")
      x420.r := Math.fix2fix(x913, true, 64, 0, Some(0.0), ensig0, Truncate, Wrapping, "x420").r
      val x421 = x381_dram2D
      val x926 = Wire(new FixedPoint(true, 64, 0)).suggestName("x926_x421_D1") 
      x926.r := getRetimed(x421.r, 1.toInt, io.sigsIn.backpressure)
      val x422_sum = Wire(new FixedPoint(true, 64, 0)).suggestName("""x422_sum""")
      x422_sum.r := Math.add(x420,x926,Some(2.0), ensig0, Truncate, Wrapping, "x422_sum").r
      val x423_tuple = Wire(UInt(97.W)).suggestName("""x423_tuple""")
      x423_tuple.r := Cat(true.B,128L.FP(true, 32, 0).r,x422_sum.r)
      val x424 = true.B
      val x927 = Wire(Bool()).suggestName("x927_x424_D3") 
      x927.r := getRetimed(x424.r, 3.toInt, io.sigsIn.backpressure)
      x415.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(3.2.toInt.toInt, rr, io.sigsIn.backpressure) & x927 & io.sigsIn.backpressure
      x415.bits.addr := x423_tuple(63,0)
      x415.bits.size := x423_tuple(95,64)
    }
    val module = Module(new x426_inr_UnitPipe_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnitPipe x426_inr_UnitPipe **/
