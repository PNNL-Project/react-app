package edu.neu.cs6510.pnnl.hunting.mapper;

import edu.neu.cs6510.pnnl.hunting.model.Vav;
import org.apache.ibatis.annotations.Param;

public interface VavMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("vav") Vav record, @Param("vavTableName") String vavTableName);

    int insertSelective(Vav record);

    Vav selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vav record);

    int updateByPrimaryKey(Vav record);
}