package edu.neu.cs6510.pnnl.hunting.service;

import edu.neu.cs6510.pnnl.hunting.model.Vav;

import java.util.Date;
import java.util.List;

public interface VavService{


    int deleteByPrimaryKey(Integer id);

    int insert(Vav record, String vavTableName);

    int insertSelective(Vav record);

    Vav selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vav record);

    int updateByPrimaryKey(Vav record);

    List<Double> getZoneAirFlowInRange(Date start, Date end, String vavTableName);

    List<Double> getZoneTemperatureInRange(Date start, Date end, String vavTableName);

    List<Double> getZoneCoolingTemperatureSetPointInRange(Date start, Date end, String vavTableName);

    List<Double> getZoneHeatingTemperatureSetPointInRange(Date start, Date end, String vavTableName);

    List<Vav> getVavInRange(Date start, Date end, String vavTableName);


}
