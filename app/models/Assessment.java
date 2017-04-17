package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kevin on 04/04/2017.
 */

@Entity
public class Assessment extends Model {
    public double weight;
    public double chest;
    public double thigh;
    public double upperArm;
    public double waist;
    public double hips;
    public String comment;
    public Date date;

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
}
