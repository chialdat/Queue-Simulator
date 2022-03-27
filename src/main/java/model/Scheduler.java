package model;

import java.util.ArrayList;

public class Scheduler {

    private ArrayList<Server> serversList = new ArrayList<>();
    private int noOfServers;
    private int maxTasksPerServer;
    private float avgWaitingTime = 0;



    public Scheduler(int noOfServers, int maxTasksPerServer){
        this.maxTasksPerServer = maxTasksPerServer;
        this.noOfServers = noOfServers;

        for(int i = 0; i < noOfServers; i++){
            Server s = new Server(i+1, maxTasksPerServer);
            serversList.add(s);
            Thread t = new Thread(s);
            t.start();
        }

    }

    public ArrayList<Server> getServersList() {
        return serversList;
    }

    public int dispatchOnServer(Task t){

        int minimumWaitingTime = Integer.MAX_VALUE;

        Server k = null;
        for(Server s: serversList){

            if(minimumWaitingTime > s.getWaitingTime().get() && s.getSize() < maxTasksPerServer){
                minimumWaitingTime = s.getWaitingTime().get();
                k = s;
            }
        }
        if(k != null){
            k.addTaskIfPosible(t);
            return minimumWaitingTime;
        }
        else return 0;
    }

}
