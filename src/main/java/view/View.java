package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JPanel contentPane;
    private JTextField clientsTextField;
    private JLabel clientsLabel;
    private JTextField queuesTextField;
    private JTextField simulationTextLabel;
    private JLabel simlationLabel;
    private JLabel queuesLabel;
    private JTextField minArrivalField;
    private JTextField maxArrivalField;
    private JLabel minProcLabel;
    private JTextField minProcessField;
    private JTextField maxProcessField;
    private JTextField nrOfTasks;
    private JButton startBtn;

    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        clientsLabel = new JLabel("Nr of Clients:");
        clientsLabel.setBounds(10, 20, 100, 20);
        contentPane.add(clientsLabel);

        clientsTextField = new JTextField();
        clientsTextField.setBounds(120, 20, 90, 20);
        contentPane.add(clientsTextField);
        clientsTextField.setColumns(10);

        queuesLabel = new JLabel("Nr of Queues:");
        queuesLabel.setBounds(10, 60, 100, 20);
        contentPane.add(queuesLabel);

        queuesTextField = new JTextField();
        queuesTextField.setBounds(120, 60, 90, 20);
        contentPane.add(queuesTextField);
        queuesTextField.setColumns(10);

        simlationLabel = new JLabel("Sim Time:");
        simlationLabel.setBounds(10, 100, 100, 20);
        contentPane.add(simlationLabel);

        simulationTextLabel = new JTextField();
        simulationTextLabel.setBounds(120, 100, 90, 20);
        contentPane.add(simulationTextLabel);
        simulationTextLabel.setColumns(10);

        JLabel minArrLabel = new JLabel("Mini Arrival:");
        minArrLabel.setBounds(300, 20, 100, 20);
        contentPane.add(minArrLabel);

        minArrivalField = new JTextField();
        minArrivalField.setBounds(390, 20, 90, 20);
        contentPane.add(minArrivalField);
        minArrivalField.setColumns(10);

        JLabel maxArrLabel = new JLabel("Max Arrival:");
        maxArrLabel.setBounds(300, 65, 100, 20);
        contentPane.add(maxArrLabel);

        maxArrivalField = new JTextField();
        maxArrivalField.setBounds(390, 65, 90, 20);
        contentPane.add(maxArrivalField);
        maxArrivalField.setColumns(10);

        minProcLabel = new JLabel("Min Processing:");
        minProcLabel.setBounds(300, 120, 100, 20);
        contentPane.add(minProcLabel);

        minProcessField = new JTextField();
        minProcessField.setBounds(400, 120, 90, 20);
        contentPane.add(minProcessField);
        minProcessField.setColumns(10);

        JLabel maxProcLabel = new JLabel("Max Processing:");
        maxProcLabel.setBounds(300, 165, 105, 20);
        contentPane.add(maxProcLabel);

        maxProcessField = new JTextField();
        maxProcessField.setText("");
        maxProcessField.setBounds(404, 165, 86, 20);
        contentPane.add(maxProcessField);
        maxProcessField.setColumns(10);

        JLabel tasksNumberLabel = new JLabel("Max Tasks/Server:");
        tasksNumberLabel.setBounds(10, 150, 120, 20);
        contentPane.add(tasksNumberLabel);

        nrOfTasks = new JTextField();
        nrOfTasks.setText("");
        nrOfTasks.setBounds(140, 150, 90, 20);
        contentPane.add(nrOfTasks);
        nrOfTasks.setColumns(10);

        startBtn = new JButton("Start");
        startBtn.setBackground(Color.RED);
        startBtn.setBounds(225, 275, 100, 30);
        contentPane.add(startBtn);
    }

    public String getNrOfClients(){
        return clientsTextField.getText();
    }

    public String getNrOfQueues(){
        return queuesTextField.getText();
    }

    public String getSimtulationDuration(){
        return simulationTextLabel.getText();
    }

    public String getMaxTasksPerServer(){
        return nrOfTasks.getText();
    }

    public String getLowerBoundArrival(){
        return minArrivalField.getText();
    }

    public String getUpperBoundArrival(){
        return maxArrivalField.getText();
    }

    public String getLowerBoundProcessing(){
        return minProcessField.getText();
    }

    public String getUpperBoundProcessing(){
        return maxProcessField.getText();
    }

    public void runButton(ActionListener actionListener){
        this.startBtn.addActionListener(actionListener);
    }

}

