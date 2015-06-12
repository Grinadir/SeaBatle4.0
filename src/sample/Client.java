package sample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {
    private InputStream inputClientStream;
    private OutputStream outputClientStream;
    private InetAddress ip;
    private Socket clientSocket;
    private int port = 8080;

    public Client() throws IOException {
        ip = InetAddress.getByName("192.168.100.5");
    }

    public void clientWorking() throws IOException, InterruptedException {
        System.out.println("Attetion! clientWorking");
        clientSocket = new Socket(ip, port);
        inputClientStream = clientSocket.getInputStream();
        outputClientStream = clientSocket.getOutputStream();
        TimeUnit.SECONDS.sleep(5);
        if (clientSocket.isConnected() == true) {
            System.out.println("clientSocket.isConnected: " + clientSocket.isConnected());
        } else {
            clientSocket.close();
            System.out.println("executed command close, clientSocket.isClose: " + clientSocket.isClosed());
        }
    }

    public InputStream getInputClientStream() {
        return inputClientStream;
    }

    public OutputStream getOutputClientStream() {
        return outputClientStream;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

}
