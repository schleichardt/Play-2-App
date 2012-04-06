package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._ //needed for html
import models._

object Forms extends Controller {
  
  val signupForm = Form(
      mapping(
      "username" -> text(minLength = 4),
      "email" -> email,
      "password" -> text(minLength = 8)   
    )(User.apply)(User.unapply)
  )

  def signup = Action {
    Ok(html.Forms.form(signupForm));
  }

  def signupSubmit = Action { 
    implicit request =>  signupForm.bindFromRequest.fold(
      errors => BadRequest(html.Forms.form(errors)),
      user => Ok(html.Forms.signupSubmit(user))
    )
  }
  
} 
