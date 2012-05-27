import com.avaje.ebean.Ebean;
import models.User;
import play.*;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");
        if (app.isDev()) {
            Fixtures.loadAll();
        }
    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }
}