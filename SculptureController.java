

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
* This class controlls the sculpture object editing page
* @author Tim Watson 880158
* @version 1
*/
public class SculptureController {
    private Sculpture sculpture;
    @FXML
    private TextField nameBox, yearBox, reserveBox, bidsBox, userNameBox, widthBox, heightBox, depthBox, materialBox,
            nameOfArtwork, descriptionBox;

    @FXML
    Button buttonId,cancelButton;

    @FXML
    Pane rootPane;

	/**
	* When the Sculpture controller is instantiated the buttons are linked with appropriate methods
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
	* This method saves the Sculpture object being edited when the variables have been checked for type
	*/
    @FXML
    private void saveChanges() {
        try{

            int year = Integer.valueOf(yearBox.getText());
            double reserve = Double.valueOf(reserveBox.getText());
            int bids =Integer.valueOf(bidsBox.getText());
            int width =Integer.valueOf(widthBox.getText());
            int height = Integer.valueOf(heightBox.getText());
            int depth = Integer.valueOf(depthBox.getText());


            ArrayList checkLetters = new ArrayList<String>();
            checkLetters.add(nameBox.getText());
            checkLetters.add(userNameBox.getText());
            checkLetters.add(nameOfArtwork.getText());
            checkLetters.add(descriptionBox.getText());
            checkLetters.add(materialBox.getText());

            if (!Utilities.checkInputAreAlpha(checkLetters)){
                throw new Exception("Input type is incorrect");
            }
            String creatorName = nameBox.getText();
            String userName = userNameBox.getText();
            String title = nameOfArtwork.getText();
            String desc = descriptionBox.getText();
            String material = materialBox.getText();



            Utilities.saveSculpture(sculpture, year,reserve,bids,width,height,depth,creatorName,userName,material,
                    title,desc, sculpture.getId());




            Utilities.savedInput();

            Utilities.closeWindow(rootPane);

        }catch(Exception e){
            Utilities.wrongInputFound();
        }


    }

    public void sculptureToEdit(Sculpture sculptureToE) {

        this.sculpture = sculptureToE;
        nameBox.setText(sculpture.getArtworkCreator());
        yearBox.setText(String.valueOf(sculpture.getArtworkYearCreated()));
        reserveBox.setText(String.valueOf(sculpture.getReservePrice()));
        bidsBox.setText(String.valueOf(sculpture.getNumOfBids()));
        userNameBox.setText(sculpture.getArtworkSeller());
        widthBox.setText(String.valueOf(sculpture.getWidth()));
        heightBox.setText(String.valueOf(sculpture.getHeight()));
        descriptionBox.setText(sculpture.getArtworkDescription());
        nameOfArtwork.setText(sculpture.getArtworkTitle());
        depthBox.setText(String.valueOf(sculpture.getDepth()));
        materialBox.setText(sculpture.getMaterial());

    }
    
}
