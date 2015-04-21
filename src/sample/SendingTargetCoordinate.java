package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.concurrent.Task;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/*
 * нужен для того, чтобы передовать координаты атаки
 */

public class SendingTargetCoordinate extends Task {

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
        if (connector.getServer().isClosed()) {
            sendStrikeCoordinateTo("client", connector.getClient().getOutputClientStream());
        } else {
            sendStrikeCoordinateTo("server", connector.getServer().getOutputServerStream());
        }
        return null;
    }

    private void sendStrikeCoordinateTo(String who, OutputStream outputStream) {
        DataOutputStream outClient = new DataOutputStream(outputStream);
        System.out.println("isFollowStep " + status.isFollowStep());
        if (status.isFollowStep() && status.checkAndInstallStart()) {
            sendStrikeCoordinate(outClient, who);
        }
    }

    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void sendStrikeCoordinate(DataOutputStream out, String s) {
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
            e1.printStackTrace();
        }
    }

}

