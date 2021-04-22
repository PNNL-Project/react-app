package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.Ahu1Thresholds;

public interface Ahu1ThresholdsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ahu1Thresholds record);

    int insertSelective(Ahu1Thresholds record);

    Ahu1Thresholds selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ahu1Thresholds record);

    int updateByPrimaryKey(Ahu1Thresholds record);
}