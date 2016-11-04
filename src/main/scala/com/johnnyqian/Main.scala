package com.johnnyqian

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.Terminated

/**
  * Created by Johnny Qian on 11/4/16.
  */
object Main {

  import PrinterProtocol._


  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("printer-service")
    val printerSupervisor = system.actorOf(Props(new PrinterActorSupervisor), "printer-supervisor")
    // "The Supervisor is ready to supervise"
    // "Yo, I am alive!"

    printerSupervisor ! Message("...please, print me...")
    // ...please, print me...

    printerSupervisor ! Message("...another message to print, nothing should happen...")
    // ...another message to print, nothing should happen...

    printerSupervisor ! Message("...why don't you restart?!")

    printerSupervisor ! Message("...fell free to resume!")
    // ...fell free to resume!

    printerSupervisor ! Message("...you can STOP now!")
    // ...you can STOP now!
    // Goodbye world!

    printerSupervisor ! Message("...this is going to be our little secret...")
    // ...this is going to be our little secret...
    // Goodbye world!
    // Bye Bye from the Supervisor
  }


}