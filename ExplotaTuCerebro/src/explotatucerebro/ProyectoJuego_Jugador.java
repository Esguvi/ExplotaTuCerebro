package explotatucerebro;

/**
 * @see <a href="https://github.com/Esguvi/ExplotaTuCerebro">GitHub</a> 
 * @author Grupo 1 - Miriam Daimiel Acedo | Víctor Escaso Gutiérrez | Guillermo Lucio García | Alejandro Muñoz Pardo | Carlos Isaac Muriel Cuevas.
*/

// CLASS
public class ProyectoJuego_Jugador {
    
    // -- DECLARACIÓN DE VARIABLES --
    private String name;
    private String email;
    private String password;

    
    // -- CONSTRUCTOR --
    public ProyectoJuego_Jugador(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    
    // -- MÉTODOS GET Y SET DEL JUGADOR --
    
    // NAME
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // EMAIL
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // PASSWORD
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
} // class
