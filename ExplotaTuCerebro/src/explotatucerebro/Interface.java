package explotatucerebro;

import java.awt.Color;
import java.sql.*;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * @see <a href="https://github.com/Esguvi/ExplotaTuCerebro">GitHub</a>
 * @author Grupo 1 - Miriam Daimiel Acedo | Víctor Escaso Gutiérrez | Guillermo
 * Lucio García | Alejandro Muñoz Pardo | Carlos Isaac Muriel Cuevas.
 */
public class Interface extends javax.swing.JFrame {

    private String nombreUsuario;
    private String categoria;
    private String pregunta;
    private List<String> respuestas;
    private String indiceRespuestaCorrecta;
    private int contador;
    private static int quesosObtenidos;
    private static int quesosMaximos = 1;
    private ArrayList<String> preguntasSalidas = new ArrayList<>();

    /**
     * Creates new form Geografia
     */
    public Interface() {
        initComponents();
    }

    public Interface(String categoriaSeleccionada, String nombreUsuario) {
        initComponents();
        this.nombreUsuario = nombreUsuario;
        categoria = categoriaSeleccionada;
        lblSombra.setText(categoria.toUpperCase());
        lblTitulo.setText(categoria.toUpperCase());
        cargarPreguntaYRespuestas();
        mostrarPreguntaYRespuestas();
    }

    public void setPanelColor(Color color) {
        jPanelInterface.setBackground(color);
    }

