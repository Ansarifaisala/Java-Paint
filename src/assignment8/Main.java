package assignment8;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.stage.Stage;


import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

import static javafx.scene.control.Alert.*;


/**
 *  My program uses ArrayList to store the shapes and when ever user draw new shape it adds that shape into ArrayList and redraw it
 *  When user clicks undo it removes the shape from the ArrayList and redraws whole ArrayList
 *  P.S Even tho i  used Eclipse i wasn't able to implement it properly so i went with drawing circle
 * @author Ansari Mohammed Faisal - 000812671
 */
public class Main extends Application {

    /**
     *Boolean value to check if button is pressed
     */
    private Boolean primaryCheck = false;
    /**
     *Value to to check difference between Eclipse and Rectangle
     */
    private int checkValue = 0;
    /**
     *Variable used to get Screen Size
     */
    final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     *Object for Structure class
     */
    private Structure item;
    /**
     *ArrayList which Holds all the shapes
     */
    private static ArrayList<Structure> shapes = new ArrayList<>();
    /**
     *All buttons
     */
    private Button oval, rectangle, clr, undo;
    /**
     *Labels
     */
    private Label outline, col, strokeWidth, instructions, step1, step2, step3, step4;
    /**
     *TextField to get Stroke Width
     */
    private TextField width;
    /**
     *Color picker for Outline and Fill
     */
    private ColorPicker colorPicker, colorStroke;
    /**
     *Graphics Context
     */
    private GraphicsContext gc;
    /**
     *Canvas
     */
    private Canvas canvas;
    /**
     *X and Y Co-ordinates for shapes
     * Stroke is width of stroke
     */
    private double x1, x2, y1, y2, stroke;

    /**
     * @param root Pane
     *  Thi Function creates All GUI Components
     *  P.S When i tried adding all components in single addAll they wouldn't appear
     */
    private void createGUIComponents(Pane root) {

        width = new TextField("1");
        outline = new Label("Outline");
        col = new Label("Color Fill");
        instructions = new Label("**INSTRUCTIONS**");
        step1 = new Label("SELECT SHAPE AND COLOR OF YOUR CHOICE! Eclipse is Default Shape");
        step2 = new Label("CLICK AND DRAG TO DRAW!");
        step3 = new Label("PRESS UNDO TO REMOVE LAST CREATED SHAPE!");
        step4 = new Label("USE CLEAR BUTTON TO CLEAR THE SCREEN!");
        strokeWidth = new Label("Stroke Width");
        oval = new Button("Eclipse");
        rectangle = new Button("Rectangle");
        clr = new Button("Clear Screen");
        undo = new Button("Undo");
        colorPicker = new ColorPicker();
        colorStroke = new ColorPicker();
        root.getChildren().add(canvas);
        root.getChildren().addAll(oval, rectangle, clr, undo);
        root.getChildren().addAll(outline, col, strokeWidth, instructions, step1, step2, step3, step4);
        root.getChildren().add(width);
        root.getChildren().addAll(colorPicker, colorStroke);
    }

