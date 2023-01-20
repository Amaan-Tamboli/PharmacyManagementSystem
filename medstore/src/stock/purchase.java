/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package stock;

import com.mysql.jdbc.Statement;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
/**
 *
 * @author Amaan Tamboli
 */
public class purchase extends javax.swing.JFrame {

    /**
     * Creates new form purchase
     */
    public purchase() {
        initComponents();
        Connect();
        Vendor();
    }
    
    
    
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    DefaultTableModel df;
    ResultSet rs;
    
    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/medstore","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(vendor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(vendor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Vendor()
    {
        try {
            pst = con.prepareStatement("select Distinct name from vendor");
            rs = pst.executeQuery();
            
            txtvendor.removeAllItems();
            
            while(rs.next())
            {
                txtvendor.addItem(rs.getString("name"));
            }
                    } catch (SQLException ex) {
            Logger.getLogger(purchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void barcode()
    {
        try {
            String pcode = txtmcode.getText();
            pst = con.prepareStatement("select * from medicines where barcode=?");
            
            pst.setString(1, pcode);
            rs = pst.executeQuery();
            
            if(rs.next()==false)
            {
                JOptionPane.showMessageDialog(this, "Barcode Not Found !!!");
                txtmcode.setText("");
            }
            else
            {
                String pname = rs.getString("mname");
                String price = rs.getString("rprice");
                
                txtmname.setText(pname.trim());
                txtprice.setText(price.trim());
                
                txtqty.requestFocus();
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(purchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void Tpurchase()
    {
        int price = Integer.parseInt(txtprice.getText());
        int qty = Integer.parseInt(txtqty.getText());
        
        int tot = price*qty;
        
        df = (DefaultTableModel)jTable1.getModel();
        df.addRow(new Object[]
        {
            txtmcode.getText(),
            txtmname.getText(),
            txtprice.getText(),
            txtqty.getText(),
            tot
        });
        
        int sum = 0;
        for(int i = 0;i<jTable1.getRowCount();i++)
        {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i,4).toString());
            
        }
        
        txttcost.setText(String.valueOf(sum));
        txtmcode.setText("");
        txtmname.setText("");
        txtprice.setText("");
        txtqty.setText("");
    }
    
    
    public void add()
    {
        try {
            DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            String date = dt.format(now);
            String vendor = txtvendor.getSelectedItem().toString();
            String subtotal = txttcost.getText();
            String pay = txtpay.getText();
            String bal = txtbal.getText();
            
            int lastid = 0;
            String query1 = "insert into purchase(date,vendor, subtotal,pay,bal) values(?,?,?,?,?)";
            pst = con.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
            
            pst.setString(1,date);
            pst.setString(2,vendor);
            pst.setString(3,subtotal);
            pst.setString(4,pay);
            pst.setString(5,bal);
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            
            if(rs.next())
            {
                lastid = rs.getInt(1);
            }
            
            String query2 = "insert purchaseitem(purid, prodid, rprice, qty, total) values(?,?,?,?,?)";
            pst1 = con.prepareStatement(query2);
            String productid;
            String price;
            String qty;
            int total=0;
            
            for(int i = 0;i<jTable1.getRowCount();i++)
            {
                productid = (String)jTable1.getValueAt(i, 0);
                price = (String)jTable1.getValueAt(i, 2);
                qty = (String)jTable1.getValueAt(i, 3);
                total = (int)jTable1.getValueAt(i, 4);
                
                pst1.setInt(1, lastid);
                pst1.setString(2, productid);
                pst1.setString(3, price);
                pst1.setString(4, qty);
                pst1.setInt(5, total);
                pst1.executeUpdate();
                
            }
            
            String query3 = "update medicines set qty = qty+? where barcode=?";
            pst2 = con.prepareStatement(query3);
            for(int i = 0;i<jTable1.getRowCount();i++)
            {
                productid = (String)jTable1.getValueAt(i, 0);
                qty = (String)jTable1.getValueAt(i, 3);
                
                pst2.setString(1, qty);
                pst2.setString(2, productid);
                pst2.executeUpdate();
                
                
            }
            
            JOptionPane.showMessageDialog(this,"Purchase Completed !!!");
            
   
        } catch (SQLException ex) {
            Logger.getLogger(purchase.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtvendor = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtmcode = new javax.swing.JTextField();
        txtmname = new javax.swing.JTextField();
        txtprice = new javax.swing.JTextField();
        txtqty = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txttcost = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtpay = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtbal = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Purchase");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 17, 917, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Vendor");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 83, 130, 41));

        getContentPane().add(txtvendor, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 84, 147, 41));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Medicine Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 9, 170, 41));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Price");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 9, 170, 41));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Medicine Code");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 9, 175, 41));

        txtmcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmcodeActionPerformed(evt);
            }
        });
        txtmcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmcodeKeyPressed(evt);
            }
        });
        jPanel1.add(txtmcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 71, 175, 30));
        jPanel1.add(txtmname, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 71, 170, 30));
        jPanel1.add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 71, 170, 30));
        jPanel1.add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 71, 169, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Code", "Medicine Name", "Price", "Qty", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 120, 740, 303));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Qty");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 9, 169, 41));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 68, 130, 34));
        jPanel1.add(txttcost, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 179, 130, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Total Cost");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, 130, 41));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Balance");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 334, 130, 41));
        jPanel1.add(txtpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 286, 130, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Payment");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 227, 130, 41));
        jPanel1.add(txtbal, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 393, 130, 30));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Order");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 441, 180, 42));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("BackToMain");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 443, 180, 38));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 152, 929, 492));

        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\Amaan Tamboli\\Downloads\\178-1783030_online-shopping-logo-png-transparent-png.png")); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 150));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        txtvendor.setEnabled(false);
        Tpurchase();
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        txtvendor.setEnabled(true);
        int pay = Integer.parseInt(txtpay.getText());
        int subtotal = Integer.parseInt(txttcost.getText());
        
        int bal = subtotal - pay;
        
        txtbal.setText(String.valueOf(bal));
        add();
        txttcost.setText("");
        txtpay.setText("");
        txtbal.setText("");
       
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtmcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmcodeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtmcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmcodeKeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            barcode();
        } 
        
        
    }//GEN-LAST:event_txtmcodeKeyPressed

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
            java.util.logging.Logger.getLogger(purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new purchase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtbal;
    private javax.swing.JTextField txtmcode;
    private javax.swing.JTextField txtmname;
    private javax.swing.JTextField txtpay;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txttcost;
    private javax.swing.JComboBox<String> txtvendor;
    // End of variables declaration//GEN-END:variables
}
