package edu.neu.cs6510.pnnl.hunting.service;

import edu.neu.cs6510.pnnl.hunting.model.VavThresholds;
public interface VavThresholdsService{


    int deleteByPrimaryKey(Integer id);

    int insert(VavThresholds record, String vavTableName);

    int insertSelective(VavThresholds record);

    VavThresholds selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VavThresholds record);

    int updateByPrimaryKey(VavThresholds record);

}
