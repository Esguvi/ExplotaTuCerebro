package explotatucerebro;


// IMPORTACIONES JAVA NETBEANS.
import java.util.*;
import java.sql.*;
import java.util.logging.*;
import java.util.regex.*;


// CLASS
public class ProyectoJuego_Main {

    private static void logIn(Scanner sc) throws SQLException {
        System.out.println("Iniciar sesión:");
        System.out.println("Escribe tu email:");
        String email = sc.next();
        System.out.println("Escribe tu contraseña:");
        String password = sc.next();

        if (verificarPersonaLogIn(email, password)) {
            System.out.println("Inicio de sesión exitoso. ¡Bienvenido!");
        } else {
            System.out.println("Credenciales incorrectas. Por favor, inténtalo de nuevo.");
        }
    }

    private static boolean verificarPersonaLogIn(String email, String password) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = ProyectoJuego_Conexion.con.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }

    private static void registrarse(Scanner sc) throws SQLException {
        System.out.println("Escribe tu nombre:");
        sc.nextLine();
        String name = sc.nextLine();

        String email = null;
        Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher;
        boolean emailExists = true;
        while (emailExists) {
            System.out.println("Escribe tu email:");
            email = sc.nextLine();

            emailExists = verificarEmail(email);
            if (emailExists) {
                System.out.println("Esta dirección de correo electrónico ya está en uso. Por favor, ingresa una dirección diferente.");
            } else {
                matcher = pattern.matcher(email);
                if (!matcher.matches()) {
                    System.out.println("No has introducido una dirección de correo electrónico válida.");
                    emailExists = true;
                }
            }
        }

        System.out.println("Escribe tu contraseña");
        String password = sc.nextLine();

        ProyectoJuego_Jugador jugador = new ProyectoJuego_Jugador(name, email, password);

        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = ProyectoJuego_Conexion.con.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            pstmt.executeUpdate();

            System.out.println("Datos insertados correctamente en la base de datos.");
        }
    }

    private static boolean verificarEmail(String email) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM users WHERE email = ?";
        try (PreparedStatement pstmt = ProyectoJuego_Conexion.con.prepareStatement(query)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }
    
    
    // MAIN
    public static void main(String[] args) throws SQLException {
        
        ProyectoJuego_Conexion.conectar();
        
        try (Scanner sc = new Scanner(System.in)) {
            int opcion = 0;

            do {
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Registrarse");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        logIn(sc);
                        break;
                    case 2:
                        registrarse(sc);
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("No has introducido una opción válida");
                }
            } while (opcion != 0); 
        }
    }   
}
