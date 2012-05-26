package forms;

public class Login {
    public String userName;
    public String password;

    @Override
    public String toString() {
        return "Login{" +
                "password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
