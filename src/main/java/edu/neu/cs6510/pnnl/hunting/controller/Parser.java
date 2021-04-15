package edu.neu.cs6510.pnnl.hunting.controller;

import edu.neu.cs6510.pnnl.hunting.mapper.TableUtilMapper;
import edu.neu.cs6510.pnnl.hunting.mapper.VavMapper;
import edu.neu.cs6510.pnnl.hunting.mapper.VavThresholdsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import edu.neu.cs6510.pnnl.hunting.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parser {

    private final String MAPPER_NAMESPACE = "edu.neu.cs6510.pnnl.hunting.mapper.";

    private final Set<String> commonSet = CommonFileName.getFileNameSet();
    private final Set<String> ahuSet = CSVFolderName.getFileNameSet();
    private Common common;
    private Ahu1 ahu1;
    private Ahu2 ahu2;
    private Ahu3 ahu3;
    private Ahu4 ahu4;
    private Ahu1Thresholds ahu1Thresholds;
    private Ahu3Thresholds ahu3Thresholds;
    private Seb seb;
    private LinkedList<Vav> vavList;
    private LinkedList<VavThresholds> vavThresholdsList;
    private Set<String> ahu1VavSet;
    private Set<String> ahu3VavSet;

    private HashMap<String,Integer> commonIndex  = new LinkedHashMap<>();
    private HashMap<String,Integer> ahu1Index  = new LinkedHashMap<>();
    private HashMap<String,Integer> ahu2Index  = new LinkedHashMap<>();
    private HashMap<String,Integer> ahu3Index  = new LinkedHashMap<>();
    private HashMap<String,Integer> ahu4Index  = new LinkedHashMap<>();
    private HashMap<String,Integer> ahu1ThresholdsIndex  = new LinkedHashMap<>();
    private HashMap<String,Integer> ahu3ThresholdsIndex  = new LinkedHashMap<>();
    private HashMap<String,HashMap<String,Integer>> vavIndex  = new LinkedHashMap<>();
    private HashMap<String,HashMap<String,Integer>> vavThresholdsIndex  = new LinkedHashMap<>();

    private SqlSession sqlSession = null;

    public Parser() {
        initData();
    }

    private void initData(){
        common = new Common();
        ahu1 = new Ahu1();
        ahu2 = new Ahu2();
        ahu3 = new Ahu3();
        ahu4 = new Ahu4();
        ahu1Thresholds = new Ahu1Thresholds();
        ahu3Thresholds = new Ahu3Thresholds();
        seb = new Seb();
        vavList = new LinkedList<>();
        vavThresholdsList = new LinkedList<>();
        ahu1VavSet  = new HashSet<>();
        ahu3VavSet  = new HashSet<>();
        try{
         sqlSession = initSession();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private SqlSession initSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        return sqlSessionFactory.openSession();
    }

    private void insertObject(SqlSession session){
        session.insert(MAPPER_NAMESPACE + "CommonMapper.insert",common);
        session.insert(MAPPER_NAMESPACE + "Ahu1Mapper.insert",ahu1);
        session.insert(MAPPER_NAMESPACE + "Ahu2Mapper.insert",ahu2);
        session.insert(MAPPER_NAMESPACE + "Ahu3Mapper.insert",ahu3);
        session.insert(MAPPER_NAMESPACE + "Ahu4Mapper.insert",ahu4);
        session.insert(MAPPER_NAMESPACE + "Ahu1ThresholdsMapper.insert",ahu1Thresholds);
        session.insert(MAPPER_NAMESPACE + "Ahu3ThresholdsMapper.insert",ahu3Thresholds);
        session.commit();
    }

    public void parseAttributes(String[] attributes){
        for (int i = 0; i < attributes.length; i++) {
            if(attributes[i].isEmpty()){
                continue;
            }
            parseAttribute(attributes[i],i);
        }
    }

    private void parseAttribute(String attribute,int index) {
        Path path = Paths.get(attribute);
        int nameCount = path.getNameCount();
        String name = path.getName(0).toString();
        String levelTwoFileName = null;
        String levelThreeFileName = null;
        switch (nameCount){
            case 1:
                if(commonSet.contains(name)){
                    commonIndex.put(name,index);
                }
                break;
            case 3:
                levelTwoFileName = path.getName(1).toString();
                levelThreeFileName = path.getName(2).toString();
                CSVFolderName csvFolderName = CSVFolderName.fromString(levelTwoFileName);
                switch (csvFolderName){
                    case AHU_1:
                        ahu1Index.put(levelThreeFileName,index);
                        break;
                    case AHU_2:
                        ahu2Index.put(levelThreeFileName,index);
                        break;
                    case AHU_3:
                        ahu3Index.put(levelThreeFileName,index);
                        break;
                    case AHU_4:
                        ahu4Index.put(levelThreeFileName,index);
                        break;
                    case AHU1_THRESHOLDS:
                        ahu1ThresholdsIndex.put(levelThreeFileName,index);
                        break;
                    case AHU3_THRESHOLDS:
                        ahu3ThresholdsIndex.put(levelThreeFileName,index);
                        break;
                    default:

                }
                break;
            case 4:
                // e.g AHU1
                levelTwoFileName = path.getName(1).toString();
                // e.g VAV100
                levelThreeFileName = path.getName(2).toString();
                // e.g X.csv
                String levelFourFileName = path.getName(3).toString();
                if(levelThreeFileName.contains(CSVFolderName.VAV_PREFIX.getCsvFileName())){
                    if(levelThreeFileName.contains(CSVFolderName.THRESHOLDS_SUFFIX.getCsvFileName())){
                        //VAV_THRESHOLDS
                        HashMap<String, Integer> curVavThresholdsIndex = vavThresholdsIndex
                                .getOrDefault(levelThreeFileName, new HashMap<>());
                        curVavThresholdsIndex.put(levelFourFileName,index);
                        vavThresholdsIndex.put(levelThreeFileName,curVavThresholdsIndex);

                    }else{
                        //VAV
                        HashMap<String, Integer> curVavIndex = vavIndex
                                .getOrDefault(levelThreeFileName, new HashMap<>());
                        curVavIndex.put(levelFourFileName,index);
                        vavIndex.put(levelThreeFileName,curVavIndex);
                    }
                    CSVFolderName ahuType = CSVFolderName.fromString(levelTwoFileName);
                    if(ahuType.equals(CSVFolderName.AHU_1)){
                        ahu1VavSet.add(levelThreeFileName);
                    }else if(ahuType.equals(CSVFolderName.AHU_3)){
                        ahu3VavSet.add(levelThreeFileName);
                    }

                }
                break;
            default:
                break;
        }

    }

    public void parseRecords(String[] records) {
        handleCommon(records);
        handleAhu1(records);
        handleAhu2(records);
        handleAhu3(records);
        handleAhu4(records);
        handleAhu1Thresholds(records);
        handleAhu3Thresholds(records);

        insertObject(sqlSession);
        handleSeb(records);
        handleVav(records);
        handleThresholdsVav(records);
        insertSeb(sqlSession);
        insertVav(sqlSession);
//        try{
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }finally {
//            if(sqlSession != null){
//                closeSession(sqlSession);
//            }
//        }
//        try{
//            sqlSession = initSession();
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }finally {
//            if(sqlSession != null){
//                closeSession(sqlSession);
//            }
//        }
    }

    private void insertSeb(SqlSession sqlSession) {
        sqlSession.insert(MAPPER_NAMESPACE + "SebMapper.insert",seb);
        sqlSession.commit();
    }

    private void handleSeb(String[] records) {
        seb.setAhu1Id(ahu1.getId());
        seb.setAhu2Id(ahu2.getId());
        seb.setAhu3Id(ahu3.getId());
        seb.setAhu4Id(ahu4.getId());
        seb.setAhu1ThresholdsId(ahu1Thresholds.getId());
        seb.setAhu3ThresholdsId(ahu3Thresholds.getId());
        seb.setCommonId(common.getId());

    }

    private void insertVav(SqlSession sqlSession) {
        TableUtilMapper tableUtilMapper = sqlSession.getMapper(TableUtilMapper.class);
        VavMapper vavMapper = sqlSession.getMapper(VavMapper.class);
        VavThresholdsMapper vavThresholdsMapper = sqlSession.getMapper(VavThresholdsMapper.class);
        for(Vav vav:vavList){
            String vavName = convertToVavTableName(vav.getVavName());
            // Create tables if it not exist
            // Comment them out will improve performance
//            int count = tableUtilMapper.existTable(vavName);
//            if(count == 0){
//                tableUtilMapper.createNewVavTable(vavName);
//            }
            vavMapper.insert(vav,vavName);
        }
        vavList.clear();
        for (VavThresholds vavThresholds: vavThresholdsList){
            String vavName = convertToVavThresholdsTableName(vavThresholds.getVavName());
//            int count = tableUtilMapper.existTable(vavName);
//            if(count == 0){
//                tableUtilMapper.createNewVavThresholdsTable(vavName);
//            }
            vavThresholdsMapper.insert(vavThresholds,vavName);
        }
        vavThresholdsList.clear();
        sqlSession.commit();
    }

    private String convertToVavThresholdsTableName(String vavName) {
        return vavName.toLowerCase(Locale.ROOT);
    }

    private String convertToVavTableName(String vavName) {
        return vavName.toLowerCase(Locale.ROOT);
    }

    private void handleThresholdsVav(String[] records) {
        for(Map.Entry<String,HashMap<String,Integer>> vavIndexEntry: vavThresholdsIndex.entrySet()){
            String vavName = vavIndexEntry.getKey();
            VavThresholds vavThresholds = new VavThresholds();
            vavThresholds.setVavName(vavName);
            if(ahu1VavSet.contains(vavName)){
                vavThresholds.setAhu1Id(ahu1.getId());
            }else if(ahu3VavSet.contains(vavName)){
                vavThresholds.setAhu3Id(ahu3.getId());
            }
            for(Map.Entry<String,Integer> indexEntry : vavIndexEntry.getValue().entrySet()){
                CSVFileName csvFileName = CSVFileName.fromString(indexEntry.getKey());
                String value = records[indexEntry.getValue()];
                switch (csvFileName){
                    case MAXIMUM_ZONE_AIR_FLOW:
                        vavThresholds.setMaximumZoneAirFlow(Double.parseDouble(value));
                        break;
                    case MINIMUM_ZONE_AIR_FLOW:
                        vavThresholds.setMinimumZoneAirFlow(Double.parseDouble(value));
                        break;
                    case ZONE_AIR_FLOW_SET_POINT_OFFSET:
                        vavThresholds.setZoneAirFlowSetPointOffset(Double.parseDouble(value));
                        break;
                    case ZONE_REHEAT_AIR_FLOW:
                        vavThresholds.setZoneReheatAirFlow(Double.parseDouble(value));
                        break;
                    default:
                }
            }
            vavThresholdsList.add(vavThresholds);
        }

    }

    private void handleVav(String[] records) {
        for(Map.Entry<String,HashMap<String,Integer>> vavIndexEntry: vavIndex.entrySet()){
            String vavName = vavIndexEntry.getKey();
            Vav vav = new Vav();
            vav.setVavName(vavName);
            if(ahu1VavSet.contains(vavName)){
                vav.setAhu1Id(ahu1.getId());
            }else if(ahu3VavSet.contains(vavName)){
                vav.setAhu3Id(ahu3.getId());
            }
            for(Map.Entry<String,Integer> indexEntry : vavIndexEntry.getValue().entrySet()){
                CSVFileName csvFileName = CSVFileName.fromString(indexEntry.getKey());
                String value = records[indexEntry.getValue()];
                switch (csvFileName){
                    case ZONE_AIR_FLOW:
                        vav.setZoneAirFlow(Double.parseDouble(value));
                        break;
                    case ZONE_COOLING_AIR_FLOW_SET_POINT:
                        vav.setZoneCoolingAirFlowSetPoint(Double.parseDouble(value));
                        break;
                    case ZONE_COOLING_TEMPERATURE_SET_POINT:
                        vav.setZoneCoolingTemperatureSetPoint(Double.parseDouble(value));
                        break;
                    case ZONE_HEATING_TEMPERATURE_SET_POINT:
                        vav.setZoneHeatingTemperatureSetPoint(Double.parseDouble(value));
                        break;
                    case ZONE_TEMPERATURE:
                        vav.setZoneTemperature(Double.parseDouble(value));
                        break;
                    default:
                }
            }
            vavList.add(vav);
        }
    }

    private void handleCommon(String[] records) {
        for(Map.Entry<String,Integer> indexEntry: commonIndex.entrySet()){
            CommonFileName commonFileName = CommonFileName.fromString(indexEntry.getKey());
            String value = records[indexEntry.getValue()];
            switch (commonFileName){
                case EXTERNAL_TEMPERATURE:
                    common.setExternalTemperature(Double.parseDouble(value));
                    break;
                case IS_SUMMER:
                    common.setIsSummer(value.equals("1"));
                    break;
                case IS_WEEKDAY:
                    common.setIsWeekday(value.equals("1"));
                    break;
                case IS_WORKING_HOUR:
                    common.setIsWorkingHours(value.equals("1"));
                    break;
                case COOLING_CONSUMPTION:
                    common.setCoolingConsumption(Double.parseDouble(value));
                    break;
                case HEATING_CONSUMPTION:
                    common.setHeatingConsumption(Double.parseDouble(value));
                    break;
                case TIME:
                    // 11/30/18 17:09
                    // Note: sample csv and full csv have different format of date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    try {
                        Date date = dateFormat.parse(value);
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Timestamp timestamp = Timestamp.valueOf(format.format(date));
                        common.setTime(timestamp);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    break;
                default:
            }
        }
    }

    private void handleAhu3Thresholds(String[] records) {
        for(Map.Entry<String,Integer> indexEntry: ahu3ThresholdsIndex.entrySet()){
            CSVFileName csvFileName = CSVFileName.fromString(indexEntry.getKey());
            String value = records[indexEntry.getValue()];
            switch (csvFileName){
                case MINIMUM_OUTDOOR_AIR_FLOW:
                    ahu3Thresholds.setMinimumOutdoorAirFlow(Double.parseDouble(value));
                    break;
                case MINIMUM_OUTDOOR_AIR_FLOW_SET_POINT:
                    ahu3Thresholds.setMinimumOutdoorAirFlowSetPoint(Double.parseDouble(value));
                    break;
                case EXHAUST_AIR_FLOW_SET_POINT:
                    ahu3Thresholds.setExhaustAirFlowSetPoint(Double.parseDouble(value));
                    break;
                default:
            }
        }
    }

    private void handleAhu1Thresholds(String[] records) {
        for(Map.Entry<String,Integer> indexEntry: ahu1ThresholdsIndex.entrySet()){
            CSVFileName csvFileName = CSVFileName.fromString(indexEntry.getKey());
            String value = records[indexEntry.getValue()];
            switch (csvFileName){
                case MINIMUM_OUTDOOR_AIR_FLOW_SET_POINT:
                    ahu1Thresholds.setMinimumOutdoorAirFlowSetPoint(Double.parseDouble(value));
                    break;
                default:
            }
        }
    }

    private void handleAhu4(String[] records) {
        for(Map.Entry<String,Integer> indexEntry: ahu4Index.entrySet()){
            CSVFileName csvFileName = CSVFileName.fromString(indexEntry.getKey());
            String value = records[indexEntry.getValue()];
            switch (csvFileName){
                case EXHAUST_AIR_FLOW:
                    ahu4.setExhaustAirFlow(Double.parseDouble(value));
                    break;
                case EXHAUST_AIR_FLOW_SET_POINT:
                    ahu4.setExhaustAirFlowSetPoint(Double.parseDouble(value));
                    break;
                case MAXIMUM_DISCHARGE_AIR_FLOW_SET_POINT:
                    ahu4.setMaximumDischargeAirFlowSetPoint(Double.parseDouble(value));
                    break;
                case MINIMUM_DISCHARGE_AIR_FLOW_SET_POINT:
                    ahu4.setMinimumDischargeAirFlowSetPoint(Double.parseDouble(value));
                    break;
                case SUPPLY_AIR_FLOW:
                    ahu4.setSupplyAirFlow(Double.parseDouble(value));
                    break;
                case UNOCCUPIED_ZONE_HEATING_TEMPERATURE_SET_POINT:
                    ahu4.setUnoccupiedZoneHeatingTemperatureSetPoint(Double.parseDouble(value));
                    break;
                case ZONE_COOLING_TEMPERATURE_SET_POINT:
                    ahu4.setZoneCoolingTemperatureSetPoint(Double.parseDouble(value));
                    break;
                case ZONE_HEATING_TEMPERATURE_SET_POINT:
                    ahu4.setZoneHeatingTemperatureSetPoint(Double.parseDouble(value));
                    break;
                case ZONE_TEMPERATURE:
                    ahu4.setZoneTemperature(Double.parseDouble(value));
                    break;
                case ZONE_OUTDOOR_AIR_FLOW:
                    ahu4.setZoneOutdoorAirFlow(Double.parseDouble(value));
                    break;
                default:
            }
        }
    }

    private void handleAhu3(String[] records) {
        for(Map.Entry<String,Integer> indexEntry: ahu3Index.entrySet()){
            CSVFileName csvFileName = CSVFileName.fromString(indexEntry.getKey());
            String value = records[indexEntry.getValue()];
            switch (csvFileName){
                case DISCHARGE_AIR_FLOW:
                    ahu3.setDischargeAirFlow(Double.parseDouble(value));
                    break;
                case EXHAUST_AIR_FLOW:
                    ahu3.setExhaustAirFlow(Double.parseDouble(value));
                    break;
                case OUTDOOR_AIR_FLOW:
                    ahu3.setOutdoorAirFlow(Double.parseDouble(value));
                    break;
                case MINIMUM_OUTDOOR_AIR_FLOW:
                    ahu3.setMinimumOutdoorAirFlow(Double.parseDouble(value));
                    break;
                default:
            }
        }
    }

    private void handleAhu2(String[] records) {
        for(Map.Entry<String,Integer> indexEntry: ahu2Index.entrySet()){
            CSVFileName csvFileName = CSVFileName.fromString(indexEntry.getKey());
            String value = records[indexEntry.getValue()];
            switch (csvFileName){
                case RETURN_AIR_FLOW:
                    ahu2.setReturnAirFlow(Double.parseDouble(value));
                    break;
                case SUPPLY_AIR_FLOW:
                    ahu2.setSupplyAirFlow(Double.parseDouble(value));
                    break;
                case SUPPLY_AIR_FLOW_SET_POINT:
                    ahu2.setSupplyAirFlowSetPoint(Double.parseDouble(value));
                    break;
                case ZONE_COOLING_TEMPERATURE_SET_POINT:
                    ahu2.setZoneCoolingTemperatureSetPoint(Double.parseDouble(value));
                    break;
                case ZONE_HEATING_TEMPERATURE_SET_POINT:
                    ahu2.setZoneHeatingTemperatureSetPoint(Double.parseDouble(value));
                    break;
                case ZONE_TEMPERATURE:
                    ahu2.setZoneTemperature(Double.parseDouble(value));
                    break;
                case OUTDOOR_AIR_FLOW:
                    ahu2.setOutdoorAirFlow(Double.parseDouble(value));
                    break;
                default:
            }
        }
    }

    private void handleAhu1(String[] records) {
        for(Map.Entry<String,Integer> indexEntry: ahu1Index.entrySet()){
            CSVFileName csvFileName = CSVFileName.fromString(indexEntry.getKey());
            String value = records[indexEntry.getValue()];
            switch (csvFileName){
                case DISCHARGE_AIR_FLOW:
                    ahu1.setDischargeAirFlow(Double.parseDouble(value));
                    break;
                case EXHAUST_AIR_FLOW_SET_POINT:
                    ahu1.setExhaustAirFlowSetPoint(Double.parseDouble(value));
                    break;
                case OUTDOOR_AIR_FLOW:
                    ahu1.setOutdoorAirFlow(Double.parseDouble(value));
                    break;
            }
        }
    }

    private void closeSession(SqlSession session){
        session.close();
    }

}
