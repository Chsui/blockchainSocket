package chsui.blockchain.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void connectServer(String IPaddr, int port, String command) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            InetAddress addr = InetAddress.getByName(IPaddr);
            socket = new Socket(addr, port);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            // System.out.println(socket.toString());
        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            out.println(command);
            out.flush();

            String str = null;
            while((str=in.readLine())!=null){
                System.out.println(str);
            }
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        connectServer(args[0], Integer.parseInt(args[1]), args[2]);
    }
}
