package controllers;

import forms.Login;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import models.Task;
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
        Result result = unauthorized(index.render(loginForm));
        if(loginForm.hasErrors()) {
            return badRequest(index.render(loginForm));
        } else if (credentialsCorrect(loginForm.get().userName, loginForm.get().password)) {
            result = ok(loggedIn.render(loginForm.get().userName));
        }
        return result;
    }

    public static Result templateExamples() {
        return  ok(templateExample.render(new User("template Hi"), null, null, createTaskList(), "bar", new String[] {"first", "second", "third"}));
    }

    private static List<Task> createTaskList() {
        final List<Task> tasks = new LinkedList<Task>();
        for (int i = 0; i < 5; i++) {
            final Task task = new Task();
            task.dueDate = new Date();
            task.title = "Task No. " + i;
            task.folder = i < 3 ? "Taskfolder 1" : "Taskfolder 2";
            task.done = i == 3;
            tasks.add(task);
        }
        return tasks;
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
