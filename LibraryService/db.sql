set names 'utf8';
\c library_db

create table book
(
    id         bigint
        primary key,
    book_name varchar(255) null,
    book_author varchar(255) null,
    book_year integer null
);

insert into book values (1, 'fisrt book', 'author 1', 1990);
insert into book values (2, 'second book', 'author 1', 1995);
insert into book values (3, 'third book', 'author 2', 1990);
insert into book values (4, 'fourth book', 'author 2', 1991);