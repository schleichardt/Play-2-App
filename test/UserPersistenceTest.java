import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import models.User;
import org.junit.Test;

public class UserPersistenceTest {
    @Test
    public void findById() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                assertThat(User.find.all().size()).isEqualTo(0).overridingErrorMessage("no users should be persisted");
                final String userName = "Michael";
                final User user = new User(userName);
                user.save();
                assertThat(User.find.all().size()).isEqualTo(1).overridingErrorMessage("1 user persisted");
                final User userLoadedFromDatabase = User.find.where().eq("name", userName).findUnique();
                assertThat(userLoadedFromDatabase.name).isEqualTo("Michael");
            }
        });
    }

}
