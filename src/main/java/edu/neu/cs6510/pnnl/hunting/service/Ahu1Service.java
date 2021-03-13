package edu.neu.cs6510.pnnl.hunting.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu1Mapper;
import edu.neu.cs6510.pnnl.hunting.model.Ahu1;
@Service
public class Ahu1Service{

    @Resource
    private Ahu1Mapper ahu1Mapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return ahu1Mapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Ahu1 record) {
        return ahu1Mapper.insert(record);
    }

    
    public int insertSelective(Ahu1 record) {
        return ahu1Mapper.insertSelective(record);
    }

    
    public Ahu1 selectByPrimaryKey(Integer id) {
        return ahu1Mapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Ahu1 record) {
        return ahu1Mapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Ahu1 record) {
        return ahu1Mapper.updateByPrimaryKey(record);
    }

}
