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
    private ClientServerConnector connector;
    private Gui gui;


    public SendingMessage(Gui gui, ClientServerConnector connector){
        this.gui=gui;
        this.connector=connector;
    }


    @Override
    protected Void call() throws Exception {
        mainFunctionOutputMessage();
        return null;
    }


//ДАЛЕЕ ИДУТ ЭКСТРАКТНЫЕ ФУНКЦИИ
    private void mainFunctionOutputMessage() {
        if (connector.getSr().getSerS() != null && !connector.getSr().getSerS().isClosed()) {
            DataOutputStream out = new DataOutputStream(connector.getSr().getOutS());
            outputAndUpdateMess(out, "Server");
        } else {
            DataOutputStream out = new DataOutputStream(connector.getCl().getOutC());
            outputAndUpdateMess(out, "Client");

        }
    }

    private void outputAndUpdateMess(DataOutputStream out, String str) {
        try {

            updateMessage(str+" " + "(" + currentDate + ")" + ":" + gui.getSendingMessage().getText());
            out.writeUTF(str+" " + "(" + currentDate + ")" + ":" + gui.getSendingMessage().getText());

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


}
