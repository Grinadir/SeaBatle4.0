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
    private ClientServerConnector connector;
    private Gui gui;
    private Status status;

    public SendingTargetCoordinate(Gui gui, ClientServerConnector connector, Status status) {
        this.connector = connector;
        this.gui = gui;
        this.status = status;
        System.out.println("isFollowStep in Constr " + status.isFollowStep());
        System.out.println("Constructor SendingTargetCoordinate");
    }

    private Date currentDate = new Date();

    @Override
    protected Object call() throws Exception {
        if (connector.getSr().getSorcetFromServer() != null
                && !connector.getSr().getSorcetFromServer().isClosed()) {
            DataOutputStream outServer = new DataOutputStream(connector.getSr().getOutS());
            System.out.println("isFollowStep " + status.isFollowStep());
            if (status.isFollowStep() && status.checkAndInstallStart()) {
                sendStrikeCoord(outServer, "server");
            }
        } else {
            DataOutputStream outClient = new DataOutputStream(connector.getCl().getOutputStreamFromClient());
            System.out.println("isFollowStep " + status.isFollowStep());
            if (status.isFollowStep() && status.checkAndInstallStart()) {
                sendStrikeCoord(outClient, "client");
            }
        }
        return null;
    }

    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void sendStrikeCoord(DataOutputStream out, String s) {
        try {
            int y = (int) (10 - (10 - gui.getTargetIndex() * 0.1));
            int x = gui.getTargetIndex() - y * 10;
            updateMessage("#attack of " + s + " (I AM)" + "(" + currentDate + ")"
                    + " attacked coordinates: " + "("
                    + "$" + x + "%" + y
                    + "*;");
            out.writeUTF("#attack of " + s + " (" + currentDate + ")"
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

