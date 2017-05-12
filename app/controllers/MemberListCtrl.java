package controllers;

import java.util.Collections;
import java.util.List;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

/**
 * Created by kevin on 07/04/2017.
 */

public class MemberListCtrl extends Controller {
    public static void index (Long id) {
        Member member = Member.findById(id);
        List<Assessment> assessmentlist = member.assessmentlist;
        Collections.sort(assessmentlist);
        Logger.info("Member id = " + id);
        render("member.html" , member, assessmentlist);
    }

    public static void deleteMember (Long memberid) {
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);

        Logger.info("Removing member " + member.name);

        trainer.memberList.remove(member);
        trainer.save();
        member.delete();
        redirect("/trainer");
    }

/*    public static void addAssessment(Long id, double weight, double chest, double thigh, double upperArm, double waist, double hips ) {
        Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
        AssessmentList assessmentList = AssessmentList.findById(id);
        assessmentList.assessments.add(assessment);
        assessmentList.save();
        redirect("/assessmentLists/" + id);
    }*/

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
