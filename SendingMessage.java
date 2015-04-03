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

    private String line;
    private Date currentDate = new Date();
    private clientServerConnector SCS;


    public SendingMessage(clientServerConnector SCS){
        this.SCS=SCS;
    }


    @Override
    protected Void call() throws Exception {


        mainFunctionOutputMessage();


        return null;
    }






//ДАЛЕЕ ИДУТ ЭКСТРАКТНЫЕ ФУНКЦИИ
    private void mainFunctionOutputMessage() {
        if (SCS.getSr().getSerS() != null && !SCS.getSr().getSerS().isClosed()) {


            DataOutputStream out = new DataOutputStream(SCS.getSr().getOutS());
            outputAndUpdateMess(out, "Server");
        } else {
            DataOutputStream out = new DataOutputStream(SCS.getCl().getOutC());

                outputAndUpdateMess(out, "Client");

        }
    }

    private void outputAndUpdateMess(DataOutputStream out, String str) {
        try {

            updateMessage(str+" " + "(" + currentDate + ")" + ":" + Gui.sendingMessage.getText());
            out.writeUTF(str+" " + "(" + currentDate + ")" + ":" + Gui.sendingMessage.getText());

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


}
