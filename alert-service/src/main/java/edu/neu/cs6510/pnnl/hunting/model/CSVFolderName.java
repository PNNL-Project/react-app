package edu.neu.cs6510.pnnl.hunting.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum CSVFolderName {
    SEB("SEB"),
    AHU_1("AHU1"),
    AHU1_THRESHOLDS("AHU1_THRESHOLDS"),
    AHU_2("AHU2"),
    AHU_3("AHU3"),
    AHU3_THRESHOLDS("AHU3_THRESHOLDS"),
    AHU_4("AHU4"),
    VAV_PREFIX("VAV"),
    THRESHOLDS_SUFFIX("_THRESHOLDS"),
    UNKNOWN("");


    private String csvFileName;


    CSVFolderName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public String getCsvFileName() {
        return csvFileName;
    }

    public static Set<String> getFileNameSet(){
        Set<String> res = new HashSet<>();
        CSVFolderName[] values = CSVFolderName.values();
        for (CSVFolderName value: values) {
            res.add(value.getCsvFileName());
        }
        res.remove(VAV_PREFIX.getCsvFileName());
        res.remove(THRESHOLDS_SUFFIX.getCsvFileName());
        res.remove(SEB.getCsvFileName());
        return res;
    }

    public static CSVFolderName fromString(String str){
        CSVFolderName[] values = CSVFolderName.values();
        return Arrays.stream(values)
                .filter(folderName -> folderName.getCsvFileName().equals(str))
                .findFirst().orElse(UNKNOWN);
    }

}
