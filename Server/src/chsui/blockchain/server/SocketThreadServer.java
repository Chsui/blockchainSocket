package chsui.blockchain.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SocketThreadServer extends Thread {
    private Socket socket;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public SocketThreadServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            String connIp = socket.getInetAddress().getHostAddress();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            String command = in.readLine();
            System.out.println(dateFormat.format(timestamp) + " [" + connIp + "] Run Command: " + command);

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
