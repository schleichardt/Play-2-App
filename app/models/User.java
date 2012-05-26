package models;

import java.util.*;
import javax.persistence.*;

import javax.validation.Constraint;
import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class User extends Model {
    @Id
    @Constraints.Min(10)
    public Long id;

    /** pseudonym */
    @Constraints.Required
    public String name;

    public User(String name) {
        this.name = name;
    }

    public static Finder<Long,User> find = new Finder<Long,User>(Long.class, User.class);
}
