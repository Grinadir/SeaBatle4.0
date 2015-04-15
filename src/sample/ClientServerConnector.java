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

    public ClientServerConnector(Gui gui){
        this.gui=gui;

    }

    @Override
    protected Void call() throws Exception {
        updateMessage("Start client-server");
        server = new Server();
        client = new Client(this);
        sysInMESSAGE=new SystemOfIncomingMessage(ClientServerConnector.this, gui);
        String mess = "";
        tryFuctionToConnection(mess);
        sysInMESSAGE.mainFuncOfIncomMessage();
        return null;
    }

    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void tryFuctionToConnection(String mess) throws IOException, InterruptedException {
        do {
            server.serverWorking();
            //mess = "После первого выполнения sr.serSv=" + sr.serS + "\n";
            updateMessage(mess);
            System.out.print("After first execute sr.serSv=" + server.getSorcetFromServer() + "\n");
            try {
                updateMessage(mess);
                System.out
                        .print("After first execute sr.serSv.isClosed()="
                                + server.getSorcetFromServer().isClosed() + "\n");
            } catch (NullPointerException e) {

            }

            if ((server.getSorcetFromServer() != null && server.getSorcetFromServer().isClosed()) || server.getSorcetFromServer() == null) {
                try {
                    System.out.println("Attempt to create client-server");
                    client.clientWorking();
                } catch (SocketException e) {
                    System.out.println("Attempt to create client-server gain");
                    server.serverWorking();
                }
            }

            System.out.println("Cycle was made, Итоги:");
            System.out.println("sr.serSv: " + server.getSorcetFromServer());

            try {
                System.out.println("sr.serS.isClosed()" + server.getSorcetFromServer().isClosed());
            } catch (NullPointerException e) {
                System.out.println("catch sr.serS=" + server.getSorcetFromServer() + "\n");
            }
            System.out.println("cl.clS: " + client.getSocketFromClientocketFromClient());
        } while (server.getSorcetFromServer() != null && server.getSorcetFromServer().isClosed() && client.getSocketFromClientocketFromClient() == null);
    }

    public  void updateMessageSCS(String s){
        this.updateMessage(s);
        if(s.equals(null)){
        this.line=s;}
    }

    //Геттеры

    public Server getSr(){
        return server;
    }
    public Client getCl(){
        return client;
    }

}
