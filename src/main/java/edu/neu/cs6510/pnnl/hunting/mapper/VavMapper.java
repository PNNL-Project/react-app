package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.Vav;

public interface VavMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Vav record);

    int insertSelective(Vav record);

    Vav selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vav record);

    int updateByPrimaryKey(Vav record);
}