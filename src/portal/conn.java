package portal;
import java.sql.*;

public class conn{
    Connection c;
    Statement s;
    public conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c =DriverManager.getConnection("jdbc:mysql:///ums","root","");
            s =c.createStatement();


        }catch(Exception e){
            System.out.println(e);
            System.out.println("JDBC driver not connected");
        }
    }
}

