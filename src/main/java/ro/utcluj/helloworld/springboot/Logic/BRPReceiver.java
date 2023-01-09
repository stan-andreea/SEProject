package ro.utcluj.helloworld.springboot.Logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BRPReceiver {
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public BRPReceiver(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.socket = serverSocket.accept();
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    public String receive() throws IOException {
        return in.readUTF();
    }

    public void acknowledge() throws IOException {
        out.writeUTF("ACK");
    }
}
