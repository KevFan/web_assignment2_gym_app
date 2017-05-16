package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.util.Calendar;
import java.util.Date;

/**
 * Class to model an assessment. The class implements the Comparable interface so that the date value can be compared
 * and sorted in an ArrayList
 * Created by kevin on 04/04/2017.
 */

@Entity
public class Assessment extends Model implements Comparable<Assessment> {
    public double weight;
    public double chest;
    public double thigh;
    public double upperArm;
    public double waist;
    public double hips;
    public String comment;
    public Date date;

    /**
     * Constructor for an assessment object. No validation is done
     * @param weight Weight measurement of the assessment
     * @param chest Chest measurement of the assessment
     * @param thigh Thigh measurement of the assessment
     * @param upperArm Upper arm measurement of the assessment
     * @param waist Waist measurement of the assessment
     * @param hips Hip measurement of the assessment
     */
    public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips) {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        comment = "";
        date = new Date();
    }

    /**
     * Overrides/Implements the compareTo method in the Comparable interface
     * @param assessment Assessment object
     * @return Integer value of whether the date matches the assessments date
     */
    @Override
    public int compareTo(Assessment assessment) {
        return assessment.date.compareTo(date);
    }
}
