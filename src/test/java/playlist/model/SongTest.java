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
 *
 */
public class SongTest extends TestCase {

  ServletContext context = new MockServletContext();

  public void testFindTracksByArtist() throws Exception {

    List<TracksDAO> songs = TracksDAO.listSongsByArtist("The Pioneers", context);

    assertEquals(44, songs.size());

    // Check the first track
    TracksDAO firstTrack = songs.get(0);

    assertEquals("Ali Button", firstTrack.getTrack());

  }

  public void testFindTrackById() throws Exception {

    TracksDAO track = TracksDAO.getTrackById("TRXQAEJ128F426C456", context);

    assertEquals("TRXQAEJ128F426C456", track.getTrack_id());
    assertEquals("Don't Fear The Reaper", track.getTrack());

  }


}
