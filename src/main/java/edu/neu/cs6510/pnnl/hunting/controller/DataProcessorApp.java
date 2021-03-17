package edu.neu.cs6510.pnnl.hunting.controller;

import edu.neu.cs6510.pnnl.hunting.mapper.CommonMapper;
import edu.neu.cs6510.pnnl.hunting.model.Common;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;

public class DataProcessorApp {

    public static Parser parser = new Parser();

    public static void main(String[] args) {
        // FIXME Avoid hard code style
//        Path path = Paths.get("src/main/resources/seb_processed_data.csv");
        Path path = Paths.get("src/main/resources/seb_processed_data_sample_2.csv");
        Examination.start();
        try(BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line = br.readLine();
            String[] attributes = line.split(",");
            parser.parseAttributes(attributes);
            line = br.readLine();

            while(line!= null){
                line = br.readLine();
                String[] records = line.split(",");
                parser.parseRecords(records);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        Examination.end();
        System.out.print(path.toString());
    }


}



