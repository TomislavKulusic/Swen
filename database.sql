CREATE DATABASE gallery;
USE gallery;

CREATE TABLE Album (
  album_id   INT         NOT NULL AUTO_INCREMENT,
  album_name VARCHAR(25) NOT NULL,
  PRIMARY KEY (album_id)

);

CREATE TABLE Images (
  image_id   INT          NOT NULL AUTO_INCREMENT,
  album_id   INT          NOT NULL,
  name       VARCHAR(255)          DEFAULT "",
  image_path VARCHAR(255) NOT NULL,
  image_date DATE,
  tag        VARCHAR(100),
  PRIMARY KEY (image_id),
  CONSTRAINT FK_albumid FOREIGN KEY (album_id)
  REFERENCES Album (album_id)
)