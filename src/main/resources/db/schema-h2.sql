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

# Build this table in code
# Every Vav will have own table
# create table vav_alert
# (
#     zone_cooling_temperature_set_point double      null,
#     zone_heating_temperature_set_point double      null,
#     zone_temperature                   double      null,
#     vav_name                           varchar(60) null,
#     time                               datetime    null
# );

