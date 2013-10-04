package playlist.model;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 8:28 AM
 *
 */

public class TracksDAO extends CassandraData {

  // Hard Coded Genres for now

  private static final List<String> GENRES = Arrays.asList(
          "classic pop and rock",
          "classical",
          "dance and electronica",
          "folk",
          "hip-hop",
          "jazz and blues",
          "metal",
          "pop",
          "punk",
          "soul and reggae"
  );

  private String track_id;
  private String artist;
  private String track;
  private String genre;
  private int track_length_in_seconds;

  TracksDAO(Row row) {
    track_id = row.getString("track_id");
    artist = row.getString("artist");
    track = row.getString("track");
    genre = row.getString("genre");
    track_length_in_seconds = row.getInt("track_length_in_seconds");

  }

  public TracksDAO(String track_id, String artist, String track, String genre, int track_length_in_seconds) {
    this.track_id = track_id;
    this.artist = artist;
    this.track = track;
    this.genre = genre;
    this.track_length_in_seconds = track_length_in_seconds;
  }

  // Static finder method

  public static List<TracksDAO> listSongsByArtist(String artist) {

    String queryText = "SELECT * FROM track_by_artist WHERE artist = '" + artist.replace("'","''") + "'";
    ResultSet results = getSession().execute(queryText);

    List<TracksDAO> tracks = new ArrayList<TracksDAO>();

    for (Row row : results) {
      tracks.add(new TracksDAO(row));
    }

    return tracks;
  }

  public static List<TracksDAO> listSongsByGenre(String genre) {

    // TODO - How do we get the subsequent chunks of data?

    String queryText = "SELECT * FROM track_by_genre WHERE genre = '" + genre.replace("'","''") + "' LIMIT 200;";
    ResultSet results = getSession().execute(queryText);

    List<TracksDAO> tracks = new ArrayList<TracksDAO>();

    for (Row row : results) {
      tracks.add(new TracksDAO(row));
    }

    return tracks;
  }

  public static TracksDAO getTrackById(String track_id) {
    PreparedStatement preparedStatement = getSession().prepare("SELECT * FROM track_by_id WHERE track_id = ?");
    BoundStatement boundStatement = preparedStatement.bind(track_id);
    ResultSet resultSet = getSession().execute(boundStatement);

    return new TracksDAO(resultSet.one());
  }


  // Accessors

  public String getTrack_id() {
    return track_id;
  }

  public String getArtist() {
    return artist;
  }

  public String getTrack() {
    return track;
  }

  public String getGenre() {
    return genre;
  }

  public int getTrack_length_in_seconds() {
    return track_length_in_seconds;
  }
}
