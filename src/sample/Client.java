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

    static public InputStream inC;
    static public OutputStream outC;
    InetAddress ip;
    Socket clS;
    int port = 8080;

    //static public OutputStream infoOutputC;

    public Client() throws IOException {

        ip = InetAddress.getByName("192.168.100.8");


    }

    public void clientWorking() throws IOException, InterruptedException {
        System.out.println("Attetion! clientWorking");
        DataInputStream in = new DataInputStream(Server.inS);
        clS = new Socket(ip, port);
        inC = clS.getInputStream();
        outC = clS.getOutputStream();
        TimeUnit.SECONDS.sleep(5);
        if (clS.isConnected() == true) {

            System.out.println("cls.isConnected: " + clS.isConnected());
        } else {

            clS.close();
            System.out.println("Выполнена команда close, cls.isClose: " + clS.isClosed());
        }
    }

}
