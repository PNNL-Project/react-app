package edu.neu.cs6510.pnnl.hunting.service.impl;

import edu.neu.cs6510.pnnl.hunting.utils.DateUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.VavMapper;
import edu.neu.cs6510.pnnl.hunting.model.Vav;
import edu.neu.cs6510.pnnl.hunting.service.VavService;

import java.util.Date;
import java.util.List;

@Service
public class VavServiceImpl implements VavService{

    @Resource
    private VavMapper vavMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return vavMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Vav record, String vavTableName) {
        return vavMapper.insert(record,vavTableName);
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

    @Override
    public List<Double> getZoneAirFlowInRange(Date start, Date end, String vavTableName) {

        // Add single quotation marks before and after time string
        String startString = addQuote(start);
        String endString = addQuote(end);
        return vavMapper.getZoneAirFlowInRange(startString,endString,vavTableName);
    }

    @Override
    public List<Double> getZoneTemperatureInRange(Date start, Date end, String vavTableName) {
        // Add single quotation marks before and after time string
        String startString = addQuote(start);
        String endString = addQuote(end);
        return vavMapper.getZoneTemperatureInRange(startString,endString,vavTableName);
    }

    @Override
    public List<Double> getZoneCoolingTemperatureSetPointInRange(Date start, Date end, String vavTableName) {
        // Add single quotation marks before and after time string
        String startString = addQuote(start);
        String endString = addQuote(end);
        return vavMapper.getZoneCoolingTemperatureSetPointInRange(startString,endString,vavTableName);
    }

    @Override
    public List<Double> getZoneHeatingTemperatureSetPointInRange(Date start, Date end, String vavTableName) {
        // Add single quotation marks before and after time string
        String startString = addQuote(start);
        String endString = addQuote(end);
        return vavMapper.getZoneHeatingTemperatureSetPointInRange(startString,endString,vavTableName);
    }
    @Cacheable("vavs")
    @Override
    public List<Vav> getVavInRange(Date start, Date end, String vavTableName) {
        System.out.println("Retrieving tasks");
        // Add single quotation marks before and after time string
        String startString = addQuote(start);
        String endString = addQuote(end);
        return vavMapper.getVavInRange(startString,endString,vavTableName);
    }

    private String addQuote(Date date) {
        return "'" + DateUtil.convertDateToString(date) + "'";
    }

}
