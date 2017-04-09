package controllers;

import models.Member;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
    public static void index()
    {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessmentlist = member.assessmentlist;
        render("dashboard.html", member, assessmentlist);
    }

    public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips)
    {
        Member member = Accounts.getLoggedInMember();
        Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
        member.assessmentlist.add(assessment);
        member.save();
        Logger.info("Adding Assessment");
        redirect("/dashboard");
    }

    public static void deleteAssessment(Long id, Long assessmentid)
    {
        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(assessmentid);
        member.assessmentlist.remove(assessment);
        member.save();
        assessment.delete();
        Logger.info("Deleting Assessment");
        redirect("/dashboard");
    }

    /**
     * This method calculates the BMI value for the member.
     *
     * The formula used for BMI is weight divided by the square of the height.
     *
     * @return the BMI value for the member.  The number returned is truncated to two decimal places.
     **/
    public static double calculateBMI() {
        Member member = Accounts.getLoggedInMember();
        if (member.height <= 0)
            return 0;
        else
        return toTwoDecimalPlaces(member.startingWeight / (member.height * member.height));
    }

    /*
     * This is a private helper method.  It ensures that all double data returned from this class
     * is restricted to two decimal places.  Note:  this method does not round
     */
    private static double toTwoDecimalPlaces( double num){
        return (int)(num *100 ) / 100.0;
    }

}
