package edu.neu.cs6510.pnnl.hunting.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.model.Common;
import edu.neu.cs6510.pnnl.hunting.mapper.CommonMapper;
@Service
public class CommonService{

    @Resource
    private CommonMapper commonMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return commonMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Common record) {
        return commonMapper.insert(record);
    }

    
    public int insertSelective(Common record) {
        return commonMapper.insertSelective(record);
    }

    
    public Common selectByPrimaryKey(Integer id) {
        return commonMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Common record) {
        return commonMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Common record) {
        return commonMapper.updateByPrimaryKey(record);
    }

}
