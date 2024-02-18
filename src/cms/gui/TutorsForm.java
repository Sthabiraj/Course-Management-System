/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cms.gui;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import cms.db.Database;
import cms.validation.Validation;

/**
 *
 * @author biraj
 */
public class TutorsForm extends javax.swing.JFrame {
    private int selectedIndex;
    private JTable table;

    /**
     * Creates new form TutorsForm
     */
    public TutorsForm() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * Represents a form for managing tutors in the Course Management System.
     * This form is used to display and edit tutor information.
     *
     * @param table         The JTable component used to display the tutor
     *                      information.
     * @param selectedIndex The index of the selected tutor in the table.
     */
    public TutorsForm(JTable table, int selectedIndex) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.selectedIndex = selectedIndex;
        this.table = table;
        formUsername.setText(table.getValueAt(selectedIndex, 1).toString());
        formEmail.setText(table.getValueAt(selectedIndex, 2).toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        formUsername = new javax.swing.JTextField();
        formEmail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout());

        jPanel1.setBackground(new java.awt.Color(123, 95, 241));

        jLabel10.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/tutors.png"))); // NOI18N
        jLabel10.setText("Tutors");

        formUsername.setBackground(new java.awt.Color(123, 95, 241));
        formUsername.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        formUsername.setForeground(new java.awt.Color(255, 255, 255));
        formUsername.setToolTipText("");
        formUsername.setBorder(javax.swing.BorderFactory.createTitledBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Username",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP,
                new java.awt.Font("Poppins Medium", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        formUsername.setCaretColor(new java.awt.Color(255, 255, 255));
        formUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        formUsername.setMinimumSize(new java.awt.Dimension(64, 45));
        formUsername.setPreferredSize(new java.awt.Dimension(64, 45));

        formEmail.setBackground(new java.awt.Color(123, 95, 241));
        formEmail.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        formEmail.setForeground(new java.awt.Color(255, 255, 255));
        formEmail.setToolTipText("");
        formEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Email",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP,
                new java.awt.Font("Poppins Medium", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        formEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        formEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        formEmail.setPreferredSize(new java.awt.Dimension(64, 45));
        formEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formEmailActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(250, 112, 112));
        jButton1.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/edit.png"))); // NOI18N
        jButton1.setText("Update");
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(formEmail, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(formUsername, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(0, 254, Short.MAX_VALUE))
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(formUsername, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(formEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE)));

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formEmailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_formEmailActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_formEmailActionPerformed

    /**
     * Event handler for the mouse click event on jButton1.
     * Retrieves the selected row index from the table and updates the corresponding
     * tutor's username and email.
     * Validates the tutor inputs and updates the database if the inputs are valid.
     * Disposes the current form, displays a success message, and updates the tutors
     * table in the dashboard.
     * Resets the form by clearing the username and email fields.
     */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseClicked
        int id = Integer.parseInt(table.getValueAt(selectedIndex, 0).toString());
        String username = formUsername.getText();
        String email = formEmail.getText();
        table.setValueAt(username, selectedIndex, 1);
        table.setValueAt(email, selectedIndex, 2);
        Validation val = new Validation();
        if (val.validateTutorInputs(id, username, email)) {
            Database db = new Database();
            db.updateInstructors(id, username, email);
            this.dispose();
            JOptionPane.showMessageDialog(null, "Tutor updated successfully");
            Dashboard.updateTutorsTable();
            // reset the form
            formUsername.setText("");
            formEmail.setText("");
        }
    }// GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TutorsForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TutorsForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TutorsForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TutorsForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TutorsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField formEmail;
    private javax.swing.JTextField formUsername;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
