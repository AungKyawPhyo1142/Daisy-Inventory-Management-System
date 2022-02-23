/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IncomeFrame;

import LogInFrame.LoginFrame;
import RadialMenu.RadialItem;
import chart.ModelChart;
import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class IncomeFrame extends javax.swing.JFrame {

    Thread thread;
    //C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\Daisy Inventory Management System\\
//    private final String dbURL = "src\\Database\\DaisyManagement.mdb";
    private final String dbURL = "D:\\Database\\DaisyManagement.mdb";
    private String[]Months;
    private double[]Income;
    
    public IncomeFrame() {
        initComponents();
        monthlyDataChart();
        dailyDataChart();
        showDailyData();
        showMonthlyData();
        showTextField();
    }

    private void showTextField( ) {
        
        thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {
                    
                    // getting the Month
                    Calendar cal = Calendar.getInstance();
                    String Month = ""+new SimpleDateFormat("MMM").format(cal.getTime());
                    
                    Connection c = DriverManager.getConnection("jdbc:ucanaccess://"+dbURL);
                    Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet r = s.executeQuery("SELECT * FROM MonthlyIncome WHERE months='"+Month+"'");

                    r.last();
                    r.beforeFirst();
                    
                    if (r.next()) {
                        monthlyIncome_txt.setText(""+r.getString("Income"));
                    }
                    
                    r.close();
                    s.close();
                    c.close();
                }catch(SQLException se) { System.out.println("TextField : "+se); }
                
            }
            
        });
        thread.start();
        
    }
    
    private void showMonthlyData( ) {
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                
                try{

                    Connection c = DriverManager.getConnection("jdbc:ucanaccess://"+dbURL);
                    Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet r = s.executeQuery("SELECT * FROM MonthlyIncome");

                    r.last();
                    r.beforeFirst();
                    
                    while(r.next()) {
                    
                    String month = r.getString("Months");
                    String income = r.getString("Income");
                    
                    String[]data = { month,income };
                    
                    DefaultTableModel m1 = (DefaultTableModel)monthlyTable.getModel();
                    m1.addRow(data);
                    monthlyTable.setModel(m1);   
                     
                       
                    }
                    
                    r.close();
                    s.close();
                    c.close();
                    
                }catch(SQLException se) { System.out.println("Monthly : "+se); }
                
            }
            
        });
        
        thread.start();
    }
    
    private void showDailyData( ){ 
        
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    
                    Connection c = DriverManager.getConnection("jdbc:ucanaccess://"+dbURL);
                    Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet r = s.executeQuery("SELECT * FROM DailyIncome");

                    r.last();
                    r.beforeFirst();
                    
                    while (r.next()) {
                    
                    String date = r.getString("Date");
                    String income = r.getString("Income");
                    
                    String[]data = { date,income };
                    
                    DefaultTableModel m1 = (DefaultTableModel)dailyTable.getModel();
                    m1.addRow(data);
                    dailyTable.setModel(m1);   
                     
                    }
                    
                    r.close();
                    s.close();
                    c.close();
                    
                }catch(SQLException se) { System.out.println("Daily : "+se); }
                
            }
            
        });
        thread.start();
        
    }
    
    private void dailyDataChart( ) {
        
        thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {

                    Connection c = DriverManager.getConnection("jdbc:ucanaccess://"+dbURL);
                    Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet r = s.executeQuery("SELECT * FROM DailyIncome");

                    r.last();
                    r.beforeFirst();
                    lineChart1.addLegend("DAILY INCOME",Color.GREEN,Color.yellow);
                    
                    while(r.next()) {
                        lineChart1.addData(new ModelChart(""+r.getString("Date"),new double[]{Double.parseDouble(r.getString("Income"))}));
                    }
                          lineChart1.start();
  
                    r.close();
                    s.close();
                    c.close();
                    
                }catch(SQLException se) { System.out.println("Data : "+se); }
                
                
            }
            
        });
        thread.start();
        
    }
    
    private void monthlyDataChart( ) {
        
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                  
                try {

                    Connection c = DriverManager.getConnection("jdbc:ucanaccess://"+dbURL);
                    Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet r = s.executeQuery("SELECT * FROM MonthlyIncome");
        
                    r.last();
                    r.beforeFirst();
                    lineChart.addLegend("MONTHLY INCOME",new Color(146, 61, 112),new Color(71, 46, 100));
                    
                    while(r.next()) {
                        lineChart.addData(new ModelChart(""+r.getString("Months"),new double[]{Double.parseDouble(r.getString("Income"))}));
                    }
                          lineChart.start();
  
                    r.close();
                    s.close();
                    c.close();
                    
                }catch(SQLException se) { System.out.println("Data : "+se); }
                
            }
            
        });
        thread.start();
            
    }
    
    public void init( ) {
        
        lineChart.addLegend("Income", Color.RED,Color.RED);
        lineChart.addData(new ModelChart("January",new double[]{4500}));
        lineChart.addData(new ModelChart("February",new double[]{2000}));
        lineChart.addData(new ModelChart("March",new double[]{15000}));
        
        lineChart.start();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel2 = new Main.RoundedPanel();
        roundedPanel1 = new Main.RoundedPanel();
        lineChart = new chart.LineChart();
        jLabel1 = new javax.swing.JLabel();
        roundedPanel3 = new Main.RoundedPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dailyTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        monthlyTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        roundedPanel4 = new Main.RoundedPanel();
        lineChart1 = new chart.LineChart();
        roundedPanel5 = new Main.RoundedPanel();
        jLabel4 = new javax.swing.JLabel();
        profit_txt = new ProductFrame.TextField();
        jLabel2 = new javax.swing.JLabel();
        monthlyIncome_txt = new ProductFrame.TextField();
        expenses_txt = new ProductFrame.TextField();
        customButton1 = new ProductFrame.CustomButton();
        jLabel6 = new javax.swing.JLabel();
        calculate_btn = new ProductFrame.CustomButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel2.setForeground(new java.awt.Color(157, 153, 255));
        roundedPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel1.setLayout(new java.awt.BorderLayout());
        roundedPanel1.add(lineChart, java.awt.BorderLayout.CENTER);

        roundedPanel2.add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 550, 300));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_35px.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        roundedPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 0, 40, 40));

        roundedPanel3.setForeground(new java.awt.Color(7, 30, 61));
        roundedPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dailyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Income"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dailyTable.setPreferredSize(new java.awt.Dimension(300, 500));
        jScrollPane1.setViewportView(dailyTable);

        roundedPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 200, 160));

        jLabel3.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DAILY INCOME");
        roundedPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 200, 30));

        monthlyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Month", "Income"
            }
        ));
        monthlyTable.setPreferredSize(new java.awt.Dimension(300, 300));
        jScrollPane3.setViewportView(monthlyTable);

        roundedPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 200, 160));

        jLabel5.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("MONTHLY INCOME");
        roundedPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 200, 30));

        roundedPanel2.add(roundedPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 670, 300));

        roundedPanel4.setForeground(new java.awt.Color(7, 30, 61));
        roundedPanel4.setLayout(new java.awt.BorderLayout());
        roundedPanel4.add(lineChart1, java.awt.BorderLayout.CENTER);

        roundedPanel2.add(roundedPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 820, 270));

        roundedPanel5.setForeground(new java.awt.Color(7, 30, 61));
        roundedPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Estimated Profits and Expenses");
        roundedPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 30));

        profit_txt.setEditable(false);
        profit_txt.setBackground(new java.awt.Color(7, 30, 61));
        profit_txt.setForeground(new java.awt.Color(157, 153, 255));
        profit_txt.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        profit_txt.setLabelText("Estimated Profits");
        roundedPanel5.add(profit_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 270, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help_15px.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        roundedPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 30, 30));

        monthlyIncome_txt.setEditable(false);
        monthlyIncome_txt.setBackground(new java.awt.Color(7, 30, 61));
        monthlyIncome_txt.setForeground(new java.awt.Color(157, 153, 255));
        monthlyIncome_txt.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        monthlyIncome_txt.setLabelText("Income of this month");
        roundedPanel5.add(monthlyIncome_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 130, -1));

        expenses_txt.setBackground(new java.awt.Color(7, 30, 61));
        expenses_txt.setForeground(new java.awt.Color(157, 153, 255));
        expenses_txt.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        expenses_txt.setLabelText("Enter Expenses");
        roundedPanel5.add(expenses_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 130, -1));

        customButton1.setBackground(new java.awt.Color(91, 89, 148));
        customButton1.setForeground(new java.awt.Color(255, 255, 255));
        customButton1.setText("MENU");
        customButton1.setFocusable(false);
        customButton1.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        customButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton1ActionPerformed(evt);
            }
        });
        roundedPanel5.add(customButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 120, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NicePng_daisy-flower-png_1377446 (1).png"))); // NOI18N
        roundedPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 90, 200));

        calculate_btn.setBackground(new java.awt.Color(91, 89, 148));
        calculate_btn.setForeground(new java.awt.Color(255, 255, 255));
        calculate_btn.setText("CALCULATE");
        calculate_btn.setFocusable(false);
        calculate_btn.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        calculate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculate_btnActionPerformed(evt);
            }
        });
        roundedPanel5.add(calculate_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 140, 40));

        roundedPanel2.add(roundedPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 320, 400, 270));

        getContentPane().add(roundedPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void calculate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculate_btnActionPerformed

        double MonthlyIncome = Double.parseDouble(monthlyIncome_txt.getText());
        double Expenses = Double.parseDouble(expenses_txt.getText());
        
        double percentage = (Expenses/MonthlyIncome)*100;

        double profit=0;
        
        if (percentage>85) {
           
            int res = JOptionPane.showConfirmDialog(null, "Your Expenses are excceeding 85% of your monthly income.","Reminder",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            
            if (res==0) {
                profit = MonthlyIncome - Expenses;
                profit_txt.setText(""+profit);
            } //yes
           
        }
        
        else {
            profit = MonthlyIncome - Expenses;
            profit_txt.setText(""+profit);
        }
                
        
    }//GEN-LAST:event_calculate_btnActionPerformed

    private void customButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton1ActionPerformed
            setVisible(false);
            this.dispose();

            LoginFrame obj = new LoginFrame();
            obj.setVisible(true);
        
            LoginFrame.signIn=true;
            LoginFrame.animatorLogin.start();

    }//GEN-LAST:event_customButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(IncomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IncomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IncomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IncomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IncomeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ProductFrame.CustomButton calculate_btn;
    private ProductFrame.CustomButton customButton1;
    private javax.swing.JTable dailyTable;
    private ProductFrame.TextField expenses_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private chart.LineChart lineChart;
    private chart.LineChart lineChart1;
    private ProductFrame.TextField monthlyIncome_txt;
    private javax.swing.JTable monthlyTable;
    private ProductFrame.TextField profit_txt;
    private Main.RoundedPanel roundedPanel1;
    private Main.RoundedPanel roundedPanel2;
    private Main.RoundedPanel roundedPanel3;
    private Main.RoundedPanel roundedPanel4;
    private Main.RoundedPanel roundedPanel5;
    // End of variables declaration//GEN-END:variables
}
