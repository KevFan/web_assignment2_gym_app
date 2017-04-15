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
        else if (member.assessmentlist.size() != 0){
           return toTwoDecimalPlaces(member.assessmentlist.get(member.assessmentlist.size() - 1).weight / ((member.height * member.height)));
        }
        else {
            return toTwoDecimalPlaces(member.startingWeight / (member.height * member.height));
        }
    }

    /*
     * This is a private helper method.  It ensures that all double data returned from this class
     * is restricted to two decimal places.  Note:  this method does not round
     */
    private static double toTwoDecimalPlaces( double num){
        return (int)(num *100 ) / 100.0;
    }


    /**
     * This method determines the BMI category that the member belongs to.
     *
     * <pre>
     *
     * The category is determined by the magnitude of the members BMI according to the following:
     *
     *     BMI less than    15   (exclusive)                      is "VERY SEVERELY UNDERWEIGHT"
     *     BMI between      15   (inclusive) and 16   (exclusive) is "SEVERELY UNDERWEIGHT"
     *     BMI between      16   (inclusive) and 18.5 (exclusive) is "UNDERWEIGHT"
     *     BMI between      18.5 (inclusive) and 25   (exclusive) is "NORMAL"
     *     BMI between      25   (inclusive) and 30   (exclusive) is "OVERWEIGHT"
     *     BMI between      30   (inclusive) and 35   (exclusive) is "MODERATELY OBESE"
     *     BMI between      35   (inclusive) and 40   (exclusive) is "SEVERELY OBESE"
     *     BMI greater than 40   (inclusive)                      is "VERY SEVERELY OBESE"
     *
     * </pre>
     *
     * @return
     * <pre>
     * The format of the String is similar to this (note the double quotes around the category):
     *     "NORMAL".
     * </pre>
     */
    public static String determineBMICategory()
    {
        double bmi = calculateBMI();

        if      (bmi < 15)    return "VERY SEVERELY UNDERWEIGHT";
        else if (bmi < 16)    return "SEVERELY UNDERWEIGHT";
        else if (bmi < 18.5)  return "UNDERWEIGHT";
        else if (bmi < 25)    return "NORMAL";
        else if (bmi < 30)    return "OVERWEIGHT";
        else if (bmi < 35)    return "MODERATELY OBESE";
        else if (bmi < 40)    return "SEVERELY OBESE";
        else                  return "VERY SEVERELY OBESE";
    }

    /**
     * This method returns a boolean to indicate if the member has an ideal
     * body weight based on the Devine formula.
     *
     * <pre>
     * For males, an ideal body weight is:   50 kg + 2.3 kg for each inch over 5 feet.
     * For females, an ideal body weight is: 45.5 kg + 2.3 kg for each inch over 5 feet.
     *
     * Note:  if no gender is specified, return the result of the female calculation.
     *
     * </pre>
     *
     * @return Returns true if the result of the devine formula is within 2 kgs (inclusive) of the
     *         starting weight; false if it is outside this range.
     */
    public static boolean isIdealBodyWeight()
    {
        Member member = Accounts.getLoggedInMember();
        double fiveFeet = 60.0;
        double idealBodyWeight;

        double inches = member.height * 39.37;

        if (inches <= fiveFeet){
            if (member.gender.equals("M")){
                idealBodyWeight = 50;
            }
            else{
                idealBodyWeight = 45.5;
            }
        }
        else{
            if (member.gender.equals("M")){
                idealBodyWeight = 50 + ((inches - fiveFeet) * 2.3);
            }
            else{
                idealBodyWeight = 45.5 + ((inches - fiveFeet) * 2.3);
            }
        }

        return (      (idealBodyWeight <= (member.startingWeight + 2.0))
                && (idealBodyWeight >= (member.startingWeight - 2.0))
        );
    }
}
