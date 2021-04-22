package edu.neu.cs6510.pnnl.hunting.model;

import lombok.Data;

@Data
public class VavThresholds {
    private Integer id;

    private Double maximumZoneAirFlow;

    private Double minimumZoneAirFlow;

    private Double zoneAirFlowSetPointOffset;

    private Double zoneReheatAirFlow;

    private Integer ahu1Id;

    private Integer ahu3Id;

    private String vavName;
}