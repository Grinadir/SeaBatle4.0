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
    private clientServerConnector connector;
    private Gui gui;

    public SendingTargetCoordinate(Gui gui, clientServerConnector connector){
        this.connector=connector;
        this.gui=gui;
    }
    //Gui.GtaskClSr


    String line;
    Date currentDate = new Date();



    @Override
    protected Object call() throws Exception {
        if (connector.getSr().getSerS()!= null
                && !connector.getSr().getSerS().isClosed()) {
            DataOutputStream outServer = new DataOutputStream(connector.getSr().getOutS());

            sendStrikeCoord(outServer, "server");
        } else {
            DataOutputStream outClient = new DataOutputStream(connector.getCl().getOutC());

            sendStrikeCoord(outClient, "client");
        }
        return null;
    }
    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void sendStrikeCoord(DataOutputStream out, String s) {
        try {
            int y = (int) (10 - (10 -gui.getTargetIndex()  * 0.1));
            int x= gui.getTargetIndex() - y * 10;

            updateMessage("#attack of " + s + " (I AM)" + "(" + currentDate + ")"
                    + " attacked coordinates: " + "("
                    + "$" + x + "%" + y
                    + "*;");
            out.writeUTF("#attack of "+s+ " (" + currentDate + ")"
                    + " attacked coordinates: " + "("
                    + "$" + x + "%" + y
                    + "*;");

            updateMessage("");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}

