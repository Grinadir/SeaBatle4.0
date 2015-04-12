package sample;

/**
 * Created by User on 22.02.2015.
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class Server {
    private InputStream inS;
    private OutputStream outS;
    private InetAddress ip;
    private ServerSocket serS;
    private Socket s;
    private int port = 8080;

    public Server() throws IOException {
        ip = InetAddress.getLocalHost();
    }

    public void serverWorking() throws IOException {
        try {
            serS = new ServerSocket(port);
            serS.setSoTimeout(5000);
            s = serS.accept();
            System.out.println("Client connected");
            inS = s.getInputStream();
            outS = s.getOutputStream();
        } catch (SocketTimeoutException e) {
            serS.close();

        } catch (BindException e) {

        }
    }

    //Геттеры
    public ServerSocket getSerS(){
        return serS;
    }
    public InputStream getInS(){
        return inS;
    }
    public OutputStream getOutS(){
        return outS;
    }

}