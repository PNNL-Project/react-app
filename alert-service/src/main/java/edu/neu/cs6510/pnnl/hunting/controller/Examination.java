package edu.neu.cs6510.pnnl.hunting.controller;

class Examination {
    public static long c1, c2;

    public static void start() {
        c1 = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
    }

    public static void end() {
        c2 = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();

        String time = String.valueOf((double) (c2 - c1) / 1000000);
        System.out.println("Time consumed: " + time.substring(0, time.equals("0,0") ? 1 : (time.indexOf(".") + 3)) + "ms");

    }

}
