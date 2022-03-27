
import contoller.Controller;
import view.View;

public class MainClass {


    public static  void main(String[] args){

        /*SimulationManager man = new SimulationManager(10, 7, 2, 20, 3, 3, 8, 4);

            Thread thread = new Thread(man);
            thread.start();
        */
        View v= new View();
        Controller t = new Controller(v);
        v.setVisible(true);


    }
}
