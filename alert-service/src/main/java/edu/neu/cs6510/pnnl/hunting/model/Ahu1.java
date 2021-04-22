package edu.neu.cs6510.pnnl.hunting.model;

import lombok.Data;

@Data
public class Ahu1 {
    private Integer id;

    private Double dischargeAirFlow;

    private Double exhaustAirFlowSetPoint;

    private Double outdoorAirFlow;
}