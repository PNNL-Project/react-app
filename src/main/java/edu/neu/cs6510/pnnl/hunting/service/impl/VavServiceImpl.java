package edu.neu.cs6510.pnnl.hunting.service.impl;

import edu.neu.cs6510.pnnl.hunting.utils.DateUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import edu.neu.cs6510.pnnl.hunting.mapper.VavMapper;
import edu.neu.cs6510.pnnl.hunting.model.Vav;
import edu.neu.cs6510.pnnl.hunting.service.VavService;

import java.util.*;

@Service
public class VavServiceImpl implements VavService{

    // FIXME Should save this relationship into external file
    Set<String> vav1Set = new HashSet<>(Arrays.asList("vav127b","vav127a","vav133_thresholds","vav143_thresholds","vav100_thresholds","vav136_thresholds","vav123b","vav120_thresholds","vav123a","vav118","vav119_thresholds","vav129_thresholds","vav119","vav150_thresholds","vav136","vav102_thresholds","vav131","vav127b_thresholds","vav133","vav121_thresholds","vav150","vav123a_thresholds","vav131_thresholds","vav127a_thresholds","vav118_thresholds","vav129","vav123b_thresholds","vav102","vav142_thresholds","vav120","vav142","vav121","vav143","vav100"));
    Set<String> vav3Set = new HashSet<>(Arrays.asList("vav104_thresholds","vav107","vav105_thresholds","vav108","vav104","vav105","vav116","vav108_thresholds","vav107_thresholds","vav112","vav112_thresholds","vav116_thresholds"));

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
//    @Cacheable("vavs")
    @Override
    public List<Vav> getVavInRange(Date start, Date end, String vavTableName) {
        // Add single quotation marks before and after time string
        String startString = addQuote(start);
        String endString = addQuote(end);
        if(vav1Set.contains(vavTableName)){
            return vavMapper.getVav1InRange(startString, endString, vavTableName);
        }else if(vav3Set.contains(vavTableName)) {
            return vavMapper.getVav3InRange(startString, endString, vavTableName);
        }else {
            return null;
        }

    }

    private String addQuote(Date date) {
        return "'" + DateUtil.convertDateToString(date) + "'";
    }

}
