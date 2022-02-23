package SalesFrame;

import LogInFrame.LoginFrame;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.Date;


public class SalesFrame extends javax.swing.JFrame {

    private Thread thread;
//    private final String dbURL = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\Daisy Inventory Management System\\src\\Database\\DaisyManagement.mdb";
    private final String dbURL = "D:\\Database\\DaisyManagement.mdb";
    private final String[]col = { "ID","Name","Type","Price","Stored","Instock" };

    private boolean INSERTED=false;
    
    private int INCOME=0;
    private int PAYMENT=0;
    private int CHANGE=0;
    
    private String id="";
    private int INSTOCK=0;
    private int SELL_QTY=0;
    private int STORED=0;
    private int TOTAL=0;
    private int PRICE=0;
    private int SOLD=0;
    
    private String currentDate="";
    private String yesterdayDate="";
    
    public static int count=1;
    
    public SalesFrame() {
        
        initComponents();
        ConnectDriver();
        showData();
        
    }
        
    private void moveDailyIncomeToMonthlyIncome(){
        
        thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {

                    // getting the Month
                    Calendar cal = Calendar.getInstance();
                    String Month = ""+new SimpleDateFormat("MMM").format(cal.getTime());

                    Connection c = DriverManager.getConnection("jdbc:ucanaccess://"+dbURL);
                    Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet incomeTable = s.executeQuery("SELECT * FROM DailyIncome");
                    ResultSet monthlyTable = s.executeQuery("SELECT * FROM MonthlyIncome WHERE months = '"+Month+"'");
                    
                    incomeTable.last();
                    incomeTable.beforeFirst();

                    monthlyTable.last();
                    monthlyTable.first();
                    
                    int monthly_income=0;

                    while (incomeTable.next()) {
                        monthly_income += Integer.parseInt(""+incomeTable.getString("Income"));
//                        incomeTable.deleteRow();
                    }

                    if (monthlyTable.next()) {

                        JOptionPane.showMessageDialog(null,"Data Found","Updated",JOptionPane.INFORMATION_MESSAGE);
                        
                        monthlyTable.updateString("Income", ""+monthly_income);
                        monthlyTable.updateRow();

                        JOptionPane.showMessageDialog(null,"Monthly Income Updated","Updated",JOptionPane.INFORMATION_MESSAGE);

                    } // if there is a row already for this month, just update the income
                    
                    else {

                    JOptionPane.showMessageDialog(null,"Data Not Found. Inserted","Updated",JOptionPane.INFORMATION_MESSAGE);

                    monthlyTable.updateString("Months", Month);
                    monthlyTable.updateString("Income", ""+monthly_income);
                    monthlyTable.insertRow();
                    monthlyTable.moveToInsertRow();

                    JOptionPane.showMessageDialog(null,"Monthly Income Inserted","Inserted",JOptionPane.INFORMATION_MESSAGE);

                    }
                    
                    monthlyTable.close();
                    incomeTable.close();
                    s.close();
                    c.close();
                    
                }catch(SQLException se) { }
                
                
            }
            
        });
        thread.start();
        
    }
    
    private boolean isLastDayOfMonth() {
        
        Calendar cal = Calendar.getInstance();
        int lastDay = cal.getActualMaximum(Calendar.DATE);
//      System.out.println("Today's Date = " + cal.getTime());
//      System.out.println("Last Date of the current month = " + res);
  
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");  
        LocalDateTime now = LocalDateTime.now();  
        
        String date = ""+dtf.format(now);

        if (date.equals(""+lastDay)) {
            return true;
        }
        
        return false;
    }
    
    private void ConnectDriver( ){
       try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
       } catch (java.lang.ClassNotFoundException e) { }

    }
    
    private void showData( ) {
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
                        String instock = r.getString("Instock");

                        String[]data = {id,name,type,price,store,instock};
                        
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel1 = new Main.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb1 = new javax.swing.JTable();
        total_txt = new ProductFrame.TextField();
        name_txt = new ProductFrame.TextField();
        price_txt = new ProductFrame.TextField();
        qty_txt = new ProductFrame.TextField();
        sell_btn = new ProductFrame.CustomButton();
        change_txt = new ProductFrame.TextField();
        clear_btn = new ProductFrame.CustomButton();
        menu_btn = new ProductFrame.CustomButton();
        saveIncomes_btn = new ProductFrame.CustomButton();
        show_btn = new ProductFrame.CustomButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
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
        roundedPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 40, 40));

        tb1.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        tb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Type", "Price", "Stored", "Instock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        roundedPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 620, 400));

        total_txt.setEditable(false);
        total_txt.setBackground(new java.awt.Color(157, 153, 255));
        total_txt.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        total_txt.setLabelText("TOTAL (Kyats)");
        roundedPanel1.add(total_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 140, -1));

        name_txt.setEditable(false);
        name_txt.setBackground(new java.awt.Color(157, 153, 255));
        name_txt.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        name_txt.setLabelText("NAME");
        roundedPanel1.add(name_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 310, -1));

        price_txt.setEditable(false);
        price_txt.setBackground(new java.awt.Color(157, 153, 255));
        price_txt.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        price_txt.setLabelText("PRICE");
        roundedPanel1.add(price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 310, -1));

        qty_txt.setBackground(new java.awt.Color(157, 153, 255));
        qty_txt.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        qty_txt.setLabelText("QUANTITY");
        roundedPanel1.add(qty_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 310, -1));

        sell_btn.setBackground(new java.awt.Color(91, 89, 148));
        sell_btn.setForeground(new java.awt.Color(255, 255, 255));
        sell_btn.setText("SELL");
        sell_btn.setFocusable(false);
        sell_btn.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        sell_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sell_btnActionPerformed(evt);
            }
        });
        roundedPanel1.add(sell_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 90, 40));

        change_txt.setEditable(false);
        change_txt.setBackground(new java.awt.Color(157, 153, 255));
        change_txt.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        change_txt.setLabelText("CHANGE (Kyats)");
        roundedPanel1.add(change_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 140, -1));

        clear_btn.setBackground(new java.awt.Color(91, 89, 148));
        clear_btn.setForeground(new java.awt.Color(255, 255, 255));
        clear_btn.setText("CLEAR");
        clear_btn.setFocusable(false);
        clear_btn.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });
        roundedPanel1.add(clear_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, 90, 40));

        menu_btn.setBackground(new java.awt.Color(91, 89, 148));
        menu_btn.setForeground(new java.awt.Color(255, 255, 255));
        menu_btn.setText("MENU");
        menu_btn.setFocusable(false);
        menu_btn.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        menu_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_btnActionPerformed(evt);
            }
        });
        roundedPanel1.add(menu_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, 150, 40));

        saveIncomes_btn.setBackground(new java.awt.Color(91, 89, 148));
        saveIncomes_btn.setForeground(new java.awt.Color(255, 255, 255));
        saveIncomes_btn.setText("SAVE INCOMES");
        saveIncomes_btn.setFocusable(false);
        saveIncomes_btn.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        saveIncomes_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveIncomes_btnActionPerformed(evt);
            }
        });
        roundedPanel1.add(saveIncomes_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 500, 150, 40));

        show_btn.setBackground(new java.awt.Color(91, 89, 148));
        show_btn.setForeground(new java.awt.Color(255, 255, 255));
        show_btn.setText("SHOW");
        show_btn.setFocusable(false);
        show_btn.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        show_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_btnActionPerformed(evt);
            }
        });
        roundedPanel1.add(show_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, 140, 40));

        getContentPane().add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
