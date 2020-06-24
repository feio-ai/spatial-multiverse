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

/** Hierarchy: x398 -> x410 -> x75 **/
/** BEGIN None x398_inr_UnitPipe **/
class x398_inr_UnitPipe_kernel(
  list_x379_dram1D: List[FixedPoint],
  list_x392: List[DecoupledIO[AppCommandDense]],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Sequenced, false   , latency = 0.0.toInt, myName = "x398_inr_UnitPipe_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x398_inr_UnitPipe_iiCtr"))
  
  abstract class x398_inr_UnitPipe_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x379_dram1D = Input(new FixedPoint(true, 64, 0))
      val in_x392 = Decoupled(new AppCommandDense(ModuleParams.getParams("x392_p").asInstanceOf[(Int,Int)] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def x379_dram1D = {io.in_x379_dram1D} 
    def x392 = {io.in_x392} 
  }
  def connectWires0(module: x398_inr_UnitPipe_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x379_dram1D <> x379_dram1D
    module.io.in_x392 <> x392
  }
  val x379_dram1D = list_x379_dram1D(0)
  val x392 = list_x392(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x398_inr_UnitPipe")
    implicit val stack = ControllerStack.stack.toList
    class x398_inr_UnitPipe_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x398_inr_UnitPipe_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val x394 = x379_dram1D
      val x395_tuple = Wire(UInt(97.W)).suggestName("""x395_tuple""")
      x395_tuple.r := Cat(true.B,256L.FP(true, 32, 0).r,x394.r)
      val x396 = true.B
      x392.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(0.0.toInt.toInt, rr, io.sigsIn.backpressure) & x396 & io.sigsIn.backpressure
      x392.bits.addr := x395_tuple(63,0)
      x392.bits.size := x395_tuple(95,64)
    }
    val module = Module(new x398_inr_UnitPipe_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnitPipe x398_inr_UnitPipe **/
