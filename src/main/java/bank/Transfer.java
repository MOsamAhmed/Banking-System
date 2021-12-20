/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author OSAMA
 */
public class Transfer extends javax.swing.JPanel {
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form Transfer
     */
    public Transfer() {
        initComponents();
        conn = JavaConnect.connectDb();
    }
    
    public void getDetails(String accNo) {
        String sql = "select * from Account where Acc=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, accNo);
            rs = pst.executeQuery();
            if(rs.next()) {
                String add1 = rs.getString("Acc");
                accountNoTextField.setText(add1);
                String add2 = rs.getString("Name");
                nameTextField.setText(add2);
                String add3 = rs.getString("Balance");
                availableBalanceTextField.setText(add3);
                rs.close();
                pst.close();
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
            } catch(Exception e) {}
        }
    }
    
    public void getAllAccountNos() {
        String sql = "select * from Account";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                String val1 = rs.getString("Acc");
                if(accountNoTextField.getText().equalsIgnoreCase(val1)) {}
                else {
                    creditAccountComboBox.addItem(val1);
                }
            }
            rs.close();
            pst.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
            } catch(Exception e) {}
        }
        
    }
    
    private void getTotalAmount() {
        if(transferAmountTextField.getText().equalsIgnoreCase("") || Integer.parseInt(transferAmountTextField.getText())==0) {
            totalTextField.setText("");
        }
        else {
            try {
                long availableBalance = Long.parseLong(availableBalanceTextField.getText());
                long transferAmount = Long.parseLong(transferAmountTextField.getText());
                long sum = availableBalance-transferAmount;
                String total = String.valueOf(sum);
                totalTextField.setText(total);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                e.printStackTrace();
            }
        }
    }
    
    private void getCreditAccAfterTransfer() {
        if(transferAmountTextField.getText().equalsIgnoreCase("") || Integer.parseInt(transferAmountTextField.getText())==0 || creditAccCurBalTextField.getText().equalsIgnoreCase("")) {
            afterTransferTextField.setText("");
        }
        else {
            try {
                long creditAccCurBalance = Long.parseLong(creditAccCurBalTextField.getText());
                long transferAmount = Long.parseLong(transferAmountTextField.getText());
                long sum = creditAccCurBalance+transferAmount;
                String total = String.valueOf(sum);
                afterTransferTextField.setText(total);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                e.printStackTrace();
            }
        }
    }
    
    private void getCreditAccCurBal() {
        try {
            String val1 = (String) creditAccountComboBox.getSelectedItem();
            String sql = "select * from Account where Acc=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, val1);
            rs = pst.executeQuery();
            if(rs.next()) {
                String val2 = rs.getString("Balance");
                creditAccCurBalTextField.setText(val2);
            }
            rs.close();
            pst.close();
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
    
    public void setCreditAccCurBal() {
        creditAccCurBalTextField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        availableBalanceLabel = new javax.swing.JLabel();
        creditAccountLabel = new javax.swing.JLabel();
        totalButton = new javax.swing.JButton();
        transferButton = new javax.swing.JButton();
        accountNoTextField = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        availableBalanceTextField = new javax.swing.JTextField();
        transferAmountTextField = new javax.swing.JTextField();
        totalTextField = new javax.swing.JTextField();
        accountNoLabel = new javax.swing.JLabel();
        creditAccCurBalLabel1 = new javax.swing.JLabel();
        creditAccountComboBox = new javax.swing.JComboBox<>();
        showButton = new javax.swing.JButton();
        creditAccCurBalLabel2 = new javax.swing.JLabel();
        transferAmountLabel = new javax.swing.JLabel();
        creditAccCurBalTextField = new javax.swing.JTextField();
        currentBalanceLabel = new javax.swing.JLabel();
        afterTransferLabel = new javax.swing.JLabel();
        afterTransferTextField = new javax.swing.JTextField();

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        nameLabel.setText("Name");

        availableBalanceLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        availableBalanceLabel.setText("Available Balance");

        creditAccountLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        creditAccountLabel.setText("Credit Account");

        totalButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalButton.setText("Total");
        totalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalButtonActionPerformed(evt);
            }
        });

        transferButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        transferButton.setText("Transfer");
        transferButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferButtonActionPerformed(evt);
            }
        });

        accountNoTextField.setEditable(false);
        accountNoTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        nameTextField.setEditable(false);
        nameTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        availableBalanceTextField.setEditable(false);
        availableBalanceTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        transferAmountTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        totalTextField.setEditable(false);
        totalTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        accountNoLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        accountNoLabel.setText("Account No.");

        creditAccCurBalLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        creditAccCurBalLabel1.setText("Credit Account");

        creditAccountComboBox.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        creditAccountComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                creditAccountComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        showButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        showButton.setText("Show");
        showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });

        creditAccCurBalLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        creditAccCurBalLabel2.setText("Balance");

        transferAmountLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        transferAmountLabel.setText("Transfer Amount");

        creditAccCurBalTextField.setEditable(false);
        creditAccCurBalTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        currentBalanceLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        currentBalanceLabel.setText("Current Balance");

        afterTransferLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        afterTransferLabel.setText("After Transfer");

        afterTransferTextField.setEditable(false);
        afterTransferTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(creditAccountLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountNoLabel)
                            .addComponent(nameLabel))
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accountNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(creditAccCurBalLabel1)
                    .addComponent(transferAmountLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(availableBalanceLabel)
                            .addComponent(creditAccCurBalLabel2))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(transferAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(availableBalanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(totalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(creditAccountComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(currentBalanceLabel))
                                    .addComponent(creditAccCurBalTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(afterTransferTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(showButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(afterTransferLabel)))))))
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(transferButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(322, 322, 322))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accountNoLabel)
                    .addComponent(accountNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(availableBalanceLabel)
                    .addComponent(availableBalanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferAmountLabel)
                    .addComponent(transferAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(creditAccountLabel)
                    .addComponent(creditAccountComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(creditAccCurBalLabel1)
                    .addComponent(currentBalanceLabel)
                    .addComponent(afterTransferLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(creditAccCurBalLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(creditAccCurBalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(afterTransferTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(showButton)))
                .addGap(18, 18, 18)
                .addComponent(transferButton)
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void totalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalButtonActionPerformed
        // TODO add your handling code here:
        getTotalAmount();
    }//GEN-LAST:event_totalButtonActionPerformed

    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showButtonActionPerformed
        // TODO add your handling code here:
        getTotalAmount();
        getCreditAccCurBal();
        getCreditAccAfterTransfer();
    }//GEN-LAST:event_showButtonActionPerformed

    private void transferButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transferButtonActionPerformed
        // TODO add your handling code here:
        getTotalAmount();
        getCreditAccCurBal();
        getCreditAccAfterTransfer();
        if(totalTextField.getText().equalsIgnoreCase("") || afterTransferTextField.getText().equalsIgnoreCase("")) {
        }
        else {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String dateTime = dtf.format(now);
                String val1 = accountNoTextField.getText();
                String val2 = (String) creditAccountComboBox.getSelectedItem();
                String val3 = transferAmountTextField.getText();
                String val4 = totalTextField.getText();
                String val5 = afterTransferTextField.getText();
                String sql = "insert into BalancesHistory(Sender,Receiver,Amount,Timestamp) values (?,?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, val1);
                pst.setString(2, val2);
                pst.setString(3, val3);
                pst.setString(4, dateTime);
                pst.execute();
                pst.close();
//                for sender
                sql = "update Account set Balance='"+val4+"' where Acc='"+val1+"'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                pst.close();
//                for receiver
                sql = "update Account set Balance='"+val5+"' where Acc='"+val2+"'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                pst.close();
                JOptionPane.showMessageDialog(null, "Transferred Successfully!");
                getDetails(val1);
                getCreditAccCurBal();
                transferAmountTextField.setText("");
                totalTextField.setText("");
                afterTransferTextField.setText("");
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
    }//GEN-LAST:event_transferButtonActionPerformed

    private void creditAccountComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_creditAccountComboBoxPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        getCreditAccCurBal();
    }//GEN-LAST:event_creditAccountComboBoxPopupMenuWillBecomeInvisible


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountNoLabel;
    private javax.swing.JTextField accountNoTextField;
    private javax.swing.JLabel afterTransferLabel;
    private javax.swing.JTextField afterTransferTextField;
    private javax.swing.JLabel availableBalanceLabel;
    private javax.swing.JTextField availableBalanceTextField;
    private javax.swing.JLabel creditAccCurBalLabel1;
    private javax.swing.JLabel creditAccCurBalLabel2;
    private javax.swing.JTextField creditAccCurBalTextField;
    private javax.swing.JComboBox<String> creditAccountComboBox;
    private javax.swing.JLabel creditAccountLabel;
    private javax.swing.JLabel currentBalanceLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton showButton;
    private javax.swing.JButton totalButton;
    private javax.swing.JTextField totalTextField;
    private javax.swing.JLabel transferAmountLabel;
    private javax.swing.JTextField transferAmountTextField;
    private javax.swing.JButton transferButton;
    // End of variables declaration//GEN-END:variables
}
