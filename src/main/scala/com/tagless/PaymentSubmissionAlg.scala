package com.tagless

import scala.language.higherKinds

case class PaymentSubmission(id: String, amount: Int, status: String)

trait PaymentSubmissionAlg[F[_]] {
  def createPaymentSubmission(paymentSubmission: PaymentSubmission): F[PaymentSubmission]

  def validate(paymentSubmission: PaymentSubmission): F[PaymentSubmission]

  def sanitise(paymentSubmission: PaymentSubmission): F[PaymentSubmission]

  def submitPaymentSubmission(paymentSubmission: PaymentSubmission): F[PaymentSubmission]

  def updatePaymentSubmission(paymentSubmission: PaymentSubmission): F[PaymentSubmission]
}



