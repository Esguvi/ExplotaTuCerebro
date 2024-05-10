package explotatucerebro;

import java.util.*;
import java.sql.*;

public class ProyectoJuego_Main {
    
    public static void main(String[] args) {
        ProyectoJuego_Conexion.conectar();
        GUI gui;
        try {
            gui = new GUI();
            gui.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}