package edu.neu.cs6510.pnnl.hunting.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu1Mapper;
import edu.neu.cs6510.pnnl.hunting.model.Ahu1;
import edu.neu.cs6510.pnnl.hunting.service.Ahu1Service;
@Service
public class Ahu1ServiceImpl implements Ahu1Service{

    @Resource
    private Ahu1Mapper ahu1Mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ahu1Mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Ahu1 record) {
        return ahu1Mapper.insert(record);
    }

    @Override
    public int insertSelective(Ahu1 record) {
        return ahu1Mapper.insertSelective(record);
    }

    @Override
    public Ahu1 selectByPrimaryKey(Integer id) {
        return ahu1Mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Ahu1 record) {
        return ahu1Mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Ahu1 record) {
        return ahu1Mapper.updateByPrimaryKey(record);
    }

}
