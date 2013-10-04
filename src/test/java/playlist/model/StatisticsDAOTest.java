package playlist.model;

import com.datastax.driver.core.Session;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 10/4/13
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class StatisticsDAOTest extends TestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    Session session  = CassandraData.getSession();
    session.execute("TRUNCATE statistics");

  }


  public void testIncrement_counter() throws Exception {
      StatisticsDAO.increment_counter("TestCounter");
      List<StatisticsDAO> stats = StatisticsDAO.getStatistics();
      assertEquals(1, stats.get(0).getCounter_value());

    StatisticsDAO.increment_counter("TestCounter");
    stats = StatisticsDAO.getStatistics();
    assertEquals(2, stats.get(0).getCounter_value());

  }

  public void testDecrement_counter() throws Exception {
    StatisticsDAO.increment_counter("TestCounter1");
    List<StatisticsDAO> stats = StatisticsDAO.getStatistics();
    assertEquals(1, stats.get(0).getCounter_value());

    StatisticsDAO.decrement_counter("TestCounter1");
    stats = StatisticsDAO.getStatistics();
    assertEquals(0, stats.get(0).getCounter_value());
  }

}
