package chart;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class PolarAreaChart extends javax.swing.JComponent {

    private final ArrayList<ModelPolarAreaChart> list = new ArrayList<>();
    
    private double maxValues;
    private double totalValues;
    private final int PADDING_BOTTON=30;
    
    private final Animator animator;
    private float animate;
    
    public PolarAreaChart() {
        initComponents();
        this.setBackground(new Color(157,153,255));
        this.setForeground(new Color(157,153,255));
//          this.setBackground(Color.WHITE);
//          this.setForeground(Color.WHITE);

    TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction){
                animate = fraction;
                repaint();
            }
    };
    animator = new Animator(500,target);
    animator.setResolution(0);
    animator.setAcceleration(0.5f);
    animator.setDeceleration(0.5f);
    }

    @Override
    public void paint(Graphics g){
        if (this.isOpaque()){
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        createChart(g);
        super.paint(g);
    }
    
    public void createChart(Graphics g){
    
        int width = getWidth();
        int height = getHeight()-PADDING_BOTTON;
        int space=5;
        int size = Math.min(width, height)-space;
        
        int x = (width-size)/2;
        int y = (height-size)/2;
        
        if (width<=0){
            width=1;
        }
        if (height<=0){
            height=1;
        }
        
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (!list.isEmpty()){

            DecimalFormat df = new DecimalFormat("#,##0.##");
            
            double startAngle=90;
            for(ModelPolarAreaChart data:list){
                g2.setColor(data.getColor());
                double angle = valuesToAngle(data.getValues());
                double rs = valuesToRealSize(data.getValues(),size)*animate;
                Shape s =createShape(startAngle,angle,rs);
                g2.fill(s);
                
                g2.setComposite(AlphaComposite.Clear);
                g2.setStroke(new BasicStroke(3f));
                g2.draw(s);
                g2.setComposite(AlphaComposite.SrcOver);
                startAngle +=angle;
                drawValues(g2,df.format(data.getValues()),startAngle-angle/2,rs/4);
                
            }
            
        } 
        
        else {
            g2.setColor(new Color(157, 200, 255));  //200,200,200
            g2.drawOval(x, y, size, size);
            g2.drawString("CHART WILL BE DISPLAYED HERE", (size-100)/2, (size+15)/2);
        }
        g2.dispose();
        g.drawImage(img, 0, 0, null);
    }
    
    private Shape createShape(double start,double end,double values){
        
        int width = getWidth();
        int height = getHeight()-PADDING_BOTTON;
        double x = (width-values)/2;
        double y = (height-values)/2;
        Shape shape = new Arc2D.Double(x,y,values,values,start,end,Arc2D.PIE);
        return shape;
        
    }
    
    private Point getLocation(double angle,double rs){
        double x = Math.cos(Math.toRadians(angle))*rs;
        double y = Math.sin(Math.toRadians(angle))*rs;
        return new Point((int)x,(int)y);
    }
    
    private void drawValues(Graphics2D g2,String values,double angle,double rs){
        int centerX = getWidth()/2;
        int centerY = (getHeight()-PADDING_BOTTON)/2;
        Point p = getLocation(angle,rs);
        g2.setColor(getForeground());
        g2.setFont(getFont());
        FontMetrics ft = g2.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(values, g2);
        double x = (centerX+p.x)-(r2.getWidth()/2);
        double y = (centerY-p.y)+(ft.getAscent()/2);
        g2.drawString(values, (int)x, (int)y);
    }
    
    private double valuesToRealSize(double values,int size){
        double n = values*100/maxValues;
        return n*size/100;
    }
    
    private double valuesToAngle(double values){
        double n = values*100/totalValues;
        
        return n*360/100;
    }
    
    private void calculateValues(ModelPolarAreaChart data){
        maxValues = Math.max(maxValues, data.getValues());
        totalValues=0;
        
        for (ModelPolarAreaChart i:list){
            totalValues += i.getValues();
        }
        
    }
    
    public void addItem(ModelPolarAreaChart data){
        list.add(data);
        calculateValues(data);
        repaint();
        
        /*
        PolarAreaLabel label = new PolarAreaLabel();
        label.setText(data.getName());
        label.setBackground(data.getColor());
        panel.add(label);
        panel.repaint();
        panel.revalidate();*/
    }
    
    public void clearArrayList(){
        list.clear();
        repaint();
    }
    
    public void start(){
        if(!animator.isRunning()){
            animator.start();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
