package com

import cats.data.{EitherT, OptionT}

import scala.concurrent.Future

package object tagless {
  type FutureOfEither[A] = EitherT[Future, String, A]
}
