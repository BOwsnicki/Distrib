DROP DATABASE IF EXISTS Library;
CREATE DATABASE Library;
USE Library;
DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS Student;


CREATE TABLE Student (
  StudentID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Name VARCHAR(45) NOT NULL,
  UWFId VARCHAR(13) UNIQUE NOT NULL,
  Age INTEGER UNSIGNED NOT NULL,
  Major VARCHAR(45),
  PRIMARY KEY (StudentID)
);

CREATE TABLE Book (
  BookID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  CatalogNumber VARCHAR(45) UNIQUE NOT NULL,
  Author VARCHAR(45) NOT NULL,
  Title VARCHAR(45) NOT NULL,
  BorrowerId INTEGER UNSIGNED,
  PRIMARY KEY (BookID),
  CONSTRAINT FK_Borrower FOREIGN KEY FK_Borrower (BorrowerId)
    REFERENCES Student (StudentID)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
);

INSERT INTO Student VALUES (null, "John Doe", "4711", 23, "Computer Science");
INSERT INTO Student VALUES (null, "Suzy Creamcheese", "388-1200", 53, null);
INSERT INTO Student VALUES (null, "Jane Doe", "555-19224", 26, "History");
SET @jane := LAST_INSERT_ID();
INSERT INTO Student VALUES (null, "Gerald Bostock", "123123-99", 44, null);
INSERT INTO Student VALUES (null, "Lisa Simpson", "123123-98", 34, null);

INSERT INTO Book VALUES (null, "TK4412", "Leo Tolstoy", "War and Peace", @jane);
INSERT INTO Book VALUES (null, "UY9175", "Emily Bronte", "Wuthering Heights", null);
INSERT INTO Book VALUES (null, "ZZ0641", "Chris Date", "Database Systems", null);
INSERT INTO Book VALUES (null, "TK4700", "J.K. Rowling", "Harry Potter and the Deathly Hallows", null);
INSERT INTO Book VALUES (null, "OZ8441", "Leo Tolstoy", "Anna Karenina", null);
INSERT INTO Book VALUES (null, "HS3691", "Burke Davis", "The Civil War", null);
INSERT INTO Book VALUES (null, "HI7762", "Bruce Catton", "The Civil War", null);

