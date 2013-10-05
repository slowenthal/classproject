package playlist.model;

import com.datastax.driver.core.Row;

/**
 * DataStax Academy Sample Application
 *
 * Copyright 2013 DataStax
 *
 */

public class CassandraInfo extends CassandraData {

  private String clusterName;
  private String cassandraVersion;

  public CassandraInfo() {

    Row row = getSession().execute("select cluster_name, release_version from system.local").one();
    cassandraVersion = row.getString("release_version");
    clusterName = row.getString("cluster_name");

  }

  public String getClusterName() {
    return clusterName;
  }

  public String getCassandraVersion() {
    return cassandraVersion;
  }
}
