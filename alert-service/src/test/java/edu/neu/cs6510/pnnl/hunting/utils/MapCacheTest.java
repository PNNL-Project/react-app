package edu.neu.cs6510.pnnl.hunting.utils;


import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class MapCacheTest {

    @Test
    void mapCache() {
    }

    @Test
    public void add() throws InterruptedException {
        MapCache mapCache = new MapCache();
        mapCache.add("uid_10001", "{1}", 5 * 1000);
        mapCache.add("uid_10002", "{2}", 5 * 1000);
        mapCache.add("uid_10003", "{3}", 5 * 1000);
        System.out.println("Get value from cache:" + mapCache.get("uid_10001"));
        Thread.sleep(5000L);
        System.out.println("Get value from cache:" + mapCache.get("uid_10001"));

    }

}