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


    private Date currentDate = new Date();

    private Gui gui;
    private clientServerConnector connector;
    private int x;
    private int y;
    public SendingResultOfFire(Gui gui, clientServerConnector connector){
        this.gui=gui;
        this.connector=connector;
        System.out.println("Конструктор SendingResultOfFire");

    }

    public void sendResult(int x, int y) {
        System.out.println("Вхождение в sendResult");
        this.x=x;
        this.y=y;

        if (connector.getSr().getSerS() != null
                && !connector.getSr().getSerS().isClosed()) {

            //workWithMyField(x, y);
            System.out.println("Вхождение в sendResult if");

            DataOutputStream out = new DataOutputStream(connector.getSr().getOutS());
            sendResultOne(out, "Server");
            System.out.println("connector.getSr().getOutS()"+connector.getSr().getOutS());
        } else {
            System.out.println("Вхождение в sendResult else");

            //workWithMyField(x, y);

            DataOutputStream out = new DataOutputStream(connector.getCl().getOutC());
            sendResultOne(out, "Client");
            System.out.println("connector.getSr().getOutS()" + connector.getCl().getOutC());


        }
        System.out.println("connector.getSr().getOutS() " + connector.getSr().getOutS());
        System.out.println("connector.getCl().getOutC( )" + connector.getCl().getOutC());
    }


    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void sendResultOne(DataOutputStream out, String s) {
        System.out.println("Вхождение в SROF");
        //try {
        System.out.println("gui.getMyRect(x, (y * 10) "+(x+y * 10));
        System.out.println("gui.getMyRect(x, (y * 10) "+gui.getMyRect(x+(y * 10)));

            MyRectangle rectangle=gui.getMyRect(x+(y * 10));
            System.out.println("regtangle "+rectangle);


            if (rectangle.getFill() == Color.ORANGE) {

                try{
                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + connector.getDx() + "%" + connector.getDy()
                        + "*DAM;");
                System.out.println("Результат атаки отправден");
                }
                catch (Exception e){
                    System.out.println("Результат атаки не отправлен");
                    e.printStackTrace();

                }
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
                try{

                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + connector.getDx() + "%" + connector.getDy()
                        + "*DESTROY;" + index1 + "&" + index2 + "@" + index3 + "#" + index4 + "~");
                System.out.println("Результат атаки отправден");
                }
                catch (Exception e){
                    System.out.println("Результат атаки не отправлен");
                    e.printStackTrace();
                }

            } else if ((rectangle.getFill() == Color.YELLOW )|| (rectangle.getFill() == Color.GREEN)) {
                try{
                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + connector.getDx() + "%" + connector.getDy()
                        + "*MISS;");
                System.out.println("Результат атаки отправден");}
                catch (Exception e){
                    System.out.println("Результат атаки не отправлен");
                    e.printStackTrace();
                }
            }


            //FIXME updateMessage("");
        //} catch (IOException e1) {
            // TODO Auto-generated catch block
        //    e1.printStackTrace();
       // }


    }



}
