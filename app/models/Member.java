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
        if (gender.equals("") || gender.toUpperCase().charAt(0) != 'M' || gender.toUpperCase().charAt(0) != 'F'){
            this.gender = "Unspecified";
        } else if (gender.toUpperCase().charAt(0) == 'M') {
            this.gender = "Male";
        } else if (gender.toUpperCase().charAt(0) == 'F') {
            this.gender = "Female";
        }
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

}
