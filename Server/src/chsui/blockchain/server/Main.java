package chsui.blockchain.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static int port = 25555;

    private static String exec(String command){
        String result = "";
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        StringBuffer sb = new StringBuffer();
        try{
            p = rt.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String cl = null;
            while((cl=in.readLine())!=null){
                sb.append(cl);
                sb.append("\n");
            }
            result = sb.toString();
            in.close();
        } catch(IOException e) {
            e.printStackTrace();
            return "";
        }
        return result;
    }

    private static void openServer() {
        Socket socket = null;
        ServerSocket s_socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            s_socket = new ServerSocket(port);
        } catch(IOException e) {
            System.out.println("Port is already in use");
        }

        try {
            System.out.println("Server open");
            socket = s_socket.accept();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            String command = in.readLine();

            out.write(exec(command));
            out.flush();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        openServer();
    }
}
