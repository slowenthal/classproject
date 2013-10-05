package playlist.controller;

import playlist.model.PlaylistDAO;
import playlist.model.StatisticsDAO;
import playlist.model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * DataStax Academy Sample Application
 *
 * Copyright 2013 DataStax
 *
 */

public class PlaylistsServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession httpSession = request.getSession(true);
    UserDAO user = (UserDAO) httpSession.getAttribute("user");

    // If we're not logged in, go to the login page
    if (user == null) {

      request.setAttribute("error", "Not Logged In");
      response.sendRedirect("login");
      return;

    }

    UserDAO userFromDB = UserDAO.getUser(user.getEmail());

    String button = request.getParameter("button");
    if (button != null && button.contentEquals("Add")) {
      String new_playlist_name = request.getParameter("new_playlist_name");
      if (new_playlist_name != null) {
        doAddPlaylist(userFromDB, new_playlist_name);
      }
    }

    request.setAttribute("email", userFromDB.getEmail());
    request.setAttribute("playlist_names", userFromDB.getPlaylist_names());
    getServletContext().getRequestDispatcher("/playlists.jsp").forward(request,response);

  }

  private void doAddPlaylist(UserDAO user, String playlistName) {

    StatisticsDAO.increment_counter("playlists");

    PlaylistDAO.createPlayList(user, playlistName);
  }

}
