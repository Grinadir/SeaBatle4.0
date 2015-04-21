package sample;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by User on 09.04.2015.
 */
public class SystemOfIncomingMessage {

    private ClientServerConnector connector;

    SystemOfIncomingMessage(ClientServerConnector connector) {
        this.connector = connector;
    }

    public void mainFunctionOfIncomingMessage() throws IOException {
        if (connector.getServer().isClosed()) {
            waitingForIncomingMessage(connector.getClient().getInputClientStream());
        } else {
            waitingForIncomingMessage(connector.getServer().getInputServerStream());
        }
    }

    private void waitingForIncomingMessage(InputStream inputStream) throws IOException {
        DataInputStream stream = new DataInputStream(inputStream);
        while (true) {
            new InputMessage(connector, stream).inputMessageHandler();
        }
    }
}
