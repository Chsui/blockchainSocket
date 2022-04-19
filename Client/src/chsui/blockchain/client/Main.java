package chsui.blockchain.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void connectServer(String IPaddr, int port) {
        Socket socket = null;
        BufferedReader in = null;
        BufferedReader in2 = null;
        PrintWriter out = null;
        InetAddress addr = null;

        try {
            addr = InetAddress.getByName(IPaddr);
            socket = new Socket(addr, port);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            in2 = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            System.out.println(socket.toString());
        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.print("To Server: ");
            String data = in2.readLine();
            out.println(data);
            out.flush();

            String str = in.readLine();
            System.out.println("From Server: \n" + str);
            while((str=in.readLine())!=null){
                System.out.println(str);
            }
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        connectServer(args[0], Integer.parseInt(args[1]));
    }
}
