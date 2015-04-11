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

    private InputStream inC;
    private OutputStream outC;
    private InetAddress ip;
    private Socket clS;
    private int port = 8080;
    private ClientServerConnector SCS;

    //static public OutputStream infoOutputC;

    public Client(ClientServerConnector SCS) throws IOException {

        ip = InetAddress.getByName("192.168.100.5");
        this.SCS=SCS;


    }

    public void clientWorking() throws IOException, InterruptedException {
        System.out.println("Attetion! clientWorking");
        DataInputStream in = new DataInputStream(SCS.getSr().getInS());
        clS = new Socket(ip, port);
        inC = clS.getInputStream();
        outC = clS.getOutputStream();
        TimeUnit.SECONDS.sleep(5);
        if (clS.isConnected() == true) {

            System.out.println("cls.isConnected: " + clS.isConnected());
        } else {

            clS.close();
            System.out.println("executed command close, cls.isClose: " + clS.isClosed());
        }
    }
    //Геттеры
    public InputStream getInC(){
        return inC;
    }

    public OutputStream getOutC(){
        return outC;
    }

    public Socket getClS(){
        return clS;
    }

}
