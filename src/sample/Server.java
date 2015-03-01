package sample;

/**
 * Created by User on 22.02.2015.
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class Server {
    public static InputStream inS;
    public static OutputStream outS;
    InetAddress ip;
    ServerSocket serS;
    Socket s;
    int port = 8080;

    public Server() throws IOException {
        ip = InetAddress.getLocalHost();

    }

    public void serverWorking() throws IOException {
        try {
            serS = new ServerSocket(port);
            serS.setSoTimeout(5000);

            String line = "Статус: сервер, установдено время ожидание 5 сек \n"
                    + "Значение сервера" + serS + "\n";

            s = serS.accept();
            System.out.println("Пришел клиент!!");

            Server.inS = s.getInputStream();
            Server.outS = s.getOutputStream();
        } catch (SocketTimeoutException e) {
            serS.close();

        } catch (BindException e) {

        }

    }
}