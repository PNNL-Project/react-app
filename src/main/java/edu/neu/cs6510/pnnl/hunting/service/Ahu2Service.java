package edu.neu.cs6510.pnnl.hunting.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.model.Ahu2;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu2Mapper;
@Service
public class Ahu2Service{

    @Resource
    private Ahu2Mapper ahu2Mapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return ahu2Mapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Ahu2 record) {
        return ahu2Mapper.insert(record);
    }

    
    public int insertSelective(Ahu2 record) {
        return ahu2Mapper.insertSelective(record);
    }

    
    public Ahu2 selectByPrimaryKey(Integer id) {
        return ahu2Mapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Ahu2 record) {
        return ahu2Mapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Ahu2 record) {
        return ahu2Mapper.updateByPrimaryKey(record);
    }

}
