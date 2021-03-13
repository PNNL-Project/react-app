package edu.neu.cs6510.pnnl.hunting.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu1ThresholdsMapper;
import edu.neu.cs6510.pnnl.hunting.model.Ahu1Thresholds;
@Service
public class Ahu1ThresholdsService{

    @Resource
    private Ahu1ThresholdsMapper ahu1ThresholdsMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return ahu1ThresholdsMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Ahu1Thresholds record) {
        return ahu1ThresholdsMapper.insert(record);
    }

    
    public int insertSelective(Ahu1Thresholds record) {
        return ahu1ThresholdsMapper.insertSelective(record);
    }

    
    public Ahu1Thresholds selectByPrimaryKey(Integer id) {
        return ahu1ThresholdsMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Ahu1Thresholds record) {
        return ahu1ThresholdsMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Ahu1Thresholds record) {
        return ahu1ThresholdsMapper.updateByPrimaryKey(record);
    }

}
