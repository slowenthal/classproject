CREATE KEYSPACE tracker WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };
CREATE TABLE trackpoints (package_id text, status_timestamp timestamp, location text, notes text, primary key (package_id, status_timestamp));
COPY trackpoints (package_id , status_timestamp , location , notes ) FROM 'CSV/transactions_shuffled.csv' WITH DELIMITER = '|';

