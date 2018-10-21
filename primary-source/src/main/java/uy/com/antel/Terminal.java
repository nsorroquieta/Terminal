package uy.com.antel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Terminal {

    public static void main (String[] Args){
        BufferedReader userInput = new BufferedReader( new InputStreamReader(System.in));
        String command = null;
        SocketClient sc = new SocketClient();
        do {
            System.out.print("Ingrese el comando->");
            String linea = null;
            try {
                linea = userInput.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] result = linea.split("\\s");

            String inputCommand = result[0].toLowerCase();
            switch (inputCommand.hashCode()){
                case 104: // H
                    System.out.println("");
                    System.out.println("Lista de comandos");
                    System.out.println("------------------");
                    System.out.println("1) Ayuda -> H");
                    System.out.println("2) Comprar Ticket -> C");
                    System.out.println("3) Anular Ticket -> A");
                    System.out.println("6) Salir -> Q");
                    System.out.println("----------------");
                    break;
                case 99: // C
                    Boolean buyFlag = true;
                    System.out.println("COMPRAR TICKET");
                    System.out.println("----------------");
                    try {
                        String carRegistration;
                        String startDate;
                        String minutes;
                        do {
                            Boolean flagCarRegistration = true;
                            Boolean flagStartTime = true;
                            Boolean flagMinutes = true;
                            do {
                                System.out.print("Matricula->");
                                carRegistration = userInput.readLine();
                                if (carRegistration.trim().equals("")) {
                                    System.out.println("----------------");
                                    System.out.println("Matricula Inválida.");
                                    System.out.println("----------------");
                                    flagCarRegistration = true;
                                } else {
                                    flagCarRegistration = false;
                                }
                            } while (flagCarRegistration);

                            do {
                                System.out.print("Fecha de inicio->");

                                startDate = userInput.readLine();
                                if (startDate.trim().equals("")) {
                                    System.out.println("----------------");
                                    System.out.println("Fecha inválida.");
                                    System.out.println("----------------");
                                    flagStartTime = true;
                                } else {
                                    flagStartTime = false;
                                }
                            } while (flagStartTime);

                            do {
                                System.out.print("Cantidad de minutos (15-30-45-60-90) ->");
                                minutes = userInput.readLine();
                                if (minutes.trim().equals("15") || minutes.trim().equals("30") || minutes.trim().equals("45") || minutes.trim().equals("60") || minutes.trim().equals("90") || minutes.trim().equals("120")) {
                                    flagMinutes = false;
                                } else {
                                    System.out.println("----------------");
                                    System.out.println("Ingrese un valor válido.");
                                    System.out.println("----------------");
                                    flagMinutes = true;
                                }
                            } while (flagMinutes);
                            buyFlag = false;
                        } while (buyFlag);

                        String json = "{\"command\":\"buyTicket\",\"carRegistration\":\"" + carRegistration + "\",\"startDate\":\"" + startDate + "\",\"minutes\":\""+minutes+"\"}";
                        //System.out.println(json);
        //c                System.out.println(sc.reciveMessage());
                       String response = sc.sendMessage(json);
                        System.out.println("respuesta: "+response);
                        sc.closeSocket();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }



                    break;
                case 97: // A
                    System.out.println("Anular ticket");
                    System.out.println("----------------");
                    System.out.println("Harcodeado");
                    break;
                default:
                        System.out.println("----------------");
                        System.out.println("Comando Inválido: El comando no existe, ingrese H Para obtener ayuda.");
                        System.out.println("----------------");
                        break;
            }
        } while(command == null);
    }
}
