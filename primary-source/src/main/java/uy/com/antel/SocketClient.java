package uy.com.antel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {

    static final String Host = "localhost";
    static final int port = 9898;
    static Socket sc;
    static DataOutputStream mensaje;
    static DataInputStream entrada;


    public static void main(String[] args){

        try {
            sc = new Socket(Host, port);
            mensaje = new DataOutputStream(sc.getOutputStream());
            mensaje.writeUTF("Hola que tal");

            sc.close();

        } catch (IOException e) {
            System.out.println("no hay conexion");
            e.printStackTrace();
        }

    }


}