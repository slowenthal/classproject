package playlist.model;

import com.datastax.driver.core.Session;
import junit.framework.TestCase;
import playlist.exceptions.UserExistsException;
import playlist.exceptions.UserLoginException;
import playlist.testhelpers.MockServletContext;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 4:13 PM
 *
 */
public class UserTest extends TestCase {

  ServletContext context = new MockServletContext();

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    Session session  = CassandraData.getSession(context);
    session.execute("TRUNCATE users");
    session.execute("TRUNCATE playlist_tracks");

  }


  public void testInsertUser() throws Exception {

    UserDAO.addUser("steve", "iforgot", context);

  }
  public void testGetUser() throws Exception {

    UserDAO.addUser("steve", "iforgot", context);
    UserDAO user = UserDAO.getUser("steve", context);

    assertEquals("steve",user.getEmail());
    assertEquals("iforgot",user.getPassword());
    assertNotNull("UUID is null", user.getUserid());

    user.deleteUser(context);

  }

  public void testDeleteUser() throws Exception {

    UserDAO newUser = UserDAO.addUser("steve", "iforgot", context);

    newUser.deleteUser(context);

    UserDAO user = UserDAO.getUser("steve", context);

    assertNull("user should be null", user);

  }

  public void testAddSameUserTwice() throws Exception {

    UserDAO.addUser("steve", "pw1", context);
    UserDAO user = UserDAO.getUser("steve", context);
    assertEquals("pw1",user.getPassword());

    boolean thrown = false;
    try {
      UserDAO.addUser("steve", "pw2", context);
    } catch (UserExistsException e) {
      thrown = true;
    }

    assertTrue("UserExistsException not thrown", thrown);

    user = UserDAO.getUser("steve", context);
    assertEquals("pw1",user.getPassword());

    user.deleteUser(context);

  }

  public void testValidateLogin() throws Exception {

    UserDAO.addUser("steve", "pw1", context);
    UserDAO user = UserDAO.getUser("steve", context);
    assertEquals("pw1",user.getPassword());

    UserDAO loginUser = UserDAO.validateLogin("steve", "pw1", context);
    assertNotNull(loginUser);
    assertEquals(user.getUserid(), loginUser.getUserid());

    user.deleteUser(context);

  }

  public void testValidateBadPassword() throws Exception {

    UserDAO newUser = UserDAO.addUser("steve", "pw1", context);
    UserDAO user = UserDAO.getUser("steve", context);
    assertEquals("pw1",user.getPassword());

    boolean thrown = false;
    try {
      UserDAO.validateLogin("steve", "badpassword", context);
    } catch (UserLoginException e) {
          thrown = true;
    }

    assertTrue("exception not thrown for bad login", thrown);

    newUser.deleteUser(context);

  }

  public void testValidateBadEmail() throws Exception {

    UserDAO newUser = UserDAO.addUser("steve", "pw1", context);
    UserDAO user = UserDAO.getUser("steve", context);
    assertEquals("pw1",user.getPassword());

    boolean thrown = false;
    try {
      UserDAO.validateLogin("baduser", "pw1", context);
    } catch (UserLoginException e) {
      thrown = true;
    }

    assertTrue("exception not thrown for bad login", thrown);

    newUser.deleteUser(context);

  }

}
