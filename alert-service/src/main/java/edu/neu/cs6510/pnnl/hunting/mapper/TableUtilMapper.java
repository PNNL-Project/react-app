package edu.neu.cs6510.pnnl.hunting.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableUtilMapper {

    int existTable(String tableName);

    int createNewVavTable(@Param("vavTableName")String vavTableName);

    int createNewVavThresholdsTable(@Param("vavTableName")String vavTableName);

    List<String> getAllTable();

}
