CREATE TABLE hero (
    id int(11) AUTO_INCREMENT,
    name varchar(30),
    hp float,
    damage int(11),
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;

SHOW CREATE TABLE hero;

DROP TABLE hero;

SELECT * FROM hero;
DELETE FROM hero WHERE id = 9;

CREATE TABLE user (
  id int(11) AUTO_INCREMENT,
  name varchar(30) ,
  password varchar(30),
  PRIMARY KEY (id)
) ;
insert into user values(null,'dashen','thisispassword');

SELECT * FROM user;
SHOW CREATE TABLE user;
SHOW index from user;

CREATE TABLE hero1 (
    id int(11) AUTO_INCREMENT,
    name varchar(30),
    hp float,
    damage int(11),
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;

DROP TABLE hero1;

SELECT * FROM hero1;
insert into hero1 values(null,'dashen', 1000, 100);