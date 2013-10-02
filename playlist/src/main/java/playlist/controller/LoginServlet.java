package playlist.controller;

import playlist.exceptions.UserExistsException;
import playlist.exceptions.UserLoginException;
import playlist.model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/19/13
 * Time: 5:22 PM
 *
 */
public class LoginServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    String button = request.getParameter("button");
    button = button == null ? "" : button;

    if (button.contentEquals("login")) {
      doLogin(request, response);
    } else if (button.contentEquals("newAccount")) {
      doCreateUser(request, response);
    } else if (button.contentEquals("logout")) {
      doLogout(request, response);
    }
    else
    {
      getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
      return;
    }
  }


  private void doLogin  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if (email.isEmpty()) {
      request.setAttribute("error", "Email Can Not Be Blank");
      getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
      return;

    }

    try {
      UserDAO user = UserDAO.validateLogin(email, password, getServletContext());
      HttpSession httpSession = request.getSession(true);
      httpSession.setAttribute("user", user);

    } catch (UserLoginException e) {

      // Go back to the user screen with an error

      request.setAttribute("error", "Email or Password is Invalid");
      getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
      return;
    }

    getServletContext().getRequestDispatcher("/playlists").forward(request, response);

  }


  private void doCreateUser  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if (email.isEmpty()) {
      request.setAttribute("error", "Email Can Not Be Blank");
      getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
      return;
    }

    HttpSession httpSession = request.getSession(true);

    // Add the user.  If it's successful, create a login session for it
    try {

      UserDAO newUser = UserDAO.addUser(email, password, getServletContext());


      // Create the user's login session so this application recognizes the user as having logged in
      httpSession.setAttribute("user", newUser);

    } catch (UserExistsException e) {

      // Go back to the user screen with an error

      request.setAttribute("error", "User Already Exists");
      getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
      return;

    }

    getServletContext().getRequestDispatcher("/playlists").forward(request, response);

  }

  private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session != null) {
      session.setAttribute("user", null);
    }

    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

  }


}
