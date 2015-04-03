package sample;

import java.io.DataInputStream;
import java.io.IOException;
import javafx.concurrent.Task;

/**
 * Created by User on 21.03.2015.
 */
public class InputMessageHandler {


    private void inputMessageHandler(DataInputStream in, String s, int x, int y) throws IOException {
        //line = in.readUTF();


        System.out.println("Запуск входящего потока "+s+"а");
        System.out.println(s);
        System.out.println(s.charAt(0));
        if (s.charAt(0) == '#') {

            System.out.println("Под атакой");
            //StartClientServer.updateMessageSCS(s);

            x = Integer.parseInt(s.substring(s.indexOf("$") + 1, s.indexOf("%")));
            y = Integer.parseInt(s.substring(s.indexOf("%") + 1, s.indexOf("*")));

            System.out.println("Наши позиции атакованы " + x + "  " + y);
            //SendingResultOfFire.sendResult(Gui. ,dX, dY);

        } else if (s.charAt(0) == '!') {
            System.out.print("Прислан результат нашей атаки ");
            //StartClientServer.updateMessageSCS(s);
            x = Integer.parseInt(s.substring(s.indexOf("$") + 1, s.indexOf("%")));
            y = Integer.parseInt(s.substring(s.indexOf("%") + 1, s.indexOf("*")));
            int n = x + (y * 10);

            System.out.println(x + (y * 10));
            System.out.println(x + "  " + y);

        } else {
            //StartClientServer.updateMessageSCS(s);
        }
    }


}
