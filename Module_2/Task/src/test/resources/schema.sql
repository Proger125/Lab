CREATE TABLE tag
(
    id int NOT NULL AUTO_INCREMENT UNIQUE ,
    name varchar(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE certificate_tag
(
    certificate_id int NOT NULL ,
    tag_id int NOT NULL
);

CREATE TABLE certificate
(
    id int NOT NULL AUTO_INCREMENT UNIQUE ,
    name varchar(50) NOT NULL ,
    description varchar(200) NOT NULL ,
    price decimal NOT NULL ,
    duration int NOT NULL ,
    create_date varchar(25) NOT NULL ,
    last_update_date varchar(25) NOT NULL,
    PRIMARY KEY (id)
);