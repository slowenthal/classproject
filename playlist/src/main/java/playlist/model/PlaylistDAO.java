package playlist.model;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 8:28 AM
 *
 */

public class PlaylistDAO extends CassandraData {

  private UUID user_id;
  private String playlist_name;
  private String email;
  private int playlist_length_in_seconds;
  private List<Track> trackList;

  public PlaylistDAO(UserDAO user, String playlist_name) {

    // Simple constructor to create an empty playlist
    this.user_id = user.getUserid();
    this.email = user.getEmail();
    this.playlist_name = playlist_name;
    playlist_length_in_seconds = 0;
    trackList = new ArrayList<Track>();

  }

  PlaylistDAO(UserDAO user, ResultSet resultSet) {

    // Call the simple constructor with the playlist's user_id and playlist_name to create an empty playlist
    this(user,
         resultSet.one().getString("playlist_name"));

    // Add the tracks to the playlist object
    for (Row row : resultSet)  {
      trackList.add(new Track(row));

      // Pre-aggregate the playlist length in seconds;
      playlist_length_in_seconds += row.getInt("track_length_in_seconds");
    }

  }

  public static class Track {

    private String track_name;
    private String artist;
    private int track_length_in_seconds;
    private Integer ordinal;

    public Track(String track_name, String artist, int track_length_in_seconds) {
      this.track_name = track_name;
      this.artist = artist;
      this.track_length_in_seconds = track_length_in_seconds;

      this.ordinal = null;  // A new track created this way has no order - it gets this when we persist it. There is no getter or setter.
    }

    public Track (Row row) {
      this.track_name = row.getString("track_name");
      this.artist = row.getString("artist");
      this.track_length_in_seconds = row.getInt("track_length_in_seconds");
      this.ordinal = row.getInt("ordinal");
    }

    public String getTrack_name() {
      return track_name;
    }

    public String getArtist() {
      return artist;
    }

    public int getTrack_length_in_seconds() {
      return track_length_in_seconds;
    }
  }

  // Static finder method

  public static PlaylistDAO getPlaylistForUser(UserDAO user, String playlist_name, ServletContext context) {

    PreparedStatement statement = getSession(context).prepare("SELECT user_id, playlist_name, artist, track_name " +
            "FROM playlist_tracks WHERE user_id = ? and playlist_name = ?");

    BoundStatement boundStatement = statement.bind(user.getUserid(), playlist_name);
    ResultSet results = getSession(context).execute(boundStatement);

    return new PlaylistDAO(user, results);
  }

  public void addTrackToEndOfPlaylist (Track newTrack, ServletContext context) throws Exception {

    // set the ordinal of the track and add it to the playlist object
    trackList.add(newTrack);

    // Create a list of one track, and add it to the playlist
    addTracksToPlaylist(Arrays.asList(newTrack), context);

  }

  public static void deleteTrackFromPlaylist(PlaylistDAO playlist, int ordinalToDelete, ServletContext context) {

    playlist.trackList.remove(ordinalToDelete);

    PreparedStatement ps = getSession(context).prepare("DELETE from playlist_tracks where user_id = ? and playlist_name = ? and ordinal = ?");
    BoundStatement bs = ps.bind(playlist.getUser_id(), playlist.getPlaylist_name(), ordinalToDelete);
    getSession(context).execute(bs);

   }


  public void deletePlayList(ServletContext context) {

    // Change single quotes to a pair of single quotes for escaping into the database
    String fixed_playlist_name = this.playlist_name.replace("'","''");

    PreparedStatement preparedStatement = getSession(context).prepare("BEGIN BATCH " +
            "UPDATE users set playlist_names = playlist_names - {'" + fixed_playlist_name + "'} WHERE email = ? " +
            "DELETE FROM playlist_tracks WHERE user_id = ? and playlist_name = ? " +
            "APPLY BATCH;");

    BoundStatement bs = preparedStatement.bind(this.email, this.user_id, this.playlist_name);

    getSession(context).execute(bs);

  }

  public static PlaylistDAO createPlayList(UserDAO user, String playlist_name, ServletContext context) {


    // Change single quotes to a pair of single quotes for escaping into the database
    String fixed_playlist_name = playlist_name.replace("'","''");

    PreparedStatement preparedStatement = getSession(context).prepare(
            "UPDATE users set playlist_names = playlist_names + {'" + fixed_playlist_name +"'} WHERE email = ?");
    BoundStatement bs = preparedStatement.bind(user.getEmail());
    getSession(context).execute(bs);

    return new PlaylistDAO(user,playlist_name);

  }


  private void deletePlaylistTracks(ServletContext context) {

    // Delete a whole playlist

    PreparedStatement ps = getSession(context).prepare("DELETE from playlist_tracks where user_id = ? and playlist_name = ?");
    BoundStatement bs = ps.bind(this.user_id, this.playlist_name);
    getSession(context).execute(bs);

  }

  public void rewritePlaylist(ServletContext context) throws Exception {

    // First delete the whole playlist
    deletePlaylistTracks(context);

    // Now insert all of the track, but first, reset all of the ordinals
    List<Track> tracklist = this.trackList;

    // remove the tracks from the playlist, so we can add them back and renumber them
    this.trackList = new ArrayList<Track>();

    // Add them back
    addTracksToPlaylist(tracklist, context);

  }

  private void addTracksToPlaylist(List<Track> newTracks, ServletContext context) throws Exception {

    // Prepare an insert statement
    PreparedStatement statement = getSession(context).prepare(
            "INSERT into playlist_tracks" +
                    " (user_id, playlist_name, ordinal, artist, track_name, track_length_in_seconds) " +
                    "VALUES (?, ?, ?, ?, ?)"
    );
    BoundStatement boundStatement = statement.bind();

    int newNumber = getTrackList().size();
    for (Track track : newTracks) {
      track.ordinal = newNumber ++;

      // Let's use named parameters this time
      boundStatement.setUUID("user_id", getUser_id());
      boundStatement.setString("playlist_name", getPlaylist_name());
      boundStatement.setInt("ordinal", track.ordinal);
      boundStatement.setString("track_name", track.getTrack_name());
      boundStatement.setString("artist", track.getArtist());
      boundStatement.setInt("track_length_in_seconds", track.getTrack_length_in_seconds());

      getSession(context).execute(boundStatement);
    }
  }

  public UUID getUser_id() {
    return user_id;
  }

  public String getPlaylist_name() {
    return playlist_name;
  }

  public List<Track> getTrackList() {
    return trackList;
  }

  public int getPlaylist_length_in_seconds() {
    return playlist_length_in_seconds;
  }

  public String getEmail() {
    return email;
  }
}
