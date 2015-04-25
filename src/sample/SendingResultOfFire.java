package sample;

import javafx.scene.paint.Color;

import java.io.DataOutputStream;
import java.util.Date;

public class SendingResultOfFire {

    private Date currentDate = new Date();
    private Engine engine;
    private ClientServerConnector connector;
    private int x;
    private int y;

    public SendingResultOfFire(Engine engine, ClientServerConnector connector) {
        this.engine = engine;
        this.connector = connector;
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

    private void sendResultOne(DataOutputStream out, String whoClientOrServer) {
        MyRectangle rectangle = engine.getRects().getMyRect(x, y);
        if (rectangle.getFill() == Color.ORANGE) {
            try {
                String reportResultOfAttack = String.format("!result attacked %s field (%s) attacked coordinates: ($%d%%%d*DAM;",
                        whoClientOrServer, currentDate, x, y);
                out.writeUTF(reportResultOfAttack);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (rectangle.getFill() == Color.BLACK) {
            int index1 = rectangle.getPrivateShip().getX1() +
                    (10 * rectangle.getPrivateShip().getY1());
            int index2 = rectangle.getPrivateShip().getX2() +
                    (10 * rectangle.getPrivateShip().getY2());
            int index3 = rectangle.getPrivateShip().getX3() +
                    (10 * rectangle.getPrivateShip().getY3());
            int index4 = rectangle.getPrivateShip().getX4() +
                    (10 * rectangle.getPrivateShip().getY4());
            try {
                String reportResultOfAttack = String.format("!result attacked %s field (%s) attacked coordinates: "
                                + "($%d%%%d*DESTROY;%d&%d@%d#%d~",
                        whoClientOrServer, currentDate, x, y, index1, index2, index3, index4);
                out.writeUTF(reportResultOfAttack);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ((rectangle.getFill() == Color.YELLOW) || (rectangle.getFill() == Color.GREEN)) {
            try {
                String reportResultOfAttack = String.format("!result attacked %s field (%s) attacked coordinates: ($%d%%%d*MISS;",
                        whoClientOrServer, currentDate, x, y);
                out.writeUTF(reportResultOfAttack);
                engine.getStatus().setFollowStep(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
