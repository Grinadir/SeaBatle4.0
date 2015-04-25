package sample;

import java.io.DataInputStream;
import java.io.IOException;

public class InputMessage {


    private ClientServerConnector connector;
    private DataInputStream dataInputStreamFromInputMessage;


    public InputMessage(ClientServerConnector connector, DataInputStream in) {
        this.connector = connector;
        this.dataInputStreamFromInputMessage = in;
    }

    public void inputMessageHandler() throws IOException {
        String line;
        line = dataInputStreamFromInputMessage.readUTF();
        connector.updateMessageSCS(line);
    }
}
