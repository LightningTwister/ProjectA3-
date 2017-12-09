import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.nio.file.FileAlreadyExistsException;

/**
 * Created by LT on 09/12/2017.
 */
public class placeBidController {
    Artwork artwork;

    @FXML
    Pane rootPane;

    @FXML
    TextField amountField;

    @FXML
    VBox vBox;

    @FXML
    Label errorTooLow, errorBidTwice;

    @FXML
    Button confirm, cancel;

    @FXML
    public void initialize() {
        errorTooLow.setBackground(new Background(new BackgroundFill(
                Color.web("#FFCCCC"), CornerRadii.EMPTY, Insets.EMPTY)));
        errorBidTwice.setBackground(new Background(new BackgroundFill(
                Color.web("#FFCCCC"), CornerRadii.EMPTY, Insets.EMPTY)));

        errorTooLow.setVisible(false);
        errorBidTwice.setVisible(false);

        cancel.setOnAction(e ->{
            goBack();
        });

        confirm.setOnAction(e ->{
            try {
                errorTooLow.setVisible(false);
                errorBidTwice.setVisible(false);
                placeBid();
            }catch (FileAlreadyExistsException c1){
                errorBidTwice.setVisible(true);

            }catch (Exception e2){
                errorTooLow.setVisible(true);
            }
        });




    }

    public void setArtwork(Artwork artwork){
        this.artwork = artwork;
    }

    private void placeBid() throws Exception{
        if(Run.database.getCurrentUser().getId() == artwork.getHighestBid().getUserID()){
            throw new FileAlreadyExistsException("Can't Bid when you're the highest bidder");
        }
        Run.database.placeBid(Double.valueOf(amountField.getText()), artwork.getId());
    }

    @FXML
    private void goBack(){
        Utilities.closeWindow(rootPane);
    }
}
