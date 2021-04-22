package edu.neu.cs6510.pnnl.hunting.model;

import java.util.Arrays;

public enum CSVFileName {

    DISCHARGE_AIR_FLOW("DischargeAirFlow.csv"),
    EXHAUST_AIR_FLOW_SET_POINT( "ExhaustAirFlowSetPoint.csv" ),
    OUTDOOR_AIR_FLOW( "OutdoorAirFlow.csv" ),
    ZONE_AIR_FLOW( "ZoneAirFlow.csv" ),
    ZONE_COOLING_AIR_FLOW_SET_POINT( "ZoneCoolingAirFlowSetPoint.csv" ),
    ZONE_COOLING_TEMPERATURE_SET_POINT( "ZoneCoolingTemperatureSetPoint.csv" ),
    ZONE_HEATING_TEMPERATURE_SET_POINT( "ZoneHeatingTemperatureSetPoint.csv" ),
    ZONE_TEMPERATURE( "ZoneTemperature.csv" ),
    MAXIMUM_ZONE_AIR_FLOW( "MaximumZoneAirFlow.csv" ),
    MINIMUM_ZONE_AIR_FLOW( "MinimumZoneAirFlow.csv" ),
    ZONE_AIR_FLOW_SET_POINT_OFFSET( "ZoneAirFlowSetPointOffset.csv" ),
    ZONE_REHEAT_AIR_FLOW( "ZoneReheatAirFlow.csv" ),
    MINIMUM_OUTDOOR_AIR_FLOW_SET_POINT( "MinimumOutdoorAirFlowSetPoint.csv" ),
    RETURN_AIR_FLOW( "ReturnAirFlow.csv" ),
    SUPPLY_AIR_FLOW( "SupplyAirFlow.csv" ),
    SUPPLY_AIR_FLOW_SET_POINT( "SupplyAirFlowSetPoint.csv" ),
    EXHAUST_AIR_FLOW( "ExhaustAirFlow.csv" ),
    MINIMUM_OUTDOOR_AIR_FLOW( "MinimumOutdoorAirFlow.csv" ),
    MAXIMUM_DISCHARGE_AIR_FLOW_SET_POINT( "MaximumDischargeAirFlowSetPoint.csv" ),
    MINIMUM_DISCHARGE_AIR_FLOW_SET_POINT( "MinimumDischargeAirFlowSetPoint.csv" ),
    UNOCCUPIED_ZONE_HEATING_TEMPERATURE_SET_POINT( "UnoccupiedZoneHeatingTemperatureSetPoint.csv" ),
    ZONE_OUTDOOR_AIR_FLOW( "ZoneOutdoorAirFlow.csv" ),
    UNKNOWN("");


    private String csvFileName;

    CSVFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public String getCsvFileName() {
        return csvFileName;
    }


    public static CSVFileName fromString(String str){
        CSVFileName[] values = CSVFileName.values();
        return Arrays.stream(values)
                .filter(fileName -> fileName.getCsvFileName().equals(str))
                .findFirst().orElse(UNKNOWN);
    }

}
