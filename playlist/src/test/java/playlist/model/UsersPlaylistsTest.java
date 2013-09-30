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
 * To change this template use File | Settings | File Templates.
 */
public class UsersPlaylistsTest extends TestCase {

  ServletContext context = new MockServletContext();


  public void testPersistPlayLists() throws Exception {

    UserDAO newUser = UserDAO.addUser("testuser", "iforgot", context);

    // Create a list of Playlists

    List<UsersPlaylistsDAO> playlists = new ArrayList<UsersPlaylistsDAO>();
    playlists.add(new UsersPlaylistsDAO("Energy Mix", "Pop"));
    playlists.add(new UsersPlaylistsDAO("Snooze Music", "Classical"));

    UsersPlaylistsDAO.persistPlayLists(newUser.getEmail(), playlists, context);

  }

  public void testGetPlayListNames() throws Exception {

    List<String> playlists = UserDAO.getUser("testuser", context).getPlaylists();

    assertEquals(2,playlists.size());
    assertEquals("Energy Mix", playlists.get(0));
    assertEquals("Snooze Music", playlists.get(1));

  }

  public void testGetPlayListWithGenre() throws Exception {

    UserDAO user = UserDAO.getUser("testuser", context);
    List<UsersPlaylistsDAO> playlists = UsersPlaylistsDAO.getPlayListWithGenre(user.getPlaylists_genre());

    assertEquals(2,playlists.size());
    assertEquals("Snooze Music", playlists.get(0).getPlaylist_name());
    assertEquals("Classical", playlists.get(0).getGenre());
    assertEquals("Energy Mix", playlists.get(1).getPlaylist_name());
  }

  public void testDeleteUser() throws Exception {
    UserDAO.deleteUser("testuser", context);
  }
}
