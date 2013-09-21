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
public class OneWebAppUnassembled {

  public static final String WARMODULE_OFFSET_FROM_ROOT = "warmodule/src/main/webapp".replace('/', File.separatorChar);

  public static void main(String[] args) throws Exception
  {

    String jetty_home = System.getProperty("jetty.home",".");

    Server server = new Server(8080);

    WebAppContext context = new WebAppContext();
    context.setDescriptor(context+"/WEB-INF/web.xml");
    context.setResourceBase(jetty_home + File.separator + WARMODULE_OFFSET_FROM_ROOT);
    context.setContextPath(OneWebApp.DEPLOYMENT_CONTEXT_PATH);
    context.setParentLoaderPriority(true);

    server.setHandler(context);

    server.start();
    server.join();
  }

}
