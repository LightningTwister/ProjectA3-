
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hugh
 */
public class DrawImage extends Application {
    
    
    private Canvas canvas;
    
    private double pressX, pressY;
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    private static final int CANVAS_WIDTH = 400;
    private static final int CANVAS_HEIGHT = 400;
        
    private GraphicsContext gc;
    private BufferedImage outputImage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage drawingStage) {
        try {
            Pane drawingPane = buildGUI();
            Scene scene = new Scene(drawingPane,600,400);
            drawingStage.setScene(scene);
            drawingStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private Pane buildGUI(){
        BorderPane root = new BorderPane();
        canvas = new Canvas(CANVAS_WIDTH,CANVAS_HEIGHT);
        root.setCenter(canvas);
        VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10,10,10,10));
        root.setLeft(sidebar);
        Button selectLine = new Button("Select Line");
	Button selectCircle = new Button("Select Circle");
        final ColorPicker colourPicker = new ColorPicker();
        colourPicker.setValue(Color.BLUE);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        Button confirmDrawing = new Button("Save Image");
        sidebar.getChildren().addAll(selectLine,selectCircle,colourPicker,confirmDrawing);
        selectLine.setOnAction(event -> {
            createFilledCircle(200,200,400,400);
            setCurrentShape("line");
        });

        selectCircle.setOnAction(event -> {
                createFilledCircle(200,20,400,40);
            setCurrentShape("circle");
        });
        
        colourPicker.setOnAction(event -> {
            gc.setFill(colourPicker.getValue());
            gc.setStroke(colourPicker.getValue());
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            setStartXY(event.getX(),event.getY());
        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent event) -> {
            if ("line".equals(currentShape)) {
                createLine(pressX,pressY,event.getX(),event.getY());
            } else {
                createFilledCircle(pressX,pressY,event.getX(),event.getY());
            }
        });

        confirmDrawing.setOnAction(event -> {
            saveDrawing();
        });
        return root;
    }
    
    private String currentShape = "line";
    
    private void setCurrentShape(String input) {
        currentShape = input;
    }
    
    private void setStartXY(double startX, double startY) {
        pressX = startX;
        pressY = startY;
    }
    
    private void createLine(double startX, double startY, double endX, double endY) {
        gc = canvas.getGraphicsContext2D();
        gc.strokeLine(startX, startY, endX, endY);
    }
    
    private void createFilledCircle(double startX, double startY, double endX, double endY) {
        gc  = canvas.getGraphicsContext2D();
        double width = endX - startX;
        double height = endY - startY;
        gc.fillOval(startX, startY, width, height);
    }
    
    private void saveDrawing() {
        try {
            WritableImage image = canvas.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File("CustomImage.png"));
        } catch (IOException ex) {
            Logger.getLogger(DrawImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setOutputImage() {
        File f = new File("CustomImage.png");
        BufferedImage i;
        try {
            i = ImageIO.read(f);
            outputImage = i;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public BufferedImage getOutputImage() {
        return outputImage;
    }
    
}