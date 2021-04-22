package edu.neu.cs6510.pnnl.hunting.model;

import lombok.Data;

@Data
public class Ahu2 {
    private Integer id;

    private Double outdoorAirFlow;

    private Double returnAirFlow;

    private Double supplyAirFlow;

    private Double supplyAirFlowSetPoint;

    private Double zoneCoolingTemperatureSetPoint;

    private Double zoneHeatingTemperatureSetPoint;

    private Double zoneTemperature;
}