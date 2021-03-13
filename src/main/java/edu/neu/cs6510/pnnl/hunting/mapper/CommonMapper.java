package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.Common;

public interface CommonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Common record);

    int insertSelective(Common record);

    Common selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Common record);

    int updateByPrimaryKey(Common record);
}