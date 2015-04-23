package sample;

import javafx.scene.paint.Color;

import java.io.DataOutputStream;
import java.util.Date;

/**
 * Created by Selkov Alexsandr on 23.02.2015.
 */
public class SendingResultOfFire {

    private Date currentDate = new Date();
    private Gui gui;
    private ClientServerConnector connector;
    private int x;
    private int y;
    private Status status;

    public SendingResultOfFire(Gui gui, ClientServerConnector connector, Status status) {
        this.gui = gui;
        this.connector = connector;
        this.status = status;
    }

    public void sendResult(int x, int y) {

        this.x = x;
        this.y = y;
        if (connector.getServer().isClosed()) {
            DataOutputStream out = new DataOutputStream(connector.getClient().getOutputClientStream());
            sendResultOne(out, "Client");

        } else {
            DataOutputStream out = new DataOutputStream(connector.getServer().getOutputServerStream());
            sendResultOne(out, "Server");
        }

    }

    private void sendResultOne(DataOutputStream out, String s) {
        MyRectangle rectangle = gui.getRects().getMyRect(x, y);
        if (rectangle.getFill() == Color.ORANGE) {
            try {
                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + x + "%" + y
                        + "*DAM;");
                System.out.println("Result of attak was send");
            } catch (Exception e) {
                System.out.println("Рesult of attack was NOT send");
                e.printStackTrace();
            }
        } else if (rectangle.getFill() == Color.BLACK) {
            System.out.println("_____________Заход");
            int index1 = rectangle.getPrivateShip().getX1() +
                    (10 * rectangle.getPrivateShip().getY1());
            int index2 = rectangle.getPrivateShip().getX2() +
                    (10 * rectangle.getPrivateShip().getY2());
            int index3 = rectangle.getPrivateShip().getX3() +
                    (10 * rectangle.getPrivateShip().getY3());
            int index4 = rectangle.getPrivateShip().getX4() +
                    (10 * rectangle.getPrivateShip().getY4());
            try {
                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + x + "%" + y
                        + "*DESTROY;" + index1 + "&" + index2 + "@" + index3 + "#" + index4 + "~");
                System.out.println("Result of attack was send");
            } catch (Exception e) {
                System.out.println("Result of attack was NOT send");
                e.printStackTrace();
            }
        } else if ((rectangle.getFill() == Color.YELLOW) || (rectangle.getFill() == Color.GREEN)) {
            try {
                out.writeUTF("!result attacked " + s + " field " + "(" + currentDate + ")"
                        + " attacked coordinates: " + "("
                        + "$" + x + "%" + y
                        + "*MISS;");
                status.setFollowStep(true);
                System.out.println("Result of attack was send");
            } catch (Exception e) {
                System.out.println("Result of fire don't delivered");
                e.printStackTrace();
            }
        }
    }
}
