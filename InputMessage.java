package sample;

import java.io.DataInputStream;
import java.io.IOException;
import javafx.concurrent.Task;

/**
 * Created by User on 21.03.2015.
 */
public class InputMessage {

    private Gui gui;
    private ClientServerConnector connector;
    private  DataInputStream in;
    private String line;
    public  InputMessage(Gui gui, ClientServerConnector connector, DataInputStream in){
        System.out.println("Constructor InputMessage");
        this.gui=gui;
        this.connector=connector;
        this.in=in;

    }

    public void inputMessageHandler(String s) throws IOException {

        line = in.readUTF();
        connector.updateMessageSCS(line);

    }

    public String getLine(){
        return this.line;
    }
}
