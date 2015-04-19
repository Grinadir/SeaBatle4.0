package sample;

/**
 * Created by User on 22.02.2015.
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class Server {
    private InputStream inputStreamFromServer;
    private OutputStream outputStreamFromServer;
    private InetAddress ip;
    private ServerSocket sorcetFromServer;
    private Socket s;
    private int port = 8080;

    public Server() throws IOException {
        ip = InetAddress.getLocalHost();
    }

    public void serverWorking() throws IOException {
        try {
            sorcetFromServer = new ServerSocket(port);
            sorcetFromServer.setSoTimeout(5000);
            s = sorcetFromServer.accept();
            System.out.println("Client connected");
            inputStreamFromServer = s.getInputStream();
            outputStreamFromServer = s.getOutputStream();
        } catch (SocketTimeoutException e) {
            sorcetFromServer.close();

        } catch (BindException e) {

        }
    }

    //Геттеры
    public ServerSocket getSorcetFromServer() {
        return sorcetFromServer;
    }

    public InputStream getInputStreamFromServer() {
        return inputStreamFromServer;
    }

    public OutputStream getOutS() {
        return outputStreamFromServer;
    }

}