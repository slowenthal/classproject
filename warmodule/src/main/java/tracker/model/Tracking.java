package tracker.model;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 8:28 AM
 * To change this template use File | Settings | File Templates.
 */

public class Tracking extends CassandraData {

  private String package_id;
  private Date status_timestamp;
  private String location;
  private String notes;

  Tracking(Row row) {
      package_id = row.getString("package_id");
      location = row.getString("location");
      notes = row.getString("notes");
      status_timestamp = row.getDate("status_timestamp");
  }

  // Static finder method

  public static List<Tracking> findTrackingById(String packageId, ServletContext context) {

    String queryText = "SELECT * FROM tracker.trackpoints WHERE package_id = '" + packageId + "'";
    ResultSet results = getSession(context).execute(queryText);

    List<Tracking> trackings = new ArrayList<Tracking>();

    for (Row row : results) {
       trackings.add(new Tracking(row));
    }

    return trackings;
  }


  // Accessors

  public String getPackage_id() {
    return package_id;
  }

  public Date getStatus_timestamp() {
    return status_timestamp;
  }

  public String getLocation() {
    return location;
  }

  public String getNotes() {
    return notes;
  }
}
