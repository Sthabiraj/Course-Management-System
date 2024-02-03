/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cms.gui;

import cms.db.Database;
import cms.error.PopupMessage;
import cms.validation.Validation;

/**
 *
 * @author biraj
 */
public class Login extends javax.swing.JFrame {
    
    PopupMessage pop = new PopupMessage();
    Validation val = new Validation();

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        formMode = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        formPassword = new javax.swing.JPasswordField();
        formEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Page");
        setPreferredSize(new java.awt.Dimension(800, 390));
        getContentPane().setLayout(new java.awt.GridLayout());

        jPanel1.setBackground(new java.awt.Color(123, 95, 241));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 0));

        jButton1.setBackground(new java.awt.Color(250, 112, 112));
        jButton1.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.setText("Login");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        formMode.setBackground(new java.awt.Color(123, 95, 241));
        formMode.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        formMode.setForeground(new java.awt.Color(255, 255, 255));
        formMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select any one", "Student", "Instructor", "Admin" }));
        formMode.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        formMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formModeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mode");

        formPassword.setBackground(new java.awt.Color(123, 95, 241));
        formPassword.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        formPassword.setForeground(new java.awt.Color(255, 255, 255));
        formPassword.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Poppins Medium", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        formPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        formPassword.setPreferredSize(new java.awt.Dimension(64, 45));

        formEmail.setBackground(new java.awt.Color(123, 95, 241));
        formEmail.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        formEmail.setForeground(new java.awt.Color(255, 255, 255));
        formEmail.setToolTipText("");
        formEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Email", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Poppins Medium", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        formEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        formEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        formEmail.setPreferredSize(new java.awt.Dimension(64, 45));
        formEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formEmailActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(250, 112, 112));
        jLabel4.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Login");

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Don't have an account?");

        jLabel11.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Sign Up");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(formEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(formPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(formMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 33, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addGap(24, 24, 24)
                .addComponent(formEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formMode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel2.setLayout(new java.awt.GridLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/illustrations/Analytics.png"))); // NOI18N
        jPanel2.add(jLabel1);

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        String email = formEmail.getText();
        String password = formPassword.getText();
        String mode = formMode.getSelectedItem().toString();
        Database db = new Database();
        if(val.validateLogin(email, password, mode)){
            if(db.login(email, password, mode)){
                pop.showErrorMessage("Account Logedin Sucessfully", "");
                Dashboard dash = new Dashboard();
                dash.setVisible(true);
                dispose();
            }
            else{
                pop.showErrorMessage("Invalid login details!!");
            }
            formEmail.setText("");
            formPassword.setText("");
            formMode.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void formEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_formEmailActionPerformed

    private void formModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formModeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_formModeActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        Signup signup = new Signup();
        signup.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton3ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton3MouseClicked
        Signup signup = new Signup();
        signup.setVisible(true);
        dispose();
    }// GEN-LAST:event_jButton3MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField formEmail;
    private javax.swing.JComboBox<String> formMode;
    private javax.swing.JPasswordField formPassword;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
