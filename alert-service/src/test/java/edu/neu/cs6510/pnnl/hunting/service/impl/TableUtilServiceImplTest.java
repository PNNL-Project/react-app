package edu.neu.cs6510.pnnl.hunting.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableUtilServiceImplTest {
    TableUtilServiceImpl service = new TableUtilServiceImpl();
    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllTable() {
    }

    @Test
    void getAllVavTable() {
        List<String> allVavTable = service.getAllVavTable();
        for(String table:allVavTable){
            System.out.println(table);
        }
    }
}