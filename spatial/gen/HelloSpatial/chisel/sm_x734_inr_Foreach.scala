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

/** Hierarchy: x734 -> x739 -> x75 **/
/** BEGIN None x734_inr_Foreach **/
class x734_inr_Foreach_kernel(
  list_b636: List[Bool],
  list_b635: List[FixedPoint],
  list_x390_sram2D_0: List[StandardInterface],
  list_x638: List[DecoupledIO[AppStoreData]],
  parent: Option[Kernel], cchain: List[CounterChainInterface], childId: Int, nMyChildren: Int, ctrcopies: Int, ctrPars: List[Int], ctrWidths: List[Int], breakpoints: Vec[Bool], rr: Bool
) extends Kernel(parent, cchain, childId, nMyChildren, ctrcopies, ctrPars, ctrWidths) {
  
  val me = this
  val sm = Module(new InnerControl(Pipelined, false   , latency = 3.2.toInt, myName = "x734_inr_Foreach_sm")); sm.io <> DontCare
  val iiCtr = Module(new IICounter(1.0.toInt, 2 + fringe.utils.log2Up(1.0.toInt), "x734_inr_Foreach_iiCtr"))
  
  abstract class x734_inr_Foreach_module(depth: Int)(implicit stack: List[KernelHash]) extends Module {
    val io = IO(new Bundle {
      val in_x638 = Decoupled(new AppStoreData(ModuleParams.getParams("x638_p").asInstanceOf[(Int,Int)] ))
      val in_b636 = Input(Bool())
      val in_x390_sram2D_0 = Flipped(new StandardInterface(ModuleParams.getParams("x390_sram2D_0_p").asInstanceOf[MemParams] ))
      val in_b635 = Input(new FixedPoint(true, 32, 0))
      val in_breakpoints = Vec(api.numArgOuts_breakpts, Output(Bool()))
      val sigsIn = Input(new InputKernelSignals(1, 1, List(8), List(32)))
      val sigsOut = Output(new OutputKernelSignals(1, 1))
      val rr = Input(Bool())
    })
    def x638 = {io.in_x638} 
    def b636 = {io.in_b636} 
    def x390_sram2D_0 = {io.in_x390_sram2D_0} ; io.in_x390_sram2D_0 := DontCare
    def b635 = {io.in_b635} 
  }
  def connectWires0(module: x734_inr_Foreach_module)(implicit stack: List[KernelHash]): Unit = {
    module.io.in_x638 <> x638
    module.io.in_b636 <> b636
    x390_sram2D_0.connectLedger(module.io.in_x390_sram2D_0)
    module.io.in_b635 <> b635
  }
  val b636 = list_b636(0)
  val b635 = list_b635(0)
  val x390_sram2D_0 = list_x390_sram2D_0(0)
  val x638 = list_x638(0)
  def kernel(): Unit = {
    Ledger.enter(this.hashCode, "x734_inr_Foreach")
    implicit val stack = ControllerStack.stack.toList
    class x734_inr_Foreach_concrete(depth: Int)(implicit stack: List[KernelHash]) extends x734_inr_Foreach_module(depth) {
      io.sigsOut := DontCare
      val breakpoints = io.in_breakpoints; breakpoints := DontCare
      val rr = io.rr
      val b651 = io.sigsIn.cchainOutputs.head.counts(0).FP(true, 32, 0); b651.suggestName("b651")
      val b652 = io.sigsIn.cchainOutputs.head.counts(1).FP(true, 32, 0); b652.suggestName("b652")
      val b653 = io.sigsIn.cchainOutputs.head.counts(2).FP(true, 32, 0); b653.suggestName("b653")
      val b654 = io.sigsIn.cchainOutputs.head.counts(3).FP(true, 32, 0); b654.suggestName("b654")
      val b655 = io.sigsIn.cchainOutputs.head.counts(4).FP(true, 32, 0); b655.suggestName("b655")
      val b656 = io.sigsIn.cchainOutputs.head.counts(5).FP(true, 32, 0); b656.suggestName("b656")
      val b657 = io.sigsIn.cchainOutputs.head.counts(6).FP(true, 32, 0); b657.suggestName("b657")
      val b658 = io.sigsIn.cchainOutputs.head.counts(7).FP(true, 32, 0); b658.suggestName("b658")
      val b659 = ~io.sigsIn.cchainOutputs.head.oobs(0); b659.suggestName("b659")
      val b660 = ~io.sigsIn.cchainOutputs.head.oobs(1); b660.suggestName("b660")
      val b661 = ~io.sigsIn.cchainOutputs.head.oobs(2); b661.suggestName("b661")
      val b662 = ~io.sigsIn.cchainOutputs.head.oobs(3); b662.suggestName("b662")
      val b663 = ~io.sigsIn.cchainOutputs.head.oobs(4); b663.suggestName("b663")
      val b664 = ~io.sigsIn.cchainOutputs.head.oobs(5); b664.suggestName("b664")
      val b665 = ~io.sigsIn.cchainOutputs.head.oobs(6); b665.suggestName("b665")
      val b666 = ~io.sigsIn.cchainOutputs.head.oobs(7); b666.suggestName("b666")
      object Block1Chunker0 { // 42 nodes, 42 weight
        def gen(): Map[String, Any] = {
          val x915 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x915""")
          val ensig0 = Wire(Bool())
          ensig0 := x638.ready
          x915.r := Math.and(b651,15L.FP(true, 32, 0),Some(0.2), ensig0,"x915").r
          val x916 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x916""")
          x916.r := Math.and(b652,15L.FP(true, 32, 0),Some(0.2), ensig0,"x916").r
          val x917 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x917""")
          x917.r := Math.and(b653,15L.FP(true, 32, 0),Some(0.2), ensig0,"x917").r
          val x918 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x918""")
          x918.r := Math.and(b654,15L.FP(true, 32, 0),Some(0.2), ensig0,"x918").r
          val x919 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x919""")
          x919.r := Math.and(b655,15L.FP(true, 32, 0),Some(0.2), ensig0,"x919").r
          val x920 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x920""")
          x920.r := Math.and(b656,15L.FP(true, 32, 0),Some(0.2), ensig0,"x920").r
          val x921 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x921""")
          x921.r := Math.and(b657,15L.FP(true, 32, 0),Some(0.2), ensig0,"x921").r
          val x922 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x922""")
          x922.r := Math.and(b658,15L.FP(true, 32, 0),Some(0.2), ensig0,"x922").r
          val x675 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x675""")
          x675.r := Math.arith_left_shift(b635, 1, Some(0.2), ensig0,"x675").r
          val x676 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x676""")
          x676.r := Math.arith_right_shift(b651, 4, Some(0.2), ensig0,"x676").r
          val x677_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x677_sum""")
          x677_sum.r := Math.add(x675,x676,Some(1.0), ensig0, Truncate, Wrapping, "x677_sum").r
          val x681 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x681""")
          x681.r := Math.arith_right_shift(b652, 4, Some(0.2), ensig0,"x681").r
          val x682_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x682_sum""")
          x682_sum.r := Math.add(x675,x681,Some(1.0), ensig0, Truncate, Wrapping, "x682_sum").r
          val x686 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x686""")
          x686.r := Math.arith_right_shift(b653, 4, Some(0.2), ensig0,"x686").r
          val x687_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x687_sum""")
          x687_sum.r := Math.add(x675,x686,Some(1.0), ensig0, Truncate, Wrapping, "x687_sum").r
          val x691 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x691""")
          x691.r := Math.arith_right_shift(b654, 4, Some(0.2), ensig0,"x691").r
          val x692_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x692_sum""")
          x692_sum.r := Math.add(x675,x691,Some(1.0), ensig0, Truncate, Wrapping, "x692_sum").r
          val x696 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x696""")
          x696.r := Math.arith_right_shift(b655, 4, Some(0.2), ensig0,"x696").r
          val x697_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x697_sum""")
          x697_sum.r := Math.add(x675,x696,Some(1.0), ensig0, Truncate, Wrapping, "x697_sum").r
          val x701 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x701""")
          x701.r := Math.arith_right_shift(b656, 4, Some(0.2), ensig0,"x701").r
          val x702_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x702_sum""")
          x702_sum.r := Math.add(x675,x701,Some(1.0), ensig0, Truncate, Wrapping, "x702_sum").r
          val x706 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x706""")
          x706.r := Math.arith_right_shift(b657, 4, Some(0.2), ensig0,"x706").r
          val x707_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x707_sum""")
          x707_sum.r := Math.add(x675,x706,Some(1.0), ensig0, Truncate, Wrapping, "x707_sum").r
          val x711 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x711""")
          x711.r := Math.arith_right_shift(b658, 4, Some(0.2), ensig0,"x711").r
          val x712_sum = Wire(new FixedPoint(true, 32, 0)).suggestName("""x712_sum""")
          x712_sum.r := Math.add(x675,x711,Some(1.0), ensig0, Truncate, Wrapping, "x712_sum").r
          val x957 = Wire(Bool()).suggestName("x957_b666_D1") 
          x957.r := getRetimed(b666.r, 1.toInt, io.sigsIn.backpressure)
          val x958 = Wire(new FixedPoint(true, 32, 0)).suggestName("x958_x917_D1") 
          x958.r := getRetimed(x917.r, 1.toInt, io.sigsIn.backpressure)
          val x959 = Wire(Bool()).suggestName("x959_b660_D1") 
          x959.r := getRetimed(b660.r, 1.toInt, io.sigsIn.backpressure)
          val x960 = Wire(new FixedPoint(true, 32, 0)).suggestName("x960_x920_D1") 
          x960.r := getRetimed(x920.r, 1.toInt, io.sigsIn.backpressure)
          val x961 = Wire(Bool()).suggestName("x961_b661_D1") 
          x961.r := getRetimed(b661.r, 1.toInt, io.sigsIn.backpressure)
          val x962 = Wire(new FixedPoint(true, 32, 0)).suggestName("x962_x916_D1") 
          x962.r := getRetimed(x916.r, 1.toInt, io.sigsIn.backpressure)
          val x963 = Wire(Bool()).suggestName("x963_b665_D1") 
          x963.r := getRetimed(b665.r, 1.toInt, io.sigsIn.backpressure)
          val x964 = Wire(new FixedPoint(true, 32, 0)).suggestName("x964_x921_D1") 
          x964.r := getRetimed(x921.r, 1.toInt, io.sigsIn.backpressure)
          val x965 = Wire(Bool()).suggestName("x965_b662_D1") 
          x965.r := getRetimed(b662.r, 1.toInt, io.sigsIn.backpressure)
          val x966 = Wire(new FixedPoint(true, 32, 0)).suggestName("x966_x919_D1") 
          x966.r := getRetimed(x919.r, 1.toInt, io.sigsIn.backpressure)
          val x967 = Wire(new FixedPoint(true, 32, 0)).suggestName("x967_x915_D1") 
          x967.r := getRetimed(x915.r, 1.toInt, io.sigsIn.backpressure)
          val x968 = Wire(Bool()).suggestName("x968_b636_D1") 
          x968.r := getRetimed(b636.r, 1.toInt, io.sigsIn.backpressure)
          val x969 = Wire(new FixedPoint(true, 32, 0)).suggestName("x969_x922_D1") 
          x969.r := getRetimed(x922.r, 1.toInt, io.sigsIn.backpressure)
          val x970 = Wire(Bool()).suggestName("x970_b663_D1") 
          x970.r := getRetimed(b663.r, 1.toInt, io.sigsIn.backpressure)
          val x971 = Wire(new FixedPoint(true, 32, 0)).suggestName("x971_x918_D1") 
          x971.r := getRetimed(x918.r, 1.toInt, io.sigsIn.backpressure)
          val x972 = Wire(Bool()).suggestName("x972_b664_D1") 
          x972.r := getRetimed(b664.r, 1.toInt, io.sigsIn.backpressure)
          val x973 = Wire(Bool()).suggestName("x973_b659_D1") 
          x973.r := getRetimed(b659.r, 1.toInt, io.sigsIn.backpressure)
          Map[String,Any]("x677_sum" -> x677_sum, "x682_sum" -> x682_sum, "x687_sum" -> x687_sum, "x692_sum" -> x692_sum, "x697_sum" -> x697_sum, "x702_sum" -> x702_sum, "x707_sum" -> x707_sum, "x712_sum" -> x712_sum, "x957" -> x957, "x958" -> x958, "x959" -> x959, "x960" -> x960, "x961" -> x961, "x962" -> x962, "x963" -> x963, "x964" -> x964, "x965" -> x965, "x966" -> x966, "x967" -> x967, "x968" -> x968, "x969" -> x969, "x970" -> x970, "x971" -> x971, "x972" -> x972, "x973" -> x973)
        }
      }
      val block1chunk0: Map[String, Any] = Block1Chunker0.gen()
      object Block1Chunker1 { // 27 nodes, 41 weight
        def gen(): Map[String, Any] = {
          val x716_rd = Wire(Vec(8, new FixedPoint(true, 32, 0))).suggestName("""x716_rd""")
          def create_x716_rd_banks(): List[UInt] = {
            val x716_rd_banks0 = List[UInt](block1chunk0("x967").asInstanceOf[FixedPoint].r,block1chunk0("x962").asInstanceOf[FixedPoint].r,block1chunk0("x958").asInstanceOf[FixedPoint].r,block1chunk0("x971").asInstanceOf[FixedPoint].r)
            val x716_rd_banks1 = List[UInt](block1chunk0("x966").asInstanceOf[FixedPoint].r,block1chunk0("x960").asInstanceOf[FixedPoint].r,block1chunk0("x964").asInstanceOf[FixedPoint].r,block1chunk0("x969").asInstanceOf[FixedPoint].r)
            x716_rd_banks0 ++ x716_rd_banks1
          }
          val x716_rd_banks = create_x716_rd_banks()
          def create_x716_rd_ofs(): List[UInt] = {
            val x716_rd_ofs0 = List[UInt](block1chunk0("x677_sum").asInstanceOf[FixedPoint].r,block1chunk0("x682_sum").asInstanceOf[FixedPoint].r,block1chunk0("x687_sum").asInstanceOf[FixedPoint].r,block1chunk0("x692_sum").asInstanceOf[FixedPoint].r)
            val x716_rd_ofs1 = List[UInt](block1chunk0("x697_sum").asInstanceOf[FixedPoint].r,block1chunk0("x702_sum").asInstanceOf[FixedPoint].r,block1chunk0("x707_sum").asInstanceOf[FixedPoint].r,block1chunk0("x712_sum").asInstanceOf[FixedPoint].r)
            x716_rd_ofs0 ++ x716_rd_ofs1
          }
          val x716_rd_ofs = create_x716_rd_ofs()
          def create_x716_rd_en(): List[Bool] = {
            val x716_rd_en0 = List[Bool](block1chunk0("x973").asInstanceOf[Bool],block1chunk0("x959").asInstanceOf[Bool],block1chunk0("x961").asInstanceOf[Bool],block1chunk0("x965").asInstanceOf[Bool])
            val x716_rd_en1 = List[Bool](block1chunk0("x970").asInstanceOf[Bool],block1chunk0("x972").asInstanceOf[Bool],block1chunk0("x963").asInstanceOf[Bool],block1chunk0("x957").asInstanceOf[Bool])
            x716_rd_en0 ++ x716_rd_en1
          }
          val x716_rd_en = create_x716_rd_en()
          x716_rd.toSeq.zip(x390_sram2D_0.connectRPort(716, x716_rd_banks, x716_rd_ofs, io.sigsIn.backpressure, x716_rd_en.map(_ && ~io.sigsIn.break && (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(1.2.toInt, rr, io.sigsIn.backpressure) && block1chunk0("x968").asInstanceOf[Bool]), true)).foreach{case (a,b) => a := b}
          val x717_elem_0 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x717_elem_0""")
          x717_elem_0.r := x716_rd(0).r
          val x718_elem_1 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x718_elem_1""")
          x718_elem_1.r := x716_rd(1).r
          val x719_elem_2 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x719_elem_2""")
          x719_elem_2.r := x716_rd(2).r
          val x720_elem_3 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x720_elem_3""")
          x720_elem_3.r := x716_rd(3).r
          val x721_elem_4 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x721_elem_4""")
          x721_elem_4.r := x716_rd(4).r
          val x722_elem_5 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x722_elem_5""")
          x722_elem_5.r := x716_rd(5).r
          val x723_elem_6 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x723_elem_6""")
          x723_elem_6.r := x716_rd(6).r
          val x724_elem_7 = Wire(new FixedPoint(true, 32, 0)).suggestName("""x724_elem_7""")
          x724_elem_7.r := x716_rd(7).r
          val x725_tuple = Wire(UInt(33.W)).suggestName("""x725_tuple""")
          x725_tuple.r := Cat(true.B,x717_elem_0.r)
          val x726_tuple = Wire(UInt(33.W)).suggestName("""x726_tuple""")
          x726_tuple.r := Cat(true.B,x718_elem_1.r)
          val x727_tuple = Wire(UInt(33.W)).suggestName("""x727_tuple""")
          x727_tuple.r := Cat(true.B,x719_elem_2.r)
          val x728_tuple = Wire(UInt(33.W)).suggestName("""x728_tuple""")
          x728_tuple.r := Cat(true.B,x720_elem_3.r)
          val x729_tuple = Wire(UInt(33.W)).suggestName("""x729_tuple""")
          x729_tuple.r := Cat(true.B,x721_elem_4.r)
          val x730_tuple = Wire(UInt(33.W)).suggestName("""x730_tuple""")
          x730_tuple.r := Cat(true.B,x722_elem_5.r)
          val x731_tuple = Wire(UInt(33.W)).suggestName("""x731_tuple""")
          x731_tuple.r := Cat(true.B,x723_elem_6.r)
          val x732_tuple = Wire(UInt(33.W)).suggestName("""x732_tuple""")
          x732_tuple.r := Cat(true.B,x724_elem_7.r)
          val x974 = Wire(Bool()).suggestName("x974_b666_D3") 
          x974.r := getRetimed(b666.r, 3.toInt, io.sigsIn.backpressure)
          val x975 = Wire(Bool()).suggestName("x975_b660_D3") 
          x975.r := getRetimed(b660.r, 3.toInt, io.sigsIn.backpressure)
          val x976 = Wire(Bool()).suggestName("x976_b661_D3") 
          x976.r := getRetimed(b661.r, 3.toInt, io.sigsIn.backpressure)
          val x977 = Wire(Bool()).suggestName("x977_b665_D3") 
          x977.r := getRetimed(b665.r, 3.toInt, io.sigsIn.backpressure)
          val x978 = Wire(Bool()).suggestName("x978_b662_D3") 
          x978.r := getRetimed(b662.r, 3.toInt, io.sigsIn.backpressure)
          val x979 = Wire(Bool()).suggestName("x979_b636_D3") 
          x979.r := getRetimed(b636.r, 3.toInt, io.sigsIn.backpressure)
          val x980 = Wire(Bool()).suggestName("x980_b663_D3") 
          x980.r := getRetimed(b663.r, 3.toInt, io.sigsIn.backpressure)
          val x981 = Wire(Bool()).suggestName("x981_b664_D3") 
          x981.r := getRetimed(b664.r, 3.toInt, io.sigsIn.backpressure)
          val x982 = Wire(Bool()).suggestName("x982_b659_D3") 
          x982.r := getRetimed(b659.r, 3.toInt, io.sigsIn.backpressure)
          x638.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(3.2.toInt.toInt, rr, io.sigsIn.backpressure) & x982&x979 & io.sigsIn.backpressure
          x638.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(3.2.toInt.toInt, rr, io.sigsIn.backpressure) & x975&x979 & io.sigsIn.backpressure
          x638.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(3.2.toInt.toInt, rr, io.sigsIn.backpressure) & x976&x979 & io.sigsIn.backpressure
          x638.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(3.2.toInt.toInt, rr, io.sigsIn.backpressure) & x978&x979 & io.sigsIn.backpressure
          x638.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(3.2.toInt.toInt, rr, io.sigsIn.backpressure) & x980&x979 & io.sigsIn.backpressure
          x638.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(3.2.toInt.toInt, rr, io.sigsIn.backpressure) & x981&x979 & io.sigsIn.backpressure
          x638.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(3.2.toInt.toInt, rr, io.sigsIn.backpressure) & x977&x979 & io.sigsIn.backpressure
          x638.valid := (io.sigsIn.datapathEn & io.sigsIn.iiDone).DS(3.2.toInt.toInt, rr, io.sigsIn.backpressure) & x974&x979 & io.sigsIn.backpressure
          x638.bits.wdata(0) := x725_tuple(31,0)
          x638.bits.wdata(1) := x726_tuple(31,0)
          x638.bits.wdata(2) := x727_tuple(31,0)
          x638.bits.wdata(3) := x728_tuple(31,0)
          x638.bits.wdata(4) := x729_tuple(31,0)
          x638.bits.wdata(5) := x730_tuple(31,0)
          x638.bits.wdata(6) := x731_tuple(31,0)
          x638.bits.wdata(7) := x732_tuple(31,0)
          x638.bits.wstrb := List(x725_tuple, x726_tuple, x727_tuple, x728_tuple, x729_tuple, x730_tuple, x731_tuple, x732_tuple).map{_(32,32)}.reduce(Cat(_,_))
          Map[String,Any]()
        }
      }
      val block1chunk1: Map[String, Any] = Block1Chunker1.gen()
    }
    val module = Module(new x734_inr_Foreach_concrete(sm.p.depth)); module.io := DontCare
    connectWires0(module)
    Ledger.connectBreakpoints(breakpoints, module.io.in_breakpoints)
    module.io.rr := rr
    module.io.sigsIn := me.sigsIn
    me.sigsOut := module.io.sigsOut
    Ledger.exit()
  }
}
/** END UnrolledForeach x734_inr_Foreach **/
