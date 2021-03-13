package edu.neu.cs6510.pnnl.hunting.model;

import lombok.Data;

@Data
public class Ahu4 {
    private Integer id;

    private Double exhaustAirFlow;

    private Double exhaustAirFlowSetPoint;

    private Integer maximumDischargeAirFlowSetPoint;

    private Integer minimumDischargeAirFlowSetPoint;

    private Double supplyAirFlow;

    private Integer unoccupiedZoneHeatingTemperatureSetPoint;

    private Integer zoneCoolingTemperatureSetPoint;

    private Integer zoneHeatingTemperatureSetPoint;

    private Double zoneOutdoorAirFlow;

    private Double zoneTemperature;
}