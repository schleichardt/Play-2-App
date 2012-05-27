package models;

import java.util.*;
import javax.persistence.*;

import javax.validation.Constraint;
import org.apache.commons.codec.net.BCodec;
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

    public User(final String name) {
        this.name = name;
        passwordSalt = BCrypt.gensalt();
    }

    public static Finder<Long,User> find = new Finder<Long,User>(Long.class, User.class);

    public void setPassword(final String password) {
        hashedPassword = hashPassword(password);
    }

    public boolean hasPassword(final String password) {
        final String inputHashed = hashPassword(password);
        return StringUtils.equals(inputHashed, hashedPassword);
    }

    private String hashPassword(final String password){
        return BCrypt.hashpw(password, passwordSalt);
    }
}