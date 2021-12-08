package main;

import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Restaurant {

    public static void main(String[] args) {
        Waiter[] waiters;
        Customer[] customers;
        int waiterNum = 0;
        System.out.println("Enter the name of the file:");
        try {
            Scanner scanIn = new Scanner(System.in);
            Scanner scanFile = new Scanner(new FileReader(scanIn.next()));
            try {
                waiterNum = scanFile.nextInt();
            } catch (InputMismatchException ime) {
                System.err.println("file does not start with a number");
            }

            waiters = new Waiter[waiterNum];

            for (int i = 0; i < waiterNum; i++) {
                String waiterName = scanFile.next();
                int numCustomers = scanFile.nextInt();
                Table[] tables = new Table[numCustomers];
                String[] WaiterCustomers = new String[numCustomers];
                String[][] courses = new String[numCustomers][3];

                for (int j = 0; j < numCustomers; j++) {
                    String customerName = scanFile.next();
                    Table tbl = new Table();

                    WaiterCustomers[j] = customerName;
                    tables[j] = tbl;

                    Thread ct = new Thread(new Customer(tbl, customerName));
                    ct.start();

                    for (int k = 0; k < 3; k++) {
                        courses[j][k] = scanFile.next();
                    }
                }
                if(scanFile.hasNext())
                    throw new NoSuchElementException();
                Thread wt = new Thread(new Waiter(tables, waiterName, WaiterCustomers, courses));
                wt.start();
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } catch (NoSuchElementException nsee) {
            System.err.println("Incorrect formatting");
        }
    }
}
