package cms.gui;

import cms.courses.Courses;
import cms.courses.Modules;
import cms.db.Database;
import cms.users.Instructors;
import cms.users.Students;
import cms.validation.Validation;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * The Dashboard class represents the main GUI window of the Course Management
 * System.
 * It extends the javax.swing.JFrame class and provides methods for updating
 * various tables
 * and performing search and filter operations.
 */
public class Dashboard extends javax.swing.JFrame {
    private int id;
    private static String username;
    private String email;
    private String password;
    private static String mode;
    private static String course;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        Dashboard.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMode(String mode) {
        Dashboard.mode = mode;
    }

    public void setCourse(String course) {
        Dashboard.course = course;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMode() {
        return mode;
    }

    public String getCourse() {
        return course;
    }

    /**
     * Updates the count of courses, instructors, and students in the dashboard.
     * Retrieves the length of the corresponding tables from the database and sets
     * the count in the UI.
     */
    public static void updateCount() {
        Database db = new Database();
        coursesCount.setText(Integer.toString(db.getLength("courses")));
        teachersCount.setText(Integer.toString(db.getLength("instructor")));
        studentsCount.setText(Integer.toString(db.getLength("student")));
    }

    /**
     * Applies a search filter to a JTable based on the text entered in a
     * JTextField.
     * The filter is applied to the DefaultTableModel of the JTable, and only rows
     * that
     * match the filter are displayed.
     *
     * @param tableName     The JTable to apply the search filter to.
     * @param textFieldName The JTextField containing the text to use as the filter.
     */
    public void searchFilter(JTable tableName, JTextField textFieldName) {
        DefaultTableModel model = (DefaultTableModel) tableName.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        tableName.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(textFieldName.getText().trim()));
    }

