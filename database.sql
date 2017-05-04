create table Album(
    album_id int NOT NULL AUTO_INCREMENT,
    album_name VARCHAR(25) not null,
    primary key(album_id)

)
create table Images(
    image_id int not null AUTO_INCREMENT,
    album_id int not null,
    name varchar(255) default "",
    image_path VARCHAR(255) not null,
    image_date date,
    tag VARCHAR (100),
    PRIMARY Key(image_id),
    CONSTRAINT FK_albumid FOREIGN KEY (album_id)
    REFERENCES Album(album_id)
)