package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
/**
 * Created by kevin on 04/04/2017.
 */

@Entity
public class AssessmentList extends Model {
    public String name;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    public AssessmentList(String name) {
        this.name = name;
    }
}
