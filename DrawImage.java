
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.Parent;

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
    @FXML
    private Canvas canvas;
    
    private double pressX, pressY;
    
    @FXML
    private Button selectLine, selectCircle,confirmDrawing;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage drawingStage) {
        try {
            Parent drawingPane = FXMLLoader.load(getClass().getResource("/DrawImageFXML.fxml"));
            Scene scene = new Scene(drawingPane,640,480);
            drawingStage.setScene(scene);
            drawingStage.show();
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
        catch(Exception e) {
            e.printStackTrace();
        }
        
        

           
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
    
    //public BufferedImage drawingHandler() {
    //    start(null);
    //    return(uploadImage("CustomImage.png"));
    //}
    
}