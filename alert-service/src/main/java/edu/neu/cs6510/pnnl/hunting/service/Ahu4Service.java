package edu.neu.cs6510.pnnl.hunting.service;

import edu.neu.cs6510.pnnl.hunting.model.Ahu4;
public interface Ahu4Service{


    int deleteByPrimaryKey(Integer id);

    int insert(Ahu4 record);

    int insertSelective(Ahu4 record);

    Ahu4 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ahu4 record);

    int updateByPrimaryKey(Ahu4 record);

}
