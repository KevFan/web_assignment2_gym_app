package controllers;

import java.util.List;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

/**
 * Controller that renders the trainer account session view
 * Created by kevin on 04/04/2017.
 */
public class Admin extends Controller {
    /**
     * Method that renders the the trainer view of the list of member's he manages
     */
    public static void index() {
        Logger.info("Rendering Trainer view");

        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> memberList = trainer.memberList;
//      List<Member> memberList = Member.findAll();
        render("trainer.html", memberList);
    }
}
