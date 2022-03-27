package model;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private BlockingQueue<Task> blockingQueue;
    private AtomicInteger waitingTime;
    private LinkedList<Task> processedTasks = new LinkedList<Task>();
    private int serverId;
    private int maxTasksPerServer;

    public Server(int id, int maxTasksPerServer){

        this.serverId = id;
        this.maxTasksPerServer = maxTasksPerServer;
        waitingTime = new AtomicInteger(0);
        blockingQueue = new LinkedBlockingQueue<Task>();
    }

    public AtomicInteger getWaitingTime() {
        return waitingTime;
    }

    public void addTaskIfPosible(Task t){

            blockingQueue.add(t);
            waitingTime.addAndGet(t.getProcessingTime());

    }

    public int getSize(){
        return blockingQueue.size();
    }

    @Override
    public void run() {

        while(true) {

            Task t;
            if (!blockingQueue.isEmpty()) {
                try {
                    // System.out.println(blockingQueue.size());
                    int k = blockingQueue.peek().getProcessingTime();
                    //  t = blockingQueue.take();
                    //System.out.println("Task no" + t.getId() + " has arrived at " + t.getArrivalTime() + " and it takes " + t.getProcessingTime() + " to be processed");
                    Thread.sleep(k * 1000);
                    t = blockingQueue.take();
                    waitingTime.addAndGet((-1) * t.getProcessingTime());
                    processedTasks.add(t);
                   // System.out.println("Server " + serverId + " has finished task " + t.getId() + " after " + t.getProcessingTime());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public String toString(){

        String k = "Queue " + this.serverId + ": ";
        if(blockingQueue.size() == 0) {
            return "Queue " + this.serverId + ":";
        } else {
                for(Task t: blockingQueue){
                    k += t.toString();
                }
                k += ";";
                return k;
        }
    }

    public BlockingQueue<Task> getBlockingQueue() {
        return blockingQueue;
    }
}
