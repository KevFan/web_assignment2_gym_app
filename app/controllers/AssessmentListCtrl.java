package controllers;

import java.util.List;

import models.AssessmentList;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;
/**
 * Created by kevin on 07/04/2017.
 */
public class AssessmentListCtrl extends Controller {
    public static void index (Long id) {
        AssessmentList assessmentList = AssessmentList.findById(id);
        Logger.info("AssessmentList id = " + id);
        render("assessmentList.html" ,assessmentList);
    }

    public static void deleteAssessment (Long id, Long assessmentid) {
        AssessmentList assessmentList = AssessmentList.findById(id);
        Assessment assessment = Assessment.findById(assessmentid);
        Logger.info("Removing " + assessmentList.name + assessmentid);

        assessmentList.assessments.remove(assessment);
        assessmentList.save();
        assessment.delete();
        render("assessmentList.html" ,assessmentList);
    }

    public static void addAssessment(Long id, double weight, double chest, double thigh, double upperArm, double waist, double hips ) {
        Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
        AssessmentList assessmentList = AssessmentList.findById(id);
        assessmentList.assessments.add(assessment);
        assessmentList.save();
        redirect("/assessmentLists/" + id);
    }
}
