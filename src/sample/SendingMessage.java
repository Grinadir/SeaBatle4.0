package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.concurrent.Task;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

public class SendingMessage extends Task {


    private Date currentDate = new Date();
    private ClientServerConnector connector;
    private Gui gui;

    public SendingMessage(Gui gui, ClientServerConnector connector) {
        this.gui = gui;
        this.connector = connector;
    }


    @Override
    protected Void call() throws Exception {
        mainFunctionOutputMessage();
        return null;
    }


    //ДАЛЕЕ ИДУТ ЭКСТРАКТНЫЕ ФУНКЦИИ
    private void mainFunctionOutputMessage() {
        if (connector.getServer().isClosed()) {
            DataOutputStream out = new DataOutputStream(connector.getClient().getOutputClientStream());
            outputAndUpdateMess(out, "Client");
        } else {
            DataOutputStream out = new DataOutputStream(connector.getServer().getOutputServerStream());
            outputAndUpdateMess(out, "Server");
        }
    }

    private void outputAndUpdateMess(DataOutputStream out, String str) {
        try {
            updateMessage(str + " " + "(" + currentDate + ")" + ":" + gui.getSendingMessage().getText());
            out.writeUTF(str + " " + "(" + currentDate + ")" + ":" + gui.getSendingMessage().getText());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


}
