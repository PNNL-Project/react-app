package edu.neu.cs6510.pnnl.hunting.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.VavMapper;
import edu.neu.cs6510.pnnl.hunting.model.Vav;
@Service
public class VavService{

    @Resource
    private VavMapper vavMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return vavMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Vav record) {
        return vavMapper.insert(record);
    }

    
    public int insertSelective(Vav record) {
        return vavMapper.insertSelective(record);
    }

    
    public Vav selectByPrimaryKey(Integer id) {
        return vavMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Vav record) {
        return vavMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Vav record) {
        return vavMapper.updateByPrimaryKey(record);
    }

}
