create table alert
(
    id   int      not null
        primary key,
    time datetime null
);

create table updateInfo
(
    vav_name    varchar(60) not null
        primary key,
    update_time datetime    null
);

create table vav_alert
(
    zone_air_flow                      double      null,
    zone_cooling_air_flow_set_point    double      null,
    zone_cooling_temperature_set_point double      null,
    zone_heating_temperature_set_point double      null,
    zone_temperature                   double      null,
    vav_name                           varchar(60) null,
    time                               datetime    null,
    maximum_zone_air_flow              double      null,
    minimum_zone_air_flow              double      null
);

