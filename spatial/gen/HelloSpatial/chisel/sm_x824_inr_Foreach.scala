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

/** Hierarchy: x824 -> x825 -> x75 **/
/** BEGIN None x824_inr_Foreach **/
class x824_inr_Foreach_kernel(
  list_x782: List[DecoupledIO[Bool]],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Pipelined, false   , latency = 1.0.toInt, myName = "x824_inr_Foreach_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x824_inr_Foreach_iiCtr"))
  
  abstract class x824_inr_Foreach_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x782 = Flipped(Decoupled(Bool()))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def x782 = {io.in_x782} 
  }
  def connectWires0(module: x824_inr_Foreach_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x782 <> x782
  }
  val x782 = list_x782(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x824_inr_Foreach")
    implicit val stack = ControllerStack.stack.toList
    class x824_inr_Foreach_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x824_inr_Foreach_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val b820 = io.sigsIn.cchainOutputs.head.counts(0).FP(true, 32, 0); b820.suggestName("b820")
      val b821 = ~io.sigsIn.cchainOutputs.head.oobs(0); b821.suggestName("b821")
      val x822 = Wire(Vec(1, Bool())).suggestName("""x822""")
      x782.ready := b821 & (io.sigsIn.datapathEn) 
      (0 until 1).map{ i => x822(i) := x782.bits }
    }
    val module = Module(new x824_inr_Foreach_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnrolledForeach x824_inr_Foreach **/
