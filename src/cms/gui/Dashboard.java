/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cms.gui;

import java.awt.Color;


/**
 *
 * @author biraj
 */
public class Dashboard extends javax.swing.JFrame {
    
    Color red = Color.decode("#FA7070");
    Color green = Color.decode("#557C55");

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        this.setLocationRelativeTo(null);
        dashboardBtn.setBackground(red);
        tabs.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        dashboardBtn = new javax.swing.JButton();
        coursesBtn = new javax.swing.JButton();
        tutorsBtn = new javax.swing.JButton();
        studentsBtn = new javax.swing.JButton();
        settingBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        tabs = new javax.swing.JTabbedPane();
        dashboardTab = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        coursesTab = new javax.swing.JPanel();
        tutorsTab = new javax.swing.JPanel();
        studentsTab = new javax.swing.JPanel();
        settingTab = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(166, 207, 152));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(220, 30));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(30, 300));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, java.awt.BorderLayout.LINE_END);

        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(30, 300));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, java.awt.BorderLayout.LINE_START);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(7, 0, 0, 10));

        dashboardBtn.setBackground(new java.awt.Color(85, 124, 85));
        dashboardBtn.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        dashboardBtn.setForeground(new java.awt.Color(255, 255, 255));
        dashboardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/dashboard.png"))); // NOI18N
        dashboardBtn.setText("Dashboard");
        dashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardBtn.setFocusPainted(false);
        dashboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardBtn.setIconTextGap(20);
        dashboardBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        dashboardBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardBtnMouseClicked(evt);
            }
        });
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });
        dashboardBtn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                backgroundChange(evt);
            }
        });
        jPanel2.add(dashboardBtn);

        coursesBtn.setBackground(new java.awt.Color(85, 124, 85));
        coursesBtn.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        coursesBtn.setForeground(new java.awt.Color(255, 255, 255));
        coursesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/courses.png"))); // NOI18N
        coursesBtn.setText("Courses");
        coursesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        coursesBtn.setFocusPainted(false);
        coursesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        coursesBtn.setIconTextGap(20);
        coursesBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        coursesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                coursesBtnMouseClicked(evt);
            }
        });
        coursesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursesBtnActionPerformed(evt);
            }
        });
        coursesBtn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                backgroundChange(evt);
            }
        });
        jPanel2.add(coursesBtn);

        tutorsBtn.setBackground(new java.awt.Color(85, 124, 85));
        tutorsBtn.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        tutorsBtn.setForeground(new java.awt.Color(255, 255, 255));
        tutorsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/tutors.png"))); // NOI18N
        tutorsBtn.setText("Tutors");
        tutorsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tutorsBtn.setFocusPainted(false);
        tutorsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tutorsBtn.setIconTextGap(20);
        tutorsBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tutorsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tutorsBtnMouseClicked(evt);
            }
        });
        tutorsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutorsBtnActionPerformed(evt);
            }
        });
        tutorsBtn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                backgroundChange(evt);
            }
        });
        jPanel2.add(tutorsBtn);

        studentsBtn.setBackground(new java.awt.Color(85, 124, 85));
        studentsBtn.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        studentsBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/students.png"))); // NOI18N
        studentsBtn.setText("Students");
        studentsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentsBtn.setFocusPainted(false);
        studentsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        studentsBtn.setIconTextGap(20);
        studentsBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        studentsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentsBtnMouseClicked(evt);
            }
        });
        studentsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentsBtnActionPerformed(evt);
            }
        });
        studentsBtn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                backgroundChange(evt);
            }
        });
        jPanel2.add(studentsBtn);

        settingBtn.setBackground(new java.awt.Color(85, 124, 85));
        settingBtn.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        settingBtn.setForeground(new java.awt.Color(255, 255, 255));
        settingBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/setting.png"))); // NOI18N
        settingBtn.setText("Setting");
        settingBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        settingBtn.setFocusPainted(false);
        settingBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        settingBtn.setIconTextGap(20);
        settingBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        settingBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingBtnMouseClicked(evt);
            }
        });
        settingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingBtnActionPerformed(evt);
            }
        });
        settingBtn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                backgroundChange(evt);
            }
        });
        jPanel2.add(settingBtn);

        logoutBtn.setBackground(new java.awt.Color(85, 124, 85));
        logoutBtn.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/logout.png"))); // NOI18N
        logoutBtn.setText("Logout");
        logoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        logoutBtn.setIconTextGap(20);
        logoutBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        logoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutBtnMouseClicked(evt);
            }
        });
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        logoutBtn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                backgroundChange(evt);
            }
        });
        jPanel2.add(logoutBtn);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 500));

        tabs.setBackground(new java.awt.Color(242, 255, 233));

        dashboardTab.setBackground(new java.awt.Color(242, 255, 233));

        jLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(85, 124, 85));
        jLabel2.setText("Dashboard");

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridLayout(1, 3, 20, 0));

        jPanel7.setBackground(new java.awt.Color(255, 188, 188));
        jPanel7.setForeground(new java.awt.Color(255, 204, 204));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Total Courses");

        jLabel3.setFont(new java.awt.Font("Poppins ExtraBold", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("5");

        jSeparator2.setForeground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(jSeparator2))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jPanel6.add(jPanel7);

        jPanel10.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Total Teachers");

        jLabel4.setFont(new java.awt.Font("Poppins ExtraBold", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("10");

        jSeparator3.setForeground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(jSeparator3))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel10);

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));

        jLabel6.setBackground(new java.awt.Color(153, 153, 153));
        jLabel6.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Total Students");

        jSeparator4.setForeground(new java.awt.Color(153, 153, 153));

        jLabel7.setFont(new java.awt.Font("Poppins ExtraBold", 0, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("200");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(jSeparator4))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel9);

        javax.swing.GroupLayout dashboardTabLayout = new javax.swing.GroupLayout(dashboardTab);
        dashboardTab.setLayout(dashboardTabLayout);
        dashboardTabLayout.setHorizontalGroup(
            dashboardTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashboardTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dashboardTabLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        dashboardTabLayout.setVerticalGroup(
            dashboardTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardTabLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(320, Short.MAX_VALUE))
        );

        tabs.addTab("tab1", dashboardTab);

        coursesTab.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout coursesTabLayout = new javax.swing.GroupLayout(coursesTab);
        coursesTab.setLayout(coursesTabLayout);
        coursesTabLayout.setHorizontalGroup(
            coursesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        coursesTabLayout.setVerticalGroup(
            coursesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        tabs.addTab("tab2", coursesTab);

        tutorsTab.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout tutorsTabLayout = new javax.swing.GroupLayout(tutorsTab);
        tutorsTab.setLayout(tutorsTabLayout);
        tutorsTabLayout.setHorizontalGroup(
            tutorsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        tutorsTabLayout.setVerticalGroup(
            tutorsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        tabs.addTab("tab3", tutorsTab);

        studentsTab.setBackground(new java.awt.Color(255, 204, 255));

        javax.swing.GroupLayout studentsTabLayout = new javax.swing.GroupLayout(studentsTab);
        studentsTab.setLayout(studentsTabLayout);
        studentsTabLayout.setHorizontalGroup(
            studentsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        studentsTabLayout.setVerticalGroup(
            studentsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        tabs.addTab("tab4", studentsTab);

        settingTab.setBackground(new java.awt.Color(242, 255, 233));

        javax.swing.GroupLayout settingTabLayout = new javax.swing.GroupLayout(settingTab);
        settingTab.setLayout(settingTabLayout);
        settingTabLayout.setHorizontalGroup(
            settingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        settingTabLayout.setVerticalGroup(
            settingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        tabs.addTab("tab5", settingTab);

        getContentPane().add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, -40, 680, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        dashboardBtn.setBackground(red);
        coursesBtn.setBackground(green);
        tutorsBtn.setBackground(green);
        studentsBtn.setBackground(green);
        settingBtn.setBackground(green);
        logoutBtn.setBackground(green);
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void dashboardBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardBtnMouseClicked
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_dashboardBtnMouseClicked

    private void coursesBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coursesBtnMouseClicked
        tabs.setSelectedIndex(1);
    }//GEN-LAST:event_coursesBtnMouseClicked

    private void tutorsBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tutorsBtnMouseClicked
        tabs.setSelectedIndex(2);
    }//GEN-LAST:event_tutorsBtnMouseClicked

    private void studentsBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentsBtnMouseClicked
        tabs.setSelectedIndex(3);
    }//GEN-LAST:event_studentsBtnMouseClicked

    private void settingBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingBtnMouseClicked
        tabs.setSelectedIndex(4);
    }//GEN-LAST:event_settingBtnMouseClicked

    private void logoutBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutBtnMouseClicked
        Login l = new Login();
        l.setVisible(true);
        dispose();
    }//GEN-LAST:event_logoutBtnMouseClicked

    private void backgroundChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_backgroundChange
        // TODO add your handling code here:
    }//GEN-LAST:event_backgroundChange

    private void coursesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursesBtnActionPerformed
        dashboardBtn.setBackground(green);
        coursesBtn.setBackground(red);
        tutorsBtn.setBackground(green);
        studentsBtn.setBackground(green);
        settingBtn.setBackground(green);
        logoutBtn.setBackground(green);
    }//GEN-LAST:event_coursesBtnActionPerformed

    private void tutorsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutorsBtnActionPerformed
        dashboardBtn.setBackground(green);
        coursesBtn.setBackground(green);
        tutorsBtn.setBackground(red);
        studentsBtn.setBackground(green);
        settingBtn.setBackground(green);
        logoutBtn.setBackground(green);
    }//GEN-LAST:event_tutorsBtnActionPerformed

    private void studentsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentsBtnActionPerformed
        dashboardBtn.setBackground(green);
        coursesBtn.setBackground(green);
        tutorsBtn.setBackground(green);
        studentsBtn.setBackground(red);
        settingBtn.setBackground(green);
        logoutBtn.setBackground(green);
    }//GEN-LAST:event_studentsBtnActionPerformed

    private void settingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingBtnActionPerformed
        dashboardBtn.setBackground(green);
        coursesBtn.setBackground(green);
        tutorsBtn.setBackground(green);
        studentsBtn.setBackground(green);
        settingBtn.setBackground(red);
        logoutBtn.setBackground(green);
    }//GEN-LAST:event_settingBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        dashboardBtn.setBackground(green);
        coursesBtn.setBackground(green);
        tutorsBtn.setBackground(green);
        studentsBtn.setBackground(green);
        settingBtn.setBackground(green);
        logoutBtn.setBackground(red);
    }//GEN-LAST:event_logoutBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton coursesBtn;
    private javax.swing.JPanel coursesTab;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JPanel dashboardTab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton settingBtn;
    private javax.swing.JPanel settingTab;
    private javax.swing.JButton studentsBtn;
    private javax.swing.JPanel studentsTab;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JButton tutorsBtn;
    private javax.swing.JPanel tutorsTab;
    // End of variables declaration//GEN-END:variables
}
