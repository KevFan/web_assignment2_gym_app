package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 15/04/2017.
 */
@Entity
public class Trainer extends Model {
    public String name;
    public String email;
    public String password;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> memberList = new ArrayList<Member>();

    public Trainer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
;
    }

    public static Trainer findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
