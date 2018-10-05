package uy.com.antel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TicketPersist extends DbConnection {

    private Ticket ticket;

    public TicketPersist(Ticket ticket) {
        this.ticket = ticket;
    }

    public void saveData(){
       // try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String salesDate = df.format(ticket.getSalesDateTime());
            String startDate = df.format(ticket.getStartDateTime());
            //String query = "INSERT INTO ticket (ticketId, nombre, fecha) VALUES('"+ticket..getTitulo()+"', '"+libro.getNombre()+"', '"+fecha+"')";
            /*conn = ds.getConnection();
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
                int insertID = rs.getInt(1);
                if(insertID > 0){
                    libro.setId(insertID);
                }
                String query2 = "INSERT INTO libro (idPublicacion, isbn) VALUES('"+libro.getId()+"', '"+libro.getIsbn()+"')";
                conn = ds.getConnection();
                ps = conn.prepareStatement(query2);
                ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //return false;
        }*/
        //return true;
    }

}
