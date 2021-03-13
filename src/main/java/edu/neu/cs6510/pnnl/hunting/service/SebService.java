package edu.neu.cs6510.pnnl.hunting.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.SebMapper;
import edu.neu.cs6510.pnnl.hunting.model.Seb;
@Service
public class SebService{

    @Resource
    private SebMapper sebMapper;

    
    public int insert(Seb record) {
        return sebMapper.insert(record);
    }

    
    public int insertSelective(Seb record) {
        return sebMapper.insertSelective(record);
    }

}
