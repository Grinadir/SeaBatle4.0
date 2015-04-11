package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.concurrent.Task;

import java.io.DataInputStream;
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

    private Server sr;
    private Client cl;
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
        sr = new Server();
        cl = new Client(this);
        sysInMESSAGE=new SystemOfIncomingMessage(ClientServerConnector.this, gui);
        String mess = "";
        tryFuctionToConnection(mess);
        sysInMESSAGE.mainFuncOfIncomMessage();
        return null;
    }



    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ


    private void tryFuctionToConnection(String mess) throws IOException, InterruptedException {
        do {

            sr.serverWorking();
            //mess = "После первого выполнения sr.serSv=" + sr.serS + "\n";
            updateMessage(mess);
            System.out.print("After first execute sr.serSv=" + sr.getSerS()
                    + "\n");
            try {

                updateMessage(mess);
                System.out
                        .print("After first execute sr.serSv.isClosed()="
                                + sr.getSerS().isClosed() + "\n");
            } catch (NullPointerException e) {

            }

            if ((sr.getSerS() != null && sr.getSerS().isClosed()) || sr.getSerS() == null) {
                try {
                    System.out.println("Attempt to create client-server");
                    cl.clientWorking();
                } catch (SocketException e) {
                    System.out.println("Attempt to create client-server gain");
                    sr.serverWorking();

                }

            }

            System.out.println("Cycle was made, Итоги:");

            System.out.println("sr.serSv: " + sr.getSerS());

            try {
                System.out.println("sr.serS.isClosed()"
                        + sr.getSerS().isClosed());
            } catch (NullPointerException e) {
                System.out.println("catch sr.serS=" + sr.getSerS() + "\n");
            }
            System.out.println("cl.clS: " + cl.getClS());


        } while (sr.getSerS() != null && sr.getSerS().isClosed() && cl.getClS() == null);
    }


    public  void updateMessageSCS(String s){
        this.updateMessage(s);
        if(s.equals(null)){
        this.line=s;}
    }

    //Геттеры

    public Server getSr(){
        return sr;
    }

    public Client getCl(){
        return cl;
    }

}
