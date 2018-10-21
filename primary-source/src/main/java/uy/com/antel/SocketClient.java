package uy.com.antel;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    static final String Host = "localhost";
    static final int port = 9898;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter out;



    public SocketClient() {
        try {
            socket = new Socket(Host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg){
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(msg);
            return this.reciveMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public String reciveMessage(){
        String line = "";
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            boolean flagRead = true;
            //reads message from client until "Over" is sent
            while (flagRead) {
                if (line != null) {
                    try {
                        line = input.readLine();
                        flagRead = false;
                    } catch (IOException i) {
                        System.out.println("cayo la lectura.");
                        System.out.println(i);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public void closeSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}