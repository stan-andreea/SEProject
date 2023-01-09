package ro.utcluj.helloworld.springboot.Logic;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BRPSender {
    private Socket socket;
    private DataOutputStream out;

    public BRPSender(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    public void send(String message) throws IOException {
        out.writeUTF(message);
    }
}
