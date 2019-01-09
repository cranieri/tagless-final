package com.tagless

import akka.actor.ActorSystem
import akka.http.javadsl.model.HttpResponse
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.stream.ActorMaterializer
import cats.data.EitherT

import scala.concurrent.Future
import scala.util.{Failure, Success}

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
  val actorSystem = ActorSystem()
  val uuidGenerator = UuidGenerator

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
    EitherT(Future.successful(Right(PaymentSubmitter.submit(paymentSubmission, uuidGenerator)(actorSystem))):Future[Either[String, PaymentSubmission]])
  }

  def updatePaymentSubmission(paymentSubmission: PaymentSubmission): FutureOfEither[PaymentSubmission] = {
    EitherT(Future.successful(Right(paymentSubmission)):Future[Either[String, PaymentSubmission]])
  }

}

object UuidGenerator {
  def generateUUid = 111
}

object PaymentSubmitter {
  def submit(paymentSubmission: PaymentSubmission, uuidGenerator: UuidGenerator.type)(implicit actorSystem: ActorSystem): PaymentSubmission = {
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = actorSystem.dispatcher

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "http://akka.io"))

    responseFuture
      .onComplete {
        case Success(res) => paymentSubmission
        case Failure(_)   => paymentSubmission
      }

    paymentSubmission
  }
}
