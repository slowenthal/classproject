package playlist.model;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/18/13
 * Time: 10:01 PM
 *
 */
public class CassandraData {


  private static Session cassandraSesson = null;

  CassandraData () {
    // Do nothing
  }

  public static Session getSession() {

    if (cassandraSesson == null) {
      cassandraSesson = createSession();
    }

    return cassandraSesson;

  }

  protected static Session createSession() {
    Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
    return cluster.connect("playlist");
  }

  protected static String secondsToMS (int seconds) {
    return (seconds / 60) + ":" + ((seconds % 60 < 10 ? "0" : "") ) + (seconds % 60);

  }


}
