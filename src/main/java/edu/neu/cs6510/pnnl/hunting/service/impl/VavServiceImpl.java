package edu.neu.cs6510.pnnl.hunting.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.VavMapper;
import edu.neu.cs6510.pnnl.hunting.model.Vav;
import edu.neu.cs6510.pnnl.hunting.service.VavService;
@Service
public class VavServiceImpl implements VavService{

    @Resource
    private VavMapper vavMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return vavMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Vav record) {
        return vavMapper.insert(record);
    }

    @Override
    public int insertSelective(Vav record) {
        return vavMapper.insertSelective(record);
    }

    @Override
    public Vav selectByPrimaryKey(Integer id) {
        return vavMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Vav record) {
        return vavMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Vav record) {
        return vavMapper.updateByPrimaryKey(record);
    }

}
