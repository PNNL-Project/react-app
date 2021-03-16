package edu.neu.cs6510.pnnl.hunting.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.model.Ahu3;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu3Mapper;
import edu.neu.cs6510.pnnl.hunting.service.Ahu3Service;
@Service
public class Ahu3ServiceImpl implements Ahu3Service{

    @Resource
    private Ahu3Mapper ahu3Mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ahu3Mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Ahu3 record) {
        return ahu3Mapper.insert(record);
    }

    @Override
    public int insertSelective(Ahu3 record) {
        return ahu3Mapper.insertSelective(record);
    }

    @Override
    public Ahu3 selectByPrimaryKey(Integer id) {
        return ahu3Mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Ahu3 record) {
        return ahu3Mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Ahu3 record) {
        return ahu3Mapper.updateByPrimaryKey(record);
    }

}
