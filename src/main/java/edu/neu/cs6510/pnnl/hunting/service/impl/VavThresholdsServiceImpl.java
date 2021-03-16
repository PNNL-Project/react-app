package edu.neu.cs6510.pnnl.hunting.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.VavThresholdsMapper;
import edu.neu.cs6510.pnnl.hunting.model.VavThresholds;
import edu.neu.cs6510.pnnl.hunting.service.VavThresholdsService;
@Service
public class VavThresholdsServiceImpl implements VavThresholdsService{

    @Resource
    private VavThresholdsMapper vavThresholdsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return vavThresholdsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VavThresholds record) {
        return vavThresholdsMapper.insert(record);
    }

    @Override
    public int insertSelective(VavThresholds record) {
        return vavThresholdsMapper.insertSelective(record);
    }

    @Override
    public VavThresholds selectByPrimaryKey(Integer id) {
        return vavThresholdsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(VavThresholds record) {
        return vavThresholdsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(VavThresholds record) {
        return vavThresholdsMapper.updateByPrimaryKey(record);
    }

}
