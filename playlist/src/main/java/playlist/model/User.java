package playlist.model;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 8:28 AM
 * To change this template use File | Settings | File Templates.
 */

public class User extends CassandraData {

  private String email;
  private String password;
  private UUID userid;

  User (Row row) {
    email = row.getString("email");
    password = row.getString("password");
    userid = row.getUUID("user_id");
  }

  public static void addUser(String email, String password, ServletContext context) {

    UUID userId = UUID.randomUUID();

    String queryText = "INSERT INTO playlist.users (email, password, user_id) values ('"
            + email + "','"
            + password + "',"
            + userId + ")";

   getSession(context).execute(queryText);

  }

  public static void deleteUser(String email, ServletContext context) {
    String queryText = "DELETE FROM playlist.users where email = '"
            + email + "'";

    getSession(context).execute(queryText);

  }

  public static User getUser(String email, ServletContext context) {

    String queryText = "SELECT * FROM playlist.users where email = '"
            + email + "'";

    Row userRow = getSession(context).execute(queryText).one();

    if (userRow == null) {
      return null;
    }

    return new User(userRow);

  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public UUID getUserid() {
    return userid;
  }

}
