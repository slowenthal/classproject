package todo;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/18/13
 * Time: 8:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ToDo extends CassandraData {

  public static List<String> listAllMock(ServletContext context) {

    List<String> todoItems = new ArrayList<String> ();
    todoItems.add("Eat Breakfast");
    todoItems.add("Eat Lunch");
    todoItems.add("Pick up Drycleaning");

    return todoItems;
  }


  public static List<String> listAll(ServletContext context) {
    Session session = getSession(context);

    List<String> todoItems = new ArrayList<String> ();

    for (Row row : session.execute("SELECT descr from todoitems")) {
        todoItems.add(row.getString("descr"));
    }

   return todoItems;
  }

}
