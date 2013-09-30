package playlist.model;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 8:28 AM
 * To change this template use File | Settings | File Templates.
 */

public class ArtistsDAO extends CassandraData {

  private String package_id;
  private Date status_timestamp;
  private String location;
  private String notes;

  // Static finder method

  public static List<String> listArtistByLetter(String first_letter, ServletContext context) {

    String queryText = "SELECT * FROM playlist.artists_by_first_letter WHERE first_letter = '" + first_letter + "'";
    ResultSet results = getSession(context).execute(queryText);

    List<String> artists = new ArrayList<String>();

    for (Row row : results) {
       artists.add(row.getString("artist"));     // Lets use column 0 since there is only one column
    }

    return artists;
  }


  // Accessors

  public String getPackage_id() {
    return package_id;
  }

  public Date getStatus_timestamp() {
    return status_timestamp;
  }

  public String getLocation() {
    return location;
  }

  public String getNotes() {
    return notes;
  }
}
