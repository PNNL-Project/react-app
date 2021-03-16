package edu.neu.cs6510.pnnl.hunting.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu1ThresholdsMapper;
import edu.neu.cs6510.pnnl.hunting.model.Ahu1Thresholds;
import edu.neu.cs6510.pnnl.hunting.service.Ahu1ThresholdsService;
@Service
public class Ahu1ThresholdsServiceImpl implements Ahu1ThresholdsService{

    @Resource
    private Ahu1ThresholdsMapper ahu1ThresholdsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ahu1ThresholdsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Ahu1Thresholds record) {
        return ahu1ThresholdsMapper.insert(record);
    }

    @Override
    public int insertSelective(Ahu1Thresholds record) {
        return ahu1ThresholdsMapper.insertSelective(record);
    }

    @Override
    public Ahu1Thresholds selectByPrimaryKey(Integer id) {
        return ahu1ThresholdsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Ahu1Thresholds record) {
        return ahu1ThresholdsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Ahu1Thresholds record) {
        return ahu1ThresholdsMapper.updateByPrimaryKey(record);
    }

}
