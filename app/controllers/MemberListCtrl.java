package controllers;

import java.util.Collections;
import java.util.List;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

/**
 * Controller that controls the view and actions that a trainer can perform to the members he manages
 * Created by kevin on 07/04/2017.
 */

public class MemberListCtrl extends Controller {
    /**
     * Method that renders a view of a member and their assessments to allow a trainer to add a comment for an
     * assessment
     * @param id ID of the member
     */
    public static void index (Long id) {
        Member member = Member.findById(id);
        List<Assessment> assessmentlist = member.assessmentlist;
        Collections.sort(assessmentlist);
        Logger.info("Member id = " + id);
        render("member.html" , member, assessmentlist);
    }

    /**
     * Method to delete a member that the trainer manages from the database
     * @param memberid Id of the member
     */
    public static void deleteMember (Long memberid) {
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);

        Logger.info("Removing member " + member.name);

        trainer.memberList.remove(member);
        trainer.save();
        member.delete();
        redirect("/trainer");
    }


    /**
     * Method for a trainer to update the comment for a specific member's assessment
     * @param memberid Id of the member
     * @param assessmentid Id of the assessment
     * @param comment Comment for the assessment
     */
    public static void updateComment (Long memberid, Long assessmentid, String comment) {
        Member member = Member.findById(memberid);
        Assessment assessment = Assessment.findById(assessmentid);

        assessment.comment = comment;
        assessment.save();
        member.save();

        Logger.info("Updating comment with " + comment + " for assessment: " + assessmentid);

        redirect("/members/" + memberid);
    }
}
