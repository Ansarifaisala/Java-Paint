package assignment8;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Ansari Mohammed Faisal - 000812671
 */
public abstract class Structure {
    /**
     * X and Y co-ordinates
     * radius of circle
     * height and width of rectangle
     * x1 and y1 starting co-ordinates
     * x2 and y2 ending co-ordinates
     */
    private double x, y, radius, height, width, x1, x2, y1, y2;
    /**
     * checkValue to check difference
     */
    private int checkValue = 0;
    /**
     * troke width
     */
    private double stroke;
    /**
     * stroke Color
     * Fill Color
     */
    private Color colorFill, colorStroke;

    /**
     * @param checkValue sets checkValue
     */
    public Structure(int checkValue) {
        this.checkValue = checkValue;
    }

    /**
     * @return returns x1
     */
    public double getX1() {
        return x1;
    }

    /**
     * @param x1 sets x1
     */
    public void setX1(double x1) {
        this.x1 = x1;
    }

    /**
     * @return returns x2
     */
    public double getX2() {
        return x2;
    }

    /**
     * @param x2 sets x2
     */
    public void setX2(double x2) {
        this.x2 = x2;
    }

    /**
     * @return returns y1
     */
    public double getY1() {
        return y1;
    }

    /**
     * @param y1 sets y1
     */
    public void setY1(double y1) {
        this.y1 = y1;
    }

    /**
     * @return returns y2
     */
    public double getY2() {
        return y2;
    }

    /**
     * @param y2 sets y2
     */
    public void setY2(double y2) {
        this.y2 = y2;
    }


    /**
     * @return returns Stroke Color
     */
    public Color getColorStoke() {
        return colorStroke;
    }

    /**
     * @param strokeColor sets Stroke color
     */
    public void setColorStroke(Color strokeColor) {
        this.colorStroke = colorStroke;
    }

    /**
     * @return returns Fill Color
     */
    public Color getFillColor() {
        return colorFill;
    }

    /**
     * @param fillColor sets Fill Color
     */
    public void setFillColor(Color fillColor) {
        this.colorFill = fillColor;
    }

    /**
     * @return returns x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x sets x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return returns y
     */
    public double getY() {
        return y;
    }


    /**
     * @param y sets y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return returns radius
     */
    public double getRadius() {
        return radius;
    }


    /**
     * @param radius sets radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @return returns height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height sets height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return returns width
     */
    public double getWidth() {
        return width;
    }


    /**
     * @param width sets width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return returns stroke
     */
    public double getStroke() {
        return stroke;
    }

    /**
     * @param stroke sets stroke
     */
    public void setStroke(double stroke) {
        this.stroke = stroke;
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
    public abstract void setParameter(double x1, double y1, double x2, double y2, double stroke, GraphicsContext gc, Color strokeColor, Color fillColor);

    /**
     * @param gc graphic Context
     */
    public void draw(GraphicsContext gc) {
    }
}
