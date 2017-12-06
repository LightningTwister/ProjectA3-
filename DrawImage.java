
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hugh
 */
public class DrawImage extends UploadImage {
    private static final int CANVAS_WIDTH = 400;
    private static final int CANVAS_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 600;
    private Canvas canvas;
    private double pressX, pressY;
    public void start (Stage drawingStage) {
        try {
        BorderPane drawingPane = new BorderPane();
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        drawingPane.setCenter(canvas);
        VBox menuBar = new VBox();
	menuBar.setSpacing(10);
	menuBar.setPadding(new Insets(10, 10, 10, 10)); 
	drawingPane.setTop(menuBar);
        
        Button selectLine = new Button("Line");
        Button selectCircle = new Button("Filled Circle");
        Button confirmDrawing = new Button("Confirm (finish)");
        Scene scene = new Scene(drawingPane,WINDOW_WIDTH,WINDOW_HEIGHT);
        drawingStage.setScene(scene);
        drawingStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        
        selectLine.setOnAction(event -> {
            setCurrentShape("line");
        });
        
        selectCircle.setOnAction(event -> {
            setCurrentShape("circle");
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
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeLine(startX, startY, endX, endY);
    }
    
    private void createFilledCircle(double startX, double startY, double endX, double endY) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillOval(startX, startY, endX, endY);
    }
    
    private void saveDrawing() {
        try {
            WritableImage image = canvas.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File("CustomImage.png"));
        } catch (IOException ex) {
            Logger.getLogger(DrawImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BufferedImage drawingHandler() {
        start(null);
        return(uploadImage("CustomImage.png"));
    }
    
}
