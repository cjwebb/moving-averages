package com.github.cjwebb.movingaverages

import akka.actor.{Props, ActorSystem}
import akka.testkit.{TestKit}
import org.scalatest.{BeforeAndAfterAll, OneInstancePerTest, FreeSpec}
import com.gitub.cjwebb.movingaverages.{MovingAverage, AddNumber, MovingAverageActor}
import akka.pattern.ask
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.util.Timeout
import org.scalatest.matchers.MustMatchers


class MovingAveragesActorTest extends TestKit(ActorSystem())
                                with FreeSpec
                                with MustMatchers
                                with BeforeAndAfterAll
                                with OneInstancePerTest {

  override def afterAll {
    system.shutdown()
  }

  implicit val timeout = Timeout(2 seconds) // needed for `?` below
  val actor = system.actorOf(Props[MovingAverageActor])

  "actor should return average" in {
    actor ! AddNumber("10")
    actor ! AddNumber("10")

    average(2) must be (BigDecimal("10"))
    average(4) must be (BigDecimal("5"))
  }

  def average(n: Int) = {
    val f = actor ? MovingAverage(n)
    Await.result[BigDecimal](f.mapTo[BigDecimal], 2 seconds)
  }

}