package tracker.model;

import junit.framework.TestCase;
import tracker.testhelpers.MockServletContext;

import javax.servlet.ServletContext;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrackingTest extends TestCase {

  ServletContext context = new MockServletContext();

  public void testFindTrackingByValidId() throws Exception {

    List<Tracking> trackingList = Tracking.findTrackingById("DH0895622326752", context);

    assertEquals(8, trackingList.size());

  }

  public void testFindTrackingByInvalidId() throws Exception {
    List<Tracking> trackingList = Tracking.findTrackingById("NOT_VALID", context);

    assertEquals(0, trackingList.size());

  }
}
