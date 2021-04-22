package edu.neu.cs6510.pnnl.hunting.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum CommonFileName {

    //Common Key
    EXTERNAL_TEMPERATURE("External Temperature"),
    IS_SUMMER("Summer"),
    IS_WEEKDAY("Weekday"),
    IS_WORKING_HOUR("Working Hours"),
    COOLING_CONSUMPTION("cooling_consumption"),
    HEATING_CONSUMPTION("heating_consumption"),
    TIME("time"),
    UNKNOWN("");

    private String fileName;

    CommonFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public static Set<String> getFileNameSet(){
        Set<String> res = new HashSet<>();
        CommonFileName[] values = CommonFileName.values();
        for (CommonFileName value: values) {
            res.add(value.getFileName());
        }
        return res;
    }

    public static CommonFileName fromString(String str){
        CommonFileName[] values = CommonFileName.values();
        return Arrays.stream(values)
                .filter(fileName -> fileName.getFileName().equals(str))
                .findFirst().orElse(UNKNOWN);
    }

}
