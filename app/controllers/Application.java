package controllers;

import forms.Login;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.loggedIn;
import views.html.templateExample;

public class Application extends Controller {
    public static Result index() {
        return ok(index.render(form(Login.class)));
    }

    public static Result signIn() {
        final Form<Login> loginForm = form(Login.class).bindFromRequest();
        final Login login = loginForm.get();
        Result result = unauthorized(index.render(loginForm));
        if (credentialsCorrect(login.userName, login.password)) {
            result = ok(loggedIn.render(login.userName));
        }
        return result;
    }

    public static Result templateExamples() {
        return  ok(templateExample.render(new User("template Hi")));
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
