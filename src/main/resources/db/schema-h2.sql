create table alert
(
    id       int auto_increment
        primary key,
    time     datetime    null,
    vav_name varchar(60) null
);

create table updateInfo
(
    vav_name    varchar(60) not null
        primary key,
    update_time datetime    null
);

