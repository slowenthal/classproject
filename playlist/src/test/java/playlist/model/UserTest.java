package playlist.model;

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
 * To change this template use File | Settings | File Templates.
 */
public class UserTest extends TestCase {

  ServletContext context = new MockServletContext();

  public void testInsertUser() throws Exception {

    UserDAO.addUser("steve", "iforgot", context);

  }
  public void testGetUser() throws Exception {

    UserDAO user = UserDAO.getUser("steve", context);

    assertEquals("steve",user.getEmail());
    assertEquals("iforgot",user.getPassword());
    assertNotNull("UUID is null", user.getUserid());

  }

  public void testDeleteUser() throws Exception {
    UserDAO.deleteUser("steve", context);

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

    UserDAO.deleteUser("steve", context);

  }

  public void testValidateLogin() throws Exception {

    UserDAO.addUser("steve", "pw1", context);
    UserDAO user = UserDAO.getUser("steve", context);
    assertEquals("pw1",user.getPassword());

    UserDAO loginUser = UserDAO.validateLogin("steve", "pw1", context);
    assertNotNull(loginUser);
    assertEquals(user.getUserid(), loginUser.getUserid());

    UserDAO.deleteUser("steve", context);

  }

  public void testValidateBadPassword() throws Exception {

    UserDAO.addUser("steve", "pw1", context);
    UserDAO user = UserDAO.getUser("steve", context);
    assertEquals("pw1",user.getPassword());

    UserDAO loginUser = null;
    boolean thrown = false;
    try {
      loginUser = UserDAO.validateLogin("steve", "badpassword", context);
    } catch (UserLoginException e) {
          thrown = true;
     }

    assertTrue("exception not thrown for bad login", thrown);

    UserDAO.deleteUser("steve", context);

  }

  public void testValidateBadEmail() throws Exception {

    UserDAO.addUser("steve", "pw1", context);
    UserDAO user = UserDAO.getUser("steve", context);
    assertEquals("pw1",user.getPassword());

    UserDAO loginUser = null;
    boolean thrown = false;
    try {
      loginUser = UserDAO.validateLogin("baduser", "pw1", context);
    } catch (UserLoginException e) {
      thrown = true;
    }

    assertTrue("exception not thrown for bad login", thrown);

    UserDAO.deleteUser("steve", context);

  }




}
