package assignment8;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * @author Ansari Mohammed Faisal - 000812671
 */
public class Rectangle extends Structure {
    /**
     * @param checkValue Initialize checkValue for rectangle
     */
    public Rectangle(int checkValue) {
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


        if (getX2() < getX1()) {
            setX(getX2());
        } else {
            setX(getX1());
        }
        if (getY2() < getY1()) {
            setY(getY2());
        } else {
            setY(getY1());
        }

        setHeight(Math.abs(getY2() - getY1()));
        setWidth(Math.abs(getX2() - getX1()));
        draw(gc);
    }


    /**
     * @param gc Graphic Context
     */
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(getStroke());
        gc.setStroke(getColorStoke());
        gc.setFill(getFillColor());
        gc.strokeRect(getX(), getY(), getWidth(), getHeight());
        gc.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}