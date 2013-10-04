package playlist.model;

import junit.framework.TestCase;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 4:13 PM
 *
 */

public class ArtistTest extends TestCase {

  public void testFindArtistsStartingWithA() throws Exception {

    List<String> artists = ArtistsDAO.listArtistByLetter("A");

    assertEquals(245, artists.size());

    // Check the first artist
    String firstArtist = artists.get(0);

    assertEquals("A Certain Ratio", firstArtist);

  }

  public void testFindArtistsStartingWithInvalidLetter() throws Exception {

    List<String> artists = ArtistsDAO.listArtistByLetter("=");

    assertEquals(0, artists.size());

  }

}
