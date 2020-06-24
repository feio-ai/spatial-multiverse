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

/** Hierarchy: x75 **/
/** BEGIN None RootController **/
class RootController_kernel(
  list_x361_argin2: List[UInt],
  list_x392: List[DecoupledIO[AppCommandDense]],
  list_x379_dram1D: List[FixedPoint],
  list_x742: List[DecoupledIO[Vec[UInt]]],
  list_x638: List[DecoupledIO[AppStoreData]],
  list_x741: List[DecoupledIO[AppCommandSparse]],
  list_x416: List[DecoupledIO[AppLoadData]],
  list_x781: List[DecoupledIO[ScatterCmdStream]],
  list_x782: List[DecoupledIO[Bool]],
  list_x360: List[MultiArgOut],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new OuterControl(Sequenced, 7, isFSM = false   , latency = 0.0.toInt, myName = "RootController_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "RootController_iiCtr"))
  
  abstract class RootController_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x741 = Decoupled(new AppCommandSparse(ModuleParams.getParams("x741_p").asInstanceOf[(Int,Int)] ))
      val in_x379_dram1D = Input(new FixedPoint(true, 64, 0))
      val in_x782 = Flipped(Decoupled(Bool()))
      val in_x638 = Decoupled(new AppStoreData(ModuleParams.getParams("x638_p").asInstanceOf[(Int,Int)] ))
      val in_x416 = Flipped(Decoupled(new AppLoadData(ModuleParams.getParams("x416_p").asInstanceOf[(Int, Int)] )))
      val in_x380_dram1D_longer = Input(new FixedPoint(true, 64, 0))
      val in_x381_dram2D = Input(new FixedPoint(true, 64, 0))
      val in_x392 = Decoupled(new AppCommandDense(ModuleParams.getParams("x392_p").asInstanceOf[(Int,Int)] ))
      val in_x361_argin2 = Input(UInt(64.W))
      val in_x742 = Flipped(Decoupled(Vec(1,UInt(32.W))))
      val in_x388_dram_scatter1D = Input(new FixedPoint(true, 64, 0))
      val in_x593 = Flipped(Decoupled(new AppLoadData(ModuleParams.getParams("x593_p").asInstanceOf[(Int, Int)] )))
      val in_x637 = Decoupled(new AppCommandDense(ModuleParams.getParams("x637_p").asInstanceOf[(Int,Int)] ))
      val in_x393 = Flipped(Decoupled(new AppLoadData(ModuleParams.getParams("x393_p").asInstanceOf[(Int, Int)] )))
      val in_x360 = new MultiArgOut(1)
      val in_x382_dram5D = Input(new FixedPoint(true, 64, 0))
      val in_x387_dram_result2D = Input(new FixedPoint(true, 64, 0))
      val in_x359_argout1 = new MultiArgOut(1)
      val in_x781 = Decoupled(new ScatterCmdStream(ModuleParams.getParams("x781_p").asInstanceOf[StreamParInfo] ))
      val in_x358_argin1 = Input(UInt(64.W))
      val in_x415 = Decoupled(new AppCommandDense(ModuleParams.getParams("x415_p").asInstanceOf[(Int,Int)] ))
      val in_x639 = Flipped(Decoupled(Bool()))
      val in_x592 = Decoupled(new AppCommandDense(ModuleParams.getParams("x592_p").asInstanceOf[(Int,Int)] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(7, 1, List(1), List(32)))
      val sigsOut = Output(new OutputKernelSignals(7, 1))
      val rr = Input(Bool())
    })
    def x741 = {io.in_x741} 
    def x379_dram1D = {io.in_x379_dram1D} 
    def x782 = {io.in_x782} 
    def x638 = {io.in_x638} 
    def x416 = {io.in_x416} 
    def x380_dram1D_longer = {io.in_x380_dram1D_longer} 
    def x381_dram2D = {io.in_x381_dram2D} 
    def x392 = {io.in_x392} 
    def x361_argin2 = {io.in_x361_argin2} 
    def x742 = {io.in_x742} 
    def x388_dram_scatter1D = {io.in_x388_dram_scatter1D} 
    def x593 = {io.in_x593} 
    def x637 = {io.in_x637} 
    def x393 = {io.in_x393} 
    def x360 = {io.in_x360} ; io.in_x360 := DontCare
    def x382_dram5D = {io.in_x382_dram5D} 
    def x387_dram_result2D = {io.in_x387_dram_result2D} 
    def x359_argout1 = {io.in_x359_argout1} ; io.in_x359_argout1 := DontCare
    def x781 = {io.in_x781} 
    def x358_argin1 = {io.in_x358_argin1} 
    def x415 = {io.in_x415} 
    def x639 = {io.in_x639} 
    def x592 = {io.in_x592} 
  }
  def connectWires0(module: RootController_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x741 <> x741
    module.io.in_x379_dram1D <> x379_dram1D
    module.io.in_x782 <> x782
    module.io.in_x638 <> x638
    module.io.in_x416 <> x416
    module.io.in_x380_dram1D_longer <> x380_dram1D_longer
    module.io.in_x381_dram2D <> x381_dram2D
    module.io.in_x392 <> x392
    module.io.in_x361_argin2 <> x361_argin2
    module.io.in_x742 <> x742
    module.io.in_x388_dram_scatter1D <> x388_dram_scatter1D
    module.io.in_x593 <> x593
    module.io.in_x637 <> x637
    module.io.in_x393 <> x393
    x360.connectLedger(module.io.in_x360)
    module.io.in_x360.port.zip(x360.port).foreach{case (l,r) => l.ready := r.ready}
    module.io.in_x382_dram5D <> x382_dram5D
    module.io.in_x387_dram_result2D <> x387_dram_result2D
    x359_argout1.connectLedger(module.io.in_x359_argout1)
    module.io.in_x359_argout1.port.zip(x359_argout1.port).foreach{case (l,r) => l.ready := r.ready}
    module.io.in_x781 <> x781
    module.io.in_x358_argin1 <> x358_argin1
    module.io.in_x415 <> x415
    module.io.in_x639 <> x639
    module.io.in_x592 <> x592
  }
  val x361_argin2 = list_x361_argin2(0)
  val x358_argin1 = list_x361_argin2(1)
  val x392 = list_x392(0)
  val x637 = list_x392(1)
  val x415 = list_x392(2)
  val x592 = list_x392(3)
  val x379_dram1D = list_x379_dram1D(0)
  val x380_dram1D_longer = list_x379_dram1D(1)
  val x381_dram2D = list_x379_dram1D(2)
  val x388_dram_scatter1D = list_x379_dram1D(3)
  val x382_dram5D = list_x379_dram1D(4)
  val x387_dram_result2D = list_x379_dram1D(5)
  val x742 = list_x742(0)
  val x638 = list_x638(0)
  val x741 = list_x741(0)
  val x416 = list_x416(0)
  val x593 = list_x416(1)
  val x393 = list_x416(2)
  val x781 = list_x781(0)
  val x782 = list_x782(0)
  val x639 = list_x782(1)
  val x360 = list_x360(0)
  val x359_argout1 = list_x360(1)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "RootController")
    implicit val stack = ControllerStack.stack.toList
    class RootController_concrete(depth: Int)(implicit stack: List[KernelHash]) extends RootController_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val x389_sram1D_0 = (new x389_sram1D_0).m.io.asInstanceOf[StandardInterface]
      val x390_sram2D_0 = (new x390_sram2D_0).m.io.asInstanceOf[StandardInterface]
      val x391_sram5D_0 = (new x391_sram5D_0).m.io.asInstanceOf[StandardInterface]
      val x410_outr_UnitPipe = new x410_outr_UnitPipe_kernel(List(x379_dram1D), List(x392), List(x393), List(x389_sram1D_0) , Some(me), List(), 0, 2, 2, List(1), List(32), breakpoints, rr)
      x410_outr_UnitPipe.backpressure := true.B | x410_outr_UnitPipe.sm.io.doneLatch
      x410_outr_UnitPipe.forwardpressure := true.B | x410_outr_UnitPipe.sm.io.doneLatch
      x410_outr_UnitPipe.sm.io.enableOut.zip(x410_outr_UnitPipe.smEnableOuts).foreach{case (l,r) => r := l}
      x410_outr_UnitPipe.sm.io.break := false.B
      x410_outr_UnitPipe.mask := true.B & true.B
      x410_outr_UnitPipe.configure("x410_outr_UnitPipe", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x410_outr_UnitPipe.kernel()
      val x411_ctr = new CtrObject(Left(Some(0)), Left(Some(32)), Left(Some(1)), 1, 32, false)
      val x412_ctrchain_copyx426_inr_UnitPipe = (new CChainObject(List[CtrObject](x411_ctr), "x412_ctrchain_copyx426_inr_UnitPipe")).cchain.io 
      x412_ctrchain_copyx426_inr_UnitPipe.setup.isStream := false.B
      ModuleParams.addParams("x412_ctrchain_p", (x412_ctrchain_copyx426_inr_UnitPipe.par, x412_ctrchain_copyx426_inr_UnitPipe.widths))
      val x412_ctrchain_copyx577_inr_Foreach = (new CChainObject(List[CtrObject](x411_ctr), "x412_ctrchain_copyx577_inr_Foreach")).cchain.io 
      x412_ctrchain_copyx577_inr_Foreach.setup.isStream := false.B
      ModuleParams.addParams("x412_ctrchain_p", (x412_ctrchain_copyx426_inr_UnitPipe.par, x412_ctrchain_copyx426_inr_UnitPipe.widths))
      val x578_outr_Foreach = new x578_outr_Foreach_kernel(List(x381_dram2D), List(x415), List(x416), List(x390_sram2D_0) , Some(me), List(x412_ctrchain_copyx426_inr_UnitPipe,x412_ctrchain_copyx577_inr_Foreach), 1, 2, 2, List(1), List(32), breakpoints, rr)
      x578_outr_Foreach.backpressure := true.B | x578_outr_Foreach.sm.io.doneLatch
      x578_outr_Foreach.forwardpressure := true.B | x578_outr_Foreach.sm.io.doneLatch
      x578_outr_Foreach.sm.io.enableOut.zip(x578_outr_Foreach.smEnableOuts).foreach{case (l,r) => r := l}
      x578_outr_Foreach.sm.io.break := false.B
      x578_outr_Foreach.mask := ~x578_outr_Foreach.cchain.head.output.noop & true.B
      x578_outr_Foreach.configure("x578_outr_Foreach", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x578_outr_Foreach.kernel()
      val x579_ctr = new CtrObject(Left(Some(0)), Left(Some(2)), Left(Some(1)), 1, 32, false)
      val x580_ctr = new CtrObject(Left(Some(0)), Left(Some(2)), Left(Some(1)), 1, 32, false)
      val x581_ctr = new CtrObject(Left(Some(0)), Left(Some(2)), Left(Some(1)), 1, 32, false)
      val x582_ctr = new CtrObject(Left(Some(0)), Left(Some(2)), Left(Some(1)), 1, 32, false)
      val x583_ctrchain_copyx608_inr_UnitPipe = (new CChainObject(List[CtrObject](x579_ctr,x580_ctr,x581_ctr,x582_ctr), "x583_ctrchain_copyx608_inr_UnitPipe")).cchain.io 
      x583_ctrchain_copyx608_inr_UnitPipe.setup.isStream := false.B
      ModuleParams.addParams("x583_ctrchain_p", (x583_ctrchain_copyx608_inr_UnitPipe.par, x583_ctrchain_copyx608_inr_UnitPipe.widths))
      val x583_ctrchain_copyx631_inr_Foreach = (new CChainObject(List[CtrObject](x579_ctr,x580_ctr,x581_ctr,x582_ctr), "x583_ctrchain_copyx631_inr_Foreach")).cchain.io 
      x583_ctrchain_copyx631_inr_Foreach.setup.isStream := false.B
      ModuleParams.addParams("x583_ctrchain_p", (x583_ctrchain_copyx608_inr_UnitPipe.par, x583_ctrchain_copyx608_inr_UnitPipe.widths))
      val x632_outr_Foreach = new x632_outr_Foreach_kernel(List(x382_dram5D), List(x592), List(x593), List(x391_sram5D_0) , Some(me), List(x583_ctrchain_copyx608_inr_UnitPipe,x583_ctrchain_copyx631_inr_Foreach), 2, 2, 2, List(1, 1, 1, 1), List(32, 32, 32, 32), breakpoints, rr)
      x632_outr_Foreach.backpressure := true.B | x632_outr_Foreach.sm.io.doneLatch
      x632_outr_Foreach.forwardpressure := true.B | x632_outr_Foreach.sm.io.doneLatch
      x632_outr_Foreach.sm.io.enableOut.zip(x632_outr_Foreach.smEnableOuts).foreach{case (l,r) => r := l}
      x632_outr_Foreach.sm.io.break := false.B
      x632_outr_Foreach.mask := ~x632_outr_Foreach.cchain.head.output.noop & true.B
      x632_outr_Foreach.configure("x632_outr_Foreach", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x632_outr_Foreach.kernel()
      val x633_ctr = new CtrObject(Left(Some(0)), Left(Some(32)), Left(Some(1)), 1, 32, false)
      val x634_ctrchain_copyx648_inr_UnitPipe = (new CChainObject(List[CtrObject](x633_ctr), "x634_ctrchain_copyx648_inr_UnitPipe")).cchain.io 
      x634_ctrchain_copyx648_inr_UnitPipe.setup.isStream := false.B
      ModuleParams.addParams("x634_ctrchain_p", (x634_ctrchain_copyx648_inr_UnitPipe.par, x634_ctrchain_copyx648_inr_UnitPipe.widths))
      val x634_ctrchain_copyx734_inr_Foreach = (new CChainObject(List[CtrObject](x633_ctr), "x634_ctrchain_copyx734_inr_Foreach")).cchain.io 
      x634_ctrchain_copyx734_inr_Foreach.setup.isStream := false.B
      ModuleParams.addParams("x634_ctrchain_p", (x634_ctrchain_copyx648_inr_UnitPipe.par, x634_ctrchain_copyx648_inr_UnitPipe.widths))
      val x634_ctrchain_copyx738_inr_UnitPipe = (new CChainObject(List[CtrObject](x633_ctr), "x634_ctrchain_copyx738_inr_UnitPipe")).cchain.io 
      x634_ctrchain_copyx738_inr_UnitPipe.setup.isStream := false.B
      ModuleParams.addParams("x634_ctrchain_p", (x634_ctrchain_copyx648_inr_UnitPipe.par, x634_ctrchain_copyx648_inr_UnitPipe.widths))
      val x739_outr_Foreach = new x739_outr_Foreach_kernel(List(x639), List(x638), List(x390_sram2D_0), List(x387_dram_result2D), List(x637) , Some(me), List(x634_ctrchain_copyx648_inr_UnitPipe,x634_ctrchain_copyx734_inr_Foreach,x634_ctrchain_copyx738_inr_UnitPipe), 3, 3, 3, List(1), List(32), breakpoints, rr)
      x739_outr_Foreach.backpressure := true.B | x739_outr_Foreach.sm.io.doneLatch
      x739_outr_Foreach.forwardpressure := true.B | x739_outr_Foreach.sm.io.doneLatch
      x739_outr_Foreach.sm.io.enableOut.zip(x739_outr_Foreach.smEnableOuts).foreach{case (l,r) => r := l}
      x739_outr_Foreach.sm.io.break := false.B
      x739_outr_Foreach.mask := ~x739_outr_Foreach.cchain.head.output.noop & true.B
      x739_outr_Foreach.configure("x739_outr_Foreach", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x739_outr_Foreach.kernel()
      val x740_gathered_sram_0 = (new x740_gathered_sram_0).m.io.asInstanceOf[StandardInterface]
      val x780_outr_UnitPipe = new x780_outr_UnitPipe_kernel(List(x380_dram1D_longer), List(x742), List(x389_sram1D_0,x740_gathered_sram_0), List(x741) , Some(me), List(), 4, 2, 2, List(1), List(32), breakpoints, rr)
      x780_outr_UnitPipe.backpressure := true.B | x780_outr_UnitPipe.sm.io.doneLatch
      x780_outr_UnitPipe.forwardpressure := true.B | x780_outr_UnitPipe.sm.io.doneLatch
      x780_outr_UnitPipe.sm.io.enableOut.zip(x780_outr_UnitPipe.smEnableOuts).foreach{case (l,r) => r := l}
      x780_outr_UnitPipe.sm.io.break := false.B
      x780_outr_UnitPipe.mask := true.B & true.B
      x780_outr_UnitPipe.configure("x780_outr_UnitPipe", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x780_outr_UnitPipe.kernel()
      val x825_outr_UnitPipe = new x825_outr_UnitPipe_kernel(List(x388_dram_scatter1D), List(x781), List(x782), List(x389_sram1D_0,x740_gathered_sram_0) , Some(me), List(), 5, 2, 2, List(1), List(32), breakpoints, rr)
      x825_outr_UnitPipe.backpressure := true.B | x825_outr_UnitPipe.sm.io.doneLatch
      x825_outr_UnitPipe.forwardpressure := true.B | x825_outr_UnitPipe.sm.io.doneLatch
      x825_outr_UnitPipe.sm.io.enableOut.zip(x825_outr_UnitPipe.smEnableOuts).foreach{case (l,r) => r := l}
      x825_outr_UnitPipe.sm.io.break := false.B
      x825_outr_UnitPipe.mask := true.B & true.B
      x825_outr_UnitPipe.configure("x825_outr_UnitPipe", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x825_outr_UnitPipe.kernel()
      val x826_reg1_0 = (new x826_reg1_0).m.io.asInstanceOf[StandardInterface]
      val x827_reg1_1 = (new x827_reg1_1).m.io.asInstanceOf[StandardInterface]
      val x828_reg2_0 = (new x828_reg2_0).m.io.asInstanceOf[StandardInterface]
      val x841_inr_UnitPipe = new x841_inr_UnitPipe_kernel(List(x360,x359_argout1), List(x826_reg1_0,x827_reg1_1,x828_reg2_0), List(x361_argin2,x358_argin1) , Some(me), List(), 6, 1, 1, List(1), List(32), breakpoints, rr)
      x841_inr_UnitPipe.sm.io.ctrDone := risingEdge(x841_inr_UnitPipe.sm.io.ctrInc)
      x841_inr_UnitPipe.backpressure := true.B | x841_inr_UnitPipe.sm.io.doneLatch
      x841_inr_UnitPipe.forwardpressure := true.B | x841_inr_UnitPipe.sm.io.doneLatch
      x841_inr_UnitPipe.sm.io.enableOut.zip(x841_inr_UnitPipe.smEnableOuts).foreach{case (l,r) => r := l}
      x841_inr_UnitPipe.sm.io.break := false.B
      x841_inr_UnitPipe.mask := true.B & true.B
      x841_inr_UnitPipe.configure("x841_inr_UnitPipe", Some(io.sigsIn), Some(io.sigsOut), isSwitchCase = false)
      x841_inr_UnitPipe.kernel()
    }
    val module = Module(new RootController_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END AccelScope RootController **/
