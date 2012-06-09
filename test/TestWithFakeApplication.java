import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import play.test.FakeApplication;
import play.test.Helpers;

/**
 * Class for Tests with a fake application and the database.
 * Inspired by http://blog.matthieuguillermin.fr/2012/03/unit-testing-tricks-for-play-2-0-and-ebean .
 * Advantage: Initializes the server and the database only for a test class and not every test.
 */
public abstract class TestWithFakeApplication {
    public static FakeApplication app;

    @BeforeClass
    public static void startApp() throws IOException {
        app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
        Helpers.start(app);
        //tip: Fixtures.loadAll(); with @BeforeClass in inherited class
    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }
}
