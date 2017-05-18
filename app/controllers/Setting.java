package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

/**
 * Controller for a member to update account details such as name, gender, address, email, password, height
 * and starting weight
 * Created by kevin on 15/04/2017.
 */
public class Setting extends Controller {
    /**
     * Method that render's the setting.html view for the member
     */
    public static void index() {
        Logger.info("Rendering settings");
        Member member = Accounts.getLoggedInMember();
        render("setting.html", member);
    }

    /**
     * Method that updates the name of the member
     * @param name New name of the member
     */
    public static void updateMemberName(String name) {
        Member member = Accounts.getLoggedInMember();
        member.name = name;
        member.save();
        redirect("/setting");
    }

    /**
     * Method that updates the gender of the member
     * @param gender New gender fo the member
     */
    public static void updateMemberGender(String gender) {
        Member member = Accounts.getLoggedInMember();
        if (gender.equals("") || gender.toUpperCase().charAt(0) != 'M' || gender.toUpperCase().charAt(0) != 'F'){
            member.gender = "Unspecified";
        } else if (gender.toUpperCase().charAt(0) == 'M') {
            member.gender = "Male";
        } else if (gender.toUpperCase().charAt(0) == 'F') {
            member.gender = "Female";
        }
        member.save();
        redirect("/setting");
    }

    /**
     * Method that updates the email of the member
     * @param email New email of the member
     */
    public static void updateMemberEmail(String email) {
        Member member = Accounts.getLoggedInMember();
        if (Member.findByEmail(email) != null || Trainer.findByEmail(email) != null) {
            Logger.info("User email already used");
            redirect("/setting");
        } else {
            member.email = email;
            member.save();
            redirect("/setting");
        }

    }

    /**
     * Method that updates the password of the member
     * @param password New password for the member account
     */
    public static void updateMemberPassword(String password) {
        Member member = Accounts.getLoggedInMember();
        member.password = password;
        member.save();
        redirect("/setting");
    }

    /**
     * Method that updates the address of the member
     * @param address New address of the member
     */
    public static void updateMemberAddress(String address) {
        Member member = Accounts.getLoggedInMember();
        member.address = address;
        member.save();
        redirect("/setting");
    }

    /**
     * Method that updates the height of member
     * @param height New height of the member
     */
    public static void updateMemberHeight(double height) {
        Member member = Accounts.getLoggedInMember();
        member.height = height;
        member.save();
        redirect("/setting");
    }

    /**
     * Method that updates the starting weight of the member
     * @param startingWeight New starting weight of the member
     */
    public static void updateMemberStartingWeight(double startingWeight) {
        Member member = Accounts.getLoggedInMember();
        member.startingWeight = startingWeight;
        member.save();
        redirect("/setting");
    }
}