//        int res = JOptionPane.showConfirmDialog(null, "Have you save your today incomes?\nPlease save them if you haven't save.","Confirmation",JOptionPane.YES_NO_OPTION);
        
        if (INSERTED) { 
            
            if (isLastDayOfMonth()){
                moveDailyIncomeToMonthlyIncome();
                System.out.println("Moved");
//              JOptionPane.showMessageDialog(null,"This is last Day of this Month");
            }
        
            else {
                System.out.println("Not Move");
//              JOptionPane.showMessageDialog(null,"This is not the last day of this month");
            }
        
            System.exit(0);
        }
        
        else {
            JOptionPane.showMessageDialog(null,"PLEASE SAVE YOUR TODAY INCOME.\nYOU HAVEN'T SAVE IT YET.","SAVE YOUR INCOME",JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jLabel1MouseClicked

    private void tb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb1MouseClicked

        DefaultTableModel model = (DefaultTableModel)tb1.getModel();
        int MyIndex = tb1.getSelectedRow();

        id = ""+model.getValueAt(MyIndex,0);
        String name = "" + model.getValueAt(MyIndex, 1);
        String price = "" + model.getValueAt(MyIndex, 3);
        INSTOCK = Integer.parseInt(""+model.getValueAt(MyIndex,5));
        STORED = Integer.parseInt(""+model.getValueAt(MyIndex,4));
        PRICE = Integer.parseInt(price);
        
        name_txt.setText(name);
        price_txt.setText(price);
        
        
    }//GEN-LAST:event_tb1MouseClicked

        private void clearScreen( ) {
        
        name_txt.setText("");
        price_txt.setText("");
        total_txt.setText("");
        qty_txt.setText("");
        clearTable();
        
    }

    private void clearTable( ) {

        tb1.setModel(new DefaultTableModel(null, col));
        
    }
    
    private void sell_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sell_btnActionPerformed

    if (qty_txt.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"PLEASE ENTER A QUANTITY","MISSING QUANTITY",JOptionPane.WARNING_MESSAGE);
            qty_txt.grabFocus();
    }
        
    else {
        
        SELL_QTY = Integer.parseInt(""+qty_txt.getText());

        if (SELL_QTY > INSTOCK) {
            JOptionPane.showMessageDialog(null, "There is no instock left for this product","INSUFFICENT INSTOCK",JOptionPane.ERROR_MESSAGE);
        }   // if there is no instock left or the BUYING Qty is more than INSTOCK
        
        else {
            
            INSTOCK = INSTOCK - SELL_QTY;
            TOTAL = SELL_QTY*PRICE;
            total_txt.setText(""+TOTAL);

            String s = JOptionPane.showInputDialog(null,"PLEASE ENTER PAYMENT\nTOTAL COST : "+TOTAL,"PAYMENT",JOptionPane.INFORMATION_MESSAGE);
            PAYMENT = Integer.parseInt(s);

            while (PAYMENT<TOTAL) {

            JOptionPane.showMessageDialog(null,"PAYMENT MONEY IS NOT ENOUGH.\nPAY AGAIN","NOT ENOUGH",JOptionPane.ERROR_MESSAGE);
            s = JOptionPane.showInputDialog(null,"PLEASE ENTER PAYMENT\nTOTAL COST : "+TOTAL,"PAYMENT",JOptionPane.INFORMATION_MESSAGE);
            PAYMENT = Integer.parseInt(s);
                
            }
            
            if (PAYMENT>=TOTAL) {

                CHANGE = PAYMENT - TOTAL;
                change_txt.setText(""+CHANGE);
                INCOME+=TOTAL;

                clearTable();
                clearScreen();
                updateData();
                
                TOTAL=0;
                CHANGE=0;
                PAYMENT=0;
                
                JOptionPane.showMessageDialog(null,"SOLD");
                System.out.println("INCOME : "+INCOME);
                INSERTED=false;
            
            }
            

        }
    }   // first else
    
    }//GEN-LAST:event_sell_btnActionPerformed

    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed

        clearScreen();
        clearTable();
        change_txt.setText("");
        
    }//GEN-LAST:event_clear_btnActionPerformed

    private void menu_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_btnActionPerformed

