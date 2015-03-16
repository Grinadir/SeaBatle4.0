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
        if (Gui.rectMY[x + (y * 10)].getFill() == Color.BLUE) {
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
        else{




        }

    }



    //ДАЛЕЕ ИДУТ EXTRACT ФУНКЦИИ
    private static void sendResultOne(DataOutputStream out, String s) {
        try {

            out.writeUTF("!result attacked "+s+" field " + "(" + currentDate + ")"
                    + " attacked coordinates: " + "("
                    + "$" + StartClientServer.dX + "%" + StartClientServer.dY
                    + "*YES;");

            //FIXME updateMessage("");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
