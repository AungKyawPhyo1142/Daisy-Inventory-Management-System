/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogInFrame;

import AboutFrame.AboutFrame;
import IncomeFrame.IncomeFrame;
import ProductFrame.ProductsFrame;
import RadialMenu.EventRadialMenu;
import RadialMenu.RadialItem;
import SalesFrame.SalesFrame;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class LoginFrame extends javax.swing.JFrame {

    public static Animator animatorLogin;
    public static Animator animatorBody;
   
    public static boolean signIn;
    
    
    // Icons for Radial Menu Items
    private final String IncomeURL  = "/images/safe_50px.png";
    private final String ProductURL = "/images/products_50px.png";
    private final String SignOutURL = "/images/sign_out_50px.png";
    private final String ShutDownURL = "/images/shutdown_50px.png";
    private final String HelpURL = "/images/question_mark_50px.png";
    private final String AboutURL = "/images/about_50px.png";
    private final String SaleURL = "/images/sales_performance_50px.png";
    
    
    public LoginFrame() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        
        TimingTarget targetLogin = new TimingTargetAdapter(){

            @Override
            public void timingEvent(float fraction) {

                if(signIn){
                   background1.setAnimate(fraction);
                } else {
                   background1.setAnimate(1f-fraction);
                }
                
            }
            
            
            @Override
            public void end(){
                
            if(signIn){
                panelLogin.setVisible(false);
                background1.setShowPaint(true);
                panelBody.setAlpha(0);
                panelBody.setVisible(true);
                animatorBody.start();
            }else{
                user_txt.grabFocus();
            }
                
            }
        };

        TimingTarget targetBody = new TimingTargetAdapter(){

            @Override
            public void timingEvent(float fraction) {

                if(signIn){
                    panelBody.setAlpha(fraction);
                } else {
                    panelBody.setAlpha(1f-fraction);                    
                }
                
            }
            
            
            @Override
            public void end(){
                
                if(signIn==false){
                    panelBody.setVisible(false);
                    background1.setShowPaint(false);
                    background1.setAnimate(1);
                    panelLogin.setVisible(true);
                    animatorLogin.start();
                }
                
            }
        };

        
        animatorLogin = new Animator(800,targetLogin);
        animatorLogin.setResolution(0);

        animatorBody = new Animator(800,targetBody);
        animatorBody.setResolution(0);
        
        
        radialMenu.addItem(new RadialItem(new ImageIcon(getClass().getResource(IncomeURL)),new Color(255,255,255,0),"Income"));
        radialMenu.addItem(new RadialItem(new ImageIcon(getClass().getResource(ProductURL)),new Color(255,255,255,0),"Product Managment"));
        radialMenu.addItem(new RadialItem(new ImageIcon(getClass().getResource(ShutDownURL)),new Color(255,255,255,0),"Exit"));    
        radialMenu.addItem(new RadialItem(new ImageIcon(getClass().getResource(SignOutURL)),new Color(255,255,255,0),"Sign Out"));    
        radialMenu.addItem(new RadialItem(new ImageIcon(getClass().getResource(HelpURL)),new Color(255,255,255,0),"Help"));    
        radialMenu.addItem(new RadialItem(new ImageIcon(getClass().getResource(AboutURL)),new Color(255,255,255,0),"About"));    
        radialMenu.addItem(new RadialItem(new ImageIcon(getClass().getResource(SaleURL)),new Color(255,255,255,0),"Sale"));
        
        radialMenu.addEvent(new EventRadialMenu(){
            @Override 
            public void menuSelected(int index){
                
                if(index==0){ 

                    setVisible(false);
                    dispose();
                    new IncomeFrame().setVisible(true);


                }
                
                else if (index==1) {

                    //JOptionPane.showMessageDialog(null, "Product Frame");
                    setVisible(false);
                    dispose();
                    new ProductsFrame().setVisible(true);
                    
                }

                else if (index==2) {

                    int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    
                    if(res==0) { System.exit(0); }

                }
                
                else if(index==3) {

                    int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (res==0) {
                    signIn=false;
                    user_txt.setText("");
                    password_txt.setText("");
                    user_txt.setHelperText("");
                    password_txt.setHelperText("");
                    animatorBody.start();
                    }
                }
                
                else if(index==4) {
                    
                    JOptionPane.showMessageDialog(null, "Help Frame");
                    
                } // help
                
                else if (index==5) {

                    new AboutFrame().setVisible(true);
                    
                }//about
                
                else {

                    setVisible(false);
                    dispose();
                    new SalesFrame().setVisible(true);
                    
                }//sale
            }
        });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new LogInFrame.Background();
        panelLogin = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        gradientImageAvater2 = new ImageAvatar.GradientImageAvater();
        user_txt = new TextField.TextField();
        password_txt = new PasswordField.PasswordField();
        customButton1 = new LogInFrame.CustomButton();
        jLabel1 = new javax.swing.JLabel();
        panelBody = new LogInFrame.PanelTransparent();
        radialMenu = new RadialMenu.RadialMenu();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        loginHeader2 = new LogInFrame.LoginHeader();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLogin.setOpaque(false);
        panelLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel.setOpaque(false);
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gradientImageAvater2.setGradientColor2(new java.awt.Color(170, 178, 247));
        gradientImageAvater2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user photo.jpg"))); // NOI18N
        panel.add(gradientImageAvater2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 70));

        user_txt.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        user_txt.setLabelText("Username");
        user_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_txtActionPerformed(evt);
            }
        });
        panel.add(user_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 220, -1));

        password_txt.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        password_txt.setLabelText("Password");
        panel.add(password_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 220, -1));

        customButton1.setBackground(new java.awt.Color(157, 153, 255));
        customButton1.setForeground(new java.awt.Color(255, 255, 255));
        customButton1.setText("Sign In");
        customButton1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        customButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton1ActionPerformed(evt);
            }
        });
        panel.add(customButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 220, 40));

        panelLogin.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 220, 340));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_30px.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        panelLogin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 30, 30));

        background1.add(panelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 450));

        panelBody.setBackground(new java.awt.Color(255, 255, 255));
        panelBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelBody.add(radialMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 350, 280));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NicePng_daisy-flower-png_1377446 (1).png"))); // NOI18N
        panelBody.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 160, 210));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NicePng_daisy-flower-png_1377446 (1).png"))); // NOI18N
        panelBody.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 160, 210));
        panelBody.add(loginHeader2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, -1));

        background1.add(panelBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 450));
        panelBody.setVisible(false);

        getContentPane().add(background1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 450));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        
        if (!animatorLogin.isRunning()){
            
            String user = user_txt.getText();
            String password = new String(password_txt.getPassword());
            boolean action = true;
            
            if(user.equals("")){
                user_txt.setHelperText("Please Enter Username");
                user_txt.grabFocus();
                action=false;
            }
            
            if(password.equals("")){
                if (action){
                    password_txt.setHelperText("Please Enter Password");
                    password_txt.grabFocus();
                }
                action=false;
            }
            
            if (action){
                
                if (user.equals("Daisy") && password.equals("12345")) {
                    
                    signIn=true;
                    animatorLogin.start();
                
                }
                
                else {
                JOptionPane.showMessageDialog(null, "Wrong Username or Password", "Please Try Again", JOptionPane.ERROR_MESSAGE);
                user_txt.setText("");
                password_txt.setText("");
                user_txt.grabFocus();
                }   

            }
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void user_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_txtActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        for(double i=1.0;i>=0.0;i-=0.1){

                String val = ""+i;
                float f = Float.valueOf(val);
                this.setOpacity(f);
                try{
                    Thread.sleep(50);
                }catch(InterruptedException e){}

            }
        System.exit(0);
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void customButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton1ActionPerformed

                if (!animatorLogin.isRunning()){
            
            String user = user_txt.getText();
            String password = new String(password_txt.getPassword());
            boolean action = true;
            
            if(user.equals("")){
                user_txt.setHelperText("Please Enter Username");
                user_txt.grabFocus();
                action=false;
            }
            
            if(password.equals("")){
                if (action){
                    password_txt.setHelperText("Please Enter Password");
                    password_txt.grabFocus();
                }
                action=false;
            }
            
            if (action){
                
                if (user.equals("Daisy") && password.equals("12345")) {
                    
                    signIn=true;
                    animatorLogin.start();
                
                }
                
                else {
                JOptionPane.showMessageDialog(null, "Wrong Username or Password", "Please Try Again", JOptionPane.ERROR_MESSAGE);
                user_txt.setText("");
                password_txt.setText("");
                user_txt.grabFocus();
                }   

            }
        }
        
    }//GEN-LAST:event_customButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

       
        
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LogInFrame.Background background1;
    private LogInFrame.CustomButton customButton1;
    private ImageAvatar.GradientImageAvater gradientImageAvater2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private LogInFrame.LoginHeader loginHeader2;
    private javax.swing.JPanel panel;
    private LogInFrame.PanelTransparent panelBody;
    private javax.swing.JPanel panelLogin;
    private PasswordField.PasswordField password_txt;
    private RadialMenu.RadialMenu radialMenu;
    private TextField.TextField user_txt;
    // End of variables declaration//GEN-END:variables
}
