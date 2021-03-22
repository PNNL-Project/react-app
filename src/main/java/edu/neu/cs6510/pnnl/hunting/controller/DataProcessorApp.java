package edu.neu.cs6510.pnnl.hunting.controller;

import edu.neu.cs6510.pnnl.hunting.mapper.CommonMapper;
import edu.neu.cs6510.pnnl.hunting.model.Common;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;

public class DataProcessorApp {

    public static Parser parser = new Parser();
    private static final double THRESHOLD =  0.000001;
    public static void main(String[] args) {
        // FIXME Avoid hard code style
//        Path path = Paths.get("src/main/resources/seb_processed_data.csv");
        String CSVPathName = "src/main/resources/static/seb_processed_data_sample_2.csv";
//        String CSVPathName = "/Users/Shu/Downloads/seb/seb.processed/seb_processed_data.csv";
        int count = countLineNumber(CSVPathName);


        Path path = Paths.get(CSVPathName);
        Examination.start();
        try(BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line = br.readLine();
            String[] attributes = line.split(",");
            parser.parseAttributes(attributes);
            line = br.readLine();
            while(line!= null){
                String[] records = line.split(",");
                parser.parseRecords(records);
                line = br.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        Examination.end();
        System.out.print(path.toString());
    }

    public static int countLineNumber(String pathName) {
        int lines = 0;

        try {

            File file = new File(pathName);
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
            lineNumberReader.skip(Long.MAX_VALUE);
            lines = lineNumberReader.getLineNumber();
            lineNumberReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException Occurred" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException Occurred" + e.getMessage());
        }

        return lines;
    }


}



