/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bank;

/**
 *
 * @author OSAMA
 */
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Authentication extends javax.swing.JFrame {
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    
    boolean openAdmin;

    /** Creates new form Authentication */
    public Authentication() {
        initComponents();
        conn = JavaConnect.connectDb();
        ImageIcon icon = new ImageIcon("E:\\JAVA PROGRAMMING\\BRO CODE TUTORIAL\\Banking\\src\\main\\java\\images\\top icon.png");
        this.setIconImage(icon.getImage());
        String sql = "select * from LoggedIn where Status=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, "logged in");
            rs = pst.executeQuery();
            if(rs.next()) {
                String val1 = rs.getString("Acc");
                String val2 = rs.getString("Pin");
                if(val1.equalsIgnoreCase("00000000") && val2.equalsIgnoreCase("0000")) {}
                else if(val1.equalsIgnoreCase("11111111") && val2.equalsIgnoreCase("1111")) {
                    accountNoTextField.setText("admin");
                    pinPasswordField.setText("admin");
                    rs.close();
                    pst.close();
                    getLoginDetails(false);
                }
                else {
                    accountNoTextField.setText(val1);
                    pinPasswordField.setText(val2);
                    rs.close();
                    pst.close();
                    getLoginDetails(false);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {}
        }
    }
    
    private void getLoginDetails(boolean credentials) {
        String val1 = accountNoTextField.getText();
        String val2 = pinPasswordField.getText();
        if (val1.equalsIgnoreCase("admin") && val2.equalsIgnoreCase("admin")) {
            setVisible(false);
            openAdmin = true;
            setRememberMe();
            Loading obj = new Loading(accountNoTextField.getText(), openAdmin);
            obj.setUpLoading();
            obj.setVisible(true);
        }
        else {
            String sql = "select * from Account where Acc=? and Pin=?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, val1);
                pst.setString(2, val2);
                rs = pst.executeQuery();
                if (rs.next()) {
                    setVisible(false);
                    rs.close();
                    pst.close();
                    openAdmin = false;
                    setRememberMe();
                    Loading obj = new Loading(accountNoTextField.getText(), openAdmin);
                    obj.setUpLoading();
                    obj.setVisible(true);
                }
                else {
                    if(credentials) {
                        JOptionPane.showMessageDialog(null, "Incorrect Credentials!");
                    }
                    else {
                        accountNoTextField.setText("");
                        pinPasswordField.setText("");
                        setVisible(true);
                    }
                    rs.close();
                    pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {}
            }
        }
    }
    
    private void setRememberMe() {
        String val1 = accountNoTextField.getText();
        String val2 = pinPasswordField.getText();
        if(openAdmin) {
            val1 = "11111111";
            val2 = "1111";
        }
        try {
            String val3 = "logged in";
            String sql = "update LoggedIn set Acc='"+val1+"',Pin='"+val2+"' where Status='"+val3+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        } finally {
            try {
                pst.close();
            } catch (Exception e) {}
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        accountNoTextField = new javax.swing.JTextField();
        pinPasswordField = new javax.swing.JPasswordField();
        accountNoLabel = new javax.swing.JLabel();
        pinLabel = new javax.swing.JLabel();
        newAccountButton = new javax.swing.JButton();
        bankNameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OU Bank Management System - Login");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 3), "Authentication", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 28), new java.awt.Color(255, 51, 51))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 250));

        loginButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        accountNoTextField.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        pinPasswordField.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        pinPasswordField.setPreferredSize(new java.awt.Dimension(282, 31));

        accountNoLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        accountNoLabel.setText("Account No.");

        pinLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        pinLabel.setText("Pin");

        newAccountButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        newAccountButton.setText("New Account");
        newAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAccountButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(accountNoLabel)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(46, 46, 46)
                        .add(pinLabel)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 70, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(loginButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 125, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(newAccountButton))
                    .add(pinPasswordField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(accountNoTextField))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(26, 26, 26)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(accountNoLabel)
                        .add(34, 34, 34)
                        .add(pinLabel))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(accountNoTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(28, 28, 28)
                        .add(pinPasswordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(28, 28, 28)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(loginButton)
                    .add(newAccountButton))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        bankNameLabel.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        bankNameLabel.setIcon(new javax.swing.ImageIcon("E:\\JAVA PROGRAMMING\\BRO CODE TUTORIAL\\Banking\\src\\main\\java\\images\\main.png")); // NOI18N
        bankNameLabel.setText("OU Bank Mangement System");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(74, 74, 74)
                .add(bankNameLabel)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(26, 26, 26)
                .add(bankNameLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 28, Short.MAX_VALUE)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void newAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAccountButtonActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        Account obj = new Account();
        obj.setVisible(true);
    }//GEN-LAST:event_newAccountButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
//        String accountNo = accountNoTextField.getText();
//        String pin = pinPasswordField.getText();
//        if(accountNo.equalsIgnoreCase("admin") && pin.equalsIgnoreCase("admin")) {
//            setVisible(false);
//            Loading obj = new Loading();
//            obj.setUpLoading();
//            obj.setVisible(true);
//        }
        getLoginDetails(true);
        
    }//GEN-LAST:event_loginButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Authentication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountNoLabel;
    private javax.swing.JTextField accountNoTextField;
    private javax.swing.JLabel bankNameLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton newAccountButton;
    private javax.swing.JLabel pinLabel;
    private javax.swing.JPasswordField pinPasswordField;
    // End of variables declaration//GEN-END:variables

}
