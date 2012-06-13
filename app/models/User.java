package models;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

import javax.persistence.*;

import org.apache.commons.lang.StringUtils;
import play.db.ebean.*;
import play.data.validation.*;

import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
public class User extends Model {
    //TODO ebeans uses for Strings varchar(255) so the Strings should not be longer than that


    @Id
    @Constraints.Min(10)
    public Long id;

    /** pseudonym */
    @Constraints.Required
    public String name;

    /**
     * Password security will be archived by a user unique salt and the slow BCrypt algorithm.
     * See also:
     * http://www.codinghorror.com/blog/2012/04/speed-hashing.html
     * http://static.springsource.org/spring-security/site/docs/3.1.x/apidocs/org/springframework/security/crypto/bcrypt/BCrypt.html
     */
    private String hashedPassword;
    private String passwordSalt;


    public String foo = "foo attribute";
    public String foo() {
        return "foo method";
    }

    public User(final String name) {
        this.name = name;
    }

    public int compute(final int a) {
        return a * 2;
    }

    public void setPassword(final String password) {
        if(passwordSalt == null) {
            passwordSalt = BCrypt.gensalt();
        }
        hashedPassword = BCrypt.hashpw(password, passwordSalt);
    }

    public boolean hasPassword(final String password) {
        boolean hasPassword = false;
        if (isNotEmpty(password) && isNotEmpty(hashedPassword)) {
            final String inputHashed = BCrypt.hashpw(password, passwordSalt);
            hasPassword = inputHashed.equals(hashedPassword);
        }
        return hasPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "hashedPassword='" + hashedPassword + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                '}';
    }

    public String getXyz() {
        return "xyz called";
    }

    public static Finder<Long,User> find = new Finder<Long,User>(Long.class, User.class);
}
