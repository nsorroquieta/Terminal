package uy.com.antel;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class DbConnection {

    protected static String DATASOURCE_CONTEXT = "java:jboss/datasources/MySqlDS";
    protected Context ctx = null;
    protected DataSource ds = null;
    protected ResultSet rs = null;
    protected Connection conn = null;
    protected PreparedStatement ps = null;


    public DbConnection() {
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(DATASOURCE_CONTEXT);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getRs(){
        return this.rs;
    }



}