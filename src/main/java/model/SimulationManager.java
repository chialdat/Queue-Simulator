package model;

import view.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimulationManager implements Runnable {

    private int simulationTime;
    private int maxProcessTime;
    private int minProcessTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int noOfServers;
    private int noOfClients;
    private int maxTasksPerServer;
    private Scheduler scheduler;
    private BlockingQueue<Task> tasks;
    private int duration = 0;
    private int peakHour = 0;
    private float totalProcessingTime = 0;
    private float avgServiceTime = 0;
    private float avgWaitingTime ;
    private Log logObj = new Log();


    public SimulationManager(int simulationTime, int maxProcessTime, int minProcessTime, int maxArrivalTime, int minArrivalTime, int noOfServers, int noOfClients, int maxTasksPerServer) {
        this.simulationTime = simulationTime;
        this.maxProcessTime = maxProcessTime;
        this.minProcessTime = minProcessTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.noOfServers = noOfServers;
        this.noOfClients = noOfClients;
        this.maxTasksPerServer = maxTasksPerServer;
        scheduler = new Scheduler(noOfServers, maxTasksPerServer);
        tasks = new LinkedBlockingQueue<Task>();
        generateNRandom();
    }

    private void generateNRandom() {
        Random rand = new Random();
        for (int i = 0; i < noOfClients; i++) {
            int processTime = rand.nextInt(maxProcessTime + 1 - minProcessTime) + minProcessTime;
            int arrivalTime = rand.nextInt(maxArrivalTime + 1 - minArrivalTime) + minArrivalTime;
            Task t = new Task(arrivalTime, processTime);
            tasks.add(t);
            totalProcessingTime += t.getProcessingTime();
        }
    }

    public float computeAvgServiceTime(){
        avgServiceTime = totalProcessingTime / (float) noOfClients;
        return avgServiceTime;
    }

    public float computeAvgWaitingTime(){
        avgWaitingTime = avgWaitingTime / (float)noOfClients;
        return avgWaitingTime;
    }

    public void computePeakHour(int time){
        int sum = 0;
        for(Server s: scheduler.getServersList()){
            for(Task t: s.getBlockingQueue()){
                sum += t.getProcessingTime();
            }
        }
        if(sum > duration){
            peakHour = time;
            duration =  sum;
        }

    }

    public void printFile(PrintWriter pw){
        String k="";

        for(Task t : tasks){
            k += t.toString();
        }

        pw.println("Waiting clients: " + k);

        for(Server s : scheduler.getServersList()){
            pw.println(s);
        }

    }


    @Override
    public void run() {

        int time = 1;
        String k = "";
        File log=new File("fisier50.txt");
        FileWriter fw = null;
        PrintWriter pw=null;
        try{
            fw = new FileWriter(log);
            pw = new PrintWriter(fw);
        } catch (IOException e){
            e.printStackTrace();
        }
        while (time <= simulationTime) {

            System.out.println("Time " + time);

            for (Task t : tasks) {
                if (time >= t.getArrivalTime()) {
                    avgWaitingTime+=(float)scheduler.dispatchOnServer(t);
                    tasks.remove(t);
                }
            }

            if(tasks.isEmpty()){
                int ok = 1;
                for(Server s : scheduler.getServersList()){
                    if(!s.getBlockingQueue().isEmpty()){
                        ok = 0;
                    }
                }
                if(ok == 1)
                    break;
            }

            computePeakHour(time);

            for(Task t : tasks){
                k += t.toString();
            }
            logObj.setTextToBedisplayed(time+"");
            System.out.println("Waiting to be processed " + k);
            logObj.setTextToBedisplayed(k.toString());
            k = "";

            for(Server s : scheduler.getServersList()){
                System.out.println(s);
                logObj.setTextToBedisplayed(s.toString());
            }
            pw.println(time);
            printFile(pw);

            for(Server s : scheduler.getServersList()){
                if(s.getBlockingQueue().peek() != null){
                    s.getBlockingQueue().peek().setProcessingTime(s.getBlockingQueue().peek().getProcessingTime()-1);
                }
            }

            try {

                Thread.sleep(1000);
                time++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        float number = computeAvgServiceTime();
        float nr = computeAvgWaitingTime();
        logObj.setTextToBedisplayed("Average service time: " + number);
        logObj.setTextToBedisplayed("Average waiting time: " + nr);
        logObj.setTextToBedisplayed("Peak hour " + peakHour);

        pw.println("Average service time: " + number);
        pw.println("Average waiting time: " + nr);
        pw.println("Peak hour: " + peakHour);
        pw.close();
    }
}
