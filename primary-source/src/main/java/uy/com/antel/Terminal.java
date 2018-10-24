package uy.com.antel;

import oracle.jrockit.jfr.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Terminal {

    public static void main (String[] Args){
        BufferedReader userInput = new BufferedReader( new InputStreamReader(System.in));
        String command = null;

        do {
            SocketClient sc = new SocketClient();

            System.out.println("");
            System.out.println("Menu Principal");
            System.out.println("------------------");
            System.out.println("1) Comprar Ticket -> C");
            System.out.println("2) Anular Ticket -> A");
            System.out.println("3) Ayuda -> H");
            System.out.println("----------------");
            System.out.print("Ingrese el comando ->");
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
                    System.out.println("1) Comprar Ticket -> C");
                    System.out.println("2) Anular Ticket -> A");
                    System.out.println("3) Ayuda -> H");
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
                                System.out.print("Matricula ->");
                                carRegistration = userInput.readLine();
                                if (carRegistration.trim().equals("")) {
                                    System.out.println("----------------");
                                    System.out.println("Matricula Inválida.");
                                    System.out.println("----------------");
                                } else {
                                    flagCarRegistration = false;
                                }
                            } while (flagCarRegistration);

                            do {
                                System.out.print("Fecha de inicio <dd-MM-yyyy HH:mm:ss> ->");

                                startDate = userInput.readLine();

                                if (startDate.trim().equals("")) {
                                    System.out.println("----------------");
                                    System.out.println("Fecha inválida.");
                                    System.out.println("----------------");
                                } else {
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                    try {
                                        Date date = formatter.parse(startDate);
                                        flagStartTime = false;
                                    } catch (java.text.ParseException e) {
                                        System.out.println("----------------");
                                        System.out.println("La fecha debe tener el siguiente formato dd-MM-yyyy HH:mm:ss");
                                        System.out.println("----------------");
                                    }
                                }
                            } while (flagStartTime);

                            do {
                                System.out.print("Cantidad de minutos <15-30-45-60-90> ->");
                                minutes = userInput.readLine();
                                if (minutes.trim().equals("15") || minutes.trim().equals("30") || minutes.trim().equals("45") || minutes.trim().equals("60") || minutes.trim().equals("90") || minutes.trim().equals("120")) {
                                    flagMinutes = false;
                                } else {
                                    System.out.println("----------------");
                                    System.out.println("Cantidad de minutos inválida, por favor ingrese un valor válido.");
                                    System.out.println("----------------");
                                }
                            } while (flagMinutes);
                            buyFlag = false;
                        } while (buyFlag);

                        String json = "{\"command\":\"buyTicket\",\"carRegistration\":\"" + carRegistration + "\",\"startDate\":\"" + startDate + "\",\"minutes\":\""+minutes+"\"}";
                        System.out.println(json);
        //c                System.out.println(sc.reciveMessage());
                        String response = sc.sendMessage(json);
                        System.out.println("Agencia: "+response);
                        sc.closeSocket();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 97: // A
                    System.out.println("Anular ticket");
                    System.out.println("----------------");
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
