package edu.neu.cs6510.pnnl.hunting.model;

import java.util.Date;
import lombok.Data;

@Data
public class VavAlert {

    private Double zoneCoolingTemperatureSetPoint;

    private Double zoneHeatingTemperatureSetPoint;

    private Double zoneTemperature;

    private String vavName;

    private Date time;



    public VavAlert() {
    }

    public VavAlert(Vav vav) {
        this.zoneCoolingTemperatureSetPoint = vav.getZoneCoolingTemperatureSetPoint();
        this.zoneHeatingTemperatureSetPoint = vav.getZoneHeatingTemperatureSetPoint();
        this.zoneTemperature = vav.getZoneTemperature();
        this.vavName = vav.getVavName();
        if(vav.getCommon() != null){
            this.time = vav.getCommon().getTime();
        }
    }
}