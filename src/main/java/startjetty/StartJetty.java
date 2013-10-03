package startjetty;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 11:42 PM
 *
 *  This class is for running in the IDE only.  It allows jetty to start the webapp from the class files.  It avoids
 *  the need to build and deploy the WAR file.
 *
 */
public class StartJetty {

  public static final String WEBAPP_DIR = "webapp" ;
  public static void main(String[] args) throws Exception
  {

    BasicConfigurator.configure();
    String jetty_home = System.getProperty("jetty.home", ".");

    String webAppDir = jetty_home + "/" + WEBAPP_DIR;
    if (!(new File(webAppDir)).exists()) {
      // If we don't have webapp, assume we're running out of a source tree
      webAppDir = jetty_home + "/src/main/" + WEBAPP_DIR;
      if (!(new File(webAppDir)).exists()) {
        throw new Exception("Can't find the webapp dir");
      }
    }

    Server server = new Server(8080);

    WebAppContext context = new WebAppContext();
    context.setDescriptor(context+"/WEB-INF/web.xml");
    context.setResourceBase(webAppDir);

    // TODO - INFO log point
    System.out.printf("Resouce Base: %s\n", context.getResourceBase());
    context.setContextPath("/playlist");
    context.setParentLoaderPriority(true);

    server.setHandler(context);
    server.start();
    server.join();
  }

}
