package utils;

import models.Member;

/**
 * This class performs the analytics for a member such calculating BMI, determining BMI Category, is ideal weight,
 * and trend by weight
 *
 * Created by kevin on 19/05/2017.
 */
public class Analytic {
    public static int trendCounter = 0;

    /**
     * This method calculates the BMI value for the member.
     *
     * The formula used for BMI is weight divided by the square of the height.
     *
     * @return the BMI value for the member.  The number returned is truncated to two decimal places.
     **/
    public static double calculateBMI(Member member) {
        if (member.height <= 0)
            return 0;
        else if (!member.assessmentlist.isEmpty()) {
            return toTwoDecimalPlaces(member.assessmentlist.get(0).weight / ((member.height * member.height)));
        } else {
            return toTwoDecimalPlaces(member.startingWeight / (member.height * member.height));
        }
    }

    /**
     * This is a private helper method.  It ensures that all double data returned from this class
     * is restricted to two decimal places.  Note:  this method does not round
     */
    private static double toTwoDecimalPlaces(double num) {
        return (int) (num * 100) / 100.0;
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
     * @return <pre>    The format of the String is similar to this (note the double quotes around the category):
     *                  "NORMAL".
     * </pre>
     */
    public static String determineBMICategory(Member member) {
        double bmi = calculateBMI(member);

        if (bmi < 15) return "VERY SEVERELY UNDERWEIGHT";
        else if (bmi < 16) return "SEVERELY UNDERWEIGHT";
        else if (bmi < 18.5) return "UNDERWEIGHT";
        else if (bmi < 25) return "NORMAL";
        else if (bmi < 30) return "OVERWEIGHT";
        else if (bmi < 35) return "MODERATELY OBESE";
        else if (bmi < 40) return "SEVERELY OBESE";
        else return "VERY SEVERELY OBESE";
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
     * starting weight; false if it is outside this range.
     */
    public static boolean isIdealBodyWeight(Member member) {
        double fiveFeet = 60.0;
        double idealBodyWeight;

        double inches = member.height * 39.37;

        if (inches <= fiveFeet) {
            if (member.gender.toUpperCase().charAt(0) == 'M') {
                idealBodyWeight = 50;
            } else {
                idealBodyWeight = 45.5;
            }
        } else {
            if (member.gender.toUpperCase().charAt(0) == 'M') {
                idealBodyWeight = 50 + ((inches - fiveFeet) * 2.3);
            } else {
                idealBodyWeight = 45.5 + ((inches - fiveFeet) * 2.3);
            }
        }

        if (member.assessmentlist.isEmpty()) {
            return ((idealBodyWeight <= (member.startingWeight + 2.0))
                    && (idealBodyWeight >= (member.startingWeight - 2.0))
            );
        } else {
            return ((idealBodyWeight <= (member.assessmentlist.get(0).weight + 2.0))
                    && (idealBodyWeight >= (member.assessmentlist.get(0).weight - 2.0))
            );
        }
    }

    /**
     * Method that calculates the trend of the current assessment weight with the previous assessment weight. The
     * calculation is determined by a trendCounter, which is incremented by 1 each time the method is called.
     * Note: Assessment are sorted by reverse order by the dashboard, therefore the first assessment in the list is
     * the latest assessment
     *
     * @return A boolean of whether the trend is within +- 2kg
     */
    public static boolean trendByWeight(Member member) {
        double trend;
        // If the trendCounter is less the number of assessments, calculate the current assessment with the previous
        // assessment and increase the trendCounter by 1.
        if ( trendCounter < member.assessmentlist.size() - 1) {
            trend = (member.assessmentlist.get(trendCounter).weight - member.assessmentlist.get(trendCounter + 1).weight);
            trendCounter++;
        }
        // Otherwise, calculate the trend by using the current assessment weight with the starting weight, and reset
        // the trendCounter to 0.
        else {
            trend = (member.assessmentlist.get(trendCounter).weight - member.startingWeight);
            trendCounter = 0;
        }

        return ((trend + 2 >= 0) && (trend - 2 <= 0));
    }
}