    /**
     * Filters the rows of a JTable based on a given filter string.
     * 
     * @param tableName The JTable to be filtered.
     * @param filter    The filter string to be applied.
     */
    public void tableFilter(JTable tableName, String filter) {
        DefaultTableModel model = (DefaultTableModel) tableName.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        tableName.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(filter));
    }

    /**
     * Updates the courses table in the dashboard GUI.
     * Retrieves the courses from the database and populates the table with the
     * course information.
     */
    public static void updateCoursesTable() {
        // Fetch courses from the database
        Database db = new Database();
        coursesTable.repaint();

        List<Courses> courses = null;
        if (mode.equals("Admin")) {
            courses = db.fetchCoursesFromDatabase();
        } else if (mode.equals("Instructor")) {
            courses = db.fetchCoursesFromDatabase(db.getCourseId(username));
        } else if (mode.equals("Student")) {
            courses = db.fetchCoursesFromDatabase(course);
        }

        // Clear the existing table data
        DefaultTableModel tblModel = (DefaultTableModel) coursesTable.getModel();
        tblModel.setRowCount(0);

        // Populate the table with course information
        if (courses != null) {
            for (Courses course : courses) {
                String data[] = { String.valueOf(course.getCourseID()),
                        course.getCourseName(),
                        String.valueOf(course.getSeats()),
                        String.valueOf(course.getDuration()) };
                tblModel.addRow(data);
            }
        }
    }

    /**
     * Updates the modules table in the dashboard GUI.
     * Fetches the modules from the database based on the user's mode (Admin,
     * Instructor, or Student),
     * and populates the modules table with the retrieved data.
     */
    public static void updateModulesTable() {
        Database db = new Database();
        modulesTable.repaint();
        List<Modules> modules = null;

        if (mode.equals("Admin")) {
            modules = db.fetchModulesFromDatabase();
        } else if (mode.equals("Instructor")) {
            modules = db.fetchModulesFromDatabase(username);
        } else if (mode.equals("Student")) {
            modules = db.fetchModulesFromDatabase(db.getCourseID(course));
        }

        DefaultTableModel tblModel = (DefaultTableModel) modulesTable.getModel();
        tblModel.setRowCount(0);

        if (modules != null) {
            for (Modules module : modules) {
                String data[] = { String.valueOf(module.getModuleID()), module.getModuleName(),
                        String.valueOf(module.getCourseID()), module.getTutorName() };
                tblModel.addRow(data);
            }
        }

    }

    /**
     * Updates the tutors table with the latest data from the database.
     * If the mode is "Admin", fetches all instructors from the database.
     * If the mode is "Student", fetches instructors associated with the current
     * course.
     * Populates the tutors table with the fetched data.
     */
    public static void updateTutorsTable() {
        Database db = new Database();
        tutorsTable.repaint();

        List<Instructors> tutors = null;
        if (mode.equals("Admin")) {
            tutors = db.fetchInstructorsFromDatabase();
        } else if (mode.equals("Student")) {
            tutors = db.fetchInstructorsFromDatabase(db.getCourseID(course));
        }

        DefaultTableModel tblModel = (DefaultTableModel) tutorsTable.getModel();
        tblModel.setRowCount(0);

        if (tutors != null) {
            for (Instructors tutor : tutors) {
                String data[] = { String.valueOf(tutor.getInstructorID()), tutor.getUsername(),
                        tutor.getEmail() };
                tblModel.addRow(data);
            }
        }
    }

    /**
     * Updates the students table in the dashboard.
     * Retrieves the student data from the database based on the user's mode (Admin
     * or Instructor),
     * and populates the table with the fetched data.
     */
    public static void updateStudentsTable() {
        Database db = new Database();
        studentsTable.repaint();

        List<Students> students = null;
        if (mode.equals("Admin")) {
            students = db.fetchStudentsFromDatabase();
        } else if (mode.equals("Instructor")) {
            students = db.fetchStudentsFromDatabase(db.getCourseName(db.getCourseId(username)));
        }

        DefaultTableModel tblModel = (DefaultTableModel) studentsTable.getModel();
        tblModel.setRowCount(0);

        if (students != null) {
            for (Students student : students) {
                String data[] = { String.valueOf(student.getStudentID()), student.getUsername(),
                        student.getEmail(), student.getCourse() };
                tblModel.addRow(data);
            }
        }
    }

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        this.setLocationRelativeTo(null);
        dashboardBtn.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 18));
        tabs.setSelectedIndex(0);
        Database db = new Database();
        db.getActivities(activitiesTable);
        updateCount();
        updateCoursesTable();
        updateModulesTable();
        updateTutorsTable();
        updateStudentsTable();
    }

    /**
     * Represents the dashboard of the Course Management System.
     * This class is responsible for initializing the dashboard components,
     * setting the location of the dashboard window, and performing
     * specific actions based on the user's mode (Instructor or Student).
     * It also updates the activities, courses, modules, tutors, and students
     * tables.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @param mode     the mode of the user (Instructor or Student)
     */
    public Dashboard(String email, String password, String mode) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.email = email;
        this.password = password;
        Dashboard.mode = mode;
        dashboardBtn.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 18));
        tabs.setSelectedIndex(0);
        Database db = new Database();
        db.getActivities(activitiesTable);
        setId(db.getID(getEmail(), getMode()));
        setUsername(db.getUsername(getEmail(), getMode()));
        profileUsername.setText(getUsername());
        profileEmail.setText(getEmail());

        if (mode.equals("Instructor")) {
            // dashboard section
            jPanel2.remove(tutorsBtn);
            jPanel2.revalidate();
            jPanel2.repaint();
            tableFilter(activitiesTable, mode + ": " + getEmail()); // filter activities table
            // courses section
            coursesTab.remove(jButton1);
            coursesTab.remove(jButton2);
            coursesTab.remove(jButton3);
            coursesTab.revalidate();
            coursesTab.repaint();
            // modules section
            modulesTab.remove(jButton13);
            modulesTab.remove(jButton14);
            modulesTab.remove(jButton15);
            modulesTab.revalidate();
            modulesTab.repaint();
            // students section
            studentsTab.remove(jButton17);
            studentsTab.remove(jButton18);
            studentsTab.revalidate();
            studentsTab.repaint();
            jButton16.setText("Marks");
            jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/add.png")));
        } else if (mode.equals("Student")) {
            setCourse(db.getCourse(getEmail(), getMode()));
            // dashboard section
            jPanel2.remove(studentsBtn);
            jPanel2.revalidate();
            jPanel2.repaint();
            tableFilter(activitiesTable, mode + ": " + getEmail()); // filter activities table
            // courses section
            coursesTab.remove(jButton1);
            coursesTab.remove(jButton2);
            coursesTab.remove(jButton3);
            coursesTab.revalidate();
            coursesTab.repaint();
            // modules section
            modulesTab.remove(jButton13);
            modulesTab.remove(jButton14);
            modulesTab.revalidate();
            modulesTab.repaint();
            jButton15.setText("Marks");
            jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/marks.png")));
            // tutors section
            tutorsTab.remove(jButton20);
            tutorsTab.remove(jButton21);
            tutorsTab.revalidate();
            tutorsTab.repaint();
        }

        updateCount();
        updateCoursesTable();
        updateModulesTable();
        updateTutorsTable();
        updateStudentsTable();
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        logoutBtn = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        dashboardBtn = new javax.swing.JLabel();
        coursesBtn = new javax.swing.JLabel();
        modulesBtn = new javax.swing.JLabel();
        tutorsBtn = new javax.swing.JLabel();
        studentsBtn = new javax.swing.JLabel();
        settingsBtn = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        dashboardTab = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        coursesCount = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        teachersCount = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        studentsCount = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        activitiesTable = new javax.swing.JTable();
        coursesTab = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        coursesTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        modulesTab = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        modulesTable = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        tutorsTab = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        tutorsTable = new javax.swing.JTable();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        studentsTab = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        studentsTable = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        settingTab = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        profileUsername = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        profileEmail = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        oldPassword = new javax.swing.JPasswordField();
        newPassword = new javax.swing.JPasswordField();
        jSeparator9 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(123, 95, 241));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(220, 140));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/illustrations/logo.png"))); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 120));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                .addContainerGap()));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                .addContainerGap()));

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(220, 100));

        logoutBtn.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/logout.png"))); // NOI18N
        logoutBtn.setText("Logout");
        logoutBtn.setToolTipText("");
        logoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBtn.setIconTextGap(7);
        logoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutBtnMouseClicked(evt);
            }

            private void logoutBtnMouseClicked(MouseEvent evt) {
                // when the logout button is clicked a confirmation dialog is shown
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Warning",
                        JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    // if the user confirms the logout, the login page is shown
                    dispose();
                    new Login().setVisible(true);
                }
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 50, Short.MAX_VALUE)
                                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE));

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(50, 300));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE));
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));

        jPanel1.add(jPanel8, java.awt.BorderLayout.LINE_START);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(6, 0, 0, 8));

        dashboardBtn.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        dashboardBtn.setForeground(new java.awt.Color(255, 255, 255));
        dashboardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/dashboard.png"))); // NOI18N
        dashboardBtn.setText("Dashboard");
        dashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        dashboardBtn.setIconTextGap(9);
        dashboardBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardBtnMouseClicked(evt);
            }
        });
        jPanel2.add(dashboardBtn);

        coursesBtn.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        coursesBtn.setForeground(new java.awt.Color(255, 255, 255));
        coursesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/courses.png"))); // NOI18N
        coursesBtn.setText("Courses");
        coursesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        coursesBtn.setIconTextGap(7);
        coursesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                coursesBtnMouseClicked(evt);
            }
        });
        jPanel2.add(coursesBtn);

        modulesBtn.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        modulesBtn.setForeground(new java.awt.Color(255, 255, 255));
        modulesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/modules.png"))); // NOI18N
        modulesBtn.setText("Modules");
        modulesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modulesBtn.setIconTextGap(7);
        modulesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modulesBtnMouseClicked(evt);
            }
        });
        jPanel2.add(modulesBtn);

        tutorsBtn.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        tutorsBtn.setForeground(new java.awt.Color(255, 255, 255));
        tutorsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/tutors.png"))); // NOI18N
        tutorsBtn.setText("Tutors");
        tutorsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tutorsBtn.setIconTextGap(7);
        tutorsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tutorsBtnMouseClicked(evt);
            }
        });
        jPanel2.add(tutorsBtn);

        studentsBtn.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        studentsBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/students.png"))); // NOI18N
        studentsBtn.setText("Students");
        studentsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentsBtn.setIconTextGap(7);
        studentsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentsBtnMouseClicked(evt);
            }
        });
        jPanel2.add(studentsBtn);

        settingsBtn.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        settingsBtn.setForeground(new java.awt.Color(255, 255, 255));
        settingsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/setting.png"))); // NOI18N
        settingsBtn.setText("Settings");
        settingsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        settingsBtn.setIconTextGap(7);
        settingsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsBtnMouseClicked(evt);
            }
        });
        jPanel2.add(settingsBtn);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 500));

        tabs.setBackground(new java.awt.Color(255, 255, 255));

        dashboardTab.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(123, 95, 241));
        jLabel2.setText("Dashboard");

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridLayout(1, 3, 20, 0));

        jPanel7.setBackground(new java.awt.Color(123, 95, 241));
        jPanel7.setForeground(new java.awt.Color(255, 204, 204));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Total Courses");

        coursesCount.setFont(new java.awt.Font("Poppins ExtraBold", 0, 48)); // NOI18N
        coursesCount.setForeground(new java.awt.Color(255, 255, 255));
        coursesCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coursesCount.setText("5");

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(coursesCount, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 189,
                                                Short.MAX_VALUE)
                                        .addComponent(jSeparator2))
                                .addContainerGap()));
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(coursesCount, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)));

        jPanel6.add(jPanel7);

        jPanel10.setBackground(new java.awt.Color(123, 95, 241));

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Total Teachers");

        teachersCount.setFont(new java.awt.Font("Poppins ExtraBold", 0, 48)); // NOI18N
        teachersCount.setForeground(new java.awt.Color(255, 255, 255));
        teachersCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teachersCount.setText("10");

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(teachersCount, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 189,
                                                Short.MAX_VALUE)
                                        .addComponent(jSeparator3))
                                .addContainerGap()));
        jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(teachersCount, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(8, Short.MAX_VALUE)));

        jPanel6.add(jPanel10);

        jPanel9.setBackground(new java.awt.Color(123, 95, 241));

        jLabel6.setBackground(new java.awt.Color(153, 153, 153));
        jLabel6.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Total Students");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        studentsCount.setFont(new java.awt.Font("Poppins ExtraBold", 0, 48)); // NOI18N
        studentsCount.setForeground(new java.awt.Color(255, 255, 255));
        studentsCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentsCount.setText("200");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(studentsCount, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 189,
                                                Short.MAX_VALUE)
                                        .addComponent(jSeparator4))
                                .addContainerGap()));
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(8, Short.MAX_VALUE)));

        jPanel6.add(jPanel9);

        jLabel8.setBackground(new java.awt.Color(123, 95, 241));
        jLabel8.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(123, 95, 241));
        jLabel8.setText("Activities History");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(123, 95, 241), 1, true));
        jScrollPane1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jScrollPane1.setOpaque(false);

        activitiesTable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        activitiesTable.setForeground(new java.awt.Color(51, 51, 51));
        activitiesTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "ID", "Activity Name"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        activitiesTable.setRowHeight(25);
        activitiesTable.setSelectionBackground(new java.awt.Color(123, 95, 241));
        activitiesTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        activitiesTable.setShowGrid(false);
        activitiesTable.getTableHeader().setResizingAllowed(false);
        activitiesTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(activitiesTable);
        if (activitiesTable.getColumnModel().getColumnCount() > 0) {
            activitiesTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            activitiesTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        }

        javax.swing.GroupLayout dashboardTabLayout = new javax.swing.GroupLayout(dashboardTab);
        dashboardTab.setLayout(dashboardTabLayout);
        dashboardTabLayout.setHorizontalGroup(
                dashboardTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dashboardTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(dashboardTabLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                                        .addComponent(jSeparator1)
                                        .addGroup(dashboardTabLayout.createSequentialGroup()
                                                .addGroup(dashboardTabLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel8))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jScrollPane1))
                                .addGap(18, 18, 18)));
        dashboardTabLayout.setVerticalGroup(
                dashboardTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dashboardTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE)));

        tabs.addTab("tab1", dashboardTab);

        coursesTab.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(123, 95, 241));
        jLabel9.setText("Courses");

        jButton1.setBackground(new java.awt.Color(123, 95, 241));
        jButton1.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/delete.png"))); // NOI18N
        jButton1.setText("Delete");
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

        jButton2.setBackground(new java.awt.Color(123, 95, 241));
        jButton2.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/add.png"))); // NOI18N
        jButton2.setText("Add");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(123, 95, 241));
        jButton3.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/edit.png"))); // NOI18N
        jButton3.setText("Edit");
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(242, 255, 233));
        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(123, 95, 241), 1, true));

        coursesTable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        coursesTable.setForeground(new java.awt.Color(51, 51, 51));
        coursesTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "ID", "Course Name", "Seats", "No. of Years"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        coursesTable.setRowHeight(25);
        coursesTable.setSelectionBackground(new java.awt.Color(123, 95, 241));
        coursesTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(coursesTable);
        if (coursesTable.getColumnModel().getColumnCount() > 0) {
            coursesTable.getColumnModel().getColumn(0).setResizable(false);
            coursesTable.getColumnModel().getColumn(0).setPreferredWidth(3);
            coursesTable.getColumnModel().getColumn(1).setResizable(false);
            coursesTable.getColumnModel().getColumn(1).setPreferredWidth(250);
            coursesTable.getColumnModel().getColumn(2).setResizable(false);
            coursesTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            coursesTable.getColumnModel().getColumn(3).setResizable(false);
            coursesTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        jPanel5.setBackground(new java.awt.Color(123, 95, 241));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(123, 95, 241), 1, true));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/search.png"))); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(50, 22));
        jPanel5.add(jLabel4);

        jTextField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField1.setBorder(null);
        jTextField1.setPreferredSize(new java.awt.Dimension(30, 35));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel5.add(jTextField1);

        javax.swing.GroupLayout coursesTabLayout = new javax.swing.GroupLayout(coursesTab);
        coursesTab.setLayout(coursesTabLayout);
        coursesTabLayout.setHorizontalGroup(
                coursesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(coursesTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(
                                        coursesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator5)
                                                .addGroup(coursesTabLayout.createSequentialGroup()
                                                        .addComponent(jLabel9)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(coursesTabLayout.createSequentialGroup()
                                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                266, Short.MAX_VALUE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)));
        coursesTabLayout.setVerticalGroup(
                coursesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(coursesTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(coursesTabLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(coursesTabLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 35,
                                                Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                                .addGap(18, 18, 18)));

        tabs.addTab("tab2", coursesTab);

        modulesTab.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(123, 95, 241));
        jLabel10.setText("Modules");

        jScrollPane3.setBackground(new java.awt.Color(123, 95, 241));
        jScrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(123, 95, 241), 1, true));
        jScrollPane3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane3KeyReleased(evt);
            }
        });

        modulesTable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        modulesTable.setForeground(new java.awt.Color(51, 51, 51));
        modulesTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "ID", "Module Name", "Course ID", "Assigned Tutor"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        modulesTable.setRowHeight(25);
        modulesTable.setSelectionBackground(new java.awt.Color(123, 95, 241));
        modulesTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(modulesTable);
        if (modulesTable.getColumnModel().getColumnCount() > 0) {
            modulesTable.getColumnModel().getColumn(0).setResizable(false);
            modulesTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            modulesTable.getColumnModel().getColumn(1).setResizable(false);
            modulesTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            modulesTable.getColumnModel().getColumn(2).setResizable(false);
            modulesTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            modulesTable.getColumnModel().getColumn(3).setResizable(false);
            modulesTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        jButton13.setBackground(new java.awt.Color(123, 95, 241));
        jButton13.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/add.png"))); // NOI18N
        jButton13.setText("Add");
        jButton13.setBorder(null);
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(123, 95, 241));
        jButton14.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/edit.png"))); // NOI18N
        jButton14.setText("Edit");
        jButton14.setBorder(null);
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
        });
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(123, 95, 241));
        jButton15.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/delete.png"))); // NOI18N
        jButton15.setText("Delete");
        jButton15.setBorder(null);
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(123, 95, 241));
        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(123, 95, 241), 1, true));
        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.LINE_AXIS));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/search.png"))); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(50, 22));
        jPanel11.add(jLabel7);

        jTextField6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField6.setBorder(null);
        jTextField6.setPreferredSize(new java.awt.Dimension(30, 35));
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });
        jPanel11.add(jTextField6);

        javax.swing.GroupLayout modulesTabLayout = new javax.swing.GroupLayout(modulesTab);
        modulesTab.setLayout(modulesTabLayout);
        modulesTabLayout.setHorizontalGroup(
                modulesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(modulesTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(
                                        modulesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator6)
                                                .addGroup(modulesTabLayout.createSequentialGroup()
                                                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                266, Short.MAX_VALUE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(modulesTabLayout.createSequentialGroup()
                                                        .addComponent(jLabel10)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 644,
                                                        Short.MAX_VALUE))
                                .addGap(18, 18, 18)));
        modulesTabLayout.setVerticalGroup(
                modulesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(modulesTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(
                                        modulesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(modulesTabLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 35,
                                                        Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                                .addGap(18, 18, 18)));

        tabs.addTab("tab3", modulesTab);

        tutorsTab.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(123, 95, 241));
        jLabel21.setText("Tutors");

        jScrollPane5.setBackground(new java.awt.Color(123, 95, 241));
        jScrollPane5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(123, 95, 241), 1, true));
        jScrollPane5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane5KeyReleased(evt);
            }
        });

        tutorsTable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        tutorsTable.setForeground(new java.awt.Color(51, 51, 51));
        tutorsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null }
                },
                new String[] {
                        "ID", "Tutor Name", "Email"
                }) {
            Class[] types = new Class[] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tutorsTable.setRowHeight(25);
        tutorsTable.setSelectionBackground(new java.awt.Color(123, 95, 241));
        tutorsTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setViewportView(tutorsTable);
        if (tutorsTable.getColumnModel().getColumnCount() > 0) {
            tutorsTable.getColumnModel().getColumn(0).setResizable(false);
            tutorsTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            tutorsTable.getColumnModel().getColumn(1).setResizable(false);
            tutorsTable.getColumnModel().getColumn(1).setPreferredWidth(180);
            tutorsTable.getColumnModel().getColumn(2).setResizable(false);
            tutorsTable.getColumnModel().getColumn(2).setPreferredWidth(180);
        }

        jButton20.setBackground(new java.awt.Color(123, 95, 241));
        jButton20.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/edit.png"))); // NOI18N
        jButton20.setText("Edit");
        jButton20.setBorder(null);
        jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton20MouseClicked(evt);
            }
        });
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(123, 95, 241));
        jButton21.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/delete.png"))); // NOI18N
        jButton21.setText("Delete");
        jButton21.setBorder(null);
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton21MouseClicked(evt);
            }
        });
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(123, 95, 241));
        jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(123, 95, 241), 1, true));
        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/search.png"))); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(50, 22));
        jPanel13.add(jLabel22);

        jTextField8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField8.setBorder(null);
        jTextField8.setPreferredSize(new java.awt.Dimension(30, 35));
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });
        jPanel13.add(jTextField8);

        javax.swing.GroupLayout tutorsTabLayout = new javax.swing.GroupLayout(tutorsTab);
        tutorsTab.setLayout(tutorsTabLayout);
        tutorsTabLayout.setHorizontalGroup(
                tutorsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tutorsTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(tutorsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator8)
                                        .addGroup(tutorsTabLayout.createSequentialGroup()
                                                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 108,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 108,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(tutorsTabLayout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 644,
                                                Short.MAX_VALUE))
                                .addGap(18, 18, 18)));
        tutorsTabLayout.setVerticalGroup(
                tutorsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tutorsTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tutorsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(tutorsTabLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 35,
                                                Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                                .addGap(18, 18, 18)));

        tabs.addTab("tab3", tutorsTab);

        studentsTab.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(123, 95, 241));
        jLabel11.setText("Students");

        jScrollPane4.setBackground(new java.awt.Color(242, 255, 233));
        jScrollPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(123, 95, 241), 1, true));

        studentsTable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        studentsTable.setForeground(new java.awt.Color(51, 51, 51));
        studentsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "ID", "Student Name", "Email", "Course"
                }) {
            Class[] types = new Class[] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        studentsTable.setRowHeight(25);
        jScrollPane4.setViewportView(studentsTable);
        if (studentsTable.getColumnModel().getColumnCount() > 0) {
            studentsTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            studentsTable.getColumnModel().getColumn(1).setPreferredWidth(180);
            studentsTable.getColumnModel().getColumn(2).setPreferredWidth(180);
            studentsTable.getColumnModel().getColumn(3).setPreferredWidth(180);
        }

        jButton16.setBackground(new java.awt.Color(123, 95, 241));
        jButton16.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/progress.png"))); // NOI18N
        jButton16.setText("View");
        jButton16.setBorder(null);
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton16MouseClicked(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(123, 95, 241));
        jButton17.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/edit.png"))); // NOI18N
        jButton17.setText("Edit");
        jButton17.setBorder(null);
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton17MouseClicked(evt);
            }
        });
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(123, 95, 241));
        jButton18.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/delete.png"))); // NOI18N
        jButton18.setText("Delete");
        jButton18.setBorder(null);
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(123, 95, 241));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(123, 95, 241), 1, true));
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/search.png"))); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(50, 22));
        jPanel12.add(jLabel20);

        jTextField7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField7.setBorder(null);
        jTextField7.setPreferredSize(new java.awt.Dimension(30, 35));
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });
        jPanel12.add(jTextField7);

        javax.swing.GroupLayout studentsTabLayout = new javax.swing.GroupLayout(studentsTab);
        studentsTab.setLayout(studentsTabLayout);
        studentsTabLayout.setHorizontalGroup(
                studentsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(studentsTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(
                                        studentsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(studentsTabLayout.createSequentialGroup()
                                                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                266, Short.MAX_VALUE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jSeparator7)
                                                .addGroup(studentsTabLayout.createSequentialGroup()
                                                        .addComponent(jLabel11)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 644,
                                                        Short.MAX_VALUE))
                                .addGap(18, 18, 18)));
        studentsTabLayout.setVerticalGroup(
                studentsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(studentsTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(
                                        studentsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(studentsTabLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 35,
                                                        Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                                .addGap(18, 18, 18)));

        tabs.addTab("tab4", studentsTab);

        settingTab.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(123, 95, 241));
        jLabel12.setText("Settings");

        jLabel14.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(123, 95, 241));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/key.png"))); // NOI18N
        jLabel14.setText("Security & Login");

        jLabel13.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(78, 60, 153));
        jLabel13.setText("Username:");

        profileUsername.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        profileUsername.setForeground(new java.awt.Color(51, 51, 51));
        profileUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        profileUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileUsernameActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(78, 60, 153));
        jLabel15.setText("Email:");

        profileEmail.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        profileEmail.setForeground(new java.awt.Color(51, 51, 51));
        profileEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        profileEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileEmailActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(123, 95, 241));
        jButton10.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/edit.png"))); // NOI18N
        jButton10.setText("Edit Profile");
        jButton10.setBorder(null);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(123, 95, 241));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/profile.png"))); // NOI18N
        jLabel16.setText("General Profile");

        jLabel17.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(78, 60, 153));
        jLabel17.setText("Old Password:");

        jButton11.setBackground(new java.awt.Color(123, 95, 241));
        jButton11.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cms/icons/edit.png"))); // NOI18N
        jButton11.setText("Change Password");
        jButton11.setBorder(null);
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton11MouseEntered(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(78, 60, 153));
        jLabel18.setText("New Password:");

        oldPassword.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        oldPassword.setForeground(new java.awt.Color(51, 51, 51));
        oldPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        oldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oldPasswordActionPerformed(evt);
            }
        });

        newPassword.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        newPassword.setForeground(new java.awt.Color(51, 51, 51));
        newPassword.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        newPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        newPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingTabLayout = new javax.swing.GroupLayout(settingTab);
        settingTab.setLayout(settingTabLayout);
        settingTabLayout.setHorizontalGroup(
                settingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(settingTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(settingTabLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator9)
                                        .addGroup(settingTabLayout.createSequentialGroup()
                                                .addGroup(settingTabLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jLabel12)
                                                        .addComponent(jLabel16)
                                                        .addComponent(jLabel14)
                                                        .addGroup(settingTabLayout.createSequentialGroup()
                                                                .addComponent(jLabel17)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(oldPassword,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 169,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel18)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(newPassword,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 163,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(settingTabLayout.createSequentialGroup()
                                                                .addComponent(jLabel13)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(profileUsername,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 191,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel15)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(profileEmail))
                                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 81, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)));
        settingTabLayout.setVerticalGroup(
                settingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(settingTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(settingTabLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(profileEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(settingTabLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel13)
                                                .addComponent(profileUsername, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel15)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(
                                        settingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel17)
                                                .addComponent(jLabel18)
                                                .addComponent(oldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(158, 158, 158)));

        tabs.addTab("tab5", settingTab);

        getContentPane().add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, -40, 680, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void newPasswordActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newPasswordActionPerformed'");
    }

    protected void jButton11MouseEntered(MouseEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'jButton11MouseEntered'");
    }

    protected void profileUsernameActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'profileUsernameActionPerformed'");
    }

    /**
     * Handles the mouse click event for jButton16.
     * If the mode is "Admin", it opens the MarksReport window.
     * If the mode is "Instructor", it opens the MarksForm window with the current
     * username.
     */
    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton16MouseClicked
        if (mode.equals("Admin")) {
            MarksReport mr = new MarksReport();
            mr.setVisible(true);
        } else if (mode.equals("Instructor")) {
            MarksForm mf = new MarksForm(getUsername());
            mf.setVisible(true);
        }
    }// GEN-LAST:event_jButton16MouseClicked

    protected void profileEmailActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'profileEmailActionPerformed'");
    }

    protected void oldPasswordActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'oldPasswordActionPerformed'");
    }

    /**
     * Handles the mouse click event for jButton11.
     * Validates the old and new passwords and updates the password in the database
     * if valid.
     * Displays a success message if the password is updated successfully.
     * Resets the old and new password fields.
     * 
     * @param evt The mouse event that triggered the method.
     */
    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton11MouseClicked
        String oldPass = new String(oldPassword.getPassword());
        String newPass = new String(newPassword.getPassword());
        Validation val = new Validation();
        if (val.validatePassword(oldPass, newPass, getPassword())) {
            Database db = new Database();
            db.updatePassword(getId(), newPass, getMode());
            JOptionPane.showMessageDialog(this, "Password updated successfully");
            // reset fields
            oldPassword.setText("");
            newPassword.setText("");
        }
    }// GEN-LAST:event_jButton11MouseClicked

    /**
     * Event handler for the mouseClicked event of jButton10.
     * Updates the user's profile information if the entered username and email are
     * valid.
     * Displays a success message if the profile is updated successfully.
     * 
     * @param evt The MouseEvent that triggered the event.
     */
    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton10MouseClicked
        String username = profileUsername.getText();
        String email = profileEmail.getText();
        Validation val = new Validation();
        if (val.validateNameAndEmail(username, email)) {
            Database db = new Database();
            db.updateUsernameAndEmail(getId(), username, email, getMode());
            JOptionPane.showMessageDialog(this, "Profile updated successfully");
        }
    }// GEN-LAST:event_jButton10MouseClicked

    /**
     * Event handler for the mouse click event on jButton17.
     * This method is called when the button is clicked.
     * It retrieves the selected row index from studentsTable and performs the
     * following actions:
     * - If no row is selected and the table is empty, it displays a message
     * indicating that the table is empty.
     * - If no row is selected but the table is not empty, it displays a message
     * indicating that a student must be selected.
     * - If a row is selected, it creates a new instance of StudentsForm and sets
     * the selected row index.
     * It then makes the form visible.
     *
     * @param evt The MouseEvent object representing the mouse click event.
     */
    private void jButton17MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton17MouseClicked
        int selectedIndex = studentsTable.getSelectedRow();
        if (selectedIndex == -1) {
            if (studentsTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty");
            } else {
                JOptionPane.showMessageDialog(this, "You must select a student");
            }
        } else {
            StudentsForm sf = new StudentsForm(studentsTable, selectedIndex);
            sf.setVisible(true);
        }
    }// GEN-LAST:event_jButton17MouseClicked

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextField7KeyReleased
        searchFilter(studentsTable, jTextField7);
    }// GEN-LAST:event_jTextField7KeyReleased

    /**
     * Handles the mouse click event for jButton21.
     * If a tutor is selected from the tutorsTable, it prompts the user with a
     * confirmation dialog to delete the tutor.
     * If the user confirms the deletion, the tutor is deleted from the database and
     * the tutorsTable is updated.
     * If no tutor is selected or the user cancels the deletion, appropriate
     * messages are displayed.
     */
    private void jButton21MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton21MouseClicked
        int selectedIndex = tutorsTable.getSelectedRow();
        if (selectedIndex == -1) {
            if (tutorsTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty");
            } else {
                JOptionPane.showMessageDialog(this, "You must select a tutor");
            }
        } else {
            int id = Integer.parseInt((String) tutorsTable.getValueAt(selectedIndex, 0));
            int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete this tutor?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                Database db = new Database();
                db.deleteInstructor(id);
                JOptionPane.showMessageDialog(this, "Tutor deleted successfully");
                updateCount();
                updateTutorsTable();
            }
        }
    }// GEN-LAST:event_jButton21MouseClicked

    /**
     * Event handler for the mouse click event on jButton20.
     * It performs the following actions:
     * - Retrieves the selected row index from tutorsTable.
     * - If no row is selected, displays a message if the table is empty or prompts
     * the user to select a tutor.
     * - If a row is selected, opens the TutorsForm with the selected row index.
     */
    private void jButton20MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton20MouseClicked
        int selectedIndex = tutorsTable.getSelectedRow();
        if (selectedIndex == -1) {
            if (tutorsTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty");
            } else {
                JOptionPane.showMessageDialog(this, "You must select a tutor");
            }
        } else {
            TutorsForm tf = new TutorsForm(tutorsTable, selectedIndex);
            tf.setVisible(true);
        }
    }// GEN-LAST:event_jButton20MouseClicked

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextField8KeyReleased
        searchFilter(tutorsTable, jTextField8);
    }// GEN-LAST:event_jTextField8KeyReleased

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextField6KeyReleased
        searchFilter(modulesTable, jTextField6);
    }// GEN-LAST:event_jTextField6KeyReleased

    /**
     * Handles the mouse click event for jButton15.
     * If the mode is "Admin", it checks if a module is selected in the
     * modulesTable.
     * If no module is selected, it displays a message indicating that the table is
     * empty or a module must be selected.
     * If a module is selected, it prompts the user with a confirmation dialog to
     * delete the selected module.
     * If the user confirms the deletion, it deletes the module from the database,
     * updates the modulesTable, and displays a success message.
     * If the mode is "Student", it opens the MarksReport window for the current
     * user.
     */
    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton15MouseClicked
        if (mode.equals("Admin")) {
            int selectedIndex = modulesTable.getSelectedRow();
            if (selectedIndex == -1) {
                if (coursesTable.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Table is empty");
                } else {
                    JOptionPane.showMessageDialog(this, "You must select a module");
                }
            } else {
                int id = Integer.parseInt((String) modulesTable.getValueAt(selectedIndex, 0));
                int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete this module?",
                        "Warning",
                        JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    Database db = new Database();
                    db.deleteModule(id);
                    JOptionPane.showMessageDialog(this, "Module deleted successfully");
                    updateModulesTable();
                    updateCount();
                }
            }
        } else if (mode.equals("Student")) {
            MarksReport mr = new MarksReport(getId());
            mr.setVisible(true);
        }

    }// GEN-LAST:event_jButton15MouseClicked

    /**
     * Event handler for the mouse click event on jButton14.
     * It performs the following actions:
     * - Retrieves the selected index from modulesTable.
     * - If no module is selected, it displays an appropriate message.
     * - If a module is selected, it creates a new ModulesForm instance and displays
     * it.
     * 
     * @param evt The MouseEvent object representing the mouse click event.
     */
    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton14MouseClicked
        int selectedIndex = modulesTable.getSelectedRow();
        if (selectedIndex == -1) {
            if (coursesTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty");
            } else {
                JOptionPane.showMessageDialog(this, "You must select a module");
            }
        } else {
            ModulesForm mf = new ModulesForm(true, modulesTable, selectedIndex);
            mf.setVisible(true);
        }
    }// GEN-LAST:event_jButton14MouseClicked

    /**
     * Event handler for the mouse click event on jButton13.
     * Opens the ModulesForm window.
     */
    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton13MouseClicked
        ModulesForm mf = new ModulesForm();
        mf.setVisible(true);
    }// GEN-LAST:event_jButton13MouseClicked

    private void jScrollPane5KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jScrollPane5KeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_jScrollPane5KeyReleased

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton21ActionPerformed

    /**
     * Event handler for the key released event of the jTextField1 component.
     * Updates the row filter of the coursesTable based on the text entered in the
     * jTextField1.
     * 
     * @param evt The key event that triggered the method.
     */
    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextField1KeyReleased
        searchFilter(coursesTable, jTextField1);
    }// GEN-LAST:event_jTextField1KeyReleased

    /**
     * Event handler for jButton1MouseClicked.
     * Deletes the selected course from the courses table.
     */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseClicked
        DefaultTableModel model = (DefaultTableModel) coursesTable.getModel();
        int selectedIndex = coursesTable.getSelectedRow();
        if (selectedIndex == -1) {
            if (coursesTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty");
            } else {
                JOptionPane.showMessageDialog(this, "You must select a course");
            }
        } else {
            int id = Integer.parseInt((String) model.getValueAt(selectedIndex, 0));
            int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete this course?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                Database db = new Database();
                db.deleteCourse(id);
                JOptionPane.showMessageDialog(this, "Course deleted successfully");
                updateCoursesTable();
                updateCount();
            }
        }
    }// GEN-LAST:event_jButton1MouseClicked

    /**
     * Event handler for the mouse click event on jButton3.
     * It retrieves the selected row index from the coursesTable and performs the
     * following actions:
     * - If no row is selected and the table is empty, it displays a message
     * indicating that the table is empty.
     * - If no row is selected but the table is not empty, it displays a message
     * indicating that a course must be selected.
     * - If a row is selected, it creates a new instance of the CoursesForm class
     * with the selected row index and displays it.
     *
     * @param evt The MouseEvent object representing the mouse click event.
     */
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton3MouseClicked
        int selectedIndex = coursesTable.getSelectedRow();
        if (selectedIndex == -1) {
            if (coursesTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty");
            } else {
                JOptionPane.showMessageDialog(this, "You must select a course");
            }
        } else {
            CoursesForm cf = new CoursesForm(true, coursesTable, selectedIndex);
            cf.setVisible(true);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton11ActionPerformed

    /**
     * Event handler for the mouse click event on the dashboard button.
     * This method is called when the dashboard button is clicked.
     * It performs the following actions:
     * - Sets the selected index of the tabs to 0.
     * - Sets the font of the dashboard button to "Poppins ExtraBold" with a font
     * size of 18.
     * - Sets the font of the courses, modules, tutors, students, and settings
     * buttons to "Poppins Medium" with a font size of 16.
     * 
     * @param evt The MouseEvent object representing the mouse click event.
     */
    private void dashboardBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_dashboardBtnMouseClicked
        tabs.setSelectedIndex(0);
        dashboardBtn.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 18));
        coursesBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        modulesBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        tutorsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        studentsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        settingsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
    }// GEN-LAST:event_dashboardBtnMouseClicked

    /**
     * Event handler for the mouse click event on the coursesBtn.
     * This method is called when the coursesBtn is clicked.
     * It performs the following actions:
     * - Sets the selected index of the tabs to 1.
     * - Sets the font of coursesBtn to "Poppins ExtraBold" with size 18.
     * - Sets the font of dashboardBtn, modulesBtn, tutorsBtn, studentsBtn, and
     * settingsBtn to "Poppins Medium" with size 16.
     */
    private void coursesBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_coursesBtnMouseClicked
        tabs.setSelectedIndex(1);
        coursesBtn.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 18));
        dashboardBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        modulesBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        tutorsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        studentsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        settingsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
    }// GEN-LAST:event_coursesBtnMouseClicked

    /**
     * Event handler for the mouse click event on the modulesBtn.
     * This method is called when the modulesBtn is clicked.
     * It sets the selected index of the tabs to 2, changes the font styles of
     * various buttons.
     * 
     * @param evt The MouseEvent object representing the mouse click event.
     */
    private void modulesBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tutorsBtnMouseClicked
        tabs.setSelectedIndex(2);
        modulesBtn.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 18));
        coursesBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        tutorsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        dashboardBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        studentsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        settingsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
    }// GEN-LAST:event_tutorsBtnMouseClicked

    /**
     * Event handler for the mouse click event on the tutorsBtn.
     * This method is called when the tutorsBtn is clicked.
     * It performs the following actions:
     * - Sets the selected index of the tabs to 3.
     * - Sets the font of the tutorsBtn to "Poppins ExtraBold" with a font size of
     * 18.
     * - Sets the font of the coursesBtn, modulesBtn, dashboardBtn, studentsBtn, and
     * settingsBtn to "Poppins Medium" with a font size of 16.
     */
    private void tutorsBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tutorsBtnMouseClicked
        tabs.setSelectedIndex(3);
        tutorsBtn.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 18));
        coursesBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        modulesBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        dashboardBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        studentsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        settingsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
    }// GEN-LAST:event_tutorsBtnMouseClicked

    /**
     * Event handler for the mouse click event on the studentsBtn.
     * This method is called when the studentsBtn is clicked.
     * It performs the following actions:
     * - Sets the selected index of the tabs to 4.
     * - Sets the font of studentsBtn to "Poppins ExtraBold" with size 18.
     * - Sets the font of coursesBtn, modulesBtn, tutorsBtn, dashboardBtn, and
     * settingsBtn to "Poppins Medium" with size 16.
     */
    private void studentsBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_studentsBtnMouseClicked
        tabs.setSelectedIndex(4);
        studentsBtn.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 18));
        coursesBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        modulesBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        tutorsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        dashboardBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        settingsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
    }// GEN-LAST:event_studentsBtnMouseClicked

    /**
     * Event handler for the mouse click event on the settings button.
     * This method is called when the settings button is clicked.
     * It performs the following actions:
     * - Sets the selected index of the tabs to 5.
     * - Sets the font of the settings button to "Poppins ExtraBold" with a font
     * size of 18.
     * - Sets the font of the courses button to "Poppins Medium" with a font size of
     * 16.
     * - Sets the font of the modules button to "Poppins Medium" with a font size of
     * 16.
     * - Sets the font of the tutors button to "Poppins Medium" with a font size of
     * 16.
     * - Sets the font of the students button to "Poppins Medium" with a font size
     * of 16.
     * - Sets the font of the dashboard button to "Poppins Medium" with a font size
     * of 16.
     *
     * @param evt The MouseEvent object representing the mouse click event.
     */
    private void settingsBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_settingsBtnMouseClicked
        tabs.setSelectedIndex(5);
        settingsBtn.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 18));
        coursesBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        modulesBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        tutorsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        studentsBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        dashboardBtn.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
    }// GEN-LAST:event_settingsBtnMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton2ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton15ActionPerformed

    private void jScrollPane3KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jScrollPane3KeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_jScrollPane3KeyReleased

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton18ActionPerformed

    /**
     * Event handler for the mouse click event on jButton2.
     * Creates a new instance of CoursesForm and makes it visible.
     */
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton2MouseClicked
        CoursesForm cf = new CoursesForm();
        cf.setVisible(true);
    }// GEN-LAST:event_jButton2MouseClicked

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable activitiesTable;
    private javax.swing.JLabel coursesBtn;
    private static javax.swing.JLabel coursesCount;
    private javax.swing.JPanel coursesTab;
    private static javax.swing.JTable coursesTable;
    private javax.swing.JLabel dashboardBtn;
    private javax.swing.JPanel dashboardTab;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel logoutBtn;
    private javax.swing.JLabel modulesBtn;
    private javax.swing.JPanel modulesTab;
    private static javax.swing.JTable modulesTable;
    private javax.swing.JPasswordField newPassword;
    private javax.swing.JPasswordField oldPassword;
    private javax.swing.JTextField profileEmail;
    private javax.swing.JTextField profileUsername;
    private javax.swing.JPanel settingTab;
    private javax.swing.JLabel settingsBtn;
    private javax.swing.JLabel studentsBtn;
    private static javax.swing.JLabel studentsCount;
    private javax.swing.JPanel studentsTab;
    private static javax.swing.JTable studentsTable;
    private javax.swing.JTabbedPane tabs;
    private static javax.swing.JLabel teachersCount;
    private javax.swing.JLabel tutorsBtn;
    private javax.swing.JPanel tutorsTab;
    private static javax.swing.JTable tutorsTable;
    // End of variables declaration//GEN-END:variables
}
