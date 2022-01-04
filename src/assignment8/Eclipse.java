package assignment8;


import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;


/**
 * @author Ansari Mohammed Faisal - 000812671
 */
public class Eclipse extends Structure {

    /**
     *X and Y Co-ordinates
     * Radius of circle
     */
    private double x, y, radius;

    /**
     * @param checkValue Initialize the checkValue for Eclipse
     */
    Eclipse(int checkValue) {
        super(checkValue);
    }

    /**
     * @param x1    Starting Co-ordinates
     * @param y1    Starting Co-ordinates
     * @param x2    Ending Co-ordinates
     * @param y2    Ending Co-ordinates
     * @param stroke    stroke width
     * @param gc    Graphics Context
     * @param strokeColor   Stroke Color
     * @param fillColor     Fil Color
     */
    @Override
    public void setParameter(double x1, double y1, double x2, double y2, double stroke, GraphicsContext gc, Color strokeColor, Color fillColor) {

        setStroke(stroke);
        setX1(x1);
        setX2(x2);
        setY1(y1);
        setY2(y2);
        setFillColor(fillColor);
        setColorStroke(strokeColor);
        x = getX1() + (getX2() - getX1()) / 2;
        y = getY1() + (getY2() - getY1()) / 2;
        radius = Math.sqrt(((getX2() - getX1()) * (getX2() - getX1())) + ((getY2() - getY1()) * (getY2() - getY1())));  //Calculating Radius
        setRadius(radius / 2);
        setX(x);
        setY(y);
        draw(gc);


    }

    /**
     * @param gc Graphics Context
     */
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(getStroke());
        gc.setStroke(getColorStoke());
        gc.setFill(getFillColor());
        gc.strokeOval(getX() - getRadius(), getY() - getRadius(), getRadius() * 2, getRadius() * 2);
        gc.fillOval(getX() - getRadius(), getY() - getRadius(), getRadius() * 2, getRadius() * 2);
    }
}