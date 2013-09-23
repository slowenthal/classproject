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
public class ArtistTest extends TestCase {

  ServletContext context = new MockServletContext();

  public void testFindArtistsStartingWithA() throws Exception {

    List<String> artists = Artists.listArtistByLetter("A", context);

    assertEquals(175, artists.size());

    // Check the first artist
    String firstArtist = artists.get(0);

    assertEquals("A Day To Remember", firstArtist);

  }

  public void testFindArtistsStartingWithInvalidLetter() throws Exception {

    List<String> artists = Artists.listArtistByLetter("=", context);

    assertEquals(0, artists.size());

  }

}
