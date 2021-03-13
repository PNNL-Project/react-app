package edu.neu.cs6510.pnnl.hunting.model;

import lombok.Data;

@Data
public class Vav {
    private Integer id;

    private Integer zoneAirFlow;

    private Double zoneCoolingAirFlowSetPoint;

    private Integer zoneCoolingTemperatureSetPoint;

    private Integer zoneHeatingTemperatureSetPoint;

    private Double zoneTemperature;

    private Integer ahu1Id;

    private Integer ahu3Id;

    private String vavName;
}