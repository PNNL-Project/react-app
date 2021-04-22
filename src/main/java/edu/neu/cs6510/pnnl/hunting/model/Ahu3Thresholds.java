package edu.neu.cs6510.pnnl.hunting.model;

import lombok.Data;

@Data
public class Ahu3Thresholds {
    private Integer id;

    private Double exhaustAirFlowSetPoint;

    private Double minimumOutdoorAirFlow;

    private Double minimumOutdoorAirFlowSetPoint;
}