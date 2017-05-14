package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to model a gym member.
 *
 * Created by kevin on 08/04/2017.
 */

@Entity
public class Member extends Model {
    public String name;
    public String gender;
    public String email;
    public String password;
    public String address;
    public double height;
    public double startingWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessmentlist = new ArrayList<Assessment>();

    /**
     * Constructor for a Member object
     * @param name Name of the Member
     * @param gender Gender of the Member
     * @param email Email of the Member
     * @param password Password of the Member
     * @param address Address of the Member
     * @param height Height of the Member
     * @param startingWeight Starting Weight of the member
     */
    public Member(String name, String gender, String email, String password, String address, double height, double startingWeight) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.address = address;
        this.height = height;
        this.startingWeight = startingWeight;
    }

    /**
     * Method to return the first member's in the database who's email matches the email passed in
     *
     * @param email Email of member to search for
     * @return The first member who's email matches the email passed in
     */
    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    /**
     * Checks the current stored password to the password passed in
     * @param password String of password to log in
     * @return Boolean of whether the password matches the stored password
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * This method calculates the BMI value for the member.
     *
     * The formula used for BMI is weight divided by the square of the height.
     *
     * @return the BMI value for the member.  The number returned is truncated to two decimal places.
     **/
    public double calculateBMI() {
        if (height <= 0)
            return 0;
        else if (!assessmentlist.isEmpty()) {
            return toTwoDecimalPlaces(assessmentlist.get(0).weight / ((height * height)));
        } else {
            return toTwoDecimalPlaces(startingWeight / (height * height));
        }
    }

    /**
     * This is a private helper method.  It ensures that all double data returned from this class
     * is restricted to two decimal places.  Note:  this method does not round
     */
    private double toTwoDecimalPlaces(double num) {
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
    public String determineBMICategory() {
        double bmi = calculateBMI();

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
    public boolean isIdealBodyWeight() {
        double fiveFeet = 60.0;
        double idealBodyWeight;

        double inches = height * 39.37;

        if (inches <= fiveFeet) {
            if (gender.equals("M")) {
                idealBodyWeight = 50;
            } else {
                idealBodyWeight = 45.5;
            }
        } else {
            if (gender.equals("M")) {
                idealBodyWeight = 50 + ((inches - fiveFeet) * 2.3);
            } else {
                idealBodyWeight = 45.5 + ((inches - fiveFeet) * 2.3);
            }
        }

        if (assessmentlist.isEmpty()) {
            return ((idealBodyWeight <= (startingWeight + 2.0))
                    && (idealBodyWeight >= (startingWeight - 2.0))
            );
        } else {
            return ((idealBodyWeight <= (assessmentlist.get(0).weight + 2.0))
                    && (idealBodyWeight >= (assessmentlist.get(0).weight - 2.0))
            );
        }
    }
}
