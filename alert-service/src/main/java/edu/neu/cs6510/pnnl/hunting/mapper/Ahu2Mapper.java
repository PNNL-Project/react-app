package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.Ahu2;

public interface Ahu2Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ahu2 record);

    int insertSelective(Ahu2 record);

    Ahu2 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ahu2 record);

    int updateByPrimaryKey(Ahu2 record);
}