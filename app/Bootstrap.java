import java.util.List;

import play.*;
import play.jobs.*;
import play.test.*;

import models.*;

/**
 * Created by kevin on 04/04/2017.
 */

@OnApplicationStart
public class Bootstrap extends Job {
    public void doJob() {
       Fixtures.loadModels("data.yml");
    }
}
