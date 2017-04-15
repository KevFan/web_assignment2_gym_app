package controllers;

import java.util.List;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

/**
 * Created by kevin on 04/04/2017.
 */
public class Admin extends Controller {
    public static void index() {
        Logger.info("Rendering Admin");

        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> memberList = trainer.memberList;
        render("trainer.html", memberList);
    }
}
