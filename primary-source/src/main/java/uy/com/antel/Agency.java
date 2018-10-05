package uy.com.antel;

import java.util.ArrayList;
import java.util.Date;

public class Agency {

    private ArrayList<Ticket> tickets;


    public void IMMTicket(){

    }

    public void buyTicket(String cardRegistration, Date salesDateTime, Date startDateTime, int minutes){
        //Connect to IMM and send Ticket.
        //response OK
        String response = "";
        if(response != null){
         Ticket ticket = new Ticket(cardRegistration, salesDateTime, startDateTime, minutes);
         float totalPrice = 0;  //Total price IMM response;
         ticket.setTotalPrice(totalPrice);
         //ticket.save();
        }
    }


}
