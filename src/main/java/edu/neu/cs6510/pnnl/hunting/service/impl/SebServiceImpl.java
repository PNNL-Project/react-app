package edu.neu.cs6510.pnnl.hunting.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.SebMapper;
import edu.neu.cs6510.pnnl.hunting.model.Seb;
import edu.neu.cs6510.pnnl.hunting.service.SebService;
@Service
public class SebServiceImpl implements SebService{

    @Resource
    private SebMapper sebMapper;

    @Override
    public int insert(Seb record) {
        return sebMapper.insert(record);
    }

    @Override
    public int insertSelective(Seb record) {
        return sebMapper.insertSelective(record);
    }

}
