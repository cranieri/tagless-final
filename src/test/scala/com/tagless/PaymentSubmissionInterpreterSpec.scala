package com.tagless

import cats.Id
import org.scalatest.{EitherValues, FunSpec, Inside, Matchers}
import org.scalatest.prop.PropertyChecks
import scala.concurrent.ExecutionContext.Implicits.global

class PaymentSubmissionInterpreterSpec extends FunSpec
  with PropertyChecks
  with Matchers
  with EitherValues
  with Inside {

  describe("PaymentSubmissionInterpreter") {
    it("creates a payment submission") {
      val paymentSubmission = PaymentSubmission("123", 10000, "")
      val paymentSubmissionCreation = PaymentSubmissionInterpreter.createPaymentSubmission(paymentSubmission)
      paymentSubmissionCreation.map { ps => assert(ps == paymentSubmission) }
    }

    it("validates a payment submission") {
      val paymentSubmission = PaymentSubmission("123", 10000, "")
      val paymentSubmissionCreation = PaymentSubmissionInterpreter.validate(paymentSubmission)
      paymentSubmissionCreation.map { ps => assert(ps == paymentSubmission) }
    }
  }

}
