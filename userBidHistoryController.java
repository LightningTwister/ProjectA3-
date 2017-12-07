import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

/**
 * Created by LT on 07/12/2017.
 */
public class userBidHistoryController {

    @FXML
    private Button backButton;

    @FXML
    private BorderPane rootPane;

    @FXML
    private ListView<String> list;





    public void initialize() {
        try {
            Run.database.placeBid(Double.MAX_VALUE,1);
        }catch (Exception e){
            Utilities.BidTooLow();
        }


        backButton.setOnAction(e ->{
            goBack();
        });

        refreshBidHistoryList();

    }

    private void goBack(){
        Utilities.closeWindow(rootPane);
    }

    private void refreshBidHistoryList() {
        // Clear the displayed list
        list.getItems().clear();

        // Add each artwork to the displayed list
        ArrayList<Bid> bids = Run.database.getBidHistory();
        for(Bid bid: bids){
            Artwork artwork = Run.database.getArtwork(bid.getArtworkID());
            list.getItems().add(artwork.getArtworkTitle() + "  " + bid.getAmount()  + "  " + bid.getDatePlaced());
        }

    }

}
