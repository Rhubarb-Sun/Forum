create table person
(
    id int auto_increment,
    title varchar(100) null,
    content varchar(256) null,
    creator int null,
    read_count int default 0 null,
    like_count int default 0 null,
    comment_count int default 0 null,
    gmt_modified bigint null,
    gmt_create bigint null,
    tag varchar(50) null,
    constraint person_pk
        primary key (id)
);