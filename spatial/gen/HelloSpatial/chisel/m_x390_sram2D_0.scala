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

class x390_sram2D_0 {
  val w0 = Access(576, 0, 0, List(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15), List(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0), None, PortInfo(Some(0), 16, 6, List(4), 32, List(List(RG(0)),List(RG(1)),List(RG(2)),List(RG(3)),List(RG(4)),List(RG(5)),List(RG(6)),List(RG(7)),List(RG(8)),List(RG(9)),List(RG(10)),List(RG(11)),List(RG(12)),List(RG(13)),List(RG(14)),List(RG(15)))))
  val r0 = Access(716, 0, 0, List(0,1,2,3,4,5,6,7), List(0,0,0,0,0,0,0,0), None, PortInfo(Some(0), 8, 6, List(4), 32, List(List(RG(8,0,16)),List(RG(8,1,16)),List(RG(8,2,16)),List(RG(8,3,16)),List(RG(8,4,16)),List(RG(8,5,16)),List(RG(8,6,16)),List(RG(8,7,16)))))
  val m = Module(new BankedSRAM( 
    List[Int](32,32), 0,
     32, 
    List[Int](16), 
    List[Int](1,16), 
    List(w0),
    List(r0),
    BankedMemory, 
    None, 
    true, 
    0,
    2, 
    myName = "x390_sram2D_0"
  ))
  m.io.asInstanceOf[StandardInterface] <> DontCare
  m.io.reset := false.B
  ModuleParams.addParams("x390_sram2D_0_p", m.io.p)
}
