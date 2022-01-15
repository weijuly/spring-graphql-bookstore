create database bookstore;

create user 'bookstore'@'localhost' identified by 'bookstore';
create user 'bookstore'@'%' identified by 'bookstore';

grant all privileges on bookstore.* to 'bookstore'@'localhost';
grant all privileges on bookstore.* to 'bookstore'@'%';

flush privileges;

use bookstore;

drop table if exists BOOK;
drop table if exists AUTHOR;
drop table if exists BOOK_TAGS;
drop table if exists CUSTOMER;
drop table if exists PURCHASES;
drop table if exists LENDINGS;
drop table if exists LIKES;

create table BOOK (
    ID              VARCHAR(64)         PRIMARY KEY,
    ISBN            VARCHAR(64)         NOT NULL UNIQUE,
    NAME            VARCHAR(256)        NOT NULL UNIQUE,
    PUB_YEAR        INT                 NOT NULL,
    PAGES           INT                 NOT NULL,
    PRICE           INT                 NOT NULL,
    COPIES          INT                 NOT NULL,
    UPDATED_ON      DATE                NOT NULL,
    AUTHOR_ID       VARCHAR(64)         NOT NULL
);

create table AUTHOR (
    ID              VARCHAR(64)         PRIMARY KEY,
    FIRST_NAME      VARCHAR(256)        NOT NULL,
    LAST_NAME       VARCHAR(256)        NOT NULL,
    constraint C_FULL_NAME UNIQUE(FIRST_NAME, LAST_NAME)
);

create table BOOK_TAGS (
    ID              VARCHAR(64)         PRIMARY KEY,
    BOOK_ID         VARCHAR(64)         NOT NULL,
    TAG             VARCHAR(64)         NOT NULL
);

create table CUSTOMER (
    ID              VARCHAR(64)         PRIMARY KEY,
    FIRST_NAME      VARCHAR(64)         NOT NULL,
    LAST_NAME       VARCHAR(64)         NOT NULL,
    constraint C_FULL_NAME UNIQUE(FIRST_NAME, LAST_NAME)
);

create table PURCHASES (
    ID              VARCHAR(64)         PRIMARY KEY,
    CUSTOMER_ID     VARCHAR(64)         NOT NULL,
    BOOK_ID         VARCHAR(64)         NOT NULL,
    PURCHASED_ON    DATE                NOT NULL
);

create table LENDINGS (
    ID              VARCHAR(64)         PRIMARY KEY,
    CUSTOMER_ID     VARCHAR(64)         NOT NULL,
    BOOK_ID         VARCHAR(64)         NOT NULL,
    DUE_ON          DATE                NOT NULL
);

create table LIKES (
    ID              VARCHAR(64)         PRIMARY KEY,
    CUSTOMER_ID     VARCHAR(64)         NOT NULL,
    BOOK_ID         VARCHAR(64)         NOT NULL
);

insert into AUTHOR
    (ID, FIRST_NAME, LAST_NAME)
values
    (UUID(), 'Charles', 'Dickens'),
    (UUID(), 'Jules', 'Verne'),
    (UUID(), 'George', 'Orwell');

set @author := (select ID from AUTHOR where FIRST_NAME='Charles' and LAST_NAME='Dickens');
insert into BOOK
    (ID, ISBN, NAME, PUB_YEAR, PAGES, PRICE, COPIES, UPDATED_ON, AUTHOR_ID)
