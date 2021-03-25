package edu.neu.cs6510.pnnl.hunting.h2mapper;

import edu.neu.cs6510.pnnl.hunting.model.Vav;
import edu.neu.cs6510.pnnl.hunting.model.VavAlert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VavAlertMapper {
    int insert(VavAlert record);

    int insertSelective(VavAlert record);

    List<VavAlert> getVavAlertInRange(@Param("start") String start, @Param("end") String end);
}