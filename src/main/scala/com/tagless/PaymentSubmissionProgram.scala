package com.tagless

import cats.Monad

import scala.language.higherKinds


class PaymentSubmissionProgram[F[_] : Monad](psa: PaymentSubmissionAlg[F]){
  import cats.syntax.functor._
  import cats.syntax.flatMap._


  def run(ps: PaymentSubmission): F[PaymentSubmission] = {
    for {
      paymentSubmission <- psa.createPaymentSubmission(ps)
      sanitisedPaymentSubmission <- psa.sanitise(paymentSubmission)
      validatedPaymentSubmission <- psa.validate(paymentSubmission)
      submittedPaymentSubmission <- psa.submitPaymentSubmission(validatedPaymentSubmission)
      updatedEntity <- psa.updatePaymentSubmission(submittedPaymentSubmission)
    } yield updatedEntity
  }
}