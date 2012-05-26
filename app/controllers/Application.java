package controllers;

import static info.schleichardt.play2.secure.Secure.credentialsCorrect;

import forms.Login;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
    public static Result index() {
        return ok(index.render("empty"));
    }

    public static Result signIn() {
        final Login credentials = form(Login.class).bindFromRequest().get();
        Result result = unauthorized();
        if (credentialsCorrect(credentials.userName, credentials.password)) {
            result = ok();
        }
        return result;
    }
}
