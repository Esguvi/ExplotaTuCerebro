package explotatucerebro;

import java.sql.*;

/**
 * @see <a href="https://github.com/Esguvi/ExplotaTuCerebro">GitHub</a> 
 * @author Grupo 1 - Miriam Daimiel Acedo | Víctor Escaso Gutiérrez | Guillermo Lucio García | Alejandro Muñoz Pardo | Carlos Isaac Muriel Cuevas.
*/

public class ProyectoJuego_Main {
    
    public static void main(String[] args) {
        ProyectoJuego_Conexion.conectar();
        GUI gui;
        try {
            gui = new GUI();
            gui.setVisible(true);
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
}