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
        if (connector.getSr().getSorcetFromServer() != null && !connector.getSr().getSorcetFromServer().isClosed()) {
            DataInputStream inServer = new DataInputStream(connector.getSr().getInputStreamFromServer());
            while (true) {
                // ������� ���� ������ ������� ������ ������.
                new InputMessage(gui, connector, inServer).inputMessageHandler("Server");
            }
        } else {
            DataInputStream inClient = new DataInputStream(connector.getCl().getInputStreamFromClient());
            while (true) {
                new InputMessage(gui, connector, inClient).inputMessageHandler("Client");
            }
        }
    }
}
