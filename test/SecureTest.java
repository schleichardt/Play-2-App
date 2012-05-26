import static controllers.routes.ref.Application;
import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.UNAUTHORIZED;
import static play.test.Helpers.callAction;
import static play.test.Helpers.status;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import play.mvc.Result;
import play.test.FakeRequest;

public class SecureTest {

    public static final String CORRECT_USERNAME = "Michael";
    public static final String CORRECT_PASSWORD = "Play1Rules.";

    @Test
    public void successfulLogin() {
        Result result = login(CORRECT_USERNAME, CORRECT_PASSWORD);
        final int responseCode = status(result);
        assertThat(responseCode).isEqualTo(OK);//"is" does not work here
    }

    @Test
    public void failingLogin() {
        Result result = login(CORRECT_USERNAME, "not " + CORRECT_PASSWORD);
        final int responseCode = status(result);
        assertThat(responseCode).isEqualTo(UNAUTHORIZED);//"is" does not work here
    }

    private Result login(String username, String password) {
        final Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userName", username);
        parameters.put("password", password);
        final FakeRequest fakeRequest = new FakeRequest().withFormUrlEncodedBody(parameters);
        /*
       Be carfull there is a static import for controllers.routes.ref.Application.
       The original controller Application is directly in package controllers.
        */
        return callAction(Application.signIn(), fakeRequest);
    }
}