    /**
     *This function configures all GUI components
     */
    private void configureGUIComponents() {
        width.setMaxWidth(60);

        outline.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.06), (SCREEN_SIZE.getHeight() * 0.78));
        col.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.16), (SCREEN_SIZE.getHeight() * 0.78));
        strokeWidth.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.26), (SCREEN_SIZE.getHeight() * 0.78));
        width.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.26), (SCREEN_SIZE.getHeight() * 0.8));
        instructions.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.55), (SCREEN_SIZE.getHeight() * 0.72));
        step1.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.55), (SCREEN_SIZE.getHeight() * 0.74));
        step2.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.55), (SCREEN_SIZE.getHeight() * 0.76));
        step3.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.55), (SCREEN_SIZE.getHeight() * 0.78));
        step4.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.55), (SCREEN_SIZE.getHeight() * 0.8));

        oval.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.05), (SCREEN_SIZE.getHeight() * 0.72));
        rectangle.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.15), (SCREEN_SIZE.getHeight() * 0.72));
        clr.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.25), (SCREEN_SIZE.getHeight() * 0.72));
        undo.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.35), (SCREEN_SIZE.getHeight() * 0.72));


        colorPicker.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.16), (SCREEN_SIZE.getHeight() * 0.8));
        colorPicker.setMaxWidth(100);

        colorStroke.relocate(((SCREEN_SIZE.getWidth() * 0.8) * 0.06), (SCREEN_SIZE.getHeight() * 0.8));
        colorStroke.setMaxWidth(100);
    }

    /**
     * @param root Pane
     * @param gc Graphic Context
     * This Function adds event of buttons and on canvas sets up the screen
     */
    private void GUIBuilder(Pane root, GraphicsContext gc) {

        createGUIComponents(root);
        configureGUIComponents();

        setScreen();
        colorPicker.setOnAction(t -> {
            Color c = colorPicker.getValue();
            gc.setFill(c);
        });
        colorStroke.setOnAction(t -> {
            Color c = colorStroke.getValue();
            gc.setStroke(c);
        });
        oval.setOnAction(this::eclipse);
        rectangle.setOnAction(this::rectangle);
        clr.setOnAction(this::clearScreen);
        undo.setOnAction(this::undoMove);
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);
    }

    /**
     * @param stage Stage
     *  start function which calls other function which builds up whole GUI
     */
    @Override
    public void start(Stage stage) {

        Path path = new Path();
        Pane root = new Pane();
        Scene scene = new Scene(root, SCREEN_SIZE.getWidth() * 0.8, SCREEN_SIZE.getHeight() * 0.9, Color.WHITE);
        canvas = new Canvas(SCREEN_SIZE.getWidth(), SCREEN_SIZE.getHeight());
        gc = canvas.getGraphicsContext2D();
        stage.setTitle("Assignment 8");
        stage.setScene(scene);
        path.setStrokeWidth(5);
        GUIBuilder(root, gc);

        gc.fill();




        stage.show();
    }

    /**
     *Functions which sets up the Screen Background
     */
    private void setScreen() {
        gc.setFill(Color.rgb(0, 213, 255));
        gc.fillRect(0, (SCREEN_SIZE.getHeight() * 0.7), (SCREEN_SIZE.getWidth() * 0.8), (SCREEN_SIZE.getHeight() * 0.3));
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, (SCREEN_SIZE.getWidth() * 0.8), (SCREEN_SIZE.getHeight() * 0.7));
    }

    /**
     * @param me
     * @throws NumberFormatException
     * this function is called when user presses left click
     */
    void pressHandler(MouseEvent me) throws NumberFormatException {
        try {
            if (Integer.parseInt(width.getText()) > 0) {
                stroke = Integer.parseInt(width.getText());
            } else {
                throw new NumberFormatException("");
            }

            x1 = me.getX();
            y1 = me.getY();

            if (me.isPrimaryButtonDown()) {          //Checks if Button is pressed
                primaryCheck = true;

                if (checkValue == 1) {
                    item = new Eclipse(checkValue);  //creates shape
                } else if (checkValue == 2) {
                    item = new Rectangle(checkValue);
                }else
                    item = new Eclipse(1);  //creates shape

            }

        } catch (NumberFormatException e) {
            double ec=2985484;
            System.out.println((int)ec);
            new Alert(AlertType.WARNING, "Invalid Line Width").showAndWait();
        }
    }

    /**
     * @param me
     * This function is called when user releases Left click
     */
    void releaseHandler(MouseEvent me) {
        if (x1 != me.getX()) {
            shapes.add(item);   //adds shape to array list
        }

    }

    /**
     * @param me
     * This function is called when user is dragging the mouse to draw shape
     */
    void dragHandler(MouseEvent me) {
        try {
            x2 = me.getX();
            y2 = me.getY();

            if (primaryCheck == true) {
                item.setParameter(x1, y1, x2, y2, stroke, gc, colorStroke.getValue(), colorPicker.getValue());  //Calls to initialize parameter and draws the object
                setScreen();
            }
            for (Structure t : shapes) {
                t.setParameter(t.getX1(), t.getY1(), t.getX2(), t.getY2(), t.getStroke(), gc, t.getColorStoke(), t.getFillColor()); //Redraws whole ArrayList
            }
            item.setParameter(x1, y1, x2, y2, stroke, gc, colorStroke.getValue(), colorPicker.getValue());

        } catch (NumberFormatException e) {
            new Alert(AlertType.WARNING, "Invalid Line Width").showAndWait();
        }
    }


    /**
     * @param e
     * Button for circle
     */
    private void eclipse(ActionEvent e) {
        checkValue = 1;
        item = new Eclipse(checkValue);
    }

    /**
     * @param e
     * Button for rectangle
     */
    private void rectangle(ActionEvent e) {
        checkValue = 2;
        item = new Rectangle(checkValue);
    }

    /**
     * @param e
     * Button to clear Screen
     */
    private void clearScreen(ActionEvent e) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        setScreen();
        shapes.removeAll(shapes);
    }

    /**
     * @param e
     * Button to undo mover
     */
    private void undoMove(ActionEvent e) {
        try {
            shapes.remove(shapes.size() - 1);   //Removes the last Shape
            setScreen();

            for (Structure t : shapes) {            //Redraws whole Array List
                t.setParameter(t.getX1(), t.getY1(), t.getX2(), t.getY2(), t.getStroke(), gc, t.getColorStoke(), t.getFillColor());
            }
        } catch (ArrayIndexOutOfBoundsException except) {
            new Alert(AlertType.WARNING, "There is no Shape to Undo!").showAndWait();
        }
    }

    /**
     * @param args
     * Main Method
     */
    public static void main(String[] args) {
        launch(args);
    }
}
