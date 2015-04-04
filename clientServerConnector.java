package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.concurrent.Task;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;

public class clientServerConnector extends Task {

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

    @Override
    protected Void call() throws Exception {

        updateMessage("Пуск клиент сервера");
        sr = new Server();
        cl = new Client(this);

        String mess = "";
        tryFuctionToConnection(mess);
        systemOfIncomingMessage();
        //setFirstfollowStep();
        return null;
    }



    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void systemOfIncomingMessage() throws IOException {
        try {
            System.out.println("Запуск входящего потока сервера");
            DataInputStream inServer = new DataInputStream(sr.getInS());

            while (true) {
                // Ожидаем пока клиент пришлет строку текста.
                inputMessageHandler(inServer, "сервер");
            }

        } catch (NullPointerException e) {
            System.out.println("Срыв входящего потока сервера");
            DataInputStream inClient = new DataInputStream(cl.getInC());

            while (true) {

                inputMessageHandler(inClient, "клиент");

            }


        }catch (Exception e){
            //Последний catch поставил, с общим Exception, чтобы компилятор не ругался на
            //недостижимый код
        }
    }

    private void tryFuctionToConnection(String mess) throws IOException, InterruptedException {
        do {

            sr.serverWorking();
            //mess = "После первого выполнения sr.serSv=" + sr.serS + "\n";
            updateMessage(mess);
            System.out.print("После первого выполнения sr.serSv=" + sr.getSerS()
                    + "\n");
            try {
                //mess = "После первого выполнения sr.serSv.isClosed()="
                //        + sr.serS.isClosed() + "\n";
                updateMessage(mess);
                System.out
                        .print("После первого выполнения sr.serSv.isClosed()="
                                + sr.getSerS().isClosed() + "\n");
            } catch (NullPointerException e) {

            }

            if ((sr.getSerS() != null && sr.getSerS().isClosed()) || sr.getSerS() == null) {
                try {
                    System.out.println("Попытка создать клиент");
                    cl.clientWorking();
                } catch (SocketException e) {
                    System.out.println("Попытка создать сервер еще раз");
                    sr.serverWorking();

                }

            }

            System.out.println("Прошел цикл, Итоги:");

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

    private void inputMessageHandler(DataInputStream in, String s) throws IOException {
        line = in.readUTF();


        System.out.println("Запуск входящего потока "+s+"а");
        System.out.println(line);
        System.out.println(line.charAt(0));
        if (line.charAt(0) == '#') {

            System.out.println("Под атакой");
            updateMessage(line);

            dX = Integer.parseInt(line.substring(line.indexOf("$") + 1, line.indexOf("%")));
            dY = Integer.parseInt(line.substring(line.indexOf("%") + 1, line.indexOf("*")));

            System.out.println("Наши позиции атакованы " + dX + "  " + dY);
            //SendingResultOfFire.sendResult(Gui. ,dX, dY);

        } else if (line.charAt(0) == '!') {
            System.out.print("Прислан результат нашей атаки ");
            updateMessage(line);
            dX = Integer.parseInt(line.substring(line.indexOf("$") + 1, line.indexOf("%")));
            dY = Integer.parseInt(line.substring(line.indexOf("%") + 1, line.indexOf("*")));
            int n = dX + (dY * 10);

            System.out.println(dX + (dY * 10));
            System.out.println(dX + "  " + dY);

        } else {
            updateMessage(line);
        }
    }


    /*private void setFirstfollowStep() {
        if (sr.getSerS() != null&& sr.getSerS().isClosed()){

            Gui.followStep=true;

        }
        else if(cl.getClS() != null){
            Gui.followStep=false;
        }
    }*/

    public  void updateMessageSCS(String s){
        updateMessage(s);
    }

    //Геттеры

    public char getFirstCharOfLine(){
        return line.charAt(0);
    }

    public Server getSr(){
        return sr;
    }

    public Client getCl(){
        return cl;
    }

    public int getDx(){
        return dX;
    }
    public int getDy(){
        return dY;
    }

}
