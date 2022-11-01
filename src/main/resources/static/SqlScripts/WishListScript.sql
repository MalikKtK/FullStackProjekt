CREATE DATABASE IF NOT EXISTS wishlist;
USE wishlist;

CREATE TABLE IF NOT EXISTS users
(
    email    varchar(50),
    userName varchar(50),
    primary key (email)
);

CREATE TABLE IF NOT EXISTS wishlists
(
    listID   int AUTO_INCREMENT,
    email    varchar(50),
    listName varchar(50),
    PRIMARY KEY (listID),
    FOREIGN KEY (email) REFERENCES users (email) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS gifts
(
    giftID     int AUTO_INCREMENT,
    listID     int,
    giftName   varchar(50),
    price      double,
    url        varchar(50),
    isReserved boolean,
    PRIMARY KEY (giftID),
    FOREIGN KEY (listID) REFERENCES wishlists (listID) ON DELETE CASCADE
);
