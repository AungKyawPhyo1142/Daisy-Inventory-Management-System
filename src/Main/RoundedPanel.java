package Main;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    
    public RoundedPanel( ) {
        
    }
    
    @Override
    public void paint(Graphics g){
        
        Graphics2D g2 = (Graphics2D)g;
        
        int width = getWidth();
        int height = getHeight();
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);        
        g2.fillRoundRect(0, 0, width, height, 25, 25);
        
        super.paintComponents(g2);
        
    }
    
}
