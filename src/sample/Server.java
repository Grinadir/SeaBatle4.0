package sample;

/**
 * Created by User on 22.02.2015.
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
    private InputStream inputServerStream;
    private OutputStream outputServerStream;
    private ServerSocket serverSocket;
    private Socket socket;
    private int port = 8080;

    public void serverWorking() throws IOException {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(5000);
            socket = serverSocket.accept();
            System.out.println("Client connected");
            inputServerStream = socket.getInputStream();
            outputServerStream = socket.getOutputStream();
        } catch (SocketTimeoutException e) {
            serverSocket.close();

        } catch (BindException e) {

        }
    }

    public boolean isClosed() {
        return (serverSocket == null || serverSocket.isClosed());
    }


    //Get-functions
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public InputStream getInputServerStream() {
        return inputServerStream;
    }

    public OutputStream getOutputServerStream() {
        return outputServerStream;
    }


    public boolean isServerSocketClosed() {
        return serverSocket.isClosed();
    }

    public String getStatusServerSocket() {
        if (serverSocket != null) {
            return String.format("server socket '" + serverSocket + "' is " + (serverSocket.isClosed() ? "closed" : "opened"));
        } else {
            return String.format("server socket '" + serverSocket);
        }
    }
}