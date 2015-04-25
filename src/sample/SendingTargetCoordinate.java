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
    private Engine engine;

    public SendingTargetCoordinate(Engine engine, ClientServerConnector connector) {
        this.engine=engine;
        this.connector = connector;
        System.out.println("isFollowStep in Constr " + engine.getStatus().isFollowStep());
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
        System.out.println("isFollowStep " + engine.getStatus().isFollowStep());
        if (engine.getStatus().isFollowStep() && engine.getStatus().checkAndInstallStart()) {
            sendStrikeCoordinate(outClient, who);
        }
    }

    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void sendStrikeCoordinate(DataOutputStream out, String s) {
        try {
            int y = (int) (10 - (10 - engine.getTargetIndex() * 0.1));
            int x = engine.getTargetIndex() - y * 10;
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

