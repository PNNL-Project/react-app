package edu.neu.cs6510.pnnl.hunting.h2mapper;

import edu.neu.cs6510.pnnl.hunting.model.UpdateInfo;
import edu.neu.cs6510.pnnl.hunting.model.Vav;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpdateInfoMapper {


    int insert(@Param("updateInfo") UpdateInfo record);

    int updateByPrimaryKeySelective(UpdateInfo record);

    UpdateInfo selectByPrimaryKey(String vavName);

}