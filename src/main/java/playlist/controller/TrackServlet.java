package playlist.controller;

import playlist.model.TracksDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * DataStax Academy Sample Application
 *
 * Copyright 2013 DataStax
 *
 */

public class TrackServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String button = request.getParameter("button");

    if (button != null && button.contentEquals("addTrack")) {

      //
      // Get the track_length_in_seconds and validate it's a good integer
      //

      int track_length_in_seconds;
      try {
        track_length_in_seconds = Integer.parseInt(request.getParameter("track_length_in_seconds"));
      } catch (NumberFormatException e) {
        request.setAttribute("error", "Invalid Track Length");
        getServletContext().getRequestDispatcher("/add_track.jsp").forward(request,response);
        return;

      }

      //
      // Get the remaining form fields
      //

      String track_id = request.getParameter("track_id");
      String artist =  request.getParameter("artist");
      String track_name = request.getParameter("track_name");
      String genre = request.getParameter("genre");
      String howmany = request.getParameter("howmany");

      //
      // Construct a new track object
      //

      TracksDAO newTrack = new TracksDAO(
              track_id,
              artist,
              track_name,
              genre,
              track_length_in_seconds
      );

      //
      // Add the new track to the database
      //

      newTrack.add();

      // Go to that artist to see the new track

      response.sendRedirect("tracks?artist=" + URLEncoder.encode(artist, "UTF-8") + "&howmany=" + howmany);

    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String artist = request.getParameter("artist");
    String genre = request.getParameter("genre");
    String howmany = request.getParameter("howmany");

    List<TracksDAO> tracks = null;
    if (artist != null && !artist.isEmpty()) {

      // Assume we're searching by artist
      tracks = TracksDAO.listSongsByArtist(artist);
    } else if (genre != null) {

      // Compute the limit - if the web sends "0" that means All
      int limit;

      // If what comes in is not a number or is null, default to 100,000
      try {
        limit = Integer.parseInt(howmany);
      } catch (NumberFormatException e) {
        limit = 100000;
      }

      // Assume we're searching by genre
      tracks = TracksDAO.listSongsByGenre(genre, limit);

    }

    request.setAttribute("artist", artist);
    request.setAttribute("genre", genre);
    request.setAttribute("tracks", tracks);
    request.setAttribute("howmany", howmany);
    getServletContext().getRequestDispatcher("/tracks.jsp").forward(request,response);

  }

}
