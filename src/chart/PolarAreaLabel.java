package chart;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class PolarAreaLabel extends JLabel {
    
    public PolarAreaLabel(){
     
        this.setBorder(new EmptyBorder(3,25,3,3));
        this.setFont(getFont().deriveFont(0, 13));
        setForeground(new Color(100,100,100));
    }
    
    @Override
    public void paint(Graphics g){
        
        super.paint(g);
        
        int size = getHeight()-10;
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int y = (getHeight()-size)/2;
        g2.setColor(getBackground());
        g2.fillOval(3, y, size, size);
        g2.dispose();
        
    }
    
}
