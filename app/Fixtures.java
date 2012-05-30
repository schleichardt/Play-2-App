import com.avaje.ebean.Ebean;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.DbUtils;
import play.Logger;
import play.db.DB;
import play.libs.Yaml;

public class Fixtures {
    public static void loadAll() {
        final String fixtureFile = "initial-data.yml";
        @SuppressWarnings("unchecked")
        Map<String, List<Object>> tableMap = (Map<String, List<Object>>) Yaml.load(fixtureFile);//yaml must be in conf folder?

        for (Map.Entry<String, List<Object>> tableEntry : tableMap.entrySet()) {
            Ebean.save(tableEntry.getValue());
            Logger.info("loaded " + tableEntry.getValue().size() + " " + tableEntry.getKey() + " from '" + fixtureFile + "' into the database");
        }
    }

    public static void deleteAll() {
        try {
            final Connection connection = DB.getConnection();
            final Statement sm = connection.createStatement();
            sm.execute("DROP ALL OBJECTS");
            DbUtils.closeQuietly(sm);
            DbUtils.closeQuietly(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
