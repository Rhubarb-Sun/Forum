create table comment
(
    id int auto_increment,
    parent_id int not null comment '上级ID',
    parent_type int not null comment '上级类型',
    comment varchar(1024) null comment '评论内容',
    commenter_id int not null comment '评论者',
    like_count int default 0 null comment '被赞次数',
    gmt_create bigint not null comment '创建时间',
    gmt_modified bigint not null comment '最近修改时间',
    constraint comment_pk
        primary key (id)
);