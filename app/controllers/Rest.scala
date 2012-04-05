package controllers

import play.api._
import play.api.mvc._

object Rest extends Controller {
  
  def getXml = Action {
    var name = "Michael"
    Ok(<message status="OK">Hello {name}</message>)
  }
  
} 
