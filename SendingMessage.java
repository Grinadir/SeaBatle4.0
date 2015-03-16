package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.concurrent.Task;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

public class SendingMessage extends Task {

    /*
    *Класс SendingMessage наследует класс Task
    *Применяется для создания отдельного потока
    *запускаемого из GUI
    *Изначально создан в Eclipse
    */

    String line;
    Date currentDate = new Date();

    @Override
    protected Void call() throws Exception {


        mainFunctionOutputMessage();


        return null;
    }






//ДАЛЕЕ ИДУТ ЭКСТРАКТНЫЕ ФУНКЦИИ
    private void mainFunctionOutputMessage() {
        if (StartClientServer.sr.serS != null && !StartClientServer.sr.serS.isClosed()) {

            DataOutputStream out = new DataOutputStream(Server.outS);
            outputAndUpdateMess(out, "Server");
        } else {
            DataOutputStream out = new DataOutputStream(Client.outC);

                outputAndUpdateMess(out, "Client");

        }
    }

    private void outputAndUpdateMess(DataOutputStream out, String str) {
        try {

            updateMessage(str+" " + "(" + currentDate + ")" + ":" + Gui.sendingMessage.getText());
            out.writeUTF(str+" " + "(" + currentDate + ")" + ":" + Gui.sendingMessage.getText());

            updateMessage("");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}
