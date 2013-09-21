package tracker.versioncheck;


import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/21/13
 * Time: 8:44 AM
 *
 *  Tests to check that we are running on the correct version of Java
 *
 */

public class JavaVersionTest extends TestCase {

  public static final String MINIMUM_JAVA_VERSION = "1.6";

  public void testJavaVersion() {

    String javaVersion = System.getProperty("java.version");
    System.out.printf("Current Java Version: %s\n", javaVersion);
    assertTrue("The Java Version must be > " + MINIMUM_JAVA_VERSION, javaVersion.compareTo(MINIMUM_JAVA_VERSION) > 1);
  }

}
