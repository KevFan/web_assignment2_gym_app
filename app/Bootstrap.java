import play.jobs.*;
import play.test.*;
import models.*;

/**
 * Class that loads the data yml file to the play database
 * Created by kevin on 04/04/2017.
 */

@OnApplicationStart
public class Bootstrap extends Job {
    /**
     * Method that loads the data.yml file when there are no members in the database
     */
    public void doJob() {
        if(Member.count() == 0) {
            Fixtures.loadModels("data.yml");
        }
    }
}
