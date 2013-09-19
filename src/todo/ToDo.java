package todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/18/13
 * Time: 8:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ToDo {

  public static List<String> listAll() {

    List<String> todoItems = new ArrayList<String> ();
    todoItems.add("Eat Breakfast");
    todoItems.add("Eat Lunch");
    todoItems.add("Pick up Drycleaning");

    return todoItems;
  }

}
