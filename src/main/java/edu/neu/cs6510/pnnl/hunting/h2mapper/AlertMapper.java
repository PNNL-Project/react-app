package edu.neu.cs6510.pnnl.hunting.h2mapper;

import edu.neu.cs6510.pnnl.hunting.model.Alert;
import edu.neu.cs6510.pnnl.hunting.model.UpdateInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlertMapper {


    int insert(@Param("alert") Alert record);

    List<Alert> getAllAlert();

}