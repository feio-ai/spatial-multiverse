package forge

import forge.tags.SrcCtxMacro

case class SrcCtx(dir: String, file: String, line: Int, column: Int, content: Option[String]) extends utils.Ctx {
  override def toString = s"$file:$line:$column"
}

object SrcCtx {
  lazy val empty = SrcCtx("?", "?", 0, 0, None)

  implicit def _sc: SrcCtx = macro SrcCtxMacro.impl
}
