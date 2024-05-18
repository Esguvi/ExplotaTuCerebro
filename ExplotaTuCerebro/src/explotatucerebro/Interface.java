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
    private static ArrayList<String> preguntasSalidas = new ArrayList<>();

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
        jLabel1.setText(categoria.toUpperCase());
        jLabel2.setText(categoria.toUpperCase());
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
                if (rs.next()) {
                    if (!preguntasSalidas.contains(rs.getString("pregunta"))) {

                        pregunta = rs.getString("pregunta");
                        preguntasSalidas.add(rs.getString("pregunta"));
                        respuestas.add(rs.getString("respuesta1"));
                        respuestas.add(rs.getString("respuesta2"));
                        respuestas.add(rs.getString("respuesta3"));
                        respuestas.add(rs.getString("respuestaCorrecta"));

                        indiceRespuestaCorrecta = rs.getString("respuestaCorrecta");
                    }else{
                        cargarPreguntaYRespuestas();
                    }
                } else {
                    jButton6.setEnabled(false);
                }
            } else {
                System.out.println("Queso ganado");
                JOptionPane.showMessageDialog(this, "¡Nuevo queso ganado!", "¡Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);
                quesosObtenidos++;
                System.out.println(quesosObtenidos);
                GUICategorias categorias = new GUICategorias(nombreUsuario, quesosObtenidos);
                categorias.setVisible(true);
                this.dispose();
            }
        } catch (SQLException ex) {
            ex.getMessage();
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
        jButton1.setBackground(new JButton().getBackground());
        jButton2.setBackground(new JButton().getBackground());
        jButton3.setBackground(new JButton().getBackground());
        jButton4.setBackground(new JButton().getBackground());

        jLabel5.setText(pregunta);
        if (!respuestas.isEmpty()) {
            Collections.shuffle(respuestas);

            jButton1.setText(respuestas.get(0));
            jButton2.setText(respuestas.get(1));
            jButton3.setText(respuestas.get(2));
            jButton4.setText(respuestas.get(3));

            jButton1.putClientProperty("indiceCorrecto", respuestas.indexOf(respuestas.get(3)));
            jButton2.putClientProperty("indiceCorrecto", respuestas.indexOf(respuestas.get(3)));
            jButton3.putClientProperty("indiceCorrecto", respuestas.indexOf(respuestas.get(3)));
            jButton4.putClientProperty("indiceCorrecto", respuestas.indexOf(respuestas.get(3)));
        } else {
            jButton1.setText("");
            jButton2.setText("");
            jButton3.setText("");
            jButton4.setText("");
        }
        puntuacion.setText("Puntuación: " + contador);
    }

    private void comprobarRespuesta(final JButton clickedButton) {
        final String indiceRespuestaSeleccionada = clickedButton.getText();
        Color colorDeFondo = clickedButton.getBackground();
        jButton1.setBackground(Color.RED);
        jButton2.setBackground(Color.RED);
        jButton3.setBackground(Color.RED);
        jButton4.setBackground(Color.RED);
        if (!indiceRespuestaSeleccionada.equals(indiceRespuestaCorrecta)) {
            // Si la respuesta seleccionada es incorrecta, ponerla en rojo
            clickedButton.setBackground(Color.RED);
            // Buscar el botón que contiene la respuesta correcta y ponerlo en verde
            if (jButton1.getText().equals(indiceRespuestaCorrecta)) {
                jButton1.setBackground(Color.GREEN);
            } else if (jButton2.getText().equals(indiceRespuestaCorrecta)) {
                jButton2.setBackground(Color.GREEN);
            } else if (jButton3.getText().equals(indiceRespuestaCorrecta)) {
                jButton3.setBackground(Color.GREEN);
            } else if (jButton4.getText().equals(indiceRespuestaCorrecta)) {
                jButton4.setBackground(Color.GREEN);
            }
        } else {
            clickedButton.setBackground(Color.GREEN);
            contador++;
            puntuacion.setText("Puntuación: " + contador);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        puntuacion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanelInterface.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CATEGORIA");
        jPanelInterface.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 630, 60));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CATEGORIA");
        jPanelInterface.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 640, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("______");
        jPanelInterface.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        jLabel4.setText("______");
        jPanelInterface.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, -10, -1, 110));

        puntuacion.setText("Puntuación: ");
        jPanelInterface.add(puntuacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 90, 100));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Pregunta");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 90));

        jPanelInterface.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 590, 90));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(153, 255, 255));
        jButton1.setText("Respuesta1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 50));

        jPanelInterface.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 280, 50));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(153, 255, 255));
        jButton2.setText("Respuesta2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 50));

        jPanelInterface.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 260, 50));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(153, 255, 255));
        jButton3.setText("Respuesta3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 50));

        jPanelInterface.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 280, 50));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setBackground(new java.awt.Color(153, 255, 255));
        jButton4.setText("Respuesta4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 50));

        jPanelInterface.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 260, 50));

        jButton6.setText("Siguiente");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanelInterface.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 350, 90, 20));

        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanelInterface.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 90, 20));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JButton clickedButton = (JButton) evt.getSource();
        comprobarRespuesta(clickedButton);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JButton clickedButton = (JButton) evt.getSource();
        comprobarRespuesta(clickedButton);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JButton clickedButton = (JButton) evt.getSource();
        comprobarRespuesta(clickedButton);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JButton clickedButton = (JButton) evt.getSource();
        comprobarRespuesta(clickedButton);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        GUICategorias categorias = new GUICategorias(nombreUsuario,quesosObtenidos);
        categorias.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cargarPreguntaYRespuestas();
        mostrarPreguntaYRespuestas();
    }//GEN-LAST:event_jButton6ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelInterface;
    private javax.swing.JLabel puntuacion;
    // End of variables declaration//GEN-END:variables
}
