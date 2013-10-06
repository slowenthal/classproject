package playlist.model;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * DataStax Academy Sample Application
 *
 * Copyright 2013 DataStax
 *
 */


public class TracksDAO extends CassandraData {

  // Hard Coded Genres for now

  private final String track_id;
  private final String artist;
  private final String track;
  private final String genre;
  private final int track_length_in_seconds;

  private TracksDAO(Row row) {
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

    List<TracksDAO> tracks = new ArrayList<>();

    for (Row row : results) {
      tracks.add(new TracksDAO(row));
    }

    return tracks;
  }

  public static List<TracksDAO> listSongsByGenre(String genre) {

    // TODO - How do we get the subsequent chunks of data?

    String queryText = "SELECT * FROM track_by_genre WHERE genre = '" + genre.replace("'","''") + "' LIMIT 200;";
    ResultSet results = getSession().execute(queryText);

    List<TracksDAO> tracks = new ArrayList<>();

    for (Row row : results) {
      tracks.add(new TracksDAO(row));
    }

    return tracks;
  }

  public static TracksDAO getTrackById(String track_id) {
    PreparedStatement preparedStatement = getSession().prepare("SELECT * FROM track_by_id WHERE track_id = ?");
    BoundStatement boundStatement = preparedStatement.bind(track_id);
    ResultSet resultSet = getSession().execute(boundStatement);

    // Return null if there is no track found

    if (resultSet.isExhausted()) {
      return null;
    }

    return new TracksDAO(resultSet.one());
  }

  /**
   * Add this track to the database
   */

  public void add() {

    // Compute the first letter of the artists name for the artists_by_first_letter table
    String artist_first_letter = this.artist.substring(0,1).toUpperCase();

    PreparedStatement preparedStatement =
            getSession().prepare("INSERT INTO artists_by_first_letter (first_letter, artist) VALUES (?, ?)");
    BoundStatement boundStatement = preparedStatement.bind(artist_first_letter, this.artist);
    getSession().execute(boundStatement);

    preparedStatement = getSession().prepare("INSERT INTO track_by_id (genre, track_id, artist, track, track_length_in_seconds) VALUES (?, ?, ?, ?, ?)");
    boundStatement = preparedStatement.bind(this.genre, this.track_id, this.artist, this.track, this.track_length_in_seconds);
    getSession().execute(boundStatement);

    preparedStatement = getSession().prepare("INSERT INTO track_by_genre (genre, track_id, artist, track, track_length_in_seconds) VALUES (?, ?, ?, ?, ?)");
    boundStatement = preparedStatement.bind(this.genre, this.track_id, this.artist, this.track, this.track_length_in_seconds);
    getSession().execute(boundStatement);

    preparedStatement = getSession().prepare("INSERT INTO track_by_artist (genre, track_id, artist, track, track_length_in_seconds) VALUES (?, ?, ?, ?, ?)");
    boundStatement = preparedStatement.bind(this.genre, this.track_id, this.artist, this.track, this.track_length_in_seconds);
    getSession().execute(boundStatement);

  }


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

  public String getTrack_length_in_MS() {
    return secondsToMS(track_length_in_seconds);
  }

}
