package edu.neu.cs6510.pnnl.hunting.h2mapper;

import edu.neu.cs6510.pnnl.hunting.model.Vav;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VavH2Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("vav") Vav record, @Param("vavTableName") String vavTableName);

    int insertSelective(Vav record);

    Vav selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vav record);

    int updateByPrimaryKey(Vav record);

    List<Double> getZoneAirFlowInRange(@Param("start") String start, @Param("end") String end, @Param("vavTableName") String vavTableName);

    List<Double> getZoneTemperatureInRange(@Param("start") String start, @Param("end") String end, @Param("vavTableName") String vavTableName);

    List<Double> getZoneHeatingTemperatureSetPointInRange(@Param("start") String start, @Param("end") String end, @Param("vavTableName") String vavTableName);

    List<Double> getZoneCoolingTemperatureSetPointInRange(@Param("start") String start, @Param("end") String end, @Param("vavTableName") String vavTableName);

    List<Vav> getVav1InRange(@Param("start") String start, @Param("end") String end, @Param("vavTableName") String vavTableName);

    List<Vav> getVav3InRange(@Param("start") String start, @Param("end") String end, @Param("vavTableName") String vavTableName);

    List<Vav> getAllVav();


}