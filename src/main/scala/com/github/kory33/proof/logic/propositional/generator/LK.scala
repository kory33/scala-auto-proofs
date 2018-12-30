package com.github.kory33.proof.logic.propositional.generator

object LK {

  type ∧[A, B] = (A, B)
  type ∨[A, B] = Either[A, B]

  /**
    * Γ => (A ∨ Δ)      Γ => (B ∨ Δ)
    * ------------------------------ (∧R)
    *       Γ => ((A ∧ B) ∨ Δ)
    */
  def conjRight[Γ, Δ, A, B](pf1: Γ => (A ∨ Δ), pf2: Γ => (B ∨ Δ)): Γ => ((A ∧ B) ∨ Δ) = { g: Γ =>
    val r1: A ∨ Δ = pf1(g)
    val r2: B ∨ Δ = pf2(g)
    r1 match {
      case Left(a) =>
        r2 match {
          case Left(b) => Left((a, b))
          case Right(d) => Right(d)
        }
      case Right(d) => Right(d)
    }
  }

  /**
    * (A ∧ Γ) => Δ      (B ∧ Γ) => Δ
    * ------------------------------ (∨L)
    *       ((A ∨ B) ∧ Γ) => Δ
    */
  def disjLeft[Γ, Δ, A, B](pf1: (A ∧ Γ) => Δ, pf2: (B ∧ Γ) => Δ): ((A ∨ B) ∧ Γ) => Δ = { assumption: (A ∨ B) ∧ Γ =>
    val g: Γ = assumption._2
    val aob: A ∨ B = assumption._1
    aob match {
      case Left(a) => pf1((a, g))
      case Right(b) => pf2((b, g))
    }
  }

  /**
    *  A ∧ Γ => B ∨ ∆
    * ----------------- (=>R)
    * Γ => (A => B) ∨ Δ
    */
  def implRight[Γ, Δ, A, B](pf: (A ∧ Γ) => (B ∨ Δ)): Γ => ((A => B) ∨ Δ) = { g: Γ =>
    def doubleNegElim[C](f: (C => Nothing) => Nothing): C = f(a => return a)
    def middleExcl[C]: C ∨ (C => Nothing) = doubleNegElim { asm =>
      val a: C = doubleNegElim { asm1 => asm(Right(asm1)) }
      asm(Left(a))
    }
    middleExcl[Δ].map { f => a: A =>
      pf((a, g)) match {
        case Left(b) => b
        case Right(d) => f(d)
      }
    }.swap
  }

  /**
    * Γ => A ∨ Δ     B ∨ Γ => Δ
    * ------------------------- (=>L)
    *    ((A => B) ∧ Γ) => Δ
    */
  def implLeft[Γ, Δ, A, B](pf1: Γ => A ∨ Δ, pf2: B ∨ Γ => Δ): ((A => B) ∧ Γ) => Δ = { f: (A => B) ∧ Γ =>
    val f1: (A => B) = f._1
    val g: Γ = f._2
    pf1(g) match {
      case Left(a) => pf2(Left(f1(a)))
      case Right(d) => d
    }
  }

}
