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

public class ArtistTest extends TestCase {

  ServletContext context = new MockServletContext();

  public void testFindArtistsStartingWithA() throws Exception {

    List<String> artists = ArtistsDAO.listArtistByLetter("A", context);

    assertEquals(885, artists.size());

    // Check the first artist
    String firstArtist = artists.get(0);

    assertEquals("A Bay Bay", firstArtist);

  }

  public void testFindArtistsStartingWithInvalidLetter() throws Exception {

    List<String> artists = ArtistsDAO.listArtistByLetter("=", context);

    assertEquals(0, artists.size());

  }

}
