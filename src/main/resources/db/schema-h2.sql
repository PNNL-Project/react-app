drop table if exists vav;
create table vav
(
    ID                                 int auto_increment
        primary key,
    zone_air_flow                      int         null,
    zone_cooling_air_flow_set_point    double      null,
    zone_cooling_temperature_set_point double      null,
    zone_heating_temperature_set_point double      null,
    zone_temperature                   double      null,
    ahu1_id                            int         null,
    ahu3_id                            int         null,
    vav_name                           varchar(60) null
);

