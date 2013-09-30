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

public class TracksDAO extends CassandraData {

  private int track_id;
  private String artist;
  private String track;

  TracksDAO(Row row) {
      track_id = row.getInt("track_id");
      artist = row.getString("artist");
      track = row.getString("track");
  }

  // Static finder method

  public static List<TracksDAO> listSongsByArtist(String artist, ServletContext context) {

    String queryText = "SELECT * FROM playlist.track_by_artist WHERE artist = '" + artist.replace("'","''") + "'";
    ResultSet results = getSession(context).execute(queryText);

    List<TracksDAO> tracks = new ArrayList<TracksDAO>();

    for (Row row : results) {
       tracks.add(new TracksDAO(row));
    }

    return tracks;
  }


  // Accessors

  public int getTrack_id() {
    return track_id;
  }

  public String getArtist() {
    return artist;
  }

  public String getTrack() {
    return track;
  }
}