values
    (UUID(), UUID(), 'A Christmas Carol', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'The Pickwick Papers', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Oliver Twist', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Nicholas Nickleby', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Bleak House', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'David Copperfield', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Hard Times', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'A Tale of Two Cities', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Great Expectations', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'The Battle of Life', 1843, 700, 100, 100, '2021-12-30', @author);

set @author := (select ID from AUTHOR where FIRST_NAME='Jules' and LAST_NAME='Verne');
insert into BOOK
    (ID, ISBN, NAME, PUB_YEAR, PAGES, PRICE, COPIES, UPDATED_ON, AUTHOR_ID)
values
    (UUID(), UUID(), 'Five Weeks in a Balloon', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'The Adventures of Captain Hatteras', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Journey to the Center of the Earth', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'From the Earth to the Moon', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'In Search of the Castaways', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Twenty Thousand Leagues Under the Sea', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Around the Moon', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'A Floating City', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Around the World in Eighty Days', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'The Mysterious Island', 1843, 700, 100, 100, '2021-12-30', @author);

set @author := (select ID from AUTHOR where FIRST_NAME='George' and LAST_NAME='Orwell');
insert into BOOK
    (ID, ISBN, NAME, PUB_YEAR, PAGES, PRICE, COPIES, UPDATED_ON, AUTHOR_ID)
values
    (UUID(), UUID(), 'Down and Out in Paris and London', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Burmese Days', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'A Clergymans Daughter', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Keep the Aspidistra Flying', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'The Road to Wigan Pier', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Homage to Catalonia', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Coming Up for Air', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Animal Farm', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'Nineteen Eighty-Four', 1843, 700, 100, 100, '2021-12-30', @author),
    (UUID(), UUID(), 'A Dressed Man and a Naked Man', 1843, 700, 100, 100, '2021-12-30', @author);

insert into BOOK_TAGS
    (ID, BOOK_ID, TAG)
values
    (UUID(), (select ID from BOOK where NAME = 'A Christmas Carol'), 'FICTION'),
    (UUID(), (select ID from BOOK where NAME = 'The Pickwick Papers'), 'FICTION'),
    (UUID(), (select ID from BOOK where NAME = 'Oliver Twist'), 'FICTION'),
    (UUID(), (select ID from BOOK where NAME = 'Nicholas Nickleby'), 'FICTION'),
    (UUID(), (select ID from BOOK where NAME = 'Bleak House'), 'FICTION'),
    (UUID(), (select ID from BOOK where NAME = 'David Copperfield'), 'FICTION'),
    (UUID(), (select ID from BOOK where NAME = 'Hard Times'), 'FICTION'),
    (UUID(), (select ID from BOOK where NAME = 'A Tale of Two Cities'), 'FICTION'),
    (UUID(), (select ID from BOOK where NAME = 'Great Expectations'), 'FICTION'),
    (UUID(), (select ID from BOOK where NAME = 'The Battle of Life'), 'FICTION');

insert into BOOK_TAGS
    (ID, BOOK_ID, TAG)
values
    (UUID(), (select ID from BOOK where NAME = 'Five Weeks in a Balloon'), 'THRILLER'),
    (UUID(), (select ID from BOOK where NAME = 'The Adventures of Captain Hatteras'), 'THRILLER'),
    (UUID(), (select ID from BOOK where NAME = 'Journey to the Center of the Earth'), 'THRILLER'),
    (UUID(), (select ID from BOOK where NAME = 'From the Earth to the Moon'), 'THRILLER'),
    (UUID(), (select ID from BOOK where NAME = 'In Search of the Castaways'), 'THRILLER'),
    (UUID(), (select ID from BOOK where NAME = 'Twenty Thousand Leagues Under the Sea'), 'THRILLER'),
    (UUID(), (select ID from BOOK where NAME = 'Around the Moon'), 'THRILLER'),
    (UUID(), (select ID from BOOK where NAME = 'A Floating City'), 'THRILLER'),
    (UUID(), (select ID from BOOK where NAME = 'Around the World in Eighty Days'), 'THRILLER'),
    (UUID(), (select ID from BOOK where NAME = 'The Mysterious Island'), 'THRILLER');

insert into BOOK_TAGS
    (ID, BOOK_ID, TAG)
values
    (UUID(), (select ID from BOOK where NAME = 'Down and Out in Paris and London'), 'POLITICS'),
    (UUID(), (select ID from BOOK where NAME = 'Burmese Days'), 'POLITICS'),
    (UUID(), (select ID from BOOK where NAME = 'A Clergymans Daughter'), 'POLITICS'),
    (UUID(), (select ID from BOOK where NAME = 'Keep the Aspidistra Flying'), 'POLITICS'),
    (UUID(), (select ID from BOOK where NAME = 'The Road to Wigan Pier'), 'POLITICS'),
    (UUID(), (select ID from BOOK where NAME = 'Homage to Catalonia'), 'POLITICS'),
    (UUID(), (select ID from BOOK where NAME = 'Coming Up for Air'), 'POLITICS'),
    (UUID(), (select ID from BOOK where NAME = 'Animal Farm'), 'POLITICS'),
    (UUID(), (select ID from BOOK where NAME = 'Nineteen Eighty-Four'), 'POLITICS'),
    (UUID(), (select ID from BOOK where NAME = 'A Dressed Man and a Naked Man'), 'POLITICS');