    private void cargarPreguntaYRespuestas() {
        respuestas = new ArrayList<>();
        try {
            Connection conexion = ProyectoJuego_Conexion.conectar();
            String query = "SELECT pregunta, respuesta1, respuesta2, respuesta3, respuestaCorrecta FROM pregunta WHERE idTest = ? ORDER BY RAND() LIMIT 1";

            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setInt(1, obtenerIdTest());
            ResultSet rs = pstmt.executeQuery();

            if (contador < 3) {
                if (rs.next() && preguntasSalidas.size() < 5) {
                    if (!preguntasSalidas.contains(rs.getString("pregunta"))) {

                        pregunta = rs.getString("pregunta");
                        preguntasSalidas.add(rs.getString("pregunta"));
                        respuestas.add("<html><center>"+rs.getString("respuesta1")+"<center><html>");
                        respuestas.add("<html><center>"+rs.getString("respuesta2")+"<center><html>");
                        respuestas.add("<html><center>"+rs.getString("respuesta3")+"<center><html>");
                        respuestas.add("<html><center>"+rs.getString("respuestaCorrecta")+"<center><html>");

                        indiceRespuestaCorrecta = "<html><center>"+rs.getString("respuestaCorrecta")+"<center><html>";
                    }else{
                        cargarPreguntaYRespuestas();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Has fallado todas las preguntas de la categoría, presiona siguiente", "Lo siento", JOptionPane.ERROR_MESSAGE);
                    btnSiguiente.setEnabled(false);
                }
            } else {
                System.out.println("Queso ganado");
                JOptionPane.showMessageDialog(this, "¡Nuevo queso ganado!", "¡Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);
                quesosObtenidos++;
                System.out.println(quesosObtenidos);
                GUICategorias categorias = new GUICategorias(nombreUsuario, quesosObtenidos);
                if(quesosObtenidos > quesosMaximos){
                    categorias.setVisible(true);
                    this.dispose();
                }
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private int obtenerIdTest() {
        try {
            Connection conexion = ProyectoJuego_Conexion.conectar();
            String query = "SELECT idTest FROM test WHERE categoria = ?";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, categoria);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idTest");
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return -1;
    }

    private void mostrarPreguntaYRespuestas() {
        lblRespuesta1.setBackground(new JButton().getBackground());
        lblRespuesta2.setBackground(new JButton().getBackground());
        lblRespuesta3.setBackground(new JButton().getBackground());
        lblRespuesta4.setBackground(new JButton().getBackground());

        lblPregunta.setText("<html><center>"+pregunta+"<center><html>");
        if (!respuestas.isEmpty()) {
            Collections.shuffle(respuestas);

            lblRespuesta1.setText(respuestas.get(0));
            lblRespuesta2.setText(respuestas.get(1));
            lblRespuesta3.setText(respuestas.get(2));
            lblRespuesta4.setText(respuestas.get(3));

            lblRespuesta1.putClientProperty("indiceCorrecto", respuestas.indexOf(respuestas.get(3)));
            lblRespuesta2.putClientProperty("indiceCorrecto", respuestas.indexOf(respuestas.get(3)));
            lblRespuesta3.putClientProperty("indiceCorrecto", respuestas.indexOf(respuestas.get(3)));
            lblRespuesta4.putClientProperty("indiceCorrecto", respuestas.indexOf(respuestas.get(3)));
        } else {
            lblRespuesta1.setText("");
            lblRespuesta2.setText("");
            lblRespuesta3.setText("");
            lblRespuesta4.setText("");
        }
        lblPuntuacion.setText("Puntuación: " + contador);
    }

    private void comprobarRespuesta(final JButton clickedButton) {
        final String indiceRespuestaSeleccionada = clickedButton.getText();
        Color colorDeFondo = clickedButton.getBackground();
        lblRespuesta1.setBackground(Color.RED);
        lblRespuesta2.setBackground(Color.RED);
        lblRespuesta3.setBackground(Color.RED);
        lblRespuesta4.setBackground(Color.RED);
        if (!indiceRespuestaSeleccionada.equals(indiceRespuestaCorrecta)) {
            // Si la respuesta seleccionada es incorrecta, ponerla en rojo
            clickedButton.setBackground(Color.RED);
            // Buscar el botón que contiene la respuesta correcta y ponerlo en verde
            if (lblRespuesta1.getText().equals(indiceRespuestaCorrecta)) {
                lblRespuesta1.setBackground(Color.GREEN);
            } else if (lblRespuesta2.getText().equals(indiceRespuestaCorrecta)) {
                lblRespuesta2.setBackground(Color.GREEN);
            } else if (lblRespuesta3.getText().equals(indiceRespuestaCorrecta)) {
                lblRespuesta3.setBackground(Color.GREEN);
            } else if (lblRespuesta4.getText().equals(indiceRespuestaCorrecta)) {
                lblRespuesta4.setBackground(Color.GREEN);
            }
        } else {
            clickedButton.setBackground(Color.GREEN);
            contador++;
            lblPuntuacion.setText("Puntuación: " + contador);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelInterface = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSombra = new javax.swing.JLabel();
        lblLinea = new javax.swing.JLabel();
        lblSombraLinea = new javax.swing.JLabel();
        lblPuntuacion = new javax.swing.JLabel();
        panelPregunta = new javax.swing.JPanel();
        lblPregunta = new javax.swing.JLabel();
        panelRespuesta1 = new javax.swing.JPanel();
        lblRespuesta1 = new javax.swing.JButton();
        panelRespuesta2 = new javax.swing.JPanel();
        lblRespuesta2 = new javax.swing.JButton();
        panelRespuesta3 = new javax.swing.JPanel();
        lblRespuesta3 = new javax.swing.JButton();
        panelRespuesta4 = new javax.swing.JPanel();
        lblRespuesta4 = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanelInterface.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CATEGORIA");
        jPanelInterface.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 630, 60));

        lblSombra.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblSombra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSombra.setText("CATEGORIA");
        jPanelInterface.add(lblSombra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 640, 50));

        lblLinea.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        lblLinea.setForeground(new java.awt.Color(255, 255, 255));
        lblLinea.setText("______");
        jPanelInterface.add(lblLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        lblSombraLinea.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        lblSombraLinea.setText("______");
        jPanelInterface.add(lblSombraLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, -10, -1, 110));

        lblPuntuacion.setText("Puntuación: ");
        jPanelInterface.add(lblPuntuacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 90, 100));

        panelPregunta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPregunta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPregunta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPregunta.setText("Pregunta");
        panelPregunta.add(lblPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 90));

        jPanelInterface.add(panelPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 590, 90));

        panelRespuesta1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRespuesta1.setBackground(new java.awt.Color(153, 255, 255));
        lblRespuesta1.setText("Respuesta1");
        lblRespuesta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblRespuesta1ActionPerformed(evt);
            }
        });
        panelRespuesta1.add(lblRespuesta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 50));

        jPanelInterface.add(panelRespuesta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 280, 50));

        panelRespuesta2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRespuesta2.setBackground(new java.awt.Color(153, 255, 255));
        lblRespuesta2.setText("Respuesta2");
        lblRespuesta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblRespuesta2ActionPerformed(evt);
            }
        });
        panelRespuesta2.add(lblRespuesta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 50));

        jPanelInterface.add(panelRespuesta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 260, 50));

        panelRespuesta3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRespuesta3.setBackground(new java.awt.Color(153, 255, 255));
        lblRespuesta3.setText("Respuesta3");
        lblRespuesta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblRespuesta3ActionPerformed(evt);
            }
        });
        panelRespuesta3.add(lblRespuesta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 50));

        jPanelInterface.add(panelRespuesta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 280, 50));

        panelRespuesta4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRespuesta4.setBackground(new java.awt.Color(153, 255, 255));
        lblRespuesta4.setText("Respuesta4");
        lblRespuesta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblRespuesta4ActionPerformed(evt);
            }
        });
        panelRespuesta4.add(lblRespuesta4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 50));

        jPanelInterface.add(panelRespuesta4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 260, 50));

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        jPanelInterface.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 90, 20));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanelInterface.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 90, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInterface, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInterface, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblRespuesta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblRespuesta1ActionPerformed
        JButton clickedButton = (JButton) evt.getSource();
        comprobarRespuesta(clickedButton);
    }//GEN-LAST:event_lblRespuesta1ActionPerformed

    private void lblRespuesta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblRespuesta2ActionPerformed
        JButton clickedButton = (JButton) evt.getSource();
        comprobarRespuesta(clickedButton);
    }//GEN-LAST:event_lblRespuesta2ActionPerformed

    private void lblRespuesta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblRespuesta3ActionPerformed
        JButton clickedButton = (JButton) evt.getSource();
        comprobarRespuesta(clickedButton);
    }//GEN-LAST:event_lblRespuesta3ActionPerformed

    private void lblRespuesta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblRespuesta4ActionPerformed
        JButton clickedButton = (JButton) evt.getSource();
        comprobarRespuesta(clickedButton);
    }//GEN-LAST:event_lblRespuesta4ActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        GUICategorias categorias = new GUICategorias(nombreUsuario,quesosObtenidos);
        categorias.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        cargarPreguntaYRespuestas();
        mostrarPreguntaYRespuestas();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ProyectoJuego_Conexion.conectar();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JPanel jPanelInterface;
    private javax.swing.JLabel lblLinea;
    private javax.swing.JLabel lblPregunta;
    private javax.swing.JLabel lblPuntuacion;
    private javax.swing.JButton lblRespuesta1;
    private javax.swing.JButton lblRespuesta2;
    private javax.swing.JButton lblRespuesta3;
    private javax.swing.JButton lblRespuesta4;
    private javax.swing.JLabel lblSombra;
    private javax.swing.JLabel lblSombraLinea;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelPregunta;
    private javax.swing.JPanel panelRespuesta1;
    private javax.swing.JPanel panelRespuesta2;
    private javax.swing.JPanel panelRespuesta3;
    private javax.swing.JPanel panelRespuesta4;
    // End of variables declaration//GEN-END:variables
}
