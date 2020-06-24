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

/** Hierarchy: x577 -> x578 -> x75 **/
/** BEGIN None x577_inr_Foreach **/
class x577_inr_Foreach_kernel(
  list_b414: List[Bool],
  list_b413: List[FixedPoint],
  list_x416: List[DecoupledIO[AppLoadData]],
  list_x390_sram2D_0: List[StandardInterface],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Pipelined, false   , latency = 2.2.toInt, myName = "x577_inr_Foreach_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x577_inr_Foreach_iiCtr"))
  
  abstract class x577_inr_Foreach_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x416 = Flipped(Decoupled(new AppLoadData(ModuleParams.getParams("x416_p").asInstanceOf[(Int, Int)] )))
      val in_b413 = Input(new FixedPoint(true, 32, 0))
      val in_b414 = Input(Bool())
      val in_x390_sram2D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x390_sram2D_0_p").asInstanceOf[MemParams] ))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(16), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def x416 = {io.in_x416} 
    def b413 = {io.in_b413} 
    def b414 = {io.in_b414} 
    def x390_sram2D_0 = {io.in_x390_sram2D_0} ; io.in_x390_sram2D_0 := DontCare
  }
  def connectWires0(module: x577_inr_Foreach_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x416 <> x416
    module.io.in_b413 <> b413
    module.io.in_b414 <> b414
    x390_sram2D_0.connectLedger(module.io.in_x390_sram2D_0)
  }
  val b414 = list_b414(0)
  val b413 = list_b413(0)
  val x416 = list_x416(0)
  val x390_sram2D_0 = list_x390_sram2D_0(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x577_inr_Foreach")
    implicit val stack = ControllerStack.stack.toList
    class x577_inr_Foreach_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x577_inr_Foreach_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val b430 = io.sigsIn.cchainOutputs.head.counts(0).FP(true, 32, 0); b430.suggestName("b430")
      val b431 = io.sigsIn.cchainOutputs.head.counts(1).FP(true, 32, 0); b431.suggestName("b431")
      val b432 = io.sigsIn.cchainOutputs.head.counts(2).FP(true, 32, 0); b432.suggestName("b432")
      val b433 = io.sigsIn.cchainOutputs.head.counts(3).FP(true, 32, 0); b433.suggestName("b433")
      val b434 = io.sigsIn.cchainOutputs.head.counts(4).FP(true, 32, 0); b434.suggestName("b434")
      val b435 = io.sigsIn.cchainOutputs.head.counts(5).FP(true, 32, 0); b435.suggestName("b435")
      val b436 = io.sigsIn.cchainOutputs.head.counts(6).FP(true, 32, 0); b436.suggestName("b436")
      val b437 = io.sigsIn.cchainOutputs.head.counts(7).FP(true, 32, 0); b437.suggestName("b437")
      val b438 = io.sigsIn.cchainOutputs.head.counts(8).FP(true, 32, 0); b438.suggestName("b438")
      val b439 = io.sigsIn.cchainOutputs.head.counts(9).FP(true, 32, 0); b439.suggestName("b439")
      val b440 = io.sigsIn.cchainOutputs.head.counts(10).FP(true, 32, 0); b440.suggestName("b440")
      val b441 = io.sigsIn.cchainOutputs.head.counts(11).FP(true, 32, 0); b441.suggestName("b441")
      val b442 = io.sigsIn.cchainOutputs.head.counts(12).FP(true, 32, 0); b442.suggestName("b442")
      val b443 = io.sigsIn.cchainOutputs.head.counts(13).FP(true, 32, 0); b443.suggestName("b443")
      val b444 = io.sigsIn.cchainOutputs.head.counts(14).FP(true, 32, 0); b444.suggestName("b444")
      val b445 = io.sigsIn.cchainOutputs.head.counts(15).FP(true, 32, 0); b445.suggestName("b445")
      val b446 = ~io.sigsIn.cchainOutputs.head.oobs(0); b446.suggestName("b446")
      val b447 = ~io.sigsIn.cchainOutputs.head.oobs(1); b447.suggestName("b447")
      val b448 = ~io.sigsIn.cchainOutputs.head.oobs(2); b448.suggestName("b448")
      val b449 = ~io.sigsIn.cchainOutputs.head.oobs(3); b449.suggestName("b449")
      val b450 = ~io.sigsIn.cchainOutputs.head.oobs(4); b450.suggestName("b450")
      val b451 = ~io.sigsIn.cchainOutputs.head.oobs(5); b451.suggestName("b451")
      val b452 = ~io.sigsIn.cchainOutputs.head.oobs(6); b452.suggestName("b452")
      val b453 = ~io.sigsIn.cchainOutputs.head.oobs(7); b453.suggestName("b453")
      val b454 = ~io.sigsIn.cchainOutputs.head.oobs(8); b454.suggestName("b454")
      val b455 = ~io.sigsIn.cchainOutputs.head.oobs(9); b455.suggestName("b455")
      val b456 = ~io.sigsIn.cchainOutputs.head.oobs(10); b456.suggestName("b456")
      val b457 = ~io.sigsIn.cchainOutputs.head.oobs(11); b457.suggestName("b457")
      val b458 = ~io.sigsIn.cchainOutputs.head.oobs(12); b458.suggestName("b458")
      val b459 = ~io.sigsIn.cchainOutputs.head.oobs(13); b459.suggestName("b459")
      val b460 = ~io.sigsIn.cchainOutputs.head.oobs(14); b460.suggestName("b460")
      val b461 = ~io.sigsIn.cchainOutputs.head.oobs(15); b461.suggestName("b461")
      object Block1Chunker0 { // 34 nodes, 49 weight
        def gen(): Map[String, Any] = {
          val x462 = Wire(Vec(16, new FixedPoint(true, 32, 0))).suggestName("""x462""")
          x416.ready := b458 & b454 & b451 & b447 & b446 & b452 & b457 & b460 & b456 & b453 & b449 & b414 & b459 & b455 & b450 & b461 & b448 & (io.sigsIn.datapathEn) 
          (0 until 16).map{ i => x462(i).r := x416.bits.rdata(i).r }
          val x928 = Wire(Vec(16, new FixedPoint(true, 32, 0))).suggestName("x928_x462_D1") 
          (0 until 16).foreach{i => x928(i).r := getRetimed(x462(i).r, 1.toInt, io.sigsIn.backpressure)}
          val x463_elem_0 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x463_elem_0""")
          x463_elem_0.r := x928(0).r
          val x464_elem_1 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x464_elem_1""")
          x464_elem_1.r := x928(1).r
          val x465_elem_2 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x465_elem_2""")
          x465_elem_2.r := x928(2).r
          val x466_elem_3 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x466_elem_3""")
          x466_elem_3.r := x928(3).r
          val x467_elem_4 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x467_elem_4""")
          x467_elem_4.r := x928(4).r
          val x468_elem_5 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x468_elem_5""")
          x468_elem_5.r := x928(5).r
          val x469_elem_6 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x469_elem_6""")
          x469_elem_6.r := x928(6).r
          val x470_elem_7 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x470_elem_7""")
          x470_elem_7.r := x928(7).r
          val x471_elem_8 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x471_elem_8""")
          x471_elem_8.r := x928(8).r
          val x472_elem_9 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x472_elem_9""")
          x472_elem_9.r := x928(9).r
          val x473_elem_10 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x473_elem_10""")
          x473_elem_10.r := x928(10).r
          val x474_elem_11 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x474_elem_11""")
          x474_elem_11.r := x928(11).r
          val x475_elem_12 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x475_elem_12""")
          x475_elem_12.r := x928(12).r
          val x476_elem_13 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x476_elem_13""")
          x476_elem_13.r := x928(13).r
          val x477_elem_14 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x477_elem_14""")
          x477_elem_14.r := x928(14).r
          val x478_elem_15 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x478_elem_15""")
          x478_elem_15.r := x928(15).r
          val x495 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x495""")
          x495.r := Math.arith_left_shift(b413, 1, Some(0.2), true.B,"x495").r
          val x496 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x496""")
          x496.r := Math.arith_right_shift(b430, 4, Some(0.2), true.B,"x496").r
          val x497_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x497_sum""")
          x497_sum.r := Math.add(x495,x496,Some(1.0), true.B, Truncate, Wrapping, "x497_sum").r
          val x501 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x501""")
          x501.r := Math.arith_right_shift(b431, 4, Some(0.2), true.B,"x501").r
          val x502_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x502_sum""")
          x502_sum.r := Math.add(x495,x501,Some(1.0), true.B, Truncate, Wrapping, "x502_sum").r
          val x506 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x506""")
          x506.r := Math.arith_right_shift(b432, 4, Some(0.2), true.B,"x506").r
          val x507_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x507_sum""")
          x507_sum.r := Math.add(x495,x506,Some(1.0), true.B, Truncate, Wrapping, "x507_sum").r
          val x511 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x511""")
          x511.r := Math.arith_right_shift(b433, 4, Some(0.2), true.B,"x511").r
          val x512_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x512_sum""")
          x512_sum.r := Math.add(x495,x511,Some(1.0), true.B, Truncate, Wrapping, "x512_sum").r
          val x516 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x516""")
          x516.r := Math.arith_right_shift(b434, 4, Some(0.2), true.B,"x516").r
          val x517_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x517_sum""")
          x517_sum.r := Math.add(x495,x516,Some(1.0), true.B, Truncate, Wrapping, "x517_sum").r
          val x521 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x521""")
          x521.r := Math.arith_right_shift(b435, 4, Some(0.2), true.B,"x521").r
          val x522_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x522_sum""")
          x522_sum.r := Math.add(x495,x521,Some(1.0), true.B, Truncate, Wrapping, "x522_sum").r
          val x526 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x526""")
          x526.r := Math.arith_right_shift(b436, 4, Some(0.2), true.B,"x526").r
          val x527_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x527_sum""")
          x527_sum.r := Math.add(x495,x526,Some(1.0), true.B, Truncate, Wrapping, "x527_sum").r
          val x531 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x531""")
          x531.r := Math.arith_right_shift(b437, 4, Some(0.2), true.B,"x531").r
          Map[String,Any]("x463_elem_0" -> x463_elem_0, "x464_elem_1" -> x464_elem_1, "x465_elem_2" -> x465_elem_2, "x466_elem_3" -> x466_elem_3, "x467_elem_4" -> x467_elem_4, "x468_elem_5" -> x468_elem_5, "x469_elem_6" -> x469_elem_6, "x470_elem_7" -> x470_elem_7, "x471_elem_8" -> x471_elem_8, "x472_elem_9" -> x472_elem_9, "x473_elem_10" -> x473_elem_10, "x474_elem_11" -> x474_elem_11, "x475_elem_12" -> x475_elem_12, "x476_elem_13" -> x476_elem_13, "x477_elem_14" -> x477_elem_14, "x478_elem_15" -> x478_elem_15, "x495" -> x495, "x497_sum" -> x497_sum, "x502_sum" -> x502_sum, "x507_sum" -> x507_sum, "x512_sum" -> x512_sum, "x517_sum" -> x517_sum, "x522_sum" -> x522_sum, "x527_sum" -> x527_sum, "x531" -> x531)
        }
      }
      val block1chunk0: Map[String, Any] = Block1Chunker0.gen()
      object Block1Chunker1 { // 34 nodes, 34 weight
        def gen(): Map[String, Any] = {
          val x532_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x532_sum""")
          x532_sum.r := Math.add(block1chunk0("x495").asInstanceOf[FixedPoint],block1chunk0("x531").asInstanceOf[FixedPoint],Some(1.0), true.B, Truncate, Wrapping, "x532_sum").r
          val x536 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x536""")
          x536.r := Math.arith_right_shift(b438, 4, Some(0.2), true.B,"x536").r
          val x537_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x537_sum""")
          x537_sum.r := Math.add(block1chunk0("x495").asInstanceOf[FixedPoint],x536,Some(1.0), true.B, Truncate, Wrapping, "x537_sum").r
          val x541 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x541""")
          x541.r := Math.arith_right_shift(b439, 4, Some(0.2), true.B,"x541").r
          val x542_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x542_sum""")
          x542_sum.r := Math.add(block1chunk0("x495").asInstanceOf[FixedPoint],x541,Some(1.0), true.B, Truncate, Wrapping, "x542_sum").r
          val x546 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x546""")
          x546.r := Math.arith_right_shift(b440, 4, Some(0.2), true.B,"x546").r
          val x547_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x547_sum""")
          x547_sum.r := Math.add(block1chunk0("x495").asInstanceOf[FixedPoint],x546,Some(1.0), true.B, Truncate, Wrapping, "x547_sum").r
          val x551 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x551""")
          x551.r := Math.arith_right_shift(b441, 4, Some(0.2), true.B,"x551").r
          val x552_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x552_sum""")
          x552_sum.r := Math.add(block1chunk0("x495").asInstanceOf[FixedPoint],x551,Some(1.0), true.B, Truncate, Wrapping, "x552_sum").r
          val x556 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x556""")
          x556.r := Math.arith_right_shift(b442, 4, Some(0.2), true.B,"x556").r
          val x557_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x557_sum""")
          x557_sum.r := Math.add(block1chunk0("x495").asInstanceOf[FixedPoint],x556,Some(1.0), true.B, Truncate, Wrapping, "x557_sum").r
          val x561 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x561""")
          x561.r := Math.arith_right_shift(b443, 4, Some(0.2), true.B,"x561").r
          val x562_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x562_sum""")
          x562_sum.r := Math.add(block1chunk0("x495").asInstanceOf[FixedPoint],x561,Some(1.0), true.B, Truncate, Wrapping, "x562_sum").r
          val x566 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x566""")
          x566.r := Math.arith_right_shift(b444, 4, Some(0.2), true.B,"x566").r
          val x567_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x567_sum""")
          x567_sum.r := Math.add(block1chunk0("x495").asInstanceOf[FixedPoint],x566,Some(1.0), true.B, Truncate, Wrapping, "x567_sum").r
          val x571 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x571""")
          x571.r := Math.arith_right_shift(b445, 4, Some(0.2), true.B,"x571").r
          val x572_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x572_sum""")
          x572_sum.r := Math.add(block1chunk0("x495").asInstanceOf[FixedPoint],x571,Some(1.0), true.B, Truncate, Wrapping, "x572_sum").r
          val x929 = Wire(Bool()).suggestName("x929_b449_D1") 
          x929.r := getRetimed(b449.r, 1.toInt, io.sigsIn.backpressure)
          val x930 = Wire(Bool()).suggestName("x930_b460_D1") 
          x930.r := getRetimed(b460.r, 1.toInt, io.sigsIn.backpressure)
          val x931 = Wire(Bool()).suggestName("x931_b448_D1") 
          x931.r := getRetimed(b448.r, 1.toInt, io.sigsIn.backpressure)
          val x932 = Wire(Bool()).suggestName("x932_b453_D1") 
          x932.r := getRetimed(b453.r, 1.toInt, io.sigsIn.backpressure)
          val x933 = Wire(Bool()).suggestName("x933_b452_D1") 
          x933.r := getRetimed(b452.r, 1.toInt, io.sigsIn.backpressure)
          val x934 = Wire(Bool()).suggestName("x934_b461_D1") 
          x934.r := getRetimed(b461.r, 1.toInt, io.sigsIn.backpressure)
          val x935 = Wire(Bool()).suggestName("x935_b456_D1") 
          x935.r := getRetimed(b456.r, 1.toInt, io.sigsIn.backpressure)
          val x936 = Wire(Bool()).suggestName("x936_b457_D1") 
          x936.r := getRetimed(b457.r, 1.toInt, io.sigsIn.backpressure)
          val x937 = Wire(Bool()).suggestName("x937_b451_D1") 
          x937.r := getRetimed(b451.r, 1.toInt, io.sigsIn.backpressure)
          val x938 = Wire(Bool()).suggestName("x938_b455_D1") 
          x938.r := getRetimed(b455.r, 1.toInt, io.sigsIn.backpressure)
          val x939 = Wire(Bool()).suggestName("x939_b458_D1") 
          x939.r := getRetimed(b458.r, 1.toInt, io.sigsIn.backpressure)
          val x940 = Wire(Bool()).suggestName("x940_b414_D1") 
          x940.r := getRetimed(b414.r, 1.toInt, io.sigsIn.backpressure)
          val x941 = Wire(Bool()).suggestName("x941_b446_D1") 
          x941.r := getRetimed(b446.r, 1.toInt, io.sigsIn.backpressure)
          val x942 = Wire(Bool()).suggestName("x942_b450_D1") 
          x942.r := getRetimed(b450.r, 1.toInt, io.sigsIn.backpressure)
          val x943 = Wire(Bool()).suggestName("x943_b447_D1") 
          x943.r := getRetimed(b447.r, 1.toInt, io.sigsIn.backpressure)
          val x944 = Wire(Bool()).suggestName("x944_b459_D1") 
          x944.r := getRetimed(b459.r, 1.toInt, io.sigsIn.backpressure)
          val x945 = Wire(Bool()).suggestName("x945_b454_D1") 
          x945.r := getRetimed(b454.r, 1.toInt, io.sigsIn.backpressure)
          Map[String,Any]("x532_sum" -> x532_sum, "x537_sum" -> x537_sum, "x542_sum" -> x542_sum, "x547_sum" -> x547_sum, "x552_sum" -> x552_sum, "x557_sum" -> x557_sum, "x562_sum" -> x562_sum, "x567_sum" -> x567_sum, "x572_sum" -> x572_sum, "x929" -> x929, "x930" -> x930, "x931" -> x931, "x932" -> x932, "x933" -> x933, "x934" -> x934, "x935" -> x935, "x936" -> x936, "x937" -> x937, "x938" -> x938, "x939" -> x939, "x940" -> x940, "x941" -> x941, "x942" -> x942, "x943" -> x943, "x944" -> x944, "x945" -> x945)
        }
      }
      val block1chunk1: Map[String, Any] = Block1Chunker1.gen()
      object Block1Chunker2 { // 1 nodes, 16 weight
        def gen(): Map[String, Any] = {
          def create_x576_wr_banks(): List[UInt] = {
            val x576_wr_banks0 = List[UInt](0L.FP(true, 32, 0).r,1L.FP(true, 32, 0).r,2L.FP(true, 32, 0).r,3L.FP(true, 32, 0).r,4L.FP(true, 32, 0).r,5L.FP(true, 32, 0).r,6L.FP(true, 32, 0).r,7L.FP(true, 32, 0).r)
            val x576_wr_banks1 = List[UInt](8L.FP(true, 32, 0).r,9L.FP(true, 32, 0).r,10L.FP(true, 32, 0).r,11L.FP(true, 32, 0).r,12L.FP(true, 32, 0).r,13L.FP(true, 32, 0).r,14L.FP(true, 32, 0).r,15L.FP(true, 32, 0).r)
            x576_wr_banks0 ++ x576_wr_banks1
          }
          val x576_wr_banks = create_x576_wr_banks()
          def create_x576_wr_ofs(): List[UInt] = {
            val x576_wr_ofs0 = List[UInt](block1chunk0("x497_sum").asInstanceOf[FixedPoint].r,block1chunk0("x502_sum").asInstanceOf[FixedPoint].r,block1chunk0("x507_sum").asInstanceOf[FixedPoint].r)
            val x576_wr_ofs1 = List[UInt](block1chunk0("x512_sum").asInstanceOf[FixedPoint].r,block1chunk0("x517_sum").asInstanceOf[FixedPoint].r,block1chunk0("x522_sum").asInstanceOf[FixedPoint].r)
            val x576_wr_ofs2 = List[UInt](block1chunk0("x527_sum").asInstanceOf[FixedPoint].r,block1chunk1("x532_sum").asInstanceOf[FixedPoint].r,block1chunk1("x537_sum").asInstanceOf[FixedPoint].r)
            val x576_wr_ofs3 = List[UInt](block1chunk1("x542_sum").asInstanceOf[FixedPoint].r,block1chunk1("x547_sum").asInstanceOf[FixedPoint].r,block1chunk1("x552_sum").asInstanceOf[FixedPoint].r)
            val x576_wr_ofs4 = List[UInt](block1chunk1("x557_sum").asInstanceOf[FixedPoint].r,block1chunk1("x562_sum").asInstanceOf[FixedPoint].r,block1chunk1("x567_sum").asInstanceOf[FixedPoint].r)
            val x576_wr_ofs5 = List[UInt](block1chunk1("x572_sum").asInstanceOf[FixedPoint].r)
            x576_wr_ofs0 ++ x576_wr_ofs1 ++ x576_wr_ofs2 ++ x576_wr_ofs3 ++ x576_wr_ofs4 ++ x576_wr_ofs5
          }
          val x576_wr_ofs = create_x576_wr_ofs()
          def create_x576_wr_en(): List[Bool] = {
            val x576_wr_en0 = List[Bool](block1chunk1("x941").asInstanceOf[Bool],block1chunk1("x943").asInstanceOf[Bool],block1chunk1("x931").asInstanceOf[Bool],block1chunk1("x929").asInstanceOf[Bool])
            val x576_wr_en1 = List[Bool](block1chunk1("x942").asInstanceOf[Bool],block1chunk1("x937").asInstanceOf[Bool],block1chunk1("x933").asInstanceOf[Bool],block1chunk1("x932").asInstanceOf[Bool])
            val x576_wr_en2 = List[Bool](block1chunk1("x945").asInstanceOf[Bool],block1chunk1("x938").asInstanceOf[Bool],block1chunk1("x935").asInstanceOf[Bool],block1chunk1("x936").asInstanceOf[Bool])
            val x576_wr_en3 = List[Bool](block1chunk1("x939").asInstanceOf[Bool],block1chunk1("x944").asInstanceOf[Bool],block1chunk1("x930").asInstanceOf[Bool],block1chunk1("x934").asInstanceOf[Bool])
            x576_wr_en0 ++ x576_wr_en1 ++ x576_wr_en2 ++ x576_wr_en3
          }
          val x576_wr_en = create_x576_wr_en()
          def create_x576_wr_data(): List[UInt] = {
            val x576_wr_data0 = List[UInt](block1chunk0("x463_elem_0").asInstanceOf[FixedPoint].r,block1chunk0("x464_elem_1").asInstanceOf[FixedPoint].r,block1chunk0("x465_elem_2").asInstanceOf[FixedPoint].r)
            val x576_wr_data1 = List[UInt](block1chunk0("x466_elem_3").asInstanceOf[FixedPoint].r,block1chunk0("x467_elem_4").asInstanceOf[FixedPoint].r,block1chunk0("x468_elem_5").asInstanceOf[FixedPoint].r)
            val x576_wr_data2 = List[UInt](block1chunk0("x469_elem_6").asInstanceOf[FixedPoint].r,block1chunk0("x470_elem_7").asInstanceOf[FixedPoint].r,block1chunk0("x471_elem_8").asInstanceOf[FixedPoint].r)
            val x576_wr_data3 = List[UInt](block1chunk0("x472_elem_9").asInstanceOf[FixedPoint].r,block1chunk0("x473_elem_10").asInstanceOf[FixedPoint].r,block1chunk0("x474_elem_11").asInstanceOf[FixedPoint].r)
            val x576_wr_data4 = List[UInt](block1chunk0("x475_elem_12").asInstanceOf[FixedPoint].r,block1chunk0("x476_elem_13").asInstanceOf[FixedPoint].r,block1chunk0("x477_elem_14").asInstanceOf[FixedPoint].r)
            val x576_wr_data5 = List[UInt](block1chunk0("x478_elem_15").asInstanceOf[FixedPoint].r)
            x576_wr_data0 ++ x576_wr_data1 ++ x576_wr_data2 ++ x576_wr_data3 ++ x576_wr_data4 ++ x576_wr_data5
          }
          val x576_wr_data = create_x576_wr_data()
          x390_sram2D_0.connectWPort(576, x576_wr_banks, x576_wr_ofs, x576_wr_data, x576_wr_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(1.2.toInt, rr, io.sigsIn.backpressure) & ~io.sigsIn.break && io.sigsIn.backpressure && block1chunk1("x940").asInstanceOf[Bool]))
          Map[String,Any]()
        }
      }
      val block1chunk2: Map[String, Any] = Block1Chunker2.gen()
    }
    val module = Module(new x577_inr_Foreach_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnrolledForeach x577_inr_Foreach **/
