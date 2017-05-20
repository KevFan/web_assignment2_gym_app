package controllers;

import models.Member;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

import java.util.Collections;
import java.util.List;

/**
 * Class that serves as the Controller for the main dashboard for a Member
 */
public class Dashboard extends Controller {
    /**
     * Renders the dashboard for the current member, and their assessments sorted in reverse order
     */
    public static void index() {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessmentlist = member.assessmentlist;
        Collections.sort(assessmentlist);
        render("dashboard.html", member, assessmentlist);
    }

    /**
     * Adds a new assessment for the member
     * @param weight Weight measurement of the assessment
     * @param chest Chest measurement of the assessment
     * @param thigh Thigh measurement of the assessment
     * @param upperArm Upper Arm measurement of the assessment
     * @param waist Waist measurement of the assessment
     * @param hips Hips measurement of the assessment
     */
    public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips) {
        Member member = Accounts.getLoggedInMember();
        Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
        member.assessmentlist.add(assessment);
        member.save();
        Logger.info("Adding Assessment");
        redirect("/dashboard");
    }

    /**
     * Deletes an assessment from the current member's list of assessments
     * @param id Member id
     * @param assessmentid Assessment id
     */
    public static void deleteAssessment(Long id, Long assessmentid) {
        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(assessmentid);
        member.assessmentlist.remove(assessment);
        member.save();
        assessment.delete();
        Logger.info("Deleting Assessment");
        if (session.contains("logged_in_Memberid")) {
            redirect("/dashboard");
        } else {
            redirect("/members/" + id);
        }

    }
}
