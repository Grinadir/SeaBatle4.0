package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.concurrent.Task;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;

public class StartClientServer extends Task {

     /*
    *Класс StartClientServer
    *наследующий класс Task, который выполняется в отдельном потоке
    *запускаемый из GUI
    *нужен для того, создать соединение и открыт поток для передачи
    *сообщений
    */


    static public int dX;
    static public int dY;
    static Server sr;
    static Client cl;
    static String line;


    @Override
    protected Void call() throws Exception {

        updateMessage("Пуск клиент сервера");
        sr = new Server();
        cl = new Client();
        String mess = "";

        tryFuctionToConnection(mess);


        systemOfIncomingMessage();


        setFirstfollowStep();


        return null;



    }



    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void systemOfIncomingMessage() throws IOException {
        try {
            System.out.println("Запуск входящего потока сервера");
            DataInputStream inServer = new DataInputStream(Server.inS);

            while (true) {
                // Ожидаем пока клиент пришлет строку текста.
                inputMessageHandler(inServer, "сервер");
            }

        } catch (NullPointerException e) {
            System.out.println("Срыв входящего потока сервера");
            DataInputStream inClient = new DataInputStream(Client.inC);

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
            System.out.print("После первого выполнения sr.serSv=" + sr.serS
                    + "\n");
            try {
                //mess = "После первого выполнения sr.serSv.isClosed()="
                //        + sr.serS.isClosed() + "\n";
                updateMessage(mess);
                System.out
                        .print("После первого выполнения sr.serSv.isClosed()="
                                + sr.serS.isClosed() + "\n");
            } catch (NullPointerException e) {

            }

            if ((sr.serS != null && sr.serS.isClosed()) || sr.serS == null) {
                try {
                    System.out.println("Попытка создать клиент");
                    cl.clientWorking();
                } catch (SocketException e) {
                    System.out.println("Попытка создать сервер еще раз");
                    sr.serverWorking();

                }

            }

            System.out.println("Прошел цикл, Итоги:");

            System.out.println("sr.serSv: " + sr.serS);

            try {
                System.out.println("sr.serS.isClosed()"
                        + sr.serS.isClosed());
            } catch (NullPointerException e) {
                System.out.println("catch sr.serS=" + sr.serS + "\n");
            }
            System.out.println("cl.clS: " + cl.clS);


        } while (sr.serS != null && sr.serS.isClosed() && cl.clS == null);
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
            SendingResultOfFire.sendResult(dX, dY);

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


    private void setFirstfollowStep() {
        if (sr.serS != null&& sr.serS.isClosed()){

            Gui.followStep=true;

        }
        else if(cl.clS != null){
            Gui.followStep=false;
        }
    }
}
