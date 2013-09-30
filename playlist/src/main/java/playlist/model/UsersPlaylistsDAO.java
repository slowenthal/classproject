package playlist.model;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import playlist.exceptions.UserExistsException;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 8:28 AM
 * To change this template use File | Settings | File Templates.
 */

public class UsersPlaylistsDAO extends CassandraData {

  private String playlist_name;
  private String genre;

  UsersPlaylistsDAO(String playlist_name, String genre) {
    this.playlist_name = playlist_name;
    this.genre = genre;
  }

  public static void persistPlayLists(String email, List<UsersPlaylistsDAO> playlists, ServletContext context) throws UserExistsException {

    //TODO do we want to use an Atomic Block Here?

    // First Create a Map for the playlist_genres column
    // And a list of playlists as a String in order

    Map<String, String> playlist_genres = new HashMap<String, String>();
    List<String> playlistStrings = new ArrayList<String>();

    for (UsersPlaylistsDAO p : playlists) {
      playlist_genres.put(p.getPlaylist_name(), p.getGenre());
      playlistStrings.add(p.getPlaylist_name());
    }

    // Insert the items into the user table with update statements

    PreparedStatement updatePlaylistPS = getSession(context).prepare("UPDATE playlist.users SET playlists = ?, playlists_genre = ? WHERE email = ?");
    BoundStatement updatePlaylistBS = updatePlaylistPS.bind(playlistStrings, playlist_genres, email);
    getSession(context).execute(updatePlaylistBS);

  }


  public static List<UsersPlaylistsDAO> getPlayListWithGenre(Map<String, String> playlists_genre) {

    // Build a list of the playlists sorted by Genre
    List<UsersPlaylistsDAO> playlists_genres = new ArrayList<UsersPlaylistsDAO>();
    for (String key : playlists_genre.keySet()) {
      playlists_genres.add(new UsersPlaylistsDAO(key, playlists_genre.get(key)));
    }

    // Sort the Playlists By Genre
    sortByGenre(playlists_genres);

    return  playlists_genres;

  }


  // Helper function to sort the List of Playlists by Genre

  private static void sortByGenre(List<UsersPlaylistsDAO> playlists) {
    Collections.sort(playlists, new Comparator<UsersPlaylistsDAO>() {

      // Anonymous inner class to compare the genre of one playlist to the genre of another.
      @Override
      public int compare(UsersPlaylistsDAO playlist, UsersPlaylistsDAO playlist2) {
        return playlist.getGenre().compareTo(playlist2.getGenre());
      }
    });
  }

  // Getters and Setters

  public String getPlaylist_name() {
    return playlist_name;
  }

  public String getGenre() {
    return genre;
  }

}
