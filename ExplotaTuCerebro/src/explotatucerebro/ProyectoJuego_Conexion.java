package explotatucerebro;

import java.util.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;

/**
 * @see <a href="https://github.com/Esguvi/ExplotaTuCerebro">GitHub</a> 
 * @author Grupo 1 - Miriam Daimiel Acedo | Víctor Escaso Gutiérrez | Guillermo Lucio García | Alejandro Muñoz Pardo | Carlos Isaac Muriel Cuevas.
*/

// CLASS
public class ProyectoJuego_Conexion {
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
        } // try
        catch (ClassNotFoundException error) {
            Logger.getLogger(ProyectoJuego_Conexion.class.getName()).log(Level.SEVERE,null, error);
        } // catch // catch

        String nombreServidor ="localhost:3306";
        String baseDatos = "proyecto_juego";

        String url = "jdbc:mysql://" + nombreServidor + "/" + baseDatos;

        String username = "root";
        String password = "";

        try {
            con = DriverManager.getConnection(url,username, password);
            status = "Conexión DataBase MySQL exitosa.";
            return con;
        } // try
        catch(SQLException error){
            Logger.getLogger(ProyectoJuego_Conexion.class.getName()).log(Level.SEVERE, null, error);
                status = ("Estado DataBase MySQL:" + error.getMessage());
                return con;
        } // catch // catch
    } // static conectar()
    
    
    // 4. Método para cerrar la conexión con BBDD/MySQL
    public static boolean cerrarConexion(){
        try {
            // Cerrar la conexión
            ProyectoJuego_Conexion.conectar().close();
            return true;
        } // try // try
        catch (SQLException error) {
            Logger.getLogger(ProyectoJuego_Conexion.class.getName()).log(Level.SEVERE, null, error);
            return false;
        } // catch // catch
    } // static cerrarConexion()
    
    
    // 5. Método para comprobar la existencia de usuario.
    public static boolean existeUsuario(String email) throws SQLException{
        boolean existe = false;
        String query = "SELECT COUNT(*) AS count FROM users WHERE email = ?";
        try (PreparedStatement pstmt = ProyectoJuego_Conexion.con.prepareStatement(query)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    existe = count > 0;
                }
            }
        }
        return existe;
    }
} // class

