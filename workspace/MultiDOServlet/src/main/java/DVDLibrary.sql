CREATE DATABASE IF NOT EXISTS DVDLibrary;
USE DVDLibrary;
DROP TABLE IF EXISTS DVDS;

CREATE TABLE DVDS (
  DVDId INTEGER UNSIGNED NOT NULL,
  DVDTitle VARCHAR(45) NOT NULL,
  DVDFormat VARCHAR(45) NOT NULL,
  DVDGenre VARCHAR(45) NOT NULL,
  PRIMARY KEY (DVDId)
);

INSERT INTO DVDS VALUES (1,"Breakfast at Tiffanys","Movie","Classic");
INSERT INTO DVDS VALUES (2,"Contact","Movie","Science Fiction");
INSERT INTO DVDS VALUES (3,"Little Britain","TV Series","Comedy");
INSERT INTO DVDS VALUES (4,"Harry Potter Returns","Movie","Fantasy");
INSERT INTO DVDS VALUES (5,"The Revenge of the Servlet","TV Series","Horror");