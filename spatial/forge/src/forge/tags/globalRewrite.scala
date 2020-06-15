package forge.tags

import utils.tags.MacroUtils

import scala.annotation.StaticAnnotation
import scala.reflect.macros.blackbox
import scala.language.experimental.macros

final class globalRewrite extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro globalRewrite.impl
}

object globalRewrite {
  def impl(c: blackbox.Context)(annottees: c.Tree*): c.Tree = {
    val util = new MacroUtils[c.type](c)
    import c.universe._
    import util._

    annottees.head match {
      case _:DefDef =>
      case _ => c.error(c.enclosingPosition, "@globalRewrite can only be used on defs")
    }
    def incorrectSignature(): Unit = {
      c.error(c.enclosingPosition, "@globalRewrite def must have signature 'def name(rhs: T): Unit")
    }
    def noImplicitsAllowed(): Unit = {
      c.error(c.enclosingPosition, "@globalRewrite def cannot have implicit parameters")
    }
    def noTypeParametersAllowed(): Unit = {
      c.error(c.enclosingPosition, "@globalRewrite def cannot have type parameters")
    }

    val tree = api.impl(c)(annottees:_*) match {
      case d: DefDef =>
        val paramss = d.paramss
        if (paramss.length != 2) incorrectSignature()
        else if (paramss.head.length != 1) incorrectSignature()
        else if (paramss(1).length != 2) noImplicitsAllowed()
        else if (d.tparams.nonEmpty) noTypeParametersAllowed()

        val arg0 = paramss.head.head
        val name = Literal(Constant(d.name.toString))
        d.rhs match {
          case Match(_,_) =>
          case _ => c.error(c.enclosingPosition, "@globalRewrite rule must be a partial function")
        }

        val pf =
          q"""val ${d.name}: PartialFunction[(Op[_],SrcCtx,State),Option[Sym[_]]] = {case (__op,__ctx,__state) =>
            if (__op.isInstanceOf[${arg0.tp.get}]) {
              val ${arg0.name} = __op.asInstanceOf[${arg0.tp.get}];
              implicit val ctx = __ctx;
              implicit val state = __state;
              val func: PartialFunction[Op[_],Sym[_]] = ${d.rhs}
              if (func.isDefinedAt(${arg0.name})) Some(func.apply(${arg0.name})) else None
            }
            else None
          }
          """
        val add =
          q"""
             core.rewrites.addGlobal($name,${d.name})
           """
        q"$pf; $add"

      case t =>
        c.error(c.enclosingPosition, "@globalRewrite can only be used on defs")
        t
    }
    c.info(c.enclosingPosition, showCode(tree), force = true)
    tree
  }
}

