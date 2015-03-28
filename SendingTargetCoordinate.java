package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.concurrent.Task;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

public class SendingTargetCoordinate extends Task {
    /*
    *Класс SendingTargetCoordinate
    *наследующий класс Task, который выполняется в отдельном потоке
    *запускаемый из GUI
    *нужен для того, чтобы передовать координаты атаки
    */
    private StartClientServer SCS;

    public SendingTargetCoordinate(StartClientServer SCS){
        this.SCS=SCS;
    }

    DataOutputStream outServer = new DataOutputStream(Gui.GtaskClSr.getSr().getOutS());
    DataOutputStream outClient = new DataOutputStream(Gui.GtaskClSr.getCl().getOutC());
    String line;
    Date currentDate = new Date();



    @Override
    protected Object call() throws Exception {
        if (SCS.getSr().getSerS()!= null
                && !SCS.getSr().getSerS().isClosed()) {


            sendStrikeCoord(outServer, "server");
        } else {

            sendStrikeCoord(outClient, "client");
        }
        return null;
    }
    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void sendStrikeCoord(DataOutputStream out, String s) {
        try {
            updateMessage("#attack of "+s+" (I AM)" + "(" + currentDate + ")"
                    + " attacked coordinates: " + "("
                    + "$" + EnemyRectangle.getTargetX() + "%" + EnemyRectangle.getTargetY()
                    + "*;");
            out.writeUTF("#attack of "+s+ " (" + currentDate + ")"
                    + " attacked coordinates: " + "("
                    + "$" + EnemyRectangle.getTargetX() + "%" + EnemyRectangle.getTargetY()
                    + "*;");

            updateMessage("");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}

