package edu.neu.cs6510.pnnl.hunting.model;

import lombok.Data;

import java.util.Date;

@Data
public class Alert {

    private Integer id;

    private Date time;

    private String vavName;


}
