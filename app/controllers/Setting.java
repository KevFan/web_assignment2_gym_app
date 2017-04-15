package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

/**
 * Created by kevin on 15/04/2017.
 */
public class Setting extends Controller {
    public static void index() {
        Logger.info("Rendering settings");
        Member member = Accounts.getLoggedInMember();
        render("setting.html", member);
    }
}
