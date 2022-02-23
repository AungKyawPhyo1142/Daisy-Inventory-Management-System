package Main;

import HelpFrame.HelpClass;
import LogInFrame.LoginFrame;

public class DaisyInventoryManagementSystem {

    public static void main(String[] args) {

//       new HelpClass().Help();

        SplashScreen obj = new SplashScreen();
                obj.setVisible(true);
                
        try {
            
            for (int i=0;i<=100;i++) {
                
                Thread.sleep(45);
                
                obj.progressBar.setValue(i);
                
            }
            
        }catch(Exception e) {}

        obj.setVisible(false);
        obj.dispose();


        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
 
                
                new LoginFrame().setVisible(true);
//                new ProductsFrame().setVisible(true);
//                  new SalesFrame().setVisible(true);
//                  new IncomeFrame().setVisible(true);
            }
        });
    }
    
}
