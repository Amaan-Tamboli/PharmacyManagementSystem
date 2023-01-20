/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amaan Tamboli
 */
public class medicines extends javax.swing.JFrame {

    /**
     * Creates new form medicines
     */
    public medicines() {
        initComponents();
        Connect();
        load();
    }
    
    
    Connection con;
    PreparedStatement pst;
    DefaultTableModel df;
    
    
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
    
    
    public void load()
    {
        int a;
        try {
            pst = con.prepareStatement("select * from medicines");
            ResultSet rs = pst.executeQuery();
            
            ResultSetMetaData rd = rs.getMetaData();
            a= rd.getColumnCount();
            df = (DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
            
            while(rs.next())
            {
                Vector v2 = new Vector();
                for(int i=1;i<=a;i++)
                {
                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("mname"));
                    v2.add(rs.getString("description"));
                    v2.add(rs.getString("barcode"));
                    v2.add(rs.getString("cprice"));
                    v2.add(rs.getString("rprice"));
                    v2.add(rs.getString("qty"));
                    v2.add(rs.getString("rlevel"));
                }
                df.addRow(v2);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(vendor.class.getName()).log(Level.SEVERE, null, ex);
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtmname = new javax.swing.JTextField();
        txtdes = new javax.swing.JTextField();
        txtbarcode = new javax.swing.JTextField();
        txtcprice = new javax.swing.JTextField();
        txtrprice = new javax.swing.JTextField();
        txtqty = new javax.swing.JTextField();
        txtlevel = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 522, 205, 52));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 522, 205, 52));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 522, 207, 52));

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 522, 207, 52));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("BackToMain");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(902, 522, 229, 52));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MedicineID", "MedicineName", "Description", "Barcode", "CostPrice", "RetailPrice", "Qty", "RLevel"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 88, 724, 410));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Medicine Name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 9, 106, 41));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Description");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 68, 106, 41));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Barcode");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 127, 106, 41));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Cost Price");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 186, 106, 41));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Retal Price");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 245, 106, 41));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Qty");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 304, 106, 41));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("ReOrderLevel");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 363, 106, 41));
        jPanel1.add(txtmname, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 19, 230, -1));
        jPanel1.add(txtdes, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 78, 230, -1));
        jPanel1.add(txtbarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 137, 230, -1));
        jPanel1.add(txtcprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 196, 230, -1));
        jPanel1.add(txtrprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 255, 230, -1));
        jPanel1.add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 314, 230, -1));
        jPanel1.add(txtlevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 373, 230, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 85, 1130, 510));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\Amaan Tamboli\\Downloads\\43-433284_medicine-transparent-medical-clip-art-black-and-white.png")); // NOI18N
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Medicines");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1125, 61));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 80));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        try {
            
            String mname = txtmname.getText();
            String des = txtdes.getText();
            String barcode = txtbarcode.getText();
            String cprice = txtcprice.getText();
            String rprice = txtrprice.getText();
            String qty = txtqty.getText();
            String rlevel = txtlevel.getText();
            
            
            // TODO add your handling code here:

            pst = con.prepareStatement("insert into medicines(mname,description,barcode,cprice,rprice,qty,rlevel) values(?,?,?,?,?,?,?)");
            
            pst.setString(1, mname);
            pst.setString(2, des);
            pst.setString(3, barcode);
            pst.setString(4, cprice);
            pst.setString(5, rprice);
            pst.setString(6, qty);
            pst.setString(7, rlevel);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Medicine Added !!!");
            
            txtmname.setText("");
            txtdes.setText("");
            txtbarcode.setText("");
            txtcprice.setText("");
            txtrprice.setText("");
            txtqty.setText("");
            txtlevel.setText("");
            txtmname.requestFocus();
            load();
            
        } catch (SQLException ex) {
            Logger.getLogger(vendor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        df = (DefaultTableModel)jTable1.getModel();
        int selected = jTable1.getSelectedRow();
        
        int id = Integer.parseInt(df.getValueAt(selected, 0).toString());
        
            String mname = txtmname.getText();
            String des = txtdes.getText();
            String barcode = txtbarcode.getText();
            String cprice = txtcprice.getText();
            String rprice = txtrprice.getText();
            String qty = txtqty.getText();
            String rlevel = txtlevel.getText();
            
            
        try {
            // TODO add your handling code here:

            pst = con.prepareStatement("update medicines set mname=?,description=?,barcode=?,cprice=?,rprice=?,qty=?,rlevel=? where id=?");
            pst.setString(1, mname);
            pst.setString(2, des);
            pst.setString(3, barcode);
            pst.setString(4, cprice);
            pst.setString(5, rprice);
            pst.setString(6, qty);
            pst.setString(7, rlevel);
            pst.setInt(8, id);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Medicine Updated !!!");
            
            txtmname.setText("");
            txtdes.setText("");
            txtbarcode.setText("");
            txtcprice.setText("");
            txtrprice.setText("");
            txtqty.setText("");
            txtlevel.setText("");
            txtmname.requestFocus();
            load();
            jButton1.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(vendor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        df = (DefaultTableModel)jTable1.getModel();
        int selected = jTable1.getSelectedRow();
        
        int id = Integer.parseInt(df.getValueAt(selected, 0).toString());
        
        
            
            
        try {
            // TODO add your handling code here:

            pst = con.prepareStatement("delete from medicines where id=?");
            pst.setInt(1, id);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Medicine Deleted !!!");
            
            txtmname.setText("");
            txtdes.setText("");
            txtbarcode.setText("");
            txtcprice.setText("");
            txtrprice.setText("");
            txtqty.setText("");
            txtlevel.setText("");
            load();
            jButton1.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(vendor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
            txtmname.setText("");
            txtdes.setText("");
            txtbarcode.setText("");
            txtcprice.setText("");
            txtrprice.setText("");
            txtqty.setText("");
            txtlevel.setText("");
            load();
            jButton1.setEnabled(true);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        df = (DefaultTableModel)jTable1.getModel();
        int selected = jTable1.getSelectedRow();
        
        int id = Integer.parseInt(df.getValueAt(selected, 0).toString());
        
        txtmname.setText(df.getValueAt(selected, 1).toString());
        txtdes.setText(df.getValueAt(selected, 2).toString());
        txtbarcode.setText(df.getValueAt(selected, 3).toString());
        txtcprice.setText(df.getValueAt(selected, 4).toString());
        txtrprice.setText(df.getValueAt(selected, 5).toString());
        txtqty.setText(df.getValueAt(selected, 6).toString());
        txtlevel.setText(df.getValueAt(selected, 7).toString());
        
        jButton1.setEnabled(false);
        
        
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(medicines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(medicines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(medicines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(medicines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new medicines().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtbarcode;
    private javax.swing.JTextField txtcprice;
    private javax.swing.JTextField txtdes;
    private javax.swing.JTextField txtlevel;
    private javax.swing.JTextField txtmname;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtrprice;
    // End of variables declaration//GEN-END:variables
}