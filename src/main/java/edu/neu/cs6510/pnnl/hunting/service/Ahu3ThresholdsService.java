package edu.neu.cs6510.pnnl.hunting.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.model.Ahu3Thresholds;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu3ThresholdsMapper;
@Service
public class Ahu3ThresholdsService{

    @Resource
    private Ahu3ThresholdsMapper ahu3ThresholdsMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return ahu3ThresholdsMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Ahu3Thresholds record) {
        return ahu3ThresholdsMapper.insert(record);
    }

    
    public int insertSelective(Ahu3Thresholds record) {
        return ahu3ThresholdsMapper.insertSelective(record);
    }

    
    public Ahu3Thresholds selectByPrimaryKey(Integer id) {
        return ahu3ThresholdsMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Ahu3Thresholds record) {
        return ahu3ThresholdsMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Ahu3Thresholds record) {
        return ahu3ThresholdsMapper.updateByPrimaryKey(record);
    }

}
