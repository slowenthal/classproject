CREATE KEYSPACE playlist WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };
USE playlist;
create table users (email text primary key, password text, user_id UUID);
create table track_by_id (track text, artist text, track_id text, track_length_in_seconds int, genre text, primary key (track_id));
create table track_by_artist (track text, artist text, track_id text, track_length_in_seconds int, genre text, primary key (artist, track, track_id));
create table track_by_genre (track text, artist text, track_id text, track_length_in_seconds int, genre text, primary key (genre, artist, track, track_id));
copy track_by_id (genre, track_id, artist, track, track_length_in_seconds)   FROM 'CSV/songs.csv' WITH DELIMITER = '|' AND HEADER=true;
copy track_by_artist (genre, track_id, artist, track, track_length_in_seconds)   FROM 'CSV/songs.csv' WITH DELIMITER = '|'  AND HEADER=true;
copy track_by_genre (genre, track_id, artist, track, track_length_in_seconds)   FROM 'CSV/songs.csv' WITH DELIMITER = '|'  AND HEADER=true;

create table artists_by_first_letter (first_letter text, artist text, primary key (first_letter, artist));
copy artists_by_first_letter (first_letter, artist) from 'CSV/artists.csv'  WITH DELIMITER = '|';

/* Teach Lists - Add This */
alter table users add playlist_names set<text>  ;

create table playlist_tracks (user_id UUID, playlist_name text, sequence_no int, artist text, track_name text, genre text, track_length_in_seconds int, musicfile text, primary key ((user_id, playlist_name), sequence_no ));

create table statistics (counter_name text PRIMARY KEY, counter_value counter);







