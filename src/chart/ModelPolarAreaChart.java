package chart;

import javax.swing.*;
import java.awt.*;

public class ModelPolarAreaChart {

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * @return the values
     */
    public double getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(double values) {
        this.values = values;
    }

private String name;
private Color color;
private double values;
    
    public ModelPolarAreaChart(Color color,String name,double values){

        this.color = color;
        this.name = name;
        this.values = values;

    }

    public ModelPolarAreaChart( ) {
        
    }

}
