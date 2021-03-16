package edu.neu.cs6510.pnnl.hunting.service;

import edu.neu.cs6510.pnnl.hunting.model.Ahu1;
public interface Ahu1Service{


    int deleteByPrimaryKey(Integer id);

    int insert(Ahu1 record);

    int insertSelective(Ahu1 record);

    Ahu1 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ahu1 record);

    int updateByPrimaryKey(Ahu1 record);

}
