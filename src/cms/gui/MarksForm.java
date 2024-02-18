package cms.gui;

import javax.swing.JOptionPane;

import cms.db.Database;
import cms.validation.Validation;

public class MarksForm extends javax.swing.JFrame {

    /**
     * Creates new form MarksForm
     */
    public MarksForm() {
        initComponents();
    }

    /**
     * Represents a form for managing marks in the Course Management System.
     * This form is used to display and manage marks for a specific tutor.
     * 
     * @param tutorName the name of the tutor associated with the form
     */
    public MarksForm(String tutorName) {
        initComponents();
        this.setLocationRelativeTo(null);
        Database db = new Database();
        db.getModuleID(tutorName, moduleID);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        moduleID = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        studentID = new javax.swing.JComboBox<>();
        marksObtained = new javax.swing.JTextField();
        submitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(123, 95, 241));

        jLabel10.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/marks.png"))); // NOI18N
        jLabel10.setText("Marks");

        moduleID.setBackground(new java.awt.Color(123, 95, 241));
        moduleID.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        moduleID.setForeground(new java.awt.Color(255, 255, 255));
        moduleID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select any one" }));
        moduleID.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        moduleID.setPreferredSize(new java.awt.Dimension(72, 45));
        moduleID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduleIDActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Module ID");

        jLabel11.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Student ID");

        studentID.setBackground(new java.awt.Color(123, 95, 241));
        studentID.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        studentID.setForeground(new java.awt.Color(255, 255, 255));
        studentID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select any one" }));
        studentID.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentID.setPreferredSize(new java.awt.Dimension(72, 45));

        marksObtained.setBackground(new java.awt.Color(123, 95, 241));
        marksObtained.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        marksObtained.setForeground(new java.awt.Color(255, 255, 255));
        marksObtained.setBorder(javax.swing.BorderFactory.createTitledBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Marks Obtained",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP,
                new java.awt.Font("Poppins Medium", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        marksObtained.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marksObtainedActionPerformed(evt);
            }
        });

        submitBtn.setBackground(new java.awt.Color(250, 112, 112));
        submitBtn.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        submitBtn.setForeground(new java.awt.Color(255, 255, 255));
        submitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/add.png"))); // NOI18N
        submitBtn.setText("Submit");
        submitBtn.setBorder(null);
        submitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitBtnMouseClicked(evt);
            }
        });
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(0, 256, Short.MAX_VALUE))
                                        .addComponent(moduleID, javax.swing.GroupLayout.Alignment.TRAILING, 0,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(studentID, javax.swing.GroupLayout.Alignment.TRAILING, 0,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(marksObtained)
                                        .addComponent(submitBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(moduleID, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentID, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(marksObtained, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)));

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This method is called when the moduleID combo box selection is changed.
     * It retrieves the selected module ID from the combo box and performs the
     * necessary actions.
     * If a valid module is selected, it retrieves the associated course ID and
     * course name from the database.
     * Then, it populates the student ID combo box based on the course name.
     * If no module is selected, it displays an error message.
     * 
     * @param evt The ActionEvent that triggered the method call.
     */
    private void moduleIDActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_moduleIDActionPerformed
        // Retrieve the selected module ID from the combo box
        String moduleID = this.moduleID.getSelectedItem().toString();
        studentID.removeAllItems(); // Remove all the items from the student ID combo box
        studentID.addItem("Select any one"); // Add the default item to the student ID combo box

        // Check if a valid module is selected
        if (!moduleID.equals("Select any one")) {
            // Create a new instance of the Database class
            Database db = new Database();

            // Retrieve the course ID associated with the selected module
            int courseID = db.getCourseID(Integer.parseInt(moduleID));

            // Retrieve the course name based on the course ID
            String courseName = db.getCourseName(courseID);

            // Populate the student ID combo box based on the course name
            db.getStudentID(courseName, studentID);
        } else {
            // Display an error message if no module is selected
            JOptionPane.showMessageDialog(this, "Please select a module", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_moduleIDActionPerformed

    private void marksObtainedActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_marksObtainedActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_marksObtainedActionPerformed

    /**
     * Calculates the grade based on the marks obtained.
     * 
     * @param marksObtained the marks obtained by the student
     * @return the grade corresponding to the marks obtained
     */
    public String calculateGrade(Float marksObtained) {
        String grade = "";
        if (marksObtained >= 70) {
            grade = "A";
        } else if (marksObtained >= 60) {
            grade = "B";
        } else if (marksObtained >= 50) {
            grade = "C";
        } else if (marksObtained >= 40) {
            grade = "D";
        } else {
            grade = "Fail";
        }
        return grade;
    }

    /**
     * Calculates the eligibility for the next semester based on the marks obtained.
     * 
     * @param marksObtained the marks obtained by the student
     * @return the eligibility status for the next semester
     */
    public String calculateEligibility(Float marksObtained) {
        String eligibility = "";
        if (marksObtained >= 40) {
            eligibility = "Eligible for next semester";
        } else {
            eligibility = "Not eligible for next semester";
        }
        return eligibility;
    }

    /**
     * Handles the mouse click event for the submit button.
     * Retrieves data from the form, validates the inputs, calculates grade and
     * eligibility,
     * adds marks to the database, closes the form, and displays a success message.
     * 
     * @param evt The mouse event triggered by the submit button click
     */
    private void submitBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_submitBtnMouseClicked
        // data from the form
        String moduleID = this.moduleID.getSelectedItem().toString();
        String studentID = this.studentID.getSelectedItem().toString();
        Float marksObtained = Float.parseFloat(this.marksObtained.getText());
        // Validation
        Validation val = new Validation();
        if (val.validateMarksInputs(moduleID, studentID, marksObtained)) {
            String grade = calculateGrade(marksObtained); // calculate grade
            String eligibility = calculateEligibility(marksObtained); // calculate eligibility
            // add marks to the database
            Database db = new Database();
            db.addMarks(Integer.parseInt(moduleID), Integer.parseInt(studentID), marksObtained, grade, eligibility);
            dispose(); // close the form
            // display success message
            JOptionPane.showMessageDialog(this, "Marks added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }// GEN-LAST:event_submitBtnMouseClicked

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_submitBtnActionPerformed

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
            java.util.logging.Logger.getLogger(MarksForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MarksForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MarksForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MarksForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MarksForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField marksObtained;
    private javax.swing.JComboBox<String> moduleID;
    private javax.swing.JComboBox<String> studentID;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}