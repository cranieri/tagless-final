package com.tagless


import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import cats.instances.future._

import scala.util.{Failure, Success}


object Example extends App{
  val paymentSubmissionProgram: PaymentSubmissionProgram[Future] = new PaymentSubmissionProgram[Future](PaymentSubmissionInterpreter)

  val prog = paymentSubmissionProgram.run(PaymentSubmission("123", 10000, ""))
  prog.onComplete({
    case Success(paymentSubmission) => println(s"payment id: ${paymentSubmission.id}")
    case Failure(t) => println("An error has occurred: " + t.getMessage)
  })
}
