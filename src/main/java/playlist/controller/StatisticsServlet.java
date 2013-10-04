package playlist.controller;

import playlist.model.StatisticsDAO;

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
 *
 */
public class StatisticsServlet extends HttpServlet {

 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    List<StatisticsDAO> statistics = StatisticsDAO.getStatistics();

    request.setAttribute("statistics", statistics);
    getServletContext().getRequestDispatcher("/statistics.jsp").forward(request,response);

  }
}
