package edu.neu.cs6510.pnnl.hunting.service;

import edu.neu.cs6510.pnnl.hunting.model.Ahu3Thresholds;
public interface Ahu3ThresholdsService{


    int deleteByPrimaryKey(Integer id);

    int insert(Ahu3Thresholds record);

    int insertSelective(Ahu3Thresholds record);

    Ahu3Thresholds selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ahu3Thresholds record);

    int updateByPrimaryKey(Ahu3Thresholds record);

}
