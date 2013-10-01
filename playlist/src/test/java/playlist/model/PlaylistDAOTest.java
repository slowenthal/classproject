package playlist.model;

import junit.framework.TestCase;
import playlist.testhelpers.MockServletContext;

import javax.servlet.ServletContext;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 10/1/13
 * Time: 10:26 AM
 *
 */


public class PlaylistDAOTest extends TestCase {

  ServletContext context = new MockServletContext();



  public void testGetPlaylistForUser() throws Exception {

  }

  public void testAddTrackToEndOfPlaylist() throws Exception {

  }

  public void testDeleteTrackFromPlaylist() throws Exception {

  }

  public void testDeletePlayList() throws Exception {

    UserDAO user = UserDAO.addUser("testuser1","pw",context);
    PlaylistDAO pl = PlaylistDAO.createPlayList(user,"Bob's favorites",context);

    assertEquals("testuser1",pl.getEmail() );
    assertNotNull(pl.getUser_id());

    UserDAO testUser = UserDAO.getUser("testuser1",context);
    Set<String> playlists = testUser.getPlaylist_names();

    assertTrue("playlist exists", playlists.contains("Bob's favorites"));

    pl.deletePlayList(context);
    testUser = UserDAO.getUser("testuser1", context);
    playlists = testUser.getPlaylist_names();

    assertFalse("playlist exists", playlists.contains("Bob's favorites"));

    user.deleteUser(context);

  }

  public void testAddPlayList() throws Exception {
    UserDAO user = UserDAO.addUser("testuser1","pw",context);
    PlaylistDAO pl = PlaylistDAO.createPlayList(user,"Bob's favorites",context);

    assertEquals("testuser1",pl.getEmail() );
    assertNotNull(pl.getUser_id());

    UserDAO testUser = UserDAO.getUser("testuser1",context);
    Set<String> playlists = testUser.getPlaylist_names();

    assertTrue("playlist exists", playlists.contains("Bob's favorites"));

    user.deleteUser(context);

  }

  public void testRewritePlaylist() throws Exception {

  }


}
