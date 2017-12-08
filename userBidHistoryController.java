import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox dropDown;

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

        ArrayList<Integer> auctions = Run.database.getCurrentUser().getCurrentAuctions();
        auctions.addAll(Run.database.getCurrentUser().getCompletedAuctions());
        ArrayList<Artwork> artworks = new ArrayList();
        for(Integer i : auctions){
            artworks.add(Run.database.getArtwork(i));
        }

        wakeMeUpINSIDE();

        dropDown.getSelectionModel().selectFirst();
        dropDown.getItems().add("My Bidhistory");
        for(Artwork i : artworks){
            dropDown.getItems().add(i);
        }

        dropDown.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                //changeHistory(dropDown.getItems().get((Integer) number2));
            }
        });

        refreshBidHistoryList();

    }

    private void changeHistory(){

    }

    private void goBack(){
        Utilities.closeWindow(rootPane);
    }

    private void wakeMeUpINSIDE(){
        Run.database.getCurrentUser().addCurrentAuction(1);
        Run.database.getCurrentUser().completeAuction(1);
        Run.database.getCurrentUser().addCurrentAuction(2);
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
