package sample;
/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


public class Client {
    /*
    *Класс Clent
    * *Изначально создан в Eclipse
    */

    private InputStream inputStreamFromClient;
    private OutputStream outputStreamFromClient;
    private InetAddress ip;
    private Socket socketFromClient;
    private int port = 8080;
    private ClientServerConnector connector;

    public Client(ClientServerConnector connector) throws IOException {
        ip = InetAddress.getByName("192.168.100.5");
        this.connector = connector;
    }

    public void clientWorking() throws IOException, InterruptedException {
        System.out.println("Attetion! clientWorking");
        DataInputStream in = new DataInputStream(connector.getSr().getInputStreamFromServer());
        socketFromClient = new Socket(ip, port);
        inputStreamFromClient = socketFromClient.getInputStream();
        outputStreamFromClient = socketFromClient.getOutputStream();
        TimeUnit.SECONDS.sleep(5);
        if (socketFromClient.isConnected() == true) {
            System.out.println("socketFromClient.isConnected: " + socketFromClient.isConnected());
        } else {
            socketFromClient.close();
            System.out.println("executed command close, socketFromClient.isClose: " + socketFromClient.isClosed());
        }
    }

    //Геттеры
    public InputStream getInputStreamFromClient() {
        return inputStreamFromClient;
    }

    public OutputStream getOutputStreamFromClient() {
        return outputStreamFromClient;
    }

    public Socket getSocketFromClientocketFromClient() {
        return socketFromClient;
    }

}
