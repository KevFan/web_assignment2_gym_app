package models;

import java.util.ArrayList;

/**
 * Created by kevin on 04/04/2017.
 */
public class AssessmentList {
    public String name;
    public ArrayList<Assessment> assessments = new ArrayList<Assessment>();

    public AssessmentList(String name) {
        this.name = name;
    }
}
