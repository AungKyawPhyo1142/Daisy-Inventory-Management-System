package ImageAvatar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageAvatar extends JComponent{

    /**
     * @return the icon
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * @return the borderSize
     */
    public int getBorderSize() {
        return borderSize;
    }

    /**
     * @param borderSize the borderSize to set
     */
    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }
    
    private Icon icon;
    private int borderSize;
    
    @Override
    public void paintComponent(Graphics g) {
        
        if (icon!=null) {
            
            int width = getWidth();
            int height = getHeight();
            int diameter = Math.min(width, height);
            int x = (width/2)-(diameter/2);
            int y = (height/2)-(diameter/2);
            
            int border = borderSize*2;
            diameter -= border;
            
            Dimension size = getAutoSize(icon,diameter);
            BufferedImage img = new BufferedImage(size.width,size.height,BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g2_img = img.createGraphics();
            g2_img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2_img.fillOval(0, 0, diameter, diameter);
            Composite composite = g2_img.getComposite();
            g2_img.setComposite(AlphaComposite.SrcIn);
            g2_img.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2_img.drawImage(toImage(icon), 0,0, size.width,size.height,null);
            g2_img.setComposite(composite);
            g2_img.dispose();
            
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // create the border around image
            if (borderSize>0) {
                diameter+=border;
                g2.setColor(getForeground());
                g2.fillOval(x, y, diameter, diameter);
            }
            
            //create the background
            if (isOpaque()) {
                
                g2.setColor(getBackground());
                diameter-=border;
                g2.fillOval(x+borderSize, y+borderSize, diameter, diameter);
                
            }
            
            
            g2.drawImage(img, x+borderSize, y+borderSize,null);
        } // icon
        super.paintComponent(g);
    }
    
    // this method convert the Icon to Image
    private Image toImage(Icon icon) {
        return ((ImageIcon)icon).getImage();
    }
    
    // to autoSize the Image
    private Dimension getAutoSize(Icon image,int size){
        int w = size;
        int h = size;

        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        
        double xScale = (double) w/iw;
        double yScale = (double) h/ih;
        double scale = Math.max(xScale, yScale);
        
        int width = (int) (scale*iw);
        int height = (int) (scale*ih);
        
        if (width<1) { width=1; }
        if (height<1) { height=1; }
        
        return new Dimension(width,height);
        
    }
    
}
