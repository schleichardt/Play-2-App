import com.avaje.ebean.Ebean;
import java.util.List;
import java.util.Map;
import models.User;
import play.Logger;
import play.libs.Yaml;

public class Fixtures {
    public static void loadAll() {
        if(Ebean.find(User.class).findRowCount() == 0) {
            final String fixtureFile = "initial-data.yml";
            @SuppressWarnings("unchecked")
            Map<String, List<Object>> tableMap = (Map<String, List<Object>>) Yaml.load(fixtureFile);//yaml must be in conf folder?

            for (Map.Entry<String, List<Object>> tableEntry : tableMap.entrySet()) {
                Ebean.save(tableEntry.getValue());
                Logger.info("loaded " + tableEntry.getValue().size() + " " + tableEntry.getKey() + " from '" + fixtureFile + "' into the database");
            }
        }
    }
}
