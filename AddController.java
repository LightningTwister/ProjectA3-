

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * This class controls the add artwork gui page
 * @author Tim Watson 880158
 * @version 1
 */
public class AddController {
    private Painting newPainting;
    private Sculpture newSculpture;
    private int nextId;

    @FXML
    private TextField nameBox, yearBox, reserveBox, bidsBox, userNameBox, widthBox, heightBox, depthBox, materialBox
            ,nameOfArtwork, descriptionBox;
    @FXML
    RadioButton sculptureRadio, paintingRadio;
    @FXML
    Button buttonId, cancelButton;
    @FXML
    Pane rootPane;
    @FXML
    Label depthText, materialText, depthUnits;

    /**
     *  When the artwork add page is opened this method links buttons to methods and radio buttons
     */
    public void initialize() {
        buttonId.setOnAction(e ->{
            saveChanges();
        });

        cancelButton.setOnAction(e -> {
            Utilities.cancelled();
            Utilities.closeWindow(rootPane);
        });
        sculptureRadio.setOnAction(e -> {
            toggleBoxes(true);
        });

        paintingRadio.setOnAction(e -> {
            toggleBoxes(false);
        });
    }

    /**
     *  This method un/hides the boxes for depth and material if the object the user wants to add is either a
     *  sculpture or not
     * @param objectView Value that determines if this add object is a sculpture or not.
     */
    private void toggleBoxes(boolean objectView){
        depthBox.setVisible(objectView);
        materialBox.setVisible(objectView);
        depthText.setVisible(objectView);
        materialText.setVisible(objectView);
        depthUnits.setVisible(objectView);

    }

    /**
     * When the save button is pressed this method verifies the variable types and then adds this new object to the
     * artwork list.
     */
    @FXML
    private void saveChanges() {
        try{
           int year = Integer.valueOf(yearBox.getText());
           double reserve = Double.valueOf(reserveBox.getText());
           int bids =Integer.valueOf(bidsBox.getText());
           int width =Integer.valueOf(widthBox.getText());
           int height = Integer.valueOf(heightBox.getText());


            ArrayList checkLetters = new ArrayList<String>();

            checkLetters.add(nameBox.getText());
            checkLetters.add(userNameBox.getText());
            checkLetters.add(nameOfArtwork.getText());
            checkLetters.add(descriptionBox.getText());

            if (!Utilities.checkInputAreAlpha(checkLetters)){
                throw new Exception("Input type is incorrect");
            }
            String creatorName = nameBox.getText();
            String userName = userNameBox.getText();
            String title = nameOfArtwork.getText();
            String desc = descriptionBox.getText();



            if (sculptureRadio.isSelected()){
               int depth = Integer.valueOf(depthBox.getText());

                checkLetters.add(materialBox.getText());

                if (!Utilities.checkInputAreAlpha(checkLetters)){
                    throw new Exception("Error ");
                }

                String material = materialBox.getText();
               Utilities.saveSculpture(newSculpture,year, reserve, bids, width,height,depth,
                        creatorName,userName,material,title,desc,nextId );
               Run.database.addArtwork(newSculpture);
               Run.database.saveArtwork();

            }else if (paintingRadio.isSelected()){
               Utilities.savePainting(newPainting, year, reserve, bids, width,height,
                        creatorName,userName,title,desc, nextId);
                Run.database.addArtwork(newPainting);
                Run.database.saveArtwork();
            }else{
                throw new Exception("Error: Radio button not selected");
            }


            Utilities.savedInput();

            Utilities.closeWindow(rootPane);

        }catch(Exception e){
            Utilities.wrongInputFound();
        }


    }


    /**
     * When the artwork add controller is created this page is called to reference the adding objects.
     *
     * @param painting  the painting object that may be created by the user
     * @param sculpture the sculpture object that may be created by the user
     * @param id        the id of this new object
     */
    public void artworkToAdd(Painting painting, Sculpture sculpture, int id) {

        this.newPainting = painting;
        this.newSculpture = sculpture;
        this.nextId = id;


    }


}
