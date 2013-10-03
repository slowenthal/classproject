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

    List<TracksDAO> songs = TracksDAO.listSongsByArtist("Elton John", context);

    assertEquals(50, songs.size());

    // Check the first track
    TracksDAO firstTrack = songs.get(0);

    assertEquals("Amoreena", firstTrack.getTrack());

  }

  public void testFindTrackById() throws Exception {

    TracksDAO track = TracksDAO.getTrackById(500, context);

    assertEquals(500, track.getTrack_id());
    assertEquals("Don't Stop 'Til You Get Enough", track.getTrack());

  }


}
