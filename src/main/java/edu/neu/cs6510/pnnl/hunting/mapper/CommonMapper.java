package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.Common;import org.apache.ibatis.annotations.Param;import java.util.Date;import java.util.List;

public interface CommonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Common record);

    int insertSelective(Common record);

    Common selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Common record);

    int updateByPrimaryKey(Common record);

    List<Common> selectByTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    String getVavTableName(@Param("vavTable") String vavTable);
}