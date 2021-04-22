package edu.neu.cs6510.pnnl.hunting.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.Ahu4Mapper;
import edu.neu.cs6510.pnnl.hunting.model.Ahu4;
import edu.neu.cs6510.pnnl.hunting.service.Ahu4Service;
@Service
public class Ahu4ServiceImpl implements Ahu4Service{

    @Resource
    private Ahu4Mapper ahu4Mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ahu4Mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Ahu4 record) {
        return ahu4Mapper.insert(record);
    }

    @Override
    public int insertSelective(Ahu4 record) {
        return ahu4Mapper.insertSelective(record);
    }

    @Override
    public Ahu4 selectByPrimaryKey(Integer id) {
        return ahu4Mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Ahu4 record) {
        return ahu4Mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Ahu4 record) {
        return ahu4Mapper.updateByPrimaryKey(record);
    }

}
