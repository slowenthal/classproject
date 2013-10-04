package playlist.model;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 8:28 AM
 *
 */

public class ArtistsDAO extends CassandraData {

  // Static finder method

  public static List<String> listArtistByLetter(String first_letter) {

    String queryText = "SELECT * FROM artists_by_first_letter WHERE first_letter = '" + first_letter + "'";
    ResultSet results = getSession().execute(queryText);

    List<String> artists = new ArrayList<String>();

    for (Row row : results) {
       artists.add(row.getString("artist"));     // Lets use column 0 since there is only one column
    }

    return artists;
  }
}
