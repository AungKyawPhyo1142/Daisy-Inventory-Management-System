package RadialMenu;

import javax.swing.*;
import java.awt.*;

public class RadialItem extends JComponent {

    /**
     * @return the name
     */
    public String getMenuName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setMenuName(String name) {
        this.name = name;
    }

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
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    public RadialItem(Icon icon,Color color,String name){
        this.icon = icon;
        this.color = color;
        this.name = name;
        this.setToolTipText(name);
    }
    
    public RadialItem( ){
        
    }

    private Icon icon;
    private Color color;
    private String name;

    
}
