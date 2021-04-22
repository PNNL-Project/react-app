package edu.neu.cs6510.pnnl.hunting.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.model.Common;
import edu.neu.cs6510.pnnl.hunting.mapper.CommonMapper;
import edu.neu.cs6510.pnnl.hunting.service.CommonService;
@Service
public class CommonServiceImpl implements CommonService{

    @Resource
    private CommonMapper commonMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commonMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Common record) {
        return commonMapper.insert(record);
    }

    @Override
    public int insertSelective(Common record) {
        return commonMapper.insertSelective(record);
    }

    @Override
    public Common selectByPrimaryKey(Integer id) {
        return commonMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Common record) {
        return commonMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Common record) {
        return commonMapper.updateByPrimaryKey(record);
    }

}
