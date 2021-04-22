package edu.neu.cs6510.pnnl.hunting.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.model.Ahu3Thresholds;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu3ThresholdsMapper;
import edu.neu.cs6510.pnnl.hunting.service.Ahu3ThresholdsService;
@Service
public class Ahu3ThresholdsServiceImpl implements Ahu3ThresholdsService{

    @Resource
    private Ahu3ThresholdsMapper ahu3ThresholdsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ahu3ThresholdsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Ahu3Thresholds record) {
        return ahu3ThresholdsMapper.insert(record);
    }

    @Override
    public int insertSelective(Ahu3Thresholds record) {
        return ahu3ThresholdsMapper.insertSelective(record);
    }

    @Override
    public Ahu3Thresholds selectByPrimaryKey(Integer id) {
        return ahu3ThresholdsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Ahu3Thresholds record) {
        return ahu3ThresholdsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Ahu3Thresholds record) {
        return ahu3ThresholdsMapper.updateByPrimaryKey(record);
    }

}