//        int res = JOptionPane.showConfirmDialog(null, "Have you save your today incomes?\nPlease save them if you haven't save.","Confirmation",JOptionPane.YES_NO_OPTION);
        
        if (INSERTED) { 

            if (isLastDayOfMonth()){
                moveDailyIncomeToMonthlyIncome();
                System.out.println("Moved");
                JOptionPane.showMessageDialog(null,"Today is last day of this Month.\nAll the Daily Incomes are moved to Monthly Incomes","Alert",JOptionPane.INFORMATION_MESSAGE);
            }
        
            setVisible(false);
            this.dispose();

            LoginFrame obj = new LoginFrame();
            obj.setVisible(true);
        
            LoginFrame.signIn=true;
            LoginFrame.animatorLogin.start();
        }
        
        else {
            JOptionPane.showMessageDialog(null,"Please Insert Your Today Income.\nYou haven't save it yet.","SAVE YOUR INCOME",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_menu_btnActionPerformed

    private void saveIncomes_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveIncomes_btnActionPerformed

            insertINCOME();
            INSERTED=true;
//            INCOME = 0; // set the income to 0 after inserting it to the TodayTable

    }//GEN-LAST:event_saveIncomes_btnActionPerformed

    private void show_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_btnActionPerformed
        showData();
    }//GEN-LAST:event_show_btnActionPerformed

    private void insertINCOME(){
        
        thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
                    LocalDateTime now = LocalDateTime.now();  
                    String todayDate = ""+dtf.format(now);
                    
                    Connection c = DriverManager.getConnection("jdbc:ucanaccess://"+dbURL);
                    Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet r = s.executeQuery("SELECT * FROM DailyIncome WHERE date='"+todayDate+"'");
                    
                    System.out.println("Date : "+todayDate);

                    if (r.next()) { 

                        int db_income = Integer.parseInt(r.getString("Income"));
                        db_income += INCOME;
                        r.updateString("Income", ""+db_income);
                            r.updateRow();
                            JOptionPane.showMessageDialog(null,"Income Updated");
                            INCOME=0;
                    
                    } // if the record of this day is already exists, update the existing record
                    
                    else { 

                        r.updateString("Date",todayDate);
                        r.updateString("Income",""+INCOME);
                        r.insertRow();
                        r.moveToInsertRow();
                        
                        JOptionPane.showMessageDialog(null,"Income Inserted");
                        INCOME=0;
                    } // if the record of this day is not exists, insert a new one
                    
                    r.close();
                    s.close();
                    c.close();
                    
                }catch(SQLException se) { System.out.println("SQL INCOME :"+se); }
                
            }
            
        });
        
        thread.start();
        System.out.println("Completed");
    }
    
    private void updateData( ){

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

                            SOLD = Integer.parseInt(r.getString("Sold"));
                            SOLD += SELL_QTY;

                            r.updateString("Instock",""+INSTOCK);
                            r.updateString("Sold",""+SOLD);
                            
                    r.updateRow();
                    break;
                        }
                        
                    }

                    r.close();
                    s.close();
                    c.close();
                    
                }catch(SQLException se) { System.out.println("SQL UPDATE : "+se); }
                
            }
            
        });
        
        thread.start();

    }
    
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
            java.util.logging.Logger.getLogger(SalesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SalesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ProductFrame.TextField change_txt;
    private ProductFrame.CustomButton clear_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private ProductFrame.CustomButton menu_btn;
    private ProductFrame.TextField name_txt;
    private ProductFrame.TextField price_txt;
    private ProductFrame.TextField qty_txt;
    private Main.RoundedPanel roundedPanel1;
    private ProductFrame.CustomButton saveIncomes_btn;
    private ProductFrame.CustomButton sell_btn;
    private ProductFrame.CustomButton show_btn;
    private javax.swing.JTable tb1;
    private ProductFrame.TextField total_txt;
    // End of variables declaration//GEN-END:variables
}
