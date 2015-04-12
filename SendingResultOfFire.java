package sample;

import javafx.scene.paint.Color;

import java.io.DataOutputStream;
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
    private ClientServerConnector connector;
    private int x;
    private int y;
    private Status status;

    public SendingResultOfFire(Gui gui, ClientServerConnector connector, Status status){
        this.gui=gui;
        this.connector=connector;
        this.status=status;
        System.out.println("Designer SendingResultOfFire");
    }

    public void sendResult(int x, int y) {
        System.out.println("Entry in sendResult");
        this.x=x;
        this.y=y;
        if (connector.getSr().getSerS() != null
                && !connector.getSr().getSerS().isClosed()) {
            System.out.println("Entry in sendResult if");
            DataOutputStream out = new DataOutputStream(connector.getSr().getOutS());
            sendResultOne(out, "Server");
            System.out.println("connector.getSr().getOutS()"+connector.getSr().getOutS());
        } else {
            System.out.println("Entry in sendResult else");
            DataOutputStream out = new DataOutputStream(connector.getCl().getOutC());
            sendResultOne(out, "Client");
            System.out.println("connector.getSr().getOutS()" + connector.getCl().getOutC());
        }
        System.out.println("connector.getSr().getOutS() " + connector.getSr().getOutS());
        System.out.println("connector.getCl().getOutC( )" + connector.getCl().getOutC());
    }


    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private void sendResultOne(DataOutputStream out, String s) {
        System.out.println("Entry in SROF");
        System.out.println("gui.getMyRect(x, (y * 10) "+(x+y * 10));
        System.out.println("gui.getMyRect(x, (y * 10) "+gui.getMyRect(x+(y * 10)));
        MyRectangle rectangle=gui.getMyRect(x+(y * 10));
        System.out.println("regtangle "+rectangle);
        if (rectangle.getFill() == Color.ORANGE) {
            try{
                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + x + "%" + y
                        + "*DAM;");
                System.out.println("Result of attak was send");
            }
                catch (Exception e){
                    System.out.println("Рesult of attack was NOT send");
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
                        + "$" + x + "%" + y
                        + "*DESTROY;" + index1 + "&" + index2 + "@" + index3 + "#" + index4 + "~");
                System.out.println("Result of attack was send");
            }
            catch (Exception e){
                System.out.println("Result of attack was NOT send");
                e.printStackTrace();
                }
        } else if ((rectangle.getFill() == Color.YELLOW )|| (rectangle.getFill() == Color.GREEN)) {
            try{
                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + x + "%" + y
                        + "*MISS;");
                status.setFollowStep(true);
                System.out.println("Result of attack was send");
                }
                catch (Exception e){
                    System.out.println("Result of fire don't delivered");
                    e.printStackTrace();
                }
            }
    }
}
