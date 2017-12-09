import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

/**
 * Created by LT on 07/12/2017.
 */
public class userBidHistoryController {

    private ArrayList<Artwork> artworks;


    @FXML
    private Button backButton;

    @FXML
    private Pane rootPane;

    @FXML
    private StackPane stackPane;

    private TreeView<String> treeView;





    @FXML
    public void initialize() {


        backButton.setOnAction(e ->{
            goBack();
        });

        try {
            Run.database.placeBid(Double.MAX_VALUE,1);
        }catch (Exception e){
            Utilities.BidTooLow();
        }

        Run.database.getCurrentUser().addCurrentAuction(1);
        Run.database.getCurrentUser().completeAuction(1);
        Run.database.getCurrentUser().addCurrentAuction(2);

        populateTree();
        stackPane.getChildren().add(treeView);

    }

    //makes a string into a TreeItem then adds it to the parent Node
    @FXML
    private TreeItem<String> newBranch(String s, TreeItem<String> parent){
        TreeItem<String> branch = new TreeItem<>(s);
        branch.setExpanded(true);
        parent.getChildren().add(branch);
        return branch;
    }

    //Populates data into the tree
    @FXML
    public void populateTree(){
        ArrayList<Integer> currentAuctions = Run.database.getCurrentUser().getCurrentAuctions();
        ArrayList<Integer> completedAuctions = Run.database.getCurrentUser().getCompletedAuctions();
        artworks = new ArrayList();
        TreeItem<String> placeholder;
        ArrayList<Bid> bids = Run.database.getBidHistory();

        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        treeView = new TreeView<>(root);
        treeView.setShowRoot(false);

        TreeItem<String> userHistoryRoot = newBranch("My Buyer History", root);
        TreeItem<String> artworkRoot = newBranch("My Seller History", root);
        TreeItem<String> currentArtworkRoot = newBranch("My Current Auctions", artworkRoot);
        TreeItem<String> completedArtworkRoot = newBranch("My Completed Auctions", artworkRoot);


        for(Bid bid: bids){
            Artwork artwork = Run.database.getArtwork(bid.getArtworkID());

            newBranch(("Artwork name: " + artwork.getArtworkTitle() + " Amount Bid: " +  bid.getAmount()  + " Date Placed: " + bid.getDatePlaced()),userHistoryRoot);
        }

        for(int i = 0; i < currentAuctions.size(); i++){
            int currentID = currentAuctions.get(i);
            artworks.add(Run.database.getArtwork(currentID));
            placeholder = newBranch(artworks.get(i).getArtworkTitle(), currentArtworkRoot);
            for(String j : Run.database.getBidHistory(artworks.get(i).getId())){
                newBranch(j,placeholder);
            }
        }

        artworks = new ArrayList<>();
        for(int i = 0; i < completedAuctions.size(); i++){
            int currentID = completedAuctions.get(i);
            artworks.add(Run.database.getArtwork(currentID));
            placeholder = newBranch(artworks.get(i).getArtworkTitle(), completedArtworkRoot);
            for(String j : Run.database.getBidHistory(artworks.get(i).getId())){
                newBranch(j,placeholder);
            }
        }



    }

    @FXML
    private void goBack(){
        Utilities.closeWindow(rootPane);
    }



}
