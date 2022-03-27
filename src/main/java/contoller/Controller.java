package contoller;

import model.SimulationManager;
import view.Log;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;

    public Controller(View view){

        this.view = view;

        view.runButton(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int nrOfClients = Integer.parseInt(view.getNrOfClients());
                    int nrOfQueues = Integer.parseInt(view.getNrOfQueues());
                    int nrOfTasks = Integer.parseInt(view.getMaxTasksPerServer());
                    int simulationDuration = Integer.parseInt(view.getSimtulationDuration());
                    int lowerBoundArrival = Integer.parseInt(view.getLowerBoundArrival());
                    int upperBoundArrival = Integer.parseInt(view.getUpperBoundArrival());
                    int lowerBoundProcessing = Integer.parseInt(view.getLowerBoundProcessing());
                    int upperBoundProcessing = Integer.parseInt(view.getUpperBoundProcessing());
                    SimulationManager s = new SimulationManager(simulationDuration, upperBoundProcessing, lowerBoundProcessing, upperBoundArrival, lowerBoundArrival, nrOfQueues, nrOfClients, nrOfTasks);
                    Thread thread = new Thread(s);
                    thread.start();
                } catch(NumberFormatException nfex){
                    JOptionPane.showMessageDialog(view, "Bad input!");
                }
            }
        });

    }

}
