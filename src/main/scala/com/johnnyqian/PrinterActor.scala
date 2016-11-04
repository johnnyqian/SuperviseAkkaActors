package com.johnnyqian

/**
  * Created by Johnny Qian on 11/4/16.
  */
import akka.actor.Actor

class PrinterActor extends Actor {

  import PrinterProtocol._

  override def preRestart(reason: Throwable, message: Option[Any]) = {
    println("Yo, I am restarting...")
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable) = {
    println("...restart completed!")
    super.postRestart(reason)
  }

  override def preStart() = println("Yo, I am alive!")
  override def postStop() = println("Goodbye world!")

  override def receive: Receive = {
    case Message(msg) if containsRestart(msg) =>
      println(msg); throw new RestartMeException
    case Message(msg) if containsResume(msg) =>
      println(msg); throw new ResumeMeException
    case Message(msg) if containsStop(msg) =>
      println(msg); throw new StopMeException
    case Message(msg) if containsSecret(msg) =>
      println(msg); throw new Throwable
    case Message(msg) => println(msg)
  }

  private def containsRestart = containsWordCaseInsensitive("restart")_
  private def containsResume = containsWordCaseInsensitive("resume")_
  private def containsStop = containsWordCaseInsensitive("stop")_
  private def containsSecret = containsWordCaseInsensitive("secret")_

  private def containsWordCaseInsensitive(word: String)(msg: String) =  msg matches s".*(?i)$word.*"
}
