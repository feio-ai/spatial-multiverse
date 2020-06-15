package spatial.codegen.scalagen

import argon._
import argon.node._
import spatial.lang._

trait ScalaGenVar extends ScalaCodegen {

  override protected def remap(tp: Type[_]): String = tp match {
    case tp:Var[_] => src"Ptr[${tp.A}]"
    case _ => super.remap(tp)
  }

  override protected def gen(lhs: Sym[_], rhs: Op[_]): Unit = rhs match {
    case op@VarNew(Some(init)) => emit(src"val $lhs: ${lhs.tp} = Ptr[${op.A}]($init)")
    case op@VarNew(None)       => emit(src"val $lhs: ${lhs.tp} = Ptr[${op.A}](null.asInstanceOf[${op.A}])")
    case VarRead(v)            => emit(src"val $lhs = $v.value")
    case VarAssign(v, x)       => emit(src"val $lhs = { $v.set($x) }")
    case _ => super.gen(lhs, rhs)
  }

}
