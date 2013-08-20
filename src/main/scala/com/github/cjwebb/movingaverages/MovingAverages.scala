package com.gitub.cjwebb.movingaverages

import akka.actor._
import scala.collection.mutable

case class AddNumber(number: BigDecimal)
object AddNumber {
  def apply(numberString: String): AddNumber = AddNumber(BigDecimal(numberString))
}

case class MovingAverage(divisor: Int)

class MovingAverageActor extends Actor {

  val queue = mutable.Queue[BigDecimal]()

  def receive = {
    case AddNumber(x) => queue.enqueue(x)
    case MovingAverage(d) => {
      var result = BigDecimal(0)
      queue.takeRight(d).foreach { n =>
        result = result + n
      }
      sender ! result / BigDecimal(d.toString)
    }
  }

}