package info.schleichardt.play2.secure;

public class Secure {
    public static boolean credentialsCorrect(final String userName, final String password) {
        return userName.equals("Michael") && password.equals("Play1Rules.");
    }
}
