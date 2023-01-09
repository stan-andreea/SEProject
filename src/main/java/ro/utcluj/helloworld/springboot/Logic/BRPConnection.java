package ro.utcluj.helloworld.springboot.Logic;

import java.io.IOException;

public class BRPConnection {
    private BRPSender sender;
    private BRPReceiver receiver;

    public BRPConnection(String host, int port) throws IOException {
        this.sender = new BRPSender(host, port);
        this.receiver = new BRPReceiver(port);
    }

    public void send(String message) throws IOException {
        while (true) {
            sender.send(message);
            String acknowledgement = receiver.receive();
            if ("ACK".equals(acknowledgement)) {
                break;
            }
            // handle retransmission
            try {
                Thread.sleep(1000); // wait 1 second before retrying
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

    public String receive() throws IOException {
        String message = receiver.receive();
        receiver.acknowledge();
        return message;
    }
}
