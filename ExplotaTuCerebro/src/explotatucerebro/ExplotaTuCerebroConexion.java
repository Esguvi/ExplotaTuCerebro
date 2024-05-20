package explotatucerebro;

import java.sql.*;
import java.util.logging.*;

/**
 * @see <a href="https://github.com/Esguvi/ExplotaTuCerebro">GitHub</a> 
 * @author Grupo 1 - Miriam Daimiel Acedo | Víctor Escaso Gutiérrez | Guillermo Lucio García | Alejandro Muñoz Pardo | Carlos Isaac Muriel Cuevas.
*/

// CLASS
public class ExplotaTuCerebroConexion {
    // -- DECLARACIÓN DE MÉTODOS --
    
    // 1. Método de conexión
    public static Connection con = null;
     
    // 2. Método de estado de conexión
    public static String status;
    
    // 3. Método de conectar a BBDD/MySQL
    public static java.sql.Connection conectar(){
        
        con = null;
        String driver = "com.mysql.cj.jdbc.Driver";
    
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException error) {
            Logger.getLogger(ExplotaTuCerebroConexion.class.getName()).log(Level.SEVERE,null, error);
        }

        String nombreServidor ="localhost:3306";
        String baseDatos = "proyecto_juego";

        String url = "jdbc:mysql://" + nombreServidor + "/" + baseDatos;

        String username = "root";
        String password = "";

        try {
            con = DriverManager.getConnection(url,username, password);
            status = "Conexión DataBase MySQL exitosa.";
            return con;
        } catch(SQLException error){
            Logger.getLogger(ExplotaTuCerebroConexion.class.getName()).log(Level.SEVERE, null, error);
                status = ("Estado DataBase MySQL:" + error.getMessage());
                return con;
        }
    }
        
    // 4. Método para cerrar la conexión con BBDD/MySQL
    public static boolean cerrarConexion(){
        try {
            // Cerrar la conexión
            ExplotaTuCerebroConexion.conectar().close();
            return true;
        } catch (SQLException error) {
            Logger.getLogger(ExplotaTuCerebroConexion.class.getName()).log(Level.SEVERE, null, error);
            return false;
        }
    }
    
    // 5. Método para comprobar la existencia de usuario.
    public static boolean existeUsuario(String email) throws SQLException {
        boolean existe = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conectar();
            String query = "SELECT COUNT(*) AS count FROM users WHERE email = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                existe = count > 0;
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return existe;
    }

    // 6. Método para coger el nombre por el inicio de sesión del email.
    public static String getNombreFromEmail(String email) throws SQLException {
        String nombre = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = conectar();
            String query = "SELECT name FROM users WHERE email = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("name");
            }
        } finally {
            // Cerrar recursos
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return nombre;
    }
    
    // 7. Método para coger la contraseña por el inicio de sesión del email.
    public static String getContraseñaFromEmail(String email) throws SQLException {
        String contraseña = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = conectar();
            String query = "SELECT password FROM users WHERE email = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                contraseña = rs.getString("password");
            }
        } finally {
            // Cerrar recursos
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return contraseña;
    }

} // class

