package model;

public class Task {

    private int id;
    private static int counter = 0;
    private int arrivalTime;
    private int processingTime;

    public Task(int arrivalTime, int processingTime){
        id = ++counter;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public  int getId() {
        return id;
    }

    public String toString(){
        return "(" + this.getId() + ", " + this.getArrivalTime() + ", " + this.getProcessingTime() + ")";
    }

}
