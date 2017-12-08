import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

/**
 * Created by LT on 07/12/2017.
 */
public class userBidHistoryController {

    private ArrayList<Artwork> artworks;


    @FXML
    private Button backButton;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TreeView<String> treeView;





    public void initialize() {


        backButton.setOnAction(e ->{
            goBack();
        });

        wakeMeUpINSIDE();

        populateTree();


    }

    //makes a string into a TreeItem then adds it to the parent Node
    private TreeItem<String> newBranch(String s, TreeItem<String> parent){
        TreeItem<String> branch = new TreeItem<>(s);
        branch.setExpanded(true);
        parent.getChildren().add(branch);
        return branch;
    }

    //Populates data into the tree
    private void populateTree(){
        ArrayList<Integer> currentAuctions = Run.database.getCurrentUser().getCurrentAuctions();
        ArrayList<Integer> completedAuctions = Run.database.getCurrentUser().getCompletedAuctions();
        artworks = new ArrayList();
        TreeItem<String> placeholder;
        ArrayList<Bid> bids = Run.database.getBidHistory();

        TreeItem<String> root = new TreeItem<>();

        newBranch("My Buyer History", root);
        TreeItem<String> artworkRoot = newBranch("My Seller History", root);
        TreeItem<String> currentArtworkRoot = newBranch("My Seller History", artworkRoot);
        TreeItem<String> completedArtworkRoot = newBranch("My Seller History", artworkRoot);


        for(Bid bid: bids){
            Artwork artwork = Run.database.getArtwork(bid.getArtworkID());

            newBranch((artwork.getArtworkTitle() + "  " + bid.getAmount()  + "  " + bid.getDatePlaced()),root);
        }

        for(int i = 0; i < currentAuctions.size(); i++){
            int currentID = currentAuctions.get(i);
            artworks.add(Run.database.getArtwork(currentID));
            placeholder = newBranch(artworks.get(i).getArtworkTitle(), currentArtworkRoot);
            for(String j : Run.database.getBidHistory(artworks.get(i).getId())){
                newBranch(j,placeholder);
            }
        }

        for(int i = 0; i < completedAuctions.size(); i++){
            int currentID = completedAuctions.get(i);
            artworks.add(Run.database.getArtwork(currentID));
            placeholder = newBranch(artworks.get(i).getArtworkTitle(), completedArtworkRoot);
            for(String j : Run.database.getBidHistory(artworks.get(i).getId())){
                newBranch(j,placeholder);
            }
        }

        root.setExpanded(true);
        treeView = new TreeView<>(root);
        treeView.setShowRoot(true);
        treeView.setVisible(true);

    }
    

    private void goBack(){
        Utilities.closeWindow(rootPane);
    }

    private void wakeMeUpINSIDE(){
        try {
            Run.database.placeBid(Double.MAX_VALUE,1);
        }catch (Exception e){
            Utilities.BidTooLow();
        }

        Run.database.getCurrentUser().addCurrentAuction(1);
        Run.database.getCurrentUser().completeAuction(1);
        Run.database.getCurrentUser().addCurrentAuction(2);
    }


}
