package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.concurrent.Task;

import java.io.IOException;
import java.net.SocketException;

public class ClientServerConnector extends Task {

     /*
    *Класс StartClientServer
    *наследующий класс Task, который выполняется в отдельном потоке
    *запускаемый из GUI
    *нужен для того, создать соединение и открыт поток для передачи
    *сообщений
    */

    private Server server;
    private Client client;
    private String line;
    private int dX;
    private int dY;
    private SystemOfIncomingMessage sysInMESSAGE;
    private Gui gui;

    public ClientServerConnector(Gui gui) {
        this.gui = gui;

    }

    @Override
    protected Void call() throws Exception {
        updateMessage("Start client-server");
        server = new Server();
        client = new Client(this);
        sysInMESSAGE = new SystemOfIncomingMessage(ClientServerConnector.this, gui);
        String mess = "";
        tryFuctionToConnection(mess);
        sysInMESSAGE.mainFuncOfIncomMessage();
        return null;
    }

    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void tryFuctionToConnection(String mess) throws IOException, InterruptedException {
        do {
            server.serverWorking();
            updateMessage(mess);
            System.out.print("After first execute server socket is " + server.getServerSocket() + "\n");
            try {
                updateMessage(mess);
                System.out.println("After first execute server socket closed status is "
                        + server.getServerSocket().isClosed());
            } catch (NullPointerException e) {
                e.getStackTrace();

            }

            if ((server.getServerSocket() != null && server.getServerSocket().isClosed()) || server.getServerSocket() == null) {
                try {
                    System.out.println("Attempt to create client-server");
                    client.clientWorking();
                } catch (SocketException e) {
                    System.out.println("Attempt to create client-server again");
                    server.serverWorking();
                }
            }

            System.out.println("Cycle was made, result: ");
            System.out.println("sr.serSv: " + server.getServerSocket());

            try {
                System.out.println("sr.serS.isClosed()" + server.getServerSocket().isClosed());
            } catch (NullPointerException e) {
                System.out.println("catch sr.serS=" + server.getServerSocket() + "\n");
            }
            System.out.println("cl.clS: " + client.getSocketFromClientocketFromClient());
        }
        while (server.getServerSocket() != null && server.getServerSocket().isClosed() && client.getSocketFromClientocketFromClient() == null);
    }

    public void updateMessageSCS(String s) {
        this.updateMessage(s);
        if (s.equals(null)) {
            this.line = s;
        }
    }

    //Геттеры

    public Server getServer() {
        return server;
    }

    public Client getClient() {
        return client;
    }

}
