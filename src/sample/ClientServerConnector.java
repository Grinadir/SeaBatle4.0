package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.concurrent.Task;

import java.io.IOException;
import java.net.SocketException;

public class ClientServerConnector extends Task {

    private Server server;
    private Client client;
    private SystemOfIncomingMessage sysInMESSAGE;

    public ClientServerConnector() {

    }

    @Override
    protected Void call() throws Exception {
        updateMessage("Start client-server");
        server = new Server();
        client = new Client();
        sysInMESSAGE = new SystemOfIncomingMessage(ClientServerConnector.this);
        String mess = "";
        tryFunctionToConnection(mess);
        sysInMESSAGE.mainFunctionOfIncomingMessage();
        return null;
    }

    private void tryFunctionToConnection(String mess) throws IOException, InterruptedException {
        do {
            server.serverWorking();
            updateMessage(mess);
            try {
                updateMessage(mess);
            } catch (NullPointerException e) {
                e.getStackTrace();

            }

            if (server.isClosed()) {
                try {
                    client.clientWorking();
                } catch (SocketException e) {
                    server.serverWorking();
                }
            }

        }
        while (server.isClosed() && client.getClientSocket() == null);
    }

    public void updateMessageSCS(String s) {
        this.updateMessage(s);
    }

    public Server getServer() {
        return server;
    }

    public Client getClient() {
        return client;
    }

}
