package ProductFrame;

import javax.swing.*;
import java.awt.*;
import LogInFrame.CustomButton;
import LogInFrame.LoginFrame;
import chart.ModelPolarAreaChart;
import chart.PolarAreaChart;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class ProductsFrame extends javax.swing.JFrame {

    private Thread thread;
    
    private int MyIndex;
    private String id,chart_stored,chart_instock,chart_sold;

    // C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\Daisy Inventory Management System\\
    //private final String dbURL = "src\\Database\\DaisyManagement.mdb";
    private final String dbURL = "D:\\Database\\DaisyManagement.mdb";
    
    private final String[]table_col = {"ID","Name","Type","Price","Stored (Qty)","Sold (Qty)","Instock (Qty)"};
    
    public ProductsFrame() {
        initComponents();
        ConnectDriver();
        this.setBackground(new Color(255,255,255,0));
                
    }

    private void ConnectDriver( ){
       try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
       } catch (java.lang.ClassNotFoundException e) { }

    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel1 = new Main.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        show_btn = new ProductFrame.CustomButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb1 = new javax.swing.JTable();
        sold_txt = new ProductFrame.TextField();
        name_txt = new ProductFrame.TextField();
        type_txt = new ProductFrame.TextField();
        stored_txt = new ProductFrame.TextField();
        instock_txt = new ProductFrame.TextField();
        price_txt = new ProductFrame.TextField();
        update_btn = new ProductFrame.CustomButton();
        clear_btn = new ProductFrame.CustomButton();
        delete_btn = new ProductFrame.CustomButton();
        insert_btn = new ProductFrame.CustomButton();
        menu_btn = new ProductFrame.CustomButton();
        polarAreaChart = new chart.PolarAreaChart();
        polarAreaLabel1 = new chart.PolarAreaLabel();
        polarAreaLabel2 = new chart.PolarAreaLabel();
        polarAreaLabel3 = new chart.PolarAreaLabel();
        gradientImageAvater1 = new ImageAvatar.GradientImageAvater();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.0F);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel1.setForeground(new java.awt.Color(157, 153, 255));
        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_35px.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        roundedPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 10, 30, 30));

        show_btn.setBackground(new java.awt.Color(29, 62, 126));
        show_btn.setForeground(new java.awt.Color(255, 255, 255));
        show_btn.setText("SHOW");
        show_btn.setFocusable(false);
        show_btn.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        show_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_btnActionPerformed(evt);
            }
        });
        roundedPanel1.add(show_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 590, 90, 40));

        tb1.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        tb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Type", "Price", "Stored (Qty)", "Sold (Qty)", "Instock (Qty)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb1);

        roundedPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 790, 220));

        sold_txt.setBackground(new java.awt.Color(157, 153, 255));
        sold_txt.setForeground(new java.awt.Color(19, 48, 98));
        sold_txt.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        sold_txt.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        sold_txt.setLabelText("SOLD");
        roundedPanel1.add(sold_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 380, 100, 60));

        name_txt.setBackground(new java.awt.Color(157, 153, 255));
        name_txt.setForeground(new java.awt.Color(19, 48, 98));
        name_txt.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        name_txt.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        name_txt.setLabelText("PRODUCT NAME");
        roundedPanel1.add(name_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 430, 60));

        type_txt.setBackground(new java.awt.Color(157, 153, 255));
        type_txt.setForeground(new java.awt.Color(19, 48, 98));
        type_txt.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        type_txt.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        type_txt.setLabelText("PRODUCT TYPE");
        roundedPanel1.add(type_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 430, 60));

        stored_txt.setBackground(new java.awt.Color(157, 153, 255));
        stored_txt.setForeground(new java.awt.Color(19, 48, 98));
        stored_txt.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        stored_txt.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        stored_txt.setLabelText("STORED");
        roundedPanel1.add(stored_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 100, 60));

        instock_txt.setBackground(new java.awt.Color(157, 153, 255));
        instock_txt.setForeground(new java.awt.Color(19, 48, 98));
        instock_txt.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        instock_txt.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        instock_txt.setLabelText("INSTOCK");
        roundedPanel1.add(instock_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 100, 60));

        price_txt.setBackground(new java.awt.Color(157, 153, 255));
        price_txt.setForeground(new java.awt.Color(19, 48, 98));
        price_txt.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        price_txt.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        price_txt.setLabelText("PRICE");
        roundedPanel1.add(price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 100, 60));

        update_btn.setBackground(new java.awt.Color(29, 62, 126));
        update_btn.setForeground(new java.awt.Color(255, 255, 255));
        update_btn.setText("UPDATE");
        update_btn.setFocusable(false);
        update_btn.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btnActionPerformed(evt);
            }
        });
        roundedPanel1.add(update_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 590, 90, 40));

        clear_btn.setBackground(new java.awt.Color(29, 62, 126));
        clear_btn.setForeground(new java.awt.Color(255, 255, 255));
        clear_btn.setText("CLEAR");
        clear_btn.setFocusable(false);
        clear_btn.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });
        roundedPanel1.add(clear_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 590, 90, 40));

        delete_btn.setBackground(new java.awt.Color(29, 62, 126));
        delete_btn.setForeground(new java.awt.Color(255, 255, 255));
        delete_btn.setText("DELETE");
        delete_btn.setFocusable(false);
        delete_btn.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });
        roundedPanel1.add(delete_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 590, 90, 40));

        insert_btn.setBackground(new java.awt.Color(29, 62, 126));
        insert_btn.setForeground(new java.awt.Color(255, 255, 255));
        insert_btn.setText("INSERT");
        insert_btn.setFocusable(false);
        insert_btn.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        insert_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insert_btnActionPerformed(evt);
            }
        });
        roundedPanel1.add(insert_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 590, 90, 40));

        menu_btn.setBackground(new java.awt.Color(29, 62, 126));
        menu_btn.setForeground(new java.awt.Color(255, 255, 255));
        menu_btn.setText("MENU");
        menu_btn.setFocusable(false);
        menu_btn.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        menu_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_btnActionPerformed(evt);
            }
        });
        roundedPanel1.add(menu_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 140, 40));

        polarAreaChart.setOpaque(true);

        javax.swing.GroupLayout polarAreaChartLayout = new javax.swing.GroupLayout(polarAreaChart);
        polarAreaChart.setLayout(polarAreaChartLayout);
        polarAreaChartLayout.setHorizontalGroup(
            polarAreaChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        polarAreaChartLayout.setVerticalGroup(
            polarAreaChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        roundedPanel1.add(polarAreaChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 340, 280));

        polarAreaLabel1.setBackground(new java.awt.Color(250, 207, 90));
        polarAreaLabel1.setForeground(new java.awt.Color(255, 255, 255));
        polarAreaLabel1.setText("STORED");
        polarAreaLabel1.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        roundedPanel1.add(polarAreaLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 210, -1, -1));

        polarAreaLabel2.setBackground(new java.awt.Color(130, 166, 5));
        polarAreaLabel2.setForeground(new java.awt.Color(255, 255, 255));
        polarAreaLabel2.setText("INSTOCK");
        polarAreaLabel2.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        roundedPanel1.add(polarAreaLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 150, -1, -1));

        polarAreaLabel3.setBackground(new java.awt.Color(252, 108, 68));
        polarAreaLabel3.setForeground(new java.awt.Color(255, 255, 255));
        polarAreaLabel3.setText("SOLD");
        polarAreaLabel3.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        roundedPanel1.add(polarAreaLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 180, -1, -1));

        gradientImageAvater1.setBorderSize(4);
        gradientImageAvater1.setBorderSpace(3);
        gradientImageAvater1.setGradientColor1(new java.awt.Color(255, 255, 102));
        gradientImageAvater1.setGradientColor2(new java.awt.Color(255, 255, 255));
        gradientImageAvater1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user photo.jpg"))); // NOI18N
        roundedPanel1.add(gradientImageAvater1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 80));

        jLabel2.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Daisy's Products Management");
        roundedPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 350, 60));

        getContentPane().add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        for(double i=0.0;i<=1.0;i+=0.1){
            String val = ""+i;
            float f = Float.valueOf(val);
            this.setOpacity(f);
            try{
                Thread.sleep(50);
            }catch(InterruptedException e){}   
        }
        
    }//GEN-LAST:event_formWindowOpened

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void showData() {

        thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {

                    Connection c = DriverManager.getConnection("jdbc:ucanaccess://"+dbURL);
                    Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet r = s.executeQuery("SELECT * FROM ProductTable");
        
                    r.last();
                    r.beforeFirst();
                    
                    while(r.next()){
                        
                        String id = r.getString("ID");
                        String name = r.getString("Name");
                        String type = r.getString("Type");
                        String price = r.getString("Price");
                        String store = r.getString("Stored");
                        String sold = r.getString("Sold");
                        String instock = r.getString("Instock");

                        String[]data = {id,name,type,price,store,sold,instock};
                        
                        DefaultTableModel m1 = (DefaultTableModel)tb1.getModel();
                        m1.addRow(data);
                        tb1.setModel(m1);   
                    }
                    
                    r.close();
                    s.close();
                    c.close();
                    
                }catch(SQLException se) { System.out.println("SQL : "+se); }
                
            }
            
        });
        thread.start();
        
    }
    
    private void show_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_btnActionPerformed

        showData( );
        
    }//GEN-LAST:event_show_btnActionPerformed

    private void clearScreen( ) {
        
        name_txt.setText("");
        type_txt.setText("");
        price_txt.setText("");
        stored_txt.setText("");
        sold_txt.setText("");
        instock_txt.setText("");
        
        tb1.setModel(new DefaultTableModel(null, table_col));
        polarAreaChart.clearArrayList();

    }
    
    private void tb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb1MouseClicked

        DefaultTableModel model = (DefaultTableModel)tb1.getModel();
        MyIndex = tb1.getSelectedRow();

        id = ""+model.getValueAt(MyIndex, 0);
        String name = "" + model.getValueAt(MyIndex, 1);
        String type = "" + model.getValueAt(MyIndex, 2);
        String price = "" + model.getValueAt(MyIndex, 3);
        String stored = "" + model.getValueAt(MyIndex, 4);
        String sold = "" + model.getValueAt(MyIndex, 5);
        String instock = "" + model.getValueAt(MyIndex, 6);

        chart_instock = instock;
        chart_stored = stored;
        chart_sold = sold;

        polarAreaChart.clearArrayList();
        polarAreaChart.addItem(new ModelPolarAreaChart(new Color(250,207,90),"Stored",Double.parseDouble(chart_stored)));
        polarAreaChart.addItem(new ModelPolarAreaChart(new Color(130,166,5),"Instock",Double.parseDouble(chart_instock)));
        polarAreaChart.addItem(new ModelPolarAreaChart(new Color(252,108,68),"Sold",Double.parseDouble(chart_sold)));
        polarAreaChart.start();
        
        name_txt.setText(name);
        type_txt.setText(type);
        price_txt.setText(price);
        stored_txt.setText(stored);
        sold_txt.setText(sold);
        instock_txt.setText(instock);
        
    }//GEN-LAST:event_tb1MouseClicked

    private void updateData( ) {
        
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
               
                try {
                    
                    Connection c = DriverManager.getConnection("jdbc:ucanaccess://"+dbURL);
                    Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet r = s.executeQuery("SELECT * FROM ProductTable");

                    r.last();
                    r.beforeFirst();
                    
                    while(r.next()) {
                    
                        String iid = r.getString("ID");
                        
                        if (iid.equals(id)) {

                    r.updateString("Name", name_txt.getText());
                    r.updateString("Type", type_txt.getText());
                    r.updateString("Price", price_txt.getText());
                    r.updateString("Stored", stored_txt.getText());
                    r.updateString("Sold", sold_txt.getText());
                    r.updateString("Instock", instock_txt.getText());
                    
                    r.updateRow();
                    break;
                        }
                        
                    }
                    
                    
                    JOptionPane.showMessageDialog(null, "Data Updated Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                    
                    clearScreen();
                    showData();
                    
                    r.close();
                    s.close();
                    c.close();
                    
                }catch(SQLException se) { System.out.println("SQL UPDATE : "+se); }
                
            }
            
        });
        
        thread.start();
       
    }
    
    private void deleteData( ) {
        
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                
                try {

                    Connection c = DriverManager.getConnection("jdbc:ucanaccess://"+dbURL);
                    Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet r = s.executeQuery("SELECT * FROM ProductTable");
                    
                    r.last();
                    r.beforeFirst();
            
                    while(r.next()){
                        
                        String iid = r.getString("ID");
                        
                        if (iid.equals(id)){

                            r.deleteRow();

                            JOptionPane.showMessageDialog(null, "Data Deleted Successfully","Delete",JOptionPane.INFORMATION_MESSAGE);
                            
                        }
                        
                    }
                    
                    clearScreen();
                    showData();
                    
                    r.close();
                    s.close();
                    c.close();
                    
                }catch(SQLException se) { System.out.println("SQL Delete : "+se); }
                
            }
            
        });
        thread.start();
    }
    
    private void insertData( ) {
        
        thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {
                    
                    Connection c = DriverManager.getConnection("jdbc:ucanaccess://"+dbURL);
                    Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                    ResultSet r = s.executeQuery("SELECT * FROM ProductTable");
                    
                    String name = name_txt.getText();
                    String type = type_txt.getText();
                    String price = price_txt.getText();
                    String stored = stored_txt.getText();
                    String sold = sold_txt.getText();
                    String instock = instock_txt.getText();
                    
                    if (name.equals("") || type.equals("") || price.equals("") || stored.equals("") ||
                        sold.equals("") || instock.equals("")) {
                        
                        JOptionPane.showMessageDialog(null, "Please Fill All The Informations","Try Again",JOptionPane.ERROR_MESSAGE);
                        
                    }
                                        
                    else {
                        
                        r.updateString("Name", name);
                        r.updateString("Type", type);
                        r.updateString("Price", price);
                        r.updateString("Stored", stored);
                        r.updateString("Sold", sold);
                        r.updateString("Instock", instock);
                        r.insertRow();
                        r.moveToInsertRow();
                        
                        JOptionPane.showMessageDialog(null,"Data Inserted Successfully!","Success",JOptionPane.PLAIN_MESSAGE);
                        
                    }
                    
                    clearScreen();
                    showData();
                    
                    r.close();
                    s.close();
                    c.close();
                    
                }catch(SQLException se) { System.out.println("SQL Iinsert : "+se); }
                
            }
            
        });

        thread.start();
        
    }
    
    private void update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnActionPerformed
        updateData();
    }//GEN-LAST:event_update_btnActionPerformed

    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed
        clearScreen();
    }//GEN-LAST:event_clear_btnActionPerformed

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
        deleteData();
    }//GEN-LAST:event_delete_btnActionPerformed

    private void insert_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insert_btnActionPerformed
        insertData();
    }//GEN-LAST:event_insert_btnActionPerformed

    private void menu_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_btnActionPerformed
        
        for(double i=1.0;i>=0.0;i-=0.1){

                String val = ""+i;
                float f = Float.valueOf(val);
                this.setOpacity(f);
                try{
                    Thread.sleep(50);
                }catch(InterruptedException e){}

            }
        
        this.dispose();
        
        LoginFrame obj = new LoginFrame();
        obj.setVisible(true);
        
        LoginFrame.signIn=true;
        LoginFrame.animatorLogin.start();
        
    }//GEN-LAST:event_menu_btnActionPerformed

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
            java.util.logging.Logger.getLogger(ProductsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ProductFrame.CustomButton clear_btn;
    private ProductFrame.CustomButton delete_btn;
    private ImageAvatar.GradientImageAvater gradientImageAvater1;
    private ProductFrame.CustomButton insert_btn;
    private ProductFrame.TextField instock_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private ProductFrame.CustomButton menu_btn;
    private ProductFrame.TextField name_txt;
    private chart.PolarAreaChart polarAreaChart;
    private chart.PolarAreaLabel polarAreaLabel1;
    private chart.PolarAreaLabel polarAreaLabel2;
    private chart.PolarAreaLabel polarAreaLabel3;
    private ProductFrame.TextField price_txt;
    private Main.RoundedPanel roundedPanel1;
    private ProductFrame.CustomButton show_btn;
    private ProductFrame.TextField sold_txt;
    private ProductFrame.TextField stored_txt;
    private javax.swing.JTable tb1;
    private ProductFrame.TextField type_txt;
    private ProductFrame.CustomButton update_btn;
    // End of variables declaration//GEN-END:variables
}
