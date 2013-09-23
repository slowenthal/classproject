CREATE KEYSPACE playlist WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };
USE playlist;
create table users (email text primary key, password text, user_id UUID);
create table title_by_artist (title text, artist text, title_id int, primary key (artist, title, title_id));
copy  title_by_artist (title_id, title, artist)  FROM 'CSV/songs.csv' WITH DELIMITER = '|';

create table artists_by_first_letter (first_letter text, artist text, primary key (first_letter, artist));
copy artists_by_first_letter (first_letter, artist) from 'CSV/artists.csv'  WITH DELIMITER = '|';








