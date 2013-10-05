package playlist.model;

import junit.framework.TestCase;
import java.util.List;

/**
 * DataStax Academy Sample Application
 *
 * Copyright 2013 DataStax
 *
 */

public class SongTest extends TestCase {

  public void testFindTracksByArtist() throws Exception {

    List<TracksDAO> songs = TracksDAO.listSongsByArtist("The Pioneers");

    assertEquals(44, songs.size());

    // Check the first track
    TracksDAO firstTrack = songs.get(0);

    assertEquals("Ali Button", firstTrack.getTrack());

  }

  public void testFindTracksByGenre() throws Exception {

    List<TracksDAO> songs = TracksDAO.listSongsByGenre("classical");

    assertEquals(200, songs.size());

    // Check the first track
    TracksDAO firstTrack = songs.get(0);

    assertEquals("Concerto grosso No. 10 en Ré Mineur_ Op. 6: Air lento", firstTrack.getTrack());

  }

  public void testFindTrackById() throws Exception {

    TracksDAO track = TracksDAO.getTrackById("TRXQAEJ128F426C456");

    assertEquals("TRXQAEJ128F426C456", track.getTrack_id());
    assertEquals("Don't Fear The Reaper", track.getTrack());

  }

}
