package controllers;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

/**
 * Controller class to control the accounts of Member and Trainers
 * Created by kevin on 08/04/2017.
 */
public class Accounts extends Controller {
    /**
     * Method that render's the signup.html view
     */
    public static void signup() {
        render("signup.html");
    }

    /**
     * Method that render's the login.html view
     */
    public static void login() {
        render("login.html");
    }

    /**
     * Method that registers a new member to the gym applet.
     * @param name Name of the Member
     * @param gender Gender of the Member
     * @param email Email of the Member
     * @param password Password of the Member
     * @param address Address of the Member
     * @param height Height of the Member
     * @param startingWeight Starting Weight of the Member
     */
    public static void register(String name, String gender, String email, String password, String address, double height, double startingWeight) {
        Logger.info("Registering new user " + email);
        Member member = new Member(name, gender, email, password, address, height, startingWeight);
        member.save();
        redirect("/");
    }

    /**
     * Method that authenticate the passed in email and password to an existing member/trainer's account
     * @param email Email of the member/trainer
     * @param password Password of the account
     */
    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ":" + password);
        Trainer trainer = Trainer.findByEmail(email);
        Member member = Member.findByEmail(email);
        if ((trainer != null) && (trainer.checkPassword(password))) {
            Logger.info("Authentication successful");
            session.put("logged_in_Tainerid", trainer.id);
            redirect("/trainer");
        }
        if ((member != null) && (member.checkPassword(password))) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    /**
     * Method to end the session
     */
    public static void logout() {
        session.clear();
        redirect("/");
    }

    /**
     * Method to return the Member of the current session
     * @return Member of the current session if logged in successfully
     */
    public static Member getLoggedInMember() {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    /**
     * Method to return the Trainer of the current session
     * @return Trainer of the current session if logged in successfully
     */
    public static Trainer getLoggedInTrainer() {
        Trainer trainer = null;
        if (session.contains("logged_in_Tainerid")) {
            String memberId = session.get("logged_in_Tainerid");
            trainer = Trainer.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return trainer;
    }
}
