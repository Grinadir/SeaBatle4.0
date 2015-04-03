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


    private static Date currentDate = new Date();

    Gui gui;
    clientServerConnector connector;
    public SendingResultOfFire(Gui gui, clientServerConnector connector){
        this.gui=gui;
        this.connector=connector;

    }

    public void sendResult(int x, int y) {

        if (connector.getSr().getSerS() != null
                && !connector.getSr().getSerS().isClosed()) {

            //workWithMyField(x, y);

            DataOutputStream out = new DataOutputStream(connector.getSr().getOutS());
            sendResultOne(out, "Server");
        } else {

            //workWithMyField(x, y);

            DataOutputStream out = new DataOutputStream(connector.getCl().getOutC());
            sendResultOne(out, "Client");


        }

    }


    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void sendResultOne(DataOutputStream out, String s) {
        try {

            MyRectangle rectangle=gui.getMyRect(connector.getDx(), (connector.getDy() * 10));

            if (rectangle.getFill() == Color.ORANGE) {
                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + connector.getDx() + "%" + connector.getDy()
                        + "*DAM;");
            } else if (rectangle.getFill() == Color.BLACK) {
                System.out.println("_____________Заход");
                int index1=rectangle.getPrivateShip().getX1()+
                        (10*rectangle.getPrivateShip().getY1());

                int index2=rectangle.getPrivateShip().getX2()+
                        (10*rectangle.getPrivateShip().getY2());

                int index3=rectangle.getPrivateShip().getX3()+
                        (10*rectangle.getPrivateShip().getY3());

                int index4=rectangle.getPrivateShip().getX4()+
                        (10*rectangle.getPrivateShip().getY4());

                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + connector.getDx() + "%" + connector.getDy()
                        + "*DESTROY;"+index1+"&"+index2+"@"+index3+"#"+index4+"~");

            } else if ((rectangle.getFill() == Color.YELLOW )|| (rectangle.getFill() == Color.GREEN)) {
                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + connector.getDx() + "%" + connector.getDy()
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
