package edu.neu.cs6510.pnnl.hunting.service;

import edu.neu.cs6510.pnnl.hunting.model.Ahu3;
public interface Ahu3Service{


    int deleteByPrimaryKey(Integer id);

    int insert(Ahu3 record);

    int insertSelective(Ahu3 record);

    Ahu3 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ahu3 record);

    int updateByPrimaryKey(Ahu3 record);

}
