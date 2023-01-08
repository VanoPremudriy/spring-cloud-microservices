set names 'utf8';
\c files_db

create table files
(
    id         bigint
        primary key,
    file_name varchar(255) null
);