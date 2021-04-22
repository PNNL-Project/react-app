package edu.neu.cs6510.pnnl.hunting.service;

import edu.neu.cs6510.pnnl.hunting.model.Ahu1Thresholds;
public interface Ahu1ThresholdsService{


    int deleteByPrimaryKey(Integer id);

    int insert(Ahu1Thresholds record);

    int insertSelective(Ahu1Thresholds record);

    Ahu1Thresholds selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ahu1Thresholds record);

    int updateByPrimaryKey(Ahu1Thresholds record);

}
