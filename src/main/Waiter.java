package main;

import java.util.Random;

public class Waiter implements Runnable {

    private final static int MAX_WAITER_MILLIS = 4000;
    private final static int N_COURSES = 3;

    private Table[] tables; // array of Table objects this Waiter waits on
    private String waiterName; // name of this Waiter
    private String[] customerNames; // names of Customers served by Waiter
    private String[][] courses; // multi-dimensional array of courses for each Customer of this Waiter.
                                // (courses[i][j] has the j-th course for the i-th Customer of this Waiter)

    public Waiter( Table[] tables, String waiterName, String[] customerNames, String[][] courses ){
        this.tables = tables;
        this.waiterName = waiterName;
        this.customerNames = customerNames;
        this.courses = courses;
    }

    public void run(){
        Random rng = new Random();
        for(int j = 0; j < N_COURSES; j++) {
            for(int i = 0; i < customerNames.length; i++){
                tables[i].serve(courses[i][j]);
                System.out.println(waiterName + " is serving " + customerNames[i] + " " + courses[i][j]);
                try {
                    Thread.sleep(rng.nextInt(MAX_WAITER_MILLIS));
                }catch (InterruptedException ie){
                    System.err.println(ie.getMessage());
                }
            }
        }

    }


}
