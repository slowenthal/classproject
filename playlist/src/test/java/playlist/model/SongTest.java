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
public class SongTest extends TestCase {

  ServletContext context = new MockServletContext();

  public void testFindArtistsStartingWithA() throws Exception {

    List<Titles> songs = Titles.listSongsByArtist("Elton John", context);

    assertEquals(21, songs.size());

    // Check the first title
    Titles firstTitle = songs.get(0);

    assertEquals("Bennie And The Jets", firstTitle.getTitle());

  }


}
