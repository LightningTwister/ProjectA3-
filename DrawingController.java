
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


import javax.imageio.ImageIO;
import java.io.File;

import static java.lang.Math.abs;

public class DrawingController {
    private GraphicsContext graphicsContext;
    private double pressX, pressY, releaseX, releaseY;
    private UserProfiles user;
    @FXML
    private Canvas canvas;

    @FXML
    private Button btnSave, btnCancel;

    @FXML
    private ColorPicker colourPicker;

    @FXML
    private ChoiceBox drawChoice;

    @FXML
    private CheckBox filledBox;

    @FXML
    private Pane pane;

    @FXML
    private BorderPane rootPane;

    public void initialize() {

        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(1);

        pane.setStyle("-fx-border-color: black;");

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        graphicsContext.beginPath();
                        setStartXY(event.getX(),event.getY());
                        graphicsContext.moveTo(event.getX(), event.getY());
                        graphicsContext.stroke();


                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        if(drawChoice.getSelectionModel().getSelectedItem() == "Free Hand"){
                            freeHand(event);
                        } else if(drawChoice.getSelectionModel().getSelectedItem() == "Line"){

                            setStartXY(event.getX(),event.getY());
                        }else if(drawChoice.getSelectionModel().getSelectedItem() == "Circle"){

                        }else if(drawChoice.getSelectionModel().getSelectedItem() == "Rectangle"){


                        }else{
                            System.out.println("FAILED");
                        }


                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        if(drawChoice.getSelectionModel().getSelectedItem() == "Line"){
                            setReleaseXY(event.getX(),event.getY());
                            line();

                    }else if(drawChoice.getSelectionModel().getSelectedItem()== "Circle"){
                            setReleaseXY(event.getX(),event.getY());
                            circle();
                        }else if(drawChoice.getSelectionModel().getSelectedItem() =="Rectangle"){
                            setReleaseXY(event.getX(),event.getY());
                            rectangle();
                        }

                }});

        colourPicker.setValue(Color.BLACK);

        colourPicker.setOnAction(e ->{
            graphicsContext.setFill(colourPicker.getValue());
            graphicsContext.setStroke(colourPicker.getValue());
        });
        btnCancel.setOnAction(e ->{
            cancelChanges();
        });
        drawChoice.setOnAction(e ->{
            if (drawChoice.getSelectionModel().getSelectedItem() == "Free Hand" ||
                    drawChoice.getSelectionModel().getSelectedItem() =="Line"){

                filledBox.setVisible(false);
            }else{
                filledBox.setVisible(true);
            }
        });


        drawChoice.setItems(FXCollections.observableArrayList("Free Hand","Line","Circle","Rectangle"));
        drawChoice.setTooltip(new Tooltip("Select how to draw!"));
        drawChoice.getSelectionModel().selectFirst();




        btnSave.setOnAction(e -> {
            onSave();
        });

        filledBox.setVisible(false);
    }

    private void cancelChanges(){
        Utilities.cancelled();
        Utilities.closeWindow(rootPane);
    }

    private void setStartXY(double startX, double startY) {
        pressX = startX;
        pressY = startY;
    }
    private void setReleaseXY(double rX, double rY){
        releaseX= rX;
        releaseY = rY;
    }

    private void freeHand(MouseEvent event){
        graphicsContext.lineTo(event.getX(), event.getY());
        graphicsContext.stroke();
    }
    private void line(){
        graphicsContext.lineTo(pressX,pressY);
        graphicsContext.stroke();
    }
    private void circle(){
        double width = abs(releaseX- pressX);
        double height = abs(releaseY - pressY);

        if (filledBox.isSelected()){
            graphicsContext.fillOval(releaseX,releaseY,width,height);
        }else{
            graphicsContext.strokeOval( releaseX,releaseY,width,height  );
        }

    }
    private void rectangle(){
        double width = abs(releaseX- pressX);
        double height = abs(releaseY - pressY);
        if(filledBox.isSelected()){
            graphicsContext.fillRect(releaseX,releaseY,width,height);
        }else{
            graphicsContext.strokeRect(releaseX,releaseY,width,height);
        }

    }

    private void onSave() {
        try {
            Image image = canvas.snapshot(null, null);

            String fileName = "Data/ProfilePictures/"+Run.database.getCurrentUser().getUserName()+"Profile_Image.png";

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(fileName));
            Run.database.getCurrentUser().setProfilePicture( "file:"+fileName);

            Utilities.closeWindow(rootPane);
        } catch (Exception event) {
            System.out.println("Error saving image: " + event);
        }
    }


}