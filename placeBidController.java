import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.nio.file.FileAlreadyExistsException;

/**
 * Created by LT on 09/12/2017.
 */
public class placeBidController {
    private Artwork artwork;

    @FXML
    private Pane rootPane;
    @FXML
    private TextField amountField;
    @FXML
    private VBox vBox;
    @FXML
    private Label errorTooLow, errorBidTwice;
    @FXML
    private Button confirm, cancel;

    @FXML

    /**
     * Initilise method that loads the gui elements to methods
     */
    public void initialize() {
        errorTooLow.setBackground(new Background(new BackgroundFill(
                Color.web("#FFCCCC"), CornerRadii.EMPTY, Insets.EMPTY)));
        errorBidTwice.setBackground(new Background(new BackgroundFill(
                Color.web("#FFCCCC"), CornerRadii.EMPTY, Insets.EMPTY)));

        errorTooLow.setVisible(false);
        errorBidTwice.setVisible(false);

        cancel.setOnAction(e -> {
            goBack();
        });

        confirm.setOnAction(e -> {
            try {
                errorTooLow.setVisible(false);
                errorBidTwice.setVisible(false);
                placeBid();
                goBack();
            } catch (FileAlreadyExistsException c1) {
                errorBidTwice.setVisible(true);

            } catch (Exception e2) {
                errorTooLow.setVisible(true);
            }

        });

    }

    /**
     * Gets the artwork to be bid on and saves the reference
     * @param artwork Artwork object a bid is being added for
     */
    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }

    /**
     *  Method that checks the bid being created is valid
     * @throws Exception
     */
    private void placeBid() throws Exception {
        if (Run.database.getCurrentUser().getId() == artwork.getHighestBid().getUserID()) {
            throw new FileAlreadyExistsException("Can't Bid when you're the highest bidder");
        }
        Run.database.placeBid(Double.valueOf(amountField.getText()), artwork.getId());
    }

    /**
     * Exit window
     */
    @FXML
    private void goBack() {
        Utilities.closeWindow(rootPane);
    }
}
