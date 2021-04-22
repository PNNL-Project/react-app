package edu.neu.cs6510.pnnl.hunting.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.neu.cs6510.pnnl.hunting.utils.DateUtil;
import lombok.Data;

@Data
public class VavAlert {

    private Double zoneCoolingTemperatureSetPoint;

    private Double zoneHeatingTemperatureSetPoint;

    private Double zoneTemperature;

    private String vavName;

    // FIXME the time zone here will relative with running server's time zone.
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-5")
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


    @Override
    public String toString() {
        return "VavAlert{" +
                "zoneCoolingTemperatureSetPoint=" + zoneCoolingTemperatureSetPoint +
                ", zoneHeatingTemperatureSetPoint=" + zoneHeatingTemperatureSetPoint +
                ", zoneTemperature=" + zoneTemperature +
                ", vavName='" + vavName + '\'' +
                ", time=" + DateUtil.convertDateToString(time) +
                '}';
    }
}