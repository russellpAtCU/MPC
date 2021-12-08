package main;

public class Table {

    private String course;
    private boolean isEmpty;
    private final Object lock = new Object();

    public Table(){
        this.course = "";
        this.isEmpty = true;
    }

    public void serve( String course ){
        synchronized (lock){
            while(!isEmpty){
                try{
                    lock.wait();
                }catch (InterruptedException ie){
                    System.err.println(ie.getMessage());
                }
            }
            isEmpty = false;
            this.course = course;
            lock.notify();
        }
    }

    public String eat(){
        synchronized (lock){
            while(isEmpty){
                try{
                    lock.wait();
                }catch (InterruptedException ie){
                    System.err.println(ie.getMessage());
                }
            }
            isEmpty = true;
            lock.notify();
            return this.course;
        }
    }

}
