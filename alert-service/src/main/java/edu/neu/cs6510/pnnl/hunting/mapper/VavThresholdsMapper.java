package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.Vav;
import edu.neu.cs6510.pnnl.hunting.model.VavThresholds;
import org.apache.ibatis.annotations.Param;

public interface VavThresholdsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("vavThresholds") VavThresholds record, @Param("vavTableName") String vavTableName);

    int insertSelective(VavThresholds record);

    VavThresholds selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VavThresholds record);

    int updateByPrimaryKey(VavThresholds record);

    VavThresholds selectByVavTableName(@Param("start") String start, @Param("end") String end,@Param("vavThresholdsName") String vavThresholdsName);


}