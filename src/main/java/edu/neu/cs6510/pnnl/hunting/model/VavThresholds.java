package edu.neu.cs6510.pnnl.hunting.model;

import lombok.Data;

@Data
public class VavThresholds {
    private Integer id;

    private Integer maximumZoneAirFlow;

    private Integer minimumZoneAirFlow;

    private Integer zoneAirFlowSetPointOffset;

    private Integer zoneReheatAirFlow;

    private Integer ahu1Id;

    private Integer ahu3Id;

    private String vavName;
}