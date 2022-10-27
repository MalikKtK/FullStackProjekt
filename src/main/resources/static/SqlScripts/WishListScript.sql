CREATE DATABASE wishlist;

USE wishlist;

CREATE TABLE giftlist(
    listID int auto_increment,
    listName varchar(50) NOT NULL,
    email varchar(50) UNIQUE,
    PRIMARY KEY (listID),
    FOREIGN KEY (email) REFERENCES user(email)
);

CREATE TABLE gifts(
    giftID int auto_increment,
    listID int,
    name varchar(50) NOT NULL,
    price int,
    url varchar(100),
    PRIMARY KEY (giftID),
    FOREIGN KEY (listID) REFERENCES giftlist(listID)
);

CREATE TABLE user(
    email varchar(50) UNIQUE,
    username varchar(50) UNIQUE,
    PRIMARY KEY (email)

);

INSERT INTO gifts() values();

-- order by
SELECT * FROM gifts order by name;









