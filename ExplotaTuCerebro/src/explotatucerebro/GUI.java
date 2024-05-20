package explotatucerebro;

import java.sql.SQLException;

/**
 * @see <a href="https://github.com/Esguvi/ExplotaTuCerebro">GitHub</a> 
 * @author Grupo 1 - Miriam Daimiel Acedo | Víctor Escaso Gutiérrez | Guillermo Lucio García | Alejandro Muñoz Pardo | Carlos Isaac Muriel Cuevas.
*/

public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() throws SQLException{
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInicio = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        lblSombra = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        btnSesion = new javax.swing.JButton();
        btnRegistro = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JuegoDePreguntas");
        setResizable(false);

        panelInicio.setMinimumSize(new java.awt.Dimension(621, 293));
        panelInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTitulo.setBackground(new java.awt.Color(255, 102, 0));
        panelTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));

        lblSombra.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lblSombra.setForeground(new java.awt.Color(255, 255, 255));
        lblSombra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSombra.setText("¡EXPLOTA TU CEREBRO!");

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("¡EXPLOTA TU CEREBRO!");

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTituloLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSombra, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addGroup(panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTituloLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblSombra, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTitulo))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelInicio.add(panelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 610, 80));

        btnSesion.setText("INICIAR SESIÓN");
        btnSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSesionActionPerformed(evt);
            }
        });
        panelInicio.add(btnSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 221, 94));

        btnRegistro.setText("REGISTRARSE");
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });
        panelInicio.add(btnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 221, 94));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/explotatucerebro/img/fondo.jpg"))); // NOI18N
        panelInicio.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSesionActionPerformed
        // TODO add your handling code here:
        GUISesion Sesion;
        try {
            Sesion = new GUISesion();
            Sesion.setVisible(true);
        }
        catch (SQLException ex) {
            ex.getMessage();
        }
        dispose();
    }//GEN-LAST:event_btnSesionActionPerformed

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        // TODO add your handling code here:
        GUIRegistro Registro;
        try {
            Registro = new GUIRegistro();
            Registro.setVisible(true);
        }
        catch (SQLException ex) {
            ex.getMessage();
        }
        dispose();
    }//GEN-LAST:event_btnRegistroActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GUI().setVisible(true);
                }
                catch (SQLException ex) {
                    ex.getMessage();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistro;
    private javax.swing.JButton btnSesion;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblSombra;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelTitulo;
    // End of variables declaration//GEN-END:variables
}
