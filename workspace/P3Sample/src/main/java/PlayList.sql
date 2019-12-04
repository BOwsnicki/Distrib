CREATE DATABASE IF NOT EXISTS P3Sample;
USE P3Sample;
DROP TABLE IF EXISTS Playlist;

CREATE TABLE Playlist (
  imdbRef VARCHAR(15) UNIQUE NOT NULL,
  title VARCHAR(100) NOT NULL,
  metascore INT NOT NULL,
  rottenTomatoes INT NOT NULL,
  key1 VARCHAR(20),
  key2 VARCHAR(20),
  key3 VARCHAR(20),
  key4 VARCHAR(40),
  key5 VARCHAR(40),
  PRIMARY KEY (imdbRef)
);

INSERT INTO Playlist VALUES ("tt0120737","The Fellowship of the Ring",81,44,"lotr","ring","hobbits","gandalf","magic");
INSERT INTO Playlist VALUES ("tt0120915","The Phantom Menace",90,80,"obiwan","force","sith","skywalker","palpatine");
INSERT INTO Playlist VALUES ("tt0295297","Harry Potter",63,82,"harrypotter","magic","hogwarts","hermione","weasley");