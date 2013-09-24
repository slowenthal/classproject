package playlist.controller;

import playlist.exceptions.UserExistsException;
import playlist.exceptions.UserLoginException;
import playlist.model.Titles;
import playlist.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/19/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    String button = request.getParameter("button");
    button = button == null ? "" : button;


    if (button.contentEquals("Login")) {
      doLogin(request, response);
    } else if (button.contentEquals("I Don't Have an Account")) {
      doCreateUser(request,response);
    } else {
      getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
    }

  }


  private void doLogin  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if (email.isEmpty()) {
      request.setAttribute("error", "Email Can Not Be Blank");
      getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

    }

    try {
      User user = User.validateLogin(email,password,getServletContext());
    } catch (UserLoginException e) {

      // Go back to the user screen with an error

      request.setAttribute("error", "Email or Password is Invalid");
      getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

    }

    getServletContext().getRequestDispatcher("/artists").forward(request, response);

  }


  private void doCreateUser  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if (email.isEmpty()) {
      request.setAttribute("error", "Email Can Not Be Blank");
      getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

    }

    try {
      User.addUser(email,password,getServletContext());
    } catch (UserExistsException e) {

      // Go back to the user screen with an error

      request.setAttribute("error", "User Already Exists");
      getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

    }

    getServletContext().getRequestDispatcher("/artists").forward(request, response);

  }


}
