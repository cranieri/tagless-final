package com.tagless

import scala.concurrent.Future

object PaymentSubmissionInterpreter extends PaymentSubmissionAlg[Future]{

  def createPaymentSubmission(paymentSubmission: PaymentSubmission): Future[PaymentSubmission] = {
    Future.successful(paymentSubmission)
  }

  def validate(paymentSubmission: PaymentSubmission): Future[PaymentSubmission] = {
    Future.successful(PaymentSubmission("123", 1000, "validated"))
  }

  def sanitise(paymentSubmission: PaymentSubmission): Future[PaymentSubmission] = {
    Future.successful(PaymentSubmission("123", 1000, "sanitised"))
  }

  def submitPaymentSubmission(paymentSubmission: PaymentSubmission): Future[PaymentSubmission] = {
    Future.successful(PaymentSubmission("123", 1000, "submitted"))
  }

  def updatePaymentSubmission(paymentSubmission: PaymentSubmission): Future[PaymentSubmission] = {
    Future.successful(PaymentSubmission("123", 1000, "updated"))
  }

}
