import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class OneWebAppUnassembled {
  public static void main(String[] args) throws Exception
  {
    Server server = new Server(8080);

    WebAppContext context = new WebAppContext();
    context.setDescriptor(context+"/WEB-INF/web.xml");
    context.setResourceBase("/Users/stevelowenthal/repos/tryjetty/src/main/webapp");
    context.setContextPath("/tryjetty");
    context.setParentLoaderPriority(true);

    server.setHandler(context);

    server.start();
    server.join();
  }

}
