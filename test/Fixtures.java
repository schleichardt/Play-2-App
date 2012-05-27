import com.avaje.ebean.Ebean;
import java.util.List;
import java.util.Map;
import play.libs.Yaml;

public class Fixtures {
    public static void loadAll() {
        @SuppressWarnings("unchecked")
        Map<String, List<Object>> tableMap = (Map<String, List<Object>>) Yaml.load("initial-data.yml");//yaml must be in conf folder?

        for (Map.Entry<String, List<Object>> tableEntry : tableMap.entrySet()) {
            Ebean.save(tableEntry.getValue());
        }
    }
}
