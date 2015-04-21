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

    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void tryFunctionToConnection(String mess) throws IOException, InterruptedException {
        do {
            server.serverWorking();
            updateMessage(mess);
            System.out.println("After first execute " + server.getStatusServerSocket());
            try {
                updateMessage(mess);
                System.out.println("After first execute server socket closed status is "
                        + server.getServerSocket().isClosed());
            } catch (NullPointerException e) {
                e.getStackTrace();

            }

            if (server.isClosed()) {
                try {
                    System.out.println("Attempt to create client-server");
                    client.clientWorking();
                } catch (SocketException e) {
                    System.out.println("Attempt to create client-server again");
                    server.serverWorking();
                }
            }

            System.out.println("Cycle was made, result: ");
            System.out.println(server.getStatusServerSocket());
            System.out.println("client socket is : " + client.getClientSocket());
        }
        while (server.isClosed() && client.getClientSocket() == null);
    }

    public void updateMessageSCS(String s) {
        this.updateMessage(s);
    }

    //Геттеры

    public Server getServer() {
        return server;
    }

    public Client getClient() {
        return client;
    }

}
