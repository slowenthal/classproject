package playlist.controller;

import playlist.model.CassandraInfo;
import playlist.model.StatisticsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 10/3/13
 * Time: 7:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    StatisticsDAO.increment_counter("page hits: home", getServletContext());

    String javaVersion = System.getProperty("java.version");
    CassandraInfo cassandraInfo = new CassandraInfo(getServletContext());

    request.setAttribute("java_version", javaVersion);
    request.setAttribute("cassandra_info", cassandraInfo);
    getServletContext().getRequestDispatcher("/home.jsp").forward(request,response);



  }




}
