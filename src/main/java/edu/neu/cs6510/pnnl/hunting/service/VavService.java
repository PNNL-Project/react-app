package edu.neu.cs6510.pnnl.hunting.service;

import edu.neu.cs6510.pnnl.hunting.model.Vav;
public interface VavService{


    int deleteByPrimaryKey(Integer id);

    int insert(Vav record);

    int insertSelective(Vav record);

    Vav selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vav record);

    int updateByPrimaryKey(Vav record);

}
