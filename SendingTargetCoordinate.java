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
    private clientServerConnector serverConnector;

    public SendingTargetCoordinate(clientServerConnector serverConnector){
        this.serverConnector=serverConnector;
    }
    //Gui.GtaskClSr


    String line;
    Date currentDate = new Date();



    @Override
    protected Object call() throws Exception {
        if (serverConnector.getSr().getSerS()!= null
                && !serverConnector.getSr().getSerS().isClosed()) {
            DataOutputStream outServer = new DataOutputStream(serverConnector.getSr().getOutS());

            sendStrikeCoord(outServer, "server");
        } else {
            DataOutputStream outClient = new DataOutputStream(serverConnector.getCl().getOutC());

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

