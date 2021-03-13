package edu.neu.cs6510.pnnl.hunting.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.model.Ahu3;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu3Mapper;
@Service
public class Ahu3Service{

    @Resource
    private Ahu3Mapper ahu3Mapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return ahu3Mapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Ahu3 record) {
        return ahu3Mapper.insert(record);
    }

    
    public int insertSelective(Ahu3 record) {
        return ahu3Mapper.insertSelective(record);
    }

    
    public Ahu3 selectByPrimaryKey(Integer id) {
        return ahu3Mapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Ahu3 record) {
        return ahu3Mapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Ahu3 record) {
        return ahu3Mapper.updateByPrimaryKey(record);
    }

}
