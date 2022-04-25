package chsui.blockchain.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketThreadServer extends Thread {
    private Socket socket;

    public SocketThreadServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            String connIp = socket.getInetAddress().getHostAddress();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            String command = in.readLine();
            System.out.println("[" + connIp + "] Run Command: " + command);

            out.write(Main.exec(command));
            out.flush();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null) out.close();
                if(in != null) in.close();
                if(socket != null) socket.close();
            }  catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
