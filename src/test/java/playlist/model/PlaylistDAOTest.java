package playlist.model;

import com.datastax.driver.core.Session;
import junit.framework.TestCase;
import playlist.testhelpers.MockServletContext;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.List;
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

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    Session session  = CassandraData.getSession(context);
    session.execute("TRUNCATE users");
    session.execute("TRUNCATE playlist_tracks");

  }

  public void testAddTrackToPlaylist() throws Exception {
    UserDAO user = UserDAO.addUser("testuser1","pw",context);
    PlaylistDAO newPlaylist = PlaylistDAO.createPlayList(user,"Playlist1",context);

    TracksDAO newTrack = new TracksDAO("1","Artist1","Track1", "rock", 20);
    PlaylistDAO.Track track1 = new PlaylistDAO.Track(newTrack);

    newPlaylist.addTracksToPlaylist(Arrays.asList(track1), context);

    PlaylistDAO playlist = PlaylistDAO.getPlaylistForUser(user, "Playlist1", context);
    List<PlaylistDAO.Track> tracksList = playlist.getTrackList();

    assertEquals(1, tracksList.size());

    newTrack = new TracksDAO("2","Artist2","Track2", "rock", 10);
    PlaylistDAO.Track track2 = new PlaylistDAO.Track(newTrack);
    playlist.addTracksToPlaylist(Arrays.asList(track2), context);
    playlist = PlaylistDAO.getPlaylistForUser(user, "Playlist1", context);
    tracksList = playlist.getTrackList();

    assertEquals(2, tracksList.size());

    assertEquals(30, playlist.getPlaylist_length_in_seconds());

    playlist.deletePlayList(context);
    user.deleteUser(context);


  }

  public void testDeleteTrackFromPlaylist() throws Exception {
    UserDAO user = UserDAO.addUser("testuser1","pw",context);
    PlaylistDAO newPlaylist = PlaylistDAO.createPlayList(user,"Playlist1",context);

    TracksDAO newTrack = new TracksDAO("1","Artist1","Track1", "rock", 20);
    PlaylistDAO.Track track1 = new PlaylistDAO.Track(newTrack);

    newPlaylist.addTracksToPlaylist(Arrays.asList(track1), context);

    PlaylistDAO playlist = PlaylistDAO.getPlaylistForUser(user, "Playlist1", context);
    List<PlaylistDAO.Track> tracksList = playlist.getTrackList();

    assertEquals(1, tracksList.size());
    assertEquals(20, playlist.getPlaylist_length_in_seconds());

    playlist.deleteTrackFromPlaylist(1, context);
    assertEquals(0, playlist.getPlaylist_length_in_seconds());

    playlist.deletePlayList(context);
    user.deleteUser(context);

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
    PlaylistDAO pl = PlaylistDAO.createPlayList(user, "Bob's favorites", context);

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
