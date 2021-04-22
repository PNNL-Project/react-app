package edu.neu.cs6510.pnnl.hunting.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.model.Ahu2;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu2Mapper;
import edu.neu.cs6510.pnnl.hunting.service.Ahu2Service;
@Service
public class Ahu2ServiceImpl implements Ahu2Service{

    @Resource
    private Ahu2Mapper ahu2Mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ahu2Mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Ahu2 record) {
        return ahu2Mapper.insert(record);
    }

    @Override
    public int insertSelective(Ahu2 record) {
        return ahu2Mapper.insertSelective(record);
    }

    @Override
    public Ahu2 selectByPrimaryKey(Integer id) {
        return ahu2Mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Ahu2 record) {
        return ahu2Mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Ahu2 record) {
        return ahu2Mapper.updateByPrimaryKey(record);
    }

}
