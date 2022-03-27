package view;

import javax.swing.*;
import java.awt.*;

public class Log extends JFrame {

    final JFrame frame = new JFrame("Events");
    private JLabel logs;
    private String text="";
    private JTextArea textArea;
    private JScrollPane scrollableTextArea;

    public Log(){
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBackground(Color.WHITE);

        textArea = new JTextArea(1000,100);
        scrollableTextArea = new JScrollPane(textArea);

        frame.getContentPane().add(scrollableTextArea);
    }

    public void setTextToBedisplayed(String t){
        text += t;
        text += '\n';
        textArea.setText(text);
    }

}
