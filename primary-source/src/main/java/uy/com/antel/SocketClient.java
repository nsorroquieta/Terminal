package uy.com.antel;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    static final String Host = "localhost";
    static final int port = 9898;
    private Socket sc;
    private DataOutputStream mensaje;
    private BufferedReader entrada;
    private PrintWriter out;
    private BufferedReader in;

    public SocketClient() {
        try {
            sc = new Socket(Host, port);
            out = new PrintWriter(sc.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        } catch (IOException e) {
            System.out.println("no hay conexion");
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg){
        out.println(msg);
        String resp = null;
        try {
            resp = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    /*public String sendMessage(String json){
        String response = "";
        System.out.println("llegue");
        try {
            System.out.println("llegue");
            mensaje = new DataOutputStream(this.sc.getOutputStream());
            System.out.println("llegue");
            mensaje.writeBytes(json);
            System.out.println("llegue");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }*/


    public String reciveMessage(){
        String response = "";
        try {
            entrada = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            while (true) {
                response = entrada.readLine();
                if (response!=null) {
                    System.out.println(response);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    private void closeSocket(){
        try {
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}