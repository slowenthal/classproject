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

public class ArtistsDAO extends CassandraData {

  //
  // This class retrieves Artist names from the artist table in Cassandra
  // It has a single static method which is given the artist's first letter
  // and returns a list of Artists
  //

  // Static finder method

  // TODO - one of the exersizes is to add the desc
  // TODO - rewrite this method usinga prepared statement.


  /**
   *
   * @param first_letter - first letter of the Artists name
   * @param desc - return the results in ascending or descending order
   * @return - Return the artists names as list of Strings
   */

  public static List<String> listArtistByLetter(String first_letter, boolean desc) {

    String queryText = "SELECT * FROM artists_by_first_letter WHERE first_letter = '" + first_letter + "'"

            // TODO separate this out for one of the exersizes
            + (desc ? " ORDER BY artist DESC" : "");

    ResultSet results = getSession().execute(queryText);

    List<String> artists = new ArrayList<String>();

    for (Row row : results) {
       artists.add(row.getString("artist"));     // Lets use column 0 since there is only one column
    }

    return artists;
  }
}
