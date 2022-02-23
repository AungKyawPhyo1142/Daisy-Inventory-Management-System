package TextField;

import javax.swing.*;
import java.awt.*;

public class RoundedTextField extends JTextField{

    public RoundedTextField( ) {
    
    }
    
    @Override
    public void paint(Graphics g) {
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();
        
        g2.fillRoundRect(0, 0, width, height, height, height);
        
    }

}
