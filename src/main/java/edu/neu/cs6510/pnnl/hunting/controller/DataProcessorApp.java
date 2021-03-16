//package edu.neu.cs6510.pnnl.hunting.controller;
//
//import edu.neu.cs6510.pnnl.hunting.mapper.CommonMapper;
//import edu.neu.cs6510.pnnl.hunting.model.Common;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.testng.annotations.Test;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Date;
//import java.util.LinkedList;
//
//public class DataProcessorApp {
//
//    public static Parser parser = new Parser();
//
//    public static void main(String[] args) {
//        // FIXME Avoid hard code style
////        Path path = Paths.get("src/main/resources/seb_processed_data.csv");
//        Path path = Paths.get("src/main/resources/seb_processed_data_sample.csv");
//        Examination.start();
//        try(BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
//            String line = br.readLine();
//            String[] attributes = line.split(",");
//            parser.parseAttributes(attributes);
//            line = br.readLine();
//
//            while(line!= null){
//                String[] records = line.split(",");
//                parser.parseRecords(records);
//                line = br.readLine();
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        Examination.end();
//        System.out.print(path.toString());
//    }
//
//
//    @Test
//    public void testMybaits() throws IOException {
//        // FIXME Avoid hard code style
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        SqlSession session = sqlSessionFactory.openSession();
//
//        Common common1 = new Common();
//        common1.setIsSummer(true);
//        common1.setIsWorkingHours(true);
//        common1.setHeatingConsumption(1.1d);
//        common1.setTime(new Date());
//        common1.setCoolingConsumption(1.2d);
//        common1.setExternalTemperature(1.3d);
//        common1.setIsWeekday(false);
//        session.insert("xyz.drshu.cs6510.dataProcessor.mapper.CommonMapper.insert",common1);
//
//        session.commit();
//
//        session.close();
//    }
//
//
////    @Test
////    public void testDataLoad() throws IOException {
////        String resource = "mybatis-config.xml";
////        InputStream inputStream = Resources.getResourceAsStream(resource);
////        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
////
////        SqlSession session = sqlSessionFactory.openSession();
////        LinkedList<Common> commons = new LinkedList<>();
////        CommonMapper mapper = session.getMapper(CommonMapper.class);
////
////
////    }
//}
//
//
//
