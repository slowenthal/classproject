package playlist.model;

import junit.framework.TestCase;
import playlist.testhelpers.MockServletContext;

import javax.servlet.ServletContext;
import java.util.List;

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

    User.addUser("steve","iforgot", context);

  }
  public void testGetUser() throws Exception {

    User user = User.getUser("steve", context);

    assertEquals("steve",user.getEmail());
    assertEquals("iforgot",user.getPassword());
    assertNotNull("UUID is null", user.getUserid());

  }

  public void testDeleteUser() throws Exception {
    User.deleteUser("steve", context);

    User user = User.getUser("steve", context);

    assertNull("user should be null", user);

  }


}
