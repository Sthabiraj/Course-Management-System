/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cms.gui;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cms.db.Database;
import cms.validation.Validation;

/**
 *
 * @author biraj
 */
public class StudentsForm extends javax.swing.JFrame {
    private int selectedIndex;
    private JTable table;

    /**
     * Creates new form StudentsForm
     */

    public StudentsForm() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * Represents a form for managing student information.
     * This form is used to edit the details of a selected student.
     *
     * @param table         The JTable component that displays the student
     *                      information.
     * @param selectedIndex The index of the selected student in the table.
     */
    public StudentsForm(JTable table, int selectedIndex) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.table = table;
        this.selectedIndex = selectedIndex;
        Database db = new Database();
        db.fetchCourseNamesFromDatabase(courseName);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        studentName.setText(model.getValueAt(selectedIndex, 1).toString());
        studentEmail.setText(model.getValueAt(selectedIndex, 2).toString());
        courseName.setSelectedItem(model.getValueAt(selectedIndex, 3).toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        studentName = new javax.swing.JTextField();
        studentEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        courseName = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(123, 95, 241));

        jLabel10.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/students.png"))); // NOI18N
        jLabel10.setText("Student");

        studentName.setBackground(new java.awt.Color(123, 95, 241));
        studentName.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        studentName.setForeground(new java.awt.Color(255, 255, 255));
        studentName.setBorder(javax.swing.BorderFactory.createTitledBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Student Name",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP,
                new java.awt.Font("Poppins Medium", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        studentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentNameActionPerformed(evt);
            }
        });

        studentEmail.setBackground(new java.awt.Color(123, 95, 241));
        studentEmail.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        studentEmail.setForeground(new java.awt.Color(255, 255, 255));
        studentEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Student Email",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP,
                new java.awt.Font("Poppins Medium", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        studentEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentEmailActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Pick a Course");

        courseName.setBackground(new java.awt.Color(123, 95, 241));
        courseName.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        courseName.setForeground(new java.awt.Color(255, 255, 255));
        courseName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select any one" }));
        courseName.setPreferredSize(new java.awt.Dimension(72, 45));

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
                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(courseName, javax.swing.GroupLayout.Alignment.TRAILING, 0,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(studentName)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 364,
                                                Short.MAX_VALUE)
                                        .addComponent(studentEmail)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(studentName, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(studentEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(courseName, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE)));

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void studentNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_studentNameActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_studentNameActionPerformed

    private void studentEmailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_studentEmailActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_studentEmailActionPerformed

    /**
     * Event handler for the mouse click event on jButton1.
     * Retrieves the selected student's ID, name, email, and course from the form
     * fields.
     * Validates the student inputs using the Validation class.
     * If the inputs are valid, updates the student's information in the database
     * using the Database class.
     * Displays a success message using JOptionPane.
     * Disposes the current form.
     * Updates the students table and count in the Dashboard class.
     * Resets the form fields.
     */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseClicked
        int id = Integer.parseInt(table.getValueAt(selectedIndex, 0).toString());
        String name = studentName.getText();
        String email = studentEmail.getText();
        String course = courseName.getSelectedItem().toString();
        Validation val = new Validation();
        if (val.validateStudentInputs(id, name, email)) {
            Database db = new Database();
            db.updateStudents(id, name, email, course);
            JOptionPane.showMessageDialog(this, "Student updated successfully");
            dispose();
            Dashboard.updateStudentsTable();
            Dashboard.updateCount();
            // reset the form
            studentName.setText("");
            studentEmail.setText("");
            courseName.setSelectedIndex(0);
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
            java.util.logging.Logger.getLogger(StudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> courseName;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField studentEmail;
    private javax.swing.JTextField studentName;
    // End of variables declaration//GEN-END:variables
}
