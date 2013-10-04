package playlist.model;

import com.datastax.driver.core.Session;
import junit.framework.TestCase;
import playlist.testhelpers.MockServletContext;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 10/4/13
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class StatisticsDAOTest extends TestCase {

  ServletContext context = new MockServletContext();

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    Session session  = CassandraData.getSession(context);
    session.execute("TRUNCATE statistics");

  }


  public void testIncrement_counter() throws Exception {
      StatisticsDAO.increment_counter("TestCounter", context);
      List<StatisticsDAO> stats = StatisticsDAO.getStatistics(context);
      assertEquals(1, stats.get(0).getCounter_value());

    StatisticsDAO.increment_counter("TestCounter", context);
    stats = StatisticsDAO.getStatistics(context);
    assertEquals(2, stats.get(0).getCounter_value());

  }

  public void testDecrement_counter() throws Exception {
    StatisticsDAO.increment_counter("TestCounter1", context);
    List<StatisticsDAO> stats = StatisticsDAO.getStatistics(context);
    assertEquals(1, stats.get(0).getCounter_value());

    StatisticsDAO.decrement_counter("TestCounter1", context);
    stats = StatisticsDAO.getStatistics(context);
    assertEquals(0, stats.get(0).getCounter_value());
  }

}
