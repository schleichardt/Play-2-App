import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

import com.avaje.ebean.Ebean;
import java.util.List;
import java.util.Map;
import models.User;
import org.junit.Test;
import play.libs.Yaml;

public class InitialDataTest {
    @Test
    public void yamlLoading() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {

                final int initialUserNumber = User.find.findRowCount();
                assertThat(initialUserNumber).isEqualTo(0);

                @SuppressWarnings("unchecked")
                Map<String, List<Object>> tableMap = (Map<String, List<Object>>) Yaml.load("initial-data.yml");//yaml must be in conf folder?

                for (Map.Entry<String, List<Object>> tableEntry : tableMap.entrySet()) {
                    Ebean.save(tableEntry.getValue());
                }

                final int fixturesUserNumber = User.find.findRowCount();
                assertThat(fixturesUserNumber).isEqualTo(1);
            }
        });
    }
}
