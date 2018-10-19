package uy.com.antel;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

    static final String Host = "localhost";
    static final int port = 9898;
    static Socket sc;
    static DataOutputStream mensaje;
    static DataInputStream entrada;

    public SocketClient(String json) {
        try {
            sc = new Socket(Host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            String response;
            while (true) {
                response = in.readLine();
                if (response.startsWith("Bienvenido")) {
                    System.out.println(response);
                    break;
                }
            }
            mensaje = new DataOutputStream(sc.getOutputStream());
            mensaje.writeBytes(json);
//            mensaje.writeUTF(json);
            sc.close();

        } catch (IOException e) {
            System.out.println("no hay conexion");
            e.printStackTrace();
        }


    }
}