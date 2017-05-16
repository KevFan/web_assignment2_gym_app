package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class to model a Trainer. A Trainer has a list of members.
 * Created by kevin on 15/04/2017.
 */
@Entity
public class Trainer extends Model {
    public String name;
    public String email;
    public String password;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> memberList = new ArrayList<Member>();

    /**
     * Constructor for a Trainer object
     * @param name Name of the trainer
     * @param email Email of the trainer
     * @param password Password of the trainer
     */
    public Trainer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Find's the first trainer email in the database whose email matches the email parameter passed in
     * @param email
     * @return
     */
    public static Trainer findByEmail(String email) {
        return find("email", email).first();
    }

    /**
     * Checks the password passed in with the stored password for the trainer
     * @param password Password for the trainer's account
     * @return Boolean of whether the password matches or not
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
