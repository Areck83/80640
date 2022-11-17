package uv.mx;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexion {
    //Si es local lleva el localhost, cuando se cambie se pasa a ip
    private static String url ="jdbc:mysql://localhost:3306/nombreBD";
    private static String DriverName = "com.mysql.jdbc.Driver";
    private static String username ="root";
    private static String password ="Pl0p-Pass.test?";
    private static Connection connection = null;

    public static Connection getConnection(){
        try{
            Class.forName(DriverName);
            connection = (Connection) DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            System.out.println(e);
        }catch (ClassNotFoundException e){
            System.out.println("No encontro el driver");
        }
        return connection;
    }
}
