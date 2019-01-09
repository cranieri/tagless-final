package com.tagless

import cats.Id
import org.scalatest.{EitherValues, FunSpec, Inside, Matchers}
import org.scalatest.prop.PropertyChecks
import org.scalatest._
import org.scalatest.prop.PropertyChecks

import scala.concurrent.Future
import scala.util.{Failure, Success}

class PaymentSubmissionProgramSpec extends FunSpec
  with PropertyChecks
  with Matchers
  with EitherValues
  with Inside {

  describe("PaymentSubmissionProgram") {
    it("submits a payment submission") {
      val paymentSubmissionProgram: PaymentSubmissionProgram[Id] = new PaymentSubmissionProgram[Id](PaymentSubmissionInterpreterTest)
      val submission = paymentSubmissionProgram.run(PaymentSubmission("123", 10000, ""))
      submission.id shouldBe "123"
    }
  }
}

object PaymentSubmissionInterpreterTest extends PaymentSubmissionAlg[Id]{

  def createPaymentSubmission(paymentSubmission: PaymentSubmission): Id[PaymentSubmission] = {
    paymentSubmission
  }

  def validate(paymentSubmission: PaymentSubmission): Id[PaymentSubmission] = {
    PaymentSubmission("123", 1000, "validated")
  }

  def sanitise(paymentSubmission: PaymentSubmission): Id[PaymentSubmission] = {
    PaymentSubmission("123", 1000, "sanitised")
  }

  def submitPaymentSubmission(paymentSubmission: PaymentSubmission): Id[PaymentSubmission] = {
    PaymentSubmission("123", 1000, "submitted")
  }

  def updatePaymentSubmission(paymentSubmission: PaymentSubmission): Id[PaymentSubmission] = {
    PaymentSubmission("123", 1000, "updated")
  }

}


