import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

import static org.fest.assertions.Assertions.*;

import models.User;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

public class UserPersistenceTest {
    @Test
    public void persistAndFind() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                final int oldSize = User.find.all().size();
                final String userName = RandomStringUtils.random(10);
                final User user = new User(userName);
                user.save();
                assertThat(User.find.all().size()).isEqualTo(oldSize + 1);
                final User userLoadedFromDatabase = User.find.where().eq("name", userName).findUnique();
                assertThat(userLoadedFromDatabase.name).isEqualTo(userName);
            }
        });
    }

}
