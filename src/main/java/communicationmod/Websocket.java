package communicationmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class Websocket extends WebSocketServer {
    private static final Logger logger = LogManager.getLogger(Websocket.class.getName());
    Websocket(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        logger.info("Received message: " + s);
        try {
            CommandExecutor.executeCommand(s);
        } catch (InvalidCommandException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }

    public void send(String message) {
        for (WebSocket ws : this.getConnections()) {
            ws.send(message);
        }
    }
}