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

object Main {
  def main(top: AccelTop): Unit = {
    top.io <> DontCare
    val x358_argin1 = top.io.argIns(api.ARGIN1_arg)
    val x359_argout1 = Wire(new MultiArgOut(1)); x359_argout1.port.map(_.bits := DontCare); x359_argout1.port.map(_.valid := DontCare); x359_argout1.output.echo := DontCare
    top.io.argOuts(top.io_numArgIOs_reg + 0).port.valid := x359_argout1.port.map(_.valid).reduce{_||_}
    top.io.argOuts(top.io_numArgIOs_reg + 0).port.bits := Mux1H(x359_argout1.port.map(_.valid), x359_argout1.port.map(_.bits))
    x359_argout1.port.map(_.ready := top.io.argOuts(top.io_numArgIOs_reg + 0).port.ready)
    x359_argout1.output.echo := top.io.argOuts(top.io_numArgIOs_reg + 0).echo
    val x360 = Wire(new MultiArgOut(1)); x360.port.map(_.bits := DontCare); x360.port.map(_.valid := DontCare); x360.output.echo := DontCare
    top.io.argOuts(0).port.valid := x360.port.map(_.valid).reduce{_||_}
    top.io.argOuts(0).port.bits := Mux1H(x360.port.map(_.valid), x360.port.map(_.bits))
    x360.port.map(_.ready := top.io.argOuts(0).port.ready)
    x360.output.echo := top.io.argOuts(0).echo
    val x361_argin2 = top.io.argIns(api.ARGIN2_arg)
    val x392 = top.io.memStreams.loads(0).cmd // StreamOut
    ModuleParams.addParams("x392_p", (x392.bits.addrWidth, x392.bits.sizeWidth))  
    val x393 = top.io.memStreams.loads(0).data // StreamIn
    ModuleParams.addParams("x393_p", (x393.bits.v, x393.bits.w))  
    // scoped in dram is  
    val x379_dram1D = Wire(new FixedPoint(true, 64, 0))
    x379_dram1D.r := top.io.argIns(api.DRAM1D_ptr)
    val x741 = top.io.memStreams.gathers(0).cmd // StreamOut
    ModuleParams.addParams("x741_p", (x741.bits.v, x741.bits.addrWidth))  
    val x742 = top.io.memStreams.gathers(0).data // StreamIn
    // scoped in dram is  
    val x380_dram1D_longer = Wire(new FixedPoint(true, 64, 0))
    x380_dram1D_longer.r := top.io.argIns(api.DRAM1D_LONGER_ptr)
    val x415 = top.io.memStreams.loads(1).cmd // StreamOut
    ModuleParams.addParams("x415_p", (x415.bits.addrWidth, x415.bits.sizeWidth))  
    val x416 = top.io.memStreams.loads(1).data // StreamIn
    ModuleParams.addParams("x416_p", (x416.bits.v, x416.bits.w))  
    // scoped in dram is  
    val x381_dram2D = Wire(new FixedPoint(true, 64, 0))
    x381_dram2D.r := top.io.argIns(api.DRAM2D_ptr)
    val x592 = top.io.memStreams.loads(2).cmd // StreamOut
    ModuleParams.addParams("x592_p", (x592.bits.addrWidth, x592.bits.sizeWidth))  
    val x593 = top.io.memStreams.loads(2).data // StreamIn
    ModuleParams.addParams("x593_p", (x593.bits.v, x593.bits.w))  
    // scoped in dram is  
    val x382_dram5D = Wire(new FixedPoint(true, 64, 0))
    x382_dram5D.r := top.io.argIns(api.DRAM5D_ptr)
    val x637 = top.io.memStreams.stores(0).cmd // StreamOut
    ModuleParams.addParams("x637_p", (x637.bits.addrWidth, x637.bits.sizeWidth))  
    val x638 = top.io.memStreams.stores(0).data // StreamOut
    ModuleParams.addParams("x638_p", (x638.bits.v, x638.bits.w))  
    val x639  = top.io.memStreams.stores(0).wresp // StreamIn
    // scoped in dram is  
    val x387_dram_result2D = Wire(new FixedPoint(true, 64, 0))
    x387_dram_result2D.r := top.io.argIns(api.DRAM_RESULT2D_ptr)
    val x781 = top.io.memStreams.scatters(0).cmd // StreamOut
    ModuleParams.addParams("x781_p", x781.bits.p)  
    val x782 = top.io.memStreams.scatters(0).wresp // StreamOut
    // scoped in dram is  
    val x388_dram_scatter1D = Wire(new FixedPoint(true, 64, 0))
    x388_dram_scatter1D.r := top.io.argIns(api.DRAM_SCATTER1D_ptr)
    val retime_counter = Module(new SingleCounter(1, Some(0), Some(top.max_latency), Some(1), false)); retime_counter.io <> DontCare // Counter for masking out the noise that comes out of ShiftRegister in the first few cycles of the app
    retime_counter.io.setup.saturate := true.B; retime_counter.io.input.reset := top.reset.toBool; retime_counter.io.input.enable := true.B;
    val rr = getRetimed(retime_counter.io.output.done, 1, true.B) // break up critical path by delaying this 
    val breakpoints = Wire(Vec(top.io_numArgOuts_breakpts max 1, Bool())); breakpoints.zipWithIndex.foreach{case(b,i) => b.suggestName(s"breakpoint" + i)}; breakpoints := DontCare
    val done_latch = Module(new SRFF())
    val RootController = new RootController_kernel(List(x361_argin2,x358_argin1), List(x392,x637,x415,x592), List(x379_dram1D,x380_dram1D_longer,x381_dram2D,x388_dram_scatter1D,x382_dram5D,x387_dram_result2D), List(x742), List(x638), List(x741), List(x416,x593,x393), List(x781), List(x782,x639), List(x360,x359_argout1) , None, List(), -1, 7, 1, List(1), List(32), breakpoints, rr)
    RootController.baseEn := top.io.enable && rr && ~done_latch.io.output
    RootController.resetMe := getRetimed(top.accelReset, 1)
    RootController.mask := true.B
    RootController.sm.io.parentAck := top.io.done
    RootController.sm.io.enable := RootController.baseEn & !top.io.done & true.B
    done_latch.io.input.reset := RootController.resetMe
    done_latch.io.input.asyn_reset := RootController.resetMe
    top.io.done := done_latch.io.output
    RootController.sm.io.ctrDone := risingEdge(RootController.sm.io.ctrInc)
    RootController.backpressure := true.B | RootController.sm.io.doneLatch
    RootController.forwardpressure := true.B | RootController.sm.io.doneLatch
    RootController.sm.io.enableOut.zip(RootController.smEnableOuts).foreach{case (l,r) => r := l}
    RootController.sm.io.break := false.B
    RootController.mask := true.B & true.B
    RootController.configure("RootController", None, None, isSwitchCase = false)
    RootController.kernel()
    done_latch.io.input.set := RootController.done
    Ledger.finish()
  }
}
