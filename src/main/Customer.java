package main;

import java.util.Random;

public class Customer implements Runnable{

    private final static int MAX_CUSTOMER_MILLIS = 4000; // must wait for between 0 and 4 seconds.
    private Table table; // Table object that this Customer sits at
    private String customerName; // name of this Customer

    public Customer( Table table, String customerName ){
        this.table = table;
        this.customerName = customerName;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            String course = table.eat();
            System.out.println(customerName + " is eating: " + course);
            Random rng = new Random();
            try {
                Thread.sleep(rng.nextInt(MAX_CUSTOMER_MILLIS));
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
