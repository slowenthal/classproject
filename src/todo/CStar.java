package todo;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/18/13
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class CStar {


  public static final String C_STAR_SESSION = "CStarSession";

  public static Session getSession(ServletContext context) {
    Session session = (Session) context.getAttribute(C_STAR_SESSION);

    if (session == null) {
      Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
      session = cluster.connect("todo");
      context.setAttribute(C_STAR_SESSION, session);

    }

    return session;

  }

}
