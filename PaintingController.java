

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 *  This class edits a painting object when the user selects it from the artwork viewer
 *  @author Tim Watson 880158
 *  @version 1
 */
public class PaintingController {
    private Painting painting;
    @FXML
    private TextField nameBox, yearBox, reserveBox, bidsBox, userNameBox, widthBox, heightBox ,
            nameOfArtwork, descriptionBox;
    @FXML
    Button buttonId, cancelButton;
    @FXML
    Pane rootPane;

    /**
     * This method links the gui buttons to methods
     */
    public void initialize() {
        buttonId.setOnAction(e ->{
            saveChanges();
        });

        cancelButton.setOnAction(e -> {
            Utilities.cancelled();
            Utilities.closeWindow(rootPane);
        });
    }

	
	/**
	* When the save button is pressed this method checks that the variables are of the correct type
	* Then saves the new painting object to the artwork list
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

            Utilities.savePainting(painting, year,reserve,bids,width,height,creatorName,userName,title,desc, painting.getId());

            Utilities.savedInput();

            Utilities.closeWindow(rootPane);

        }catch (Exception e){
            Utilities.wrongInputFound();
        }


    }

    /**
     * When the controller for this class is instantiated this method populates each editing box
     *
     * @param paintingToE Painting object to be edited
     */
    public void paintingToEditing(Painting paintingToE) {

        this.painting = paintingToE;
        nameBox.setText(painting.getArtworkCreator());
        yearBox.setText(String.valueOf(painting.getArtworkYearCreated()));
        reserveBox.setText(String.valueOf(painting.getReservePrice()));
        bidsBox.setText(String.valueOf(painting.getNumOfBids()));
        userNameBox.setText(painting.getArtworkSeller());
        widthBox.setText(String.valueOf(painting.getWidth()));
        heightBox.setText(String.valueOf(painting.getHeight()));
        nameOfArtwork.setText(painting.getArtworkTitle());
        descriptionBox.setText(painting.getArtworkDescription());

    }

}
