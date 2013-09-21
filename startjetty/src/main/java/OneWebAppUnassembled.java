import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class OneWebAppUnassembled {

  public static final String WARMODULE_OFFSET_FROM_ROOT = "warmodule/src/main/webapp".replaceAll("/", File.separator);

  public static void main(String[] args) throws Exception
  {

    String jetty_home = System.getProperty("jetty.home",".");

    Server server = new Server(8080);

    WebAppContext context = new WebAppContext();
    context.setDescriptor(context+"/WEB-INF/web.xml");
    context.setResourceBase(jetty_home + WARMODULE_OFFSET_FROM_ROOT);
    context.setContextPath("/warmodule");
    context.setParentLoaderPriority(true);

    server.setHandler(context);

    server.start();
    server.join();
  }

}
