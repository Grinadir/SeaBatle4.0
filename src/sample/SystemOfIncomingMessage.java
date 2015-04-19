package sample;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by User on 09.04.2015.
 */
public class SystemOfIncomingMessage {

    private ClientServerConnector connector;
    private Gui gui;

    SystemOfIncomingMessage(ClientServerConnector connector, Gui gui) {
        this.connector = connector;
        this.gui = gui;
    }

    public void mainFuncOfIncomMessage() throws IOException {
        if (connector.getServer().getServerSocket() != null && !connector.getServer().getServerSocket().isClosed()) {
            DataInputStream inServer = new DataInputStream(connector.getServer().getInputServerStream());
            while (true) {
                // ќжидаем пока клиент пришлет строку текста.
                new InputMessage(gui, connector, inServer).inputMessageHandler("Server");
            }
        } else {
            DataInputStream inClient = new DataInputStream(connector.getClient().getInputStreamFromClient());
            while (true) {
                new InputMessage(gui, connector, inClient).inputMessageHandler("Client");
            }
        }
    }
}
