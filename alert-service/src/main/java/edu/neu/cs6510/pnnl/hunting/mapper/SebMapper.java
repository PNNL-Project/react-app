package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.Seb;

public interface SebMapper {
    int insert(Seb record);

    int insertSelective(Seb record);
}