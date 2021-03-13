package edu.neu.cs6510.pnnl.hunting.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.VavThresholdsMapper;
import edu.neu.cs6510.pnnl.hunting.model.VavThresholds;
@Service
public class VavThresholdsService{

    @Resource
    private VavThresholdsMapper vavThresholdsMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return vavThresholdsMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(VavThresholds record) {
        return vavThresholdsMapper.insert(record);
    }

    
    public int insertSelective(VavThresholds record) {
        return vavThresholdsMapper.insertSelective(record);
    }

    
    public VavThresholds selectByPrimaryKey(Integer id) {
        return vavThresholdsMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(VavThresholds record) {
        return vavThresholdsMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(VavThresholds record) {
        return vavThresholdsMapper.updateByPrimaryKey(record);
    }

}
