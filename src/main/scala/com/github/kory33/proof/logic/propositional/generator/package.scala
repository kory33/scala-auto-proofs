package com.github.kory33.proof.logic.propositional

import scala.language.experimental.macros

package object generator {

  def autoImplement[T]: T = macro Macro.autoImplMacroImpl[T]

}
