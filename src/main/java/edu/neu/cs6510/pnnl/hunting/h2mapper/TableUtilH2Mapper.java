package edu.neu.cs6510.pnnl.hunting.h2mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableUtilH2Mapper {

    int existTable(String tableName);

    int createNewVavAlertTable(@Param("vavTableName")String vavTableName);

    List<String> getAllTable();

}
