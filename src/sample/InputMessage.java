package sample;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by User on 21.03.2015.
 */
public class InputMessage {

    private Gui gui;
    private ClientServerConnector connector;
    private DataInputStream dataInputStreamFromInputMessage;
    private String line;

    public InputMessage(Gui gui, ClientServerConnector connector, DataInputStream in) {
        System.out.println("Constructor InputMessage");
        this.gui = gui;
        this.connector = connector;
        this.dataInputStreamFromInputMessage = in;
    }

    public void inputMessageHandler(String s) throws IOException {
        line = dataInputStreamFromInputMessage.readUTF();
        connector.updateMessageSCS(line);
    }

    public String getLine() {
        return this.line;
    }
}
