package edu.neu.cs6510.pnnl.hunting.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu4Mapper;
import edu.neu.cs6510.pnnl.hunting.model.Ahu4;
@Service
public class Ahu4Service{

    @Resource
    private Ahu4Mapper ahu4Mapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return ahu4Mapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Ahu4 record) {
        return ahu4Mapper.insert(record);
    }

    
    public int insertSelective(Ahu4 record) {
        return ahu4Mapper.insertSelective(record);
    }

    
    public Ahu4 selectByPrimaryKey(Integer id) {
        return ahu4Mapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Ahu4 record) {
        return ahu4Mapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Ahu4 record) {
        return ahu4Mapper.updateByPrimaryKey(record);
    }

}
