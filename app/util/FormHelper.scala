package util

import play.api.templates._
import play.api.mvc._
import views.html.helper
import play.Logger
import java.lang.annotation.Annotation
import java.lang.reflect.Field
import scala.collection.JavaConversions._
import models._
import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.api.templates.PlayMagicForJava._

object FormHelper {
   def apply[T](form: play.data.Form[T], formClass: java.lang.Class[_], action: Call): Html = {
     helper.form(action) {


      val buffer = new StringBuilder();

      val c = formClass
             for ( publicField <- c.getFields()) {

                 val annotations = publicField.getAnnotations();
                 val anos = new StringBuilder();
                 for( annotation <- annotations) {
                     anos.append(annotation);
                 }

                 val fieldName = publicField.getName();
                 val fieldType = publicField.getType().getName();




    val typeAsString:String = fieldType.getClass.getSimpleName().toString


              val ergebnis = typeAsString match {
                    case "String" =>  buffer.append(helper.inputText(form(fieldName)))
                    case _ => ""
                 }
             }


             new Html(buffer.toString + """<div class="form-actions">
                 <button type="submit" class="btn btn-primary">Submit</button>
                 <button class="btn">Cancel</button>
             </div>""")
         }
   }
}