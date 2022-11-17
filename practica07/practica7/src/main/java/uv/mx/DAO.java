package uv.mx;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

//su objetivo es independizar la logica del negocio
//
public class DAO {
    public static Conexion c = new Conexion();

    public static List<Usuario> dameUsuarios(){
        Statement stm = null;
        ResultSet rs = null;
        List<Usuario> resultado = new ArrayList<>();
        Connection cc = null;
        cc = c.getConnection();
        try{
            String sql = "SELECT * FROM usuarios";
            stm = (Statement) cc.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                Usuario u = new Usuario(rs.getString("id"), rs.getString("nombre"), rs.getString("password"));
                resultado.add(u);
            }
        }catch(Exception e){
            System.out.println(e);
        } finally {
            // vamos a liberar en este bloque todos los recursos empleando
            // se hace en orden inverso a su creación
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                rs = null;
            }

            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                stm = null;
            }
            try {
                cc.close();
                System.out.println("Closed  connection!");
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }

        return resultado;
    }

    public static String crearUsuario(Usuario u) {
        PreparedStatement stm = null;
        Connection cc = null;
        String msj = "";

        cc = c.getConnection();
        try {
            String sql = "insert into usuarios (id, nombre, password) values (?,?,?)";
            stm = (PreparedStatement) cc.prepareStatement(sql);
            stm.setString(1, u.getId());
            stm.setString(2, u.getNombre());
            stm.setString(3, u.getPassword());

            if (stm.executeUpdate() > 0)
                msj = "el usuario se agrego";
            else
                msj = "el usuario no se agrego";
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // vamos a liberar en este bloque todos los recursos empleando
            // se hace en orden inverso a su creación
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                stm = null;
            }
            try {
                cc.close();
                System.out.println("Closed  connection!");
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }

        return msj;
    }
}
