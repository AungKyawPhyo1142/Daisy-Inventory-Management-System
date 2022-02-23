package ImageAvatar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;



public class GradientImageAvater extends JComponent {

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
        repaint();
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
        repaint();
    }

    /**
     * @return the borderSpace
     */
    public int getBorderSpace() {
        return borderSpace;
    }

    /**
     * @param borderSpace the borderSpace to set
     */
    public void setBorderSpace(int borderSpace) {
        this.borderSpace = borderSpace;
        repaint();
    }

    /**
     * @return the gradientColor1
     */
    public Color getGradientColor1() {
        return gradientColor1;
    }

    /**
     * @param gradientColor1 the gradientColor1 to set
     */
    public void setGradientColor1(Color gradientColor1) {
        this.gradientColor1 = gradientColor1;
        repaint();
    }

    /**
     * @return the gradientColor2
     */
    public Color getGradientColor2() {
        return gradientColor2;
    }

    /**
     * @param gradientColor2 the gradientColor2 to set
     */
    public void setGradientColor2(Color gradientColor2) {
        this.gradientColor2 = gradientColor2;
    }

    private Icon icon;
    private int borderSize=6;
    private int borderSpace=5;
    private Color gradientColor1 = new Color(167, 255, 131);
    private Color gradientColor2 = new Color(7, 26, 82);
    
    
    
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        createBorder(g2);
        
        if (getIcon()!=null) {
            
            int width = getWidth();
            int height = getHeight();
            int diameter = Math.min(width, height)-(borderSize*2+borderSpace*2);
            int x = (width/2)-(diameter/2);
            int y = (height/2)-(diameter/2);
                        
            Dimension size = getAutoSize(getIcon(),diameter);
            BufferedImage img = new BufferedImage(size.width,size.height,BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g2_img = img.createGraphics();
            g2_img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2_img.fillOval(0, 0, diameter, diameter);
            Composite composite = g2_img.getComposite();
            g2_img.setComposite(AlphaComposite.SrcIn);
            g2_img.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2_img.drawImage(toImage(getIcon()), 0,0, size.width,size.height,null);
            g2_img.setComposite(composite);
            g2_img.dispose();
            

            //create the background
            if (isOpaque()) {
                
                g2.setColor(getBackground());
                g2.fillOval(x, y, diameter, diameter);
                
            }
            
            
            g2.drawImage(img, x, y,null);
        } // icon
        super.paintComponent(g);
    }
    
    // create the gradient border
    private void createBorder(Graphics2D g2) {
        
            int width = getWidth();
            int height = getHeight();
            int diameter = Math.min(width, height)-(borderSize*2-borderSpace*2);
            int x = (width/2)-(diameter/2);
            int y = (height/2)-(diameter/2);
        
            Area area = new Area(new Ellipse2D.Double(x,y,diameter,diameter));
            int s = diameter-(borderSize*2);
            area.subtract(new Area(new Ellipse2D.Double(x+borderSize,y+borderSize,s,s)));
            
            g2.setPaint(new GradientPaint(0,0,gradientColor1,width,height,gradientColor2));
            g2.fill(area);
        
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
