package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.VavThresholds;

public interface VavThresholdsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VavThresholds record);

    int insertSelective(VavThresholds record);

    VavThresholds selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VavThresholds record);

    int updateByPrimaryKey(VavThresholds record);
}