package sample;

/**
 * Created by User on 22.02.2015.
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

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

    //boolean isClosed() {
    //    return (socket != null && socket.isClosed());
    //}


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


}