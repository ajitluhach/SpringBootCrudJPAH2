DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
userId INT AUTO_INCREMENT PRIMARY KEY,
name varchar(255) NOT NULL,
mobileNumber varchar(255) NOT NULL
);