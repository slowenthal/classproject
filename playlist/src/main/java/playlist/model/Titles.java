package playlist.model;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 8:28 AM
 * To change this template use File | Settings | File Templates.
 */

public class Titles extends CassandraData {

  private int title_id;
  private String artist;
  private String title;

  Titles(Row row) {
      title_id = row.getInt("title_id");
      artist = row.getString("artist");
      title = row.getString("title");
  }

  // Static finder method

  public static List<Titles> listSongsByArtist(String artist, ServletContext context) {

    String queryText = "SELECT * FROM playlist.title_by_artist WHERE artist = '" + artist.replace("'","''") + "'";
    ResultSet results = getSession(context).execute(queryText);

    List<Titles> titles = new ArrayList<Titles>();

    for (Row row : results) {
       titles.add(new Titles(row));
    }

    return titles;
  }


  // Accessors

  public int getTitle_id() {
    return title_id;
  }

  public String getArtist() {
    return artist;
  }

  public String getTitle() {
    return title;
  }
}
