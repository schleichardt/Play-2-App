package forms;

import play.data.validation.Constraints;

public class Login {
    @Constraints.Required
    public String userName;
    @Constraints.Required
    @Constraints.MinLength(8)
    public String password;

    @Override
    public String toString() {
        return "Login{" +
                "password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
