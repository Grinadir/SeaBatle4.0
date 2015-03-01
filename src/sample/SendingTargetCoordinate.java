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

    DataOutputStream outServer = new DataOutputStream(Server.outS);
    DataOutputStream outClient = new DataOutputStream(Client.outC);
    String line;
    Date currentDate = new Date();

    @Override
    protected Object call() throws Exception {
        if (StartClientServer.sr.serS != null
                && !StartClientServer.sr.serS.isClosed()) {


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
                    + "$" + EnemyRectangle.targetX + "%" + EnemyRectangle.targetY
                    + "*;");
            out.writeUTF("#attack of "+s+ " (" + currentDate + ")"
                    + " attacked coordinates: " + "("
                    + "$" + EnemyRectangle.targetX + "%" + EnemyRectangle.targetY
                    + "*;");

            updateMessage("");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}

