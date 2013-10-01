package playlist.model;

import junit.framework.TestCase;
import playlist.testhelpers.MockServletContext;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/29/13
 * Time: 7:49 AM
 *
 */

public class UsersPlaylistsTest extends TestCase {

  ServletContext context = new MockServletContext();



  private void addUserAndPlaylist(String email) throws Exception {

    UserDAO newUser = UserDAO.addUser(email, "iforgot", context);

    // Create a list of Playlists

    List<UsersPlaylistsDAO> playlists = new ArrayList<UsersPlaylistsDAO>();
    playlists.add(new UsersPlaylistsDAO("Energy Mix", "Pop"));
    playlists.add(new UsersPlaylistsDAO("Snooze Music", "Classical"));

    UsersPlaylistsDAO.persistPlayLists(newUser.getEmail(), playlists, context);

  }

  public void testGetPlayListNames() throws Exception {

    addUserAndPlaylist("testUser");

    List<String> playlists = UserDAO.getUser("testuser", context).getPlaylists();

    assertEquals(2,playlists.size());
    assertEquals("Energy Mix", playlists.get(0));
    assertEquals("Snooze Music", playlists.get(1));

    UserDAO.deleteUser("testuser", context);

  }

  public void testGetPlayListWithGenre() throws Exception {

    addUserAndPlaylist("testuser");

    UserDAO user = UserDAO.getUser("testuser", context);
    List<UsersPlaylistsDAO> playlists = UsersPlaylistsDAO.getPlayListWithGenre(user.getPlaylists_genre());

    assertEquals(2,playlists.size());
    assertEquals("Snooze Music", playlists.get(0).getPlaylist_name());
    assertEquals("Classical", playlists.get(0).getGenre());
    assertEquals("Energy Mix", playlists.get(1).getPlaylist_name());

    UserDAO.deleteUser("testuser", context);

  }


}
