package playlist.model;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.SimpleStatement;
import playlist.exceptions.UserExistsException;
import playlist.exceptions.UserLoginException;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 8:28 AM
 *
 */

public class UserDAO extends CassandraData {

  private String email;
  private String password;
  private UUID userid;
  private Set<String> playlist_names;

  UserDAO(Row row) {
    email = row.getString("email");
    password = row.getString("password");
    userid = row.getUUID("user_id");
    playlist_names = row.getSet("playlist_names",String.class);

    // If the size is 0 we get a useless Set, so lets add a real one
    if (playlist_names.size() == 0) {
       playlist_names = new TreeSet<String>();
    }
  }

  UserDAO(String email, String password, UUID userid) {
    this.userid = userid;
    this.password = password;
    this.email = email;
    this.playlist_names = new TreeSet<String>();
  }

  public static UserDAO addUser(String email, String password) throws UserExistsException {

    // TODO Should read and write a quorum for this because of the unique requirement
    // TODO or better should use a transaction

    UUID userId = UUID.randomUUID();

    if (getUser(email) != null) {
      throw new UserExistsException();
    }

    String queryText = "INSERT INTO users (email, password, user_id) values ('"
            + email + "','"
            + password + "',"
            + userId + ")";

    // We want to run this statement with CL quorum
    SimpleStatement simpleStatement = new SimpleStatement(queryText);
    simpleStatement.setConsistencyLevel(ConsistencyLevel.QUORUM);
    getSession().execute(simpleStatement);

    // Return the new user so the caller can get the userid
    return new UserDAO(email, password, userId);

  }

  public void deleteUser() {
    SimpleStatement simpleStatement = new SimpleStatement("DELETE FROM users where email = '"
            + this.email + "'");

    // Delete users with CL = Quorum
    simpleStatement.setConsistencyLevel(ConsistencyLevel.QUORUM);
    getSession().execute(simpleStatement);

  }

  public static UserDAO getUser(String email) {

    String queryText = "SELECT * FROM users where email = '"
            + email + "'";

    Row userRow = getSession().execute(queryText).one();

    if (userRow == null) {
      return null;
    }

    return new UserDAO(userRow);

  }

  public static UserDAO validateLogin(String email, String password) throws UserLoginException {

    UserDAO user = getUser(email);
    if (user == null || !user.password.contentEquals(password)) {
      throw new UserLoginException();
    }

    return user;
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

  public Set<String> getPlaylist_names() {
    return playlist_names;
  }


}
