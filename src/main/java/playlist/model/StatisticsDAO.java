package playlist.model;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * DataStax Academy Sample Application
 *
 * Copyright 2013 DataStax
 *
 */


public class StatisticsDAO extends CassandraData {

  private String counter_name;
  private long counter_value;

  public StatisticsDAO(Row row) {
    counter_name = row.getString("counter_name");
    counter_value = row.getLong("counter_value");
  }

  // Static finder method

  public static List<StatisticsDAO> getStatistics() {

    String queryText = "SELECT * FROM statistics";
    ResultSet results = getSession().execute(queryText);

    List<StatisticsDAO> statistics = new ArrayList<StatisticsDAO>();

    for (Row row : results) {
       statistics.add(new StatisticsDAO(row));     // Lets use column 0 since there is only one column
    }

    return statistics;
  }

  public static void increment_counter(String counter_name) {

    String queryText = "UPDATE statistics set counter_value = counter_value + 1 where counter_name = '" + counter_name +"'";
    getSession().execute(queryText);

  }

  public static void decrement_counter(String counter_name) {

    String queryText = "UPDATE statistics set counter_value = counter_value - 1 where counter_name = '" + counter_name +"'";
    getSession().execute(queryText);

  }

  public String getCounter_name() {
    return counter_name;
  }

  public long getCounter_value() {
    return counter_value;
  }
}
