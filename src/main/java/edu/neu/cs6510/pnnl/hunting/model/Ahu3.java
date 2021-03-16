package edu.neu.cs6510.pnnl.hunting.model;

import lombok.Data;

@Data
public class Ahu3 {
    private Integer id;

    private Double dischargeAirFlow;

    private Double exhaustAirFlow;

    private Double minimumOutdoorAirFlow;

    private Double outdoorAirFlow;
}