package controllers;

import forms.Login;
import models.User;
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

    private static boolean credentialsCorrect(final String userName, final String password) {
        boolean correct = false;
        final User user = User.find.where().eq("name", userName).findUnique();
        if(user != null) {
            correct = user.hasPassword(password);
        }
        return correct;
    }
}
