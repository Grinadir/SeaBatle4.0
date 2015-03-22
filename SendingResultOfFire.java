package sample;

import javafx.scene.paint.Color;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Selkov Alexsandr on 23.02.2015.
 */
public class SendingResultOfFire {
    /*
    *Класс SendingResultOfFire
    *Класс служащий всего навсего для
    * того, чтобы отдельно лежала функция
    * sendResult()
    */


    static Date currentDate = new Date();


    static public void sendResult(int x, int y) {

        if (StartClientServer.sr.serS != null
                && !StartClientServer.sr.serS.isClosed()) {

            //workWithMyField(x, y);

            DataOutputStream out = new DataOutputStream(Server.outS);
            sendResultOne(out, "Server");
        } else {

            //workWithMyField(x, y);

            DataOutputStream out = new DataOutputStream(Client.outC);
            sendResultOne(out, "Client");


        }

    }


    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private static void sendResultOne(DataOutputStream out, String s) {
        try {

            if (Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].getFill() == Color.ORANGE) {
                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + StartClientServer.dX + "%" + StartClientServer.dY
                        + "*DAM;");
            } else if (Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].getFill() == Color.BLACK) {
                System.out.println("_____________Заход");
                int index1=Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].privateShip.getX1()+
                        (10*Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].privateShip.getY1());

                int index2=Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].privateShip.getX2()+
                        (10*Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].privateShip.getY2());

                int index3=Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].privateShip.getX3()+
                        (10*Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].privateShip.getY3());

                int index4=Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].privateShip.getX4()+
                        (10*Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].privateShip.getY4());

                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + StartClientServer.dX + "%" + StartClientServer.dY
                        + "*DESTROY;"+index1+"&"+index2+"@"+index3+"#"+index4+"~");

            } else if ((Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].getFill() == Color.YELLOW )|| (Gui.rectMY[StartClientServer.dX + (StartClientServer.dY * 10)].getFill() == Color.GREEN)) {
                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + StartClientServer.dX + "%" + StartClientServer.dY
                        + "*MISS;");

            }


            //FIXME updateMessage("");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


    }


    private int calculateIndex(int x, int y) {

        return x + (10 * y);

    }


}
