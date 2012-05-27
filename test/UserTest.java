import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;

import java.util.List;
import java.util.Map;
import models.User;
import play.libs.Yaml;
import play.test.*;
import org.junit.*;

public class UserTest {
    public static final String USERNAME = "Michael";
    public static final String PASSWORD = "Play1Rules.";
    User user;

    @Before
    public void before() {
        user = new User(USERNAME);
        user.setPassword(PASSWORD);
    }

    @Test
    public void passwordCorrect() {
        assertThat(user.hasPassword(PASSWORD)).isEqualTo(true);
    }

    @Test
    public void passwordFalse() {
        assertThat(user.hasPassword("NOT " + PASSWORD)).isEqualTo(false);
    }
}