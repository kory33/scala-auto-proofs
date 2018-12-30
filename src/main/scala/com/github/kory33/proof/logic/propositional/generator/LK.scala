package com.github.kory33.proof.logic.propositional.generator

object LK {

  type ∧[A, B] = (A, B)
  type ∨[A, B] = Either[A, B]

  /**
    * Γ => (A ∨ Δ)      Γ => (B ∨ Δ)
    * ------------------------------ (∧R)
    *       Γ => ((A ∧ B) ∨ Δ)
    */
  def conjRight[Γ, Δ, A, B](pf1: Γ => (A ∨ Δ), pf2: Γ => (B ∨ Δ)): Γ => ((A ∧ B) ∨ Δ) = {
    ???
  }

  /**
    * (A ∧ Γ) => Δ      (B ∧ Γ) => Δ
    * ------------------------------ (∨L)
    *       ((A ∨ B) ∧ Γ) => Δ
    */
  def disjLeft[Γ, Δ, A, B](pf1: (A ∧ Γ) => Δ, pf2: (B ∧ Γ) => Δ): ((A ∨ B) ∧ Γ) => Δ = {
    ???
  }

  /**
    *  A ∧ Γ => B ∨ ∆
    * ----------------- (=>R)
    * Γ => (A => B) ∨ Δ
    */
  def implRight[Γ, Δ, A, B](pf: (A ∧ Γ) => (B ∨ Δ)): Γ => ((A => B) ∨ Δ) = {
    ???
  }

  /**
    * Γ => A ∨ Δ     B ∨ Γ => Δ
    * ------------------------- (=>L)
    *    ((A => B) ∧ Γ) => Δ
    */
  def implLeft[Γ, Δ, A, B](pf1: Γ => A ∨ Δ, pf2: B ∨ Γ => Δ): ((A => B) ∧ Γ) => Δ = {
    ???
  }

}
