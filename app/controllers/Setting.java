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

    public static void updateMemberName(String name) {
        Member member = Accounts.getLoggedInMember();
        member.name = name;
        member.save();
        redirect("/setting");
    }

    public static void updateMemberGender(String gender) {
        Member member = Accounts.getLoggedInMember();
        member.gender = gender;
        member.save();
        redirect("/setting");
    }

    public static void updateMemberEmail(String email) {
        Member member = Accounts.getLoggedInMember();
        member.email = email;
        member.save();
        redirect("/setting");
    }

    public static void updateMemberPassword(String password) {
        Member member = Accounts.getLoggedInMember();
        member.password = password;
        member.save();
        redirect("/setting");
    }

    public static void updateMemberAddress(String address) {
        Member member = Accounts.getLoggedInMember();
        member.address = address;
        member.save();
        redirect("/setting");
    }

    public static void updateMemberHeight(double height) {
        Member member = Accounts.getLoggedInMember();
        member.height = height;
        member.save();
        redirect("/setting");
    }

    public static void updateMemberStartingWeight(double startingWeight) {
        Member member = Accounts.getLoggedInMember();
        member.startingWeight = startingWeight;
        member.save();
        redirect("/setting");
    }
}
