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


    /**
     * Initialise method to link buttons to methods
     */
    @FXML
    public void initialize() {


        backButton.setOnAction(e -> {
            goBack();
        });

        populateTree();
        stackPane.getChildren().add(treeView);

    }

    /**
     * Makes a string into a treeitem then adds it to the parent node
     *
     * @param s      String to be added to node
     * @param parent Node that is being added too
     * @return Tree item of the new tree
     */
    @FXML
    private TreeItem<String> newBranch(String s, TreeItem<String> parent) {
        TreeItem<String> branch = new TreeItem<>(s);
        branch.setExpanded(true);
        parent.getChildren().add(branch);
        return branch;
    }

    /**
     * Populates data into a tree format
     */
    @FXML
    public void populateTree() {
        ArrayList<Integer> currentAuctions = Main.database.getCurrentUser().getCurrentAuctions();
        ArrayList<Integer> completedAuctions = Main.database.getCurrentUser().getCompletedAuctions();
        artworks = new ArrayList();
        TreeItem<String> placeholder;
        ArrayList<Bid> bids = Main.database.getBidHistory();

        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        treeView = new TreeView<>(root);
        treeView.setShowRoot(false);

        TreeItem<String> userHistoryRoot = newBranch("My Buyer History", root);
        TreeItem<String> artworkRoot = newBranch("My Seller History", root);
        TreeItem<String> currentArtworkRoot = newBranch("My Current Auctions", artworkRoot);
        TreeItem<String> completedArtworkRoot = newBranch("My Completed Auctions", artworkRoot);

        Artwork artwork;
        for (Bid bid : bids) {
            artwork = Main.database.getArtwork(bid.getArtworkID());

            newBranch(("Artwork name: " + artwork.getArtworkTitle() + " Amount Bid: " + bid.getAmount() + " Date Placed: " + bid.getDatePlaced()), userHistoryRoot);
        }

        for (int i = 0; i < currentAuctions.size(); i++) {
            int currentID = currentAuctions.get(i);
            artworks.add(Main.database.getArtwork(currentID));
            placeholder = newBranch(artworks.get(i).getArtworkTitle(), currentArtworkRoot);
            for (String j : Main.database.getBidHistory(artworks.get(i).getId())) {
                newBranch(j, placeholder);
            }
        }

        artworks = new ArrayList<>();
        for (int i = 0; i < completedAuctions.size(); i++) {
            int currentID = completedAuctions.get(i);
            artworks.add(Main.database.getArtwork(currentID));
            placeholder = newBranch(artworks.get(i).getArtworkTitle(), completedArtworkRoot);
            for (String j : Main.database.getBidHistory(artworks.get(i).getId())) {
                newBranch(j, placeholder);
            }
        }


    }

    /**
     * Exit window
     */
    @FXML
    private void goBack() {
        Utilities.closeWindow(rootPane);
    }


}
