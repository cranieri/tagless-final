package com.tagless

import cats.data.EitherT

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

object PaymentSubmissionInterpreterEt extends PaymentSubmissionAlg[FutureOfEither]{

  def createPaymentSubmission(paymentSubmission: PaymentSubmission): FutureOfEither[PaymentSubmission] = {
    EitherT(Future.successful(Right(paymentSubmission)):Future[Either[String, PaymentSubmission]])
  }

  def validate(paymentSubmission: PaymentSubmission): FutureOfEither[PaymentSubmission] = {
    EitherT(Future.successful(Right(paymentSubmission)):Future[Either[String, PaymentSubmission]])
  }

  def sanitise(paymentSubmission: PaymentSubmission): FutureOfEither[PaymentSubmission] = {
    EitherT(Future.successful(Right(paymentSubmission)):Future[Either[String, PaymentSubmission]])
  }

  def submitPaymentSubmission(paymentSubmission: PaymentSubmission): FutureOfEither[PaymentSubmission] = {
    EitherT(Future.successful(Right(paymentSubmission)):Future[Either[String, PaymentSubmission]])
  }

  def updatePaymentSubmission(paymentSubmission: PaymentSubmission): FutureOfEither[PaymentSubmission] = {
    EitherT(Future.successful(Right(paymentSubmission)):Future[Either[String, PaymentSubmission]])
  }

}
