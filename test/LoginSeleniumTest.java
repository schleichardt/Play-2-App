import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import org.junit.Test;
import play.libs.F;
import play.test.TestBrowser;

public class LoginSeleniumTest {
    @Test
    public void runInBrowser() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Fixtures.loadAll();

                browser.goTo("http://localhost:3333");

                assertThat(browser.title()).isEqualTo("Welcome to Play 2.0");

                browser.fill("#userName").with("Michael");
                browser.fill("#password").with("Play1Rules.");
                browser.submit("form");

                assertThat(browser.url()).isEqualTo("http://localhost:3333/signIn");
                assertThat(browser.title()).isEqualTo("Welcome Michael");


            }
        });
    }
}
