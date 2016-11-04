package com.johnnyqian

/**
  * Created by Johnny Qian on 11/4/16.
  */
object PrinterProtocol {

  case class Message(msg: String)

}

class RestartMeException extends Exception("RESTART")
class ResumeMeException extends Exception("RESUME")
class StopMeException extends Exception("STOP")

