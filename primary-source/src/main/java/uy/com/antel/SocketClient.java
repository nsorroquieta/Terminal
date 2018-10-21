package uy.com.antel;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

    static final String Host = "localhost";
    static final int port = 9898;
    private Socket sc;
    private DataOutputStream mensaje;
    private BufferedReader entrada;
    private PrintWriter out;
    private BufferedReader input;



    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  in   = null;
    private DataOutputStream output     = null;




    public SocketClient() {
        try {
            socket = new Socket(Host, port);
            System.out.println("El socket está conectado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String sendMessage(String msg){
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(msg);
            this.reciveMessage();
            //output = new DataOutputStream(socket.getOutputStream());
            //output.writeBytes(msg);
            //output.close();
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
                        System.out.println("se leyo: "+line);
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
            //input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*try {
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}