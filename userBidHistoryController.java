import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LT on 07/12/2017.
 */
public class userBidHistoryController {

    private ArrayList<Artwork> artworks;


    @FXML
    private Button backButton, viewArtwork;

    @FXML
    private Pane rootPane;

    @FXML
    private StackPane stackPane;

    private TreeView<String> treeView;


    private ArrayList<Object[]> linkList;




    @FXML
    public void initialize() {


        backButton.setOnAction(e ->{
            goBack();
        });

        viewArtwork.setOnAction(e ->{
            viewSelectedArtwork();
        });

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
        linkList = new ArrayList<>();

        TreeItem<String> rootTreeNode = new TreeItem<>();
        rootTreeNode.setExpanded(true);
        treeView = new TreeView<>(rootTreeNode);
        treeView.setShowRoot(false);

        TreeItem<String> userHistoryRoot = newBranch("My Buyer History", rootTreeNode);
        TreeItem<String> artworkRoot = newBranch("My Seller History", rootTreeNode);
        TreeItem<String> currentArtworkRoot = newBranch("My Current Auctions", artworkRoot);
        TreeItem<String> completedArtworkRoot = newBranch("My Completed Auctions", artworkRoot);
        linkList = new ArrayList<>();

        for(Bid bid: bids){
            Artwork artwork = Run.database.getArtwork(bid.getArtworkID());
            String j = "Artwork name: " + artwork.getArtworkTitle() + " Amount Bid: " +
                    bid.getAmount()  + " Date Placed: " + bid.getDatePlaced();

            linkUp(artwork, j);
            newBranch(j,userHistoryRoot);
        }

        for(int i = 0; i < currentAuctions.size(); i++){
            int currentID = currentAuctions.get(i);
            artworks.add(Run.database.getArtwork(currentID));
            placeholder = newBranch(artworks.get(i).getArtworkTitle(), currentArtworkRoot);
            for(String j : Run.database.getBidHistory(artworks.get(i).getId())){
                linkUp(artworks.get(i), j);
                newBranch(j,placeholder);
            }
        }

        artworks = new ArrayList<>();
        for(int i = 0; i < completedAuctions.size(); i++){
            int currentID = completedAuctions.get(i);
            artworks.add(Run.database.getArtwork(currentID));
            placeholder = newBranch(artworks.get(i).getArtworkTitle(), completedArtworkRoot);
            for(String j : Run.database.getBidHistory(artworks.get(i).getId())){
                linkUp(artworks.get(i), j);
                newBranch(j,placeholder);
            }
        }



    }

    private void linkUp(Artwork artwork, String s){
        Object[] link = {artwork,s};
        linkList.add(link);
    }

    private void viewSelectedArtwork() {
        try {
            boolean foundArtwork = false;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/ArtworkPage.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            ArtworkController controller = fxmlLoader.<ArtworkController>getController();

            String selectedNode = treeView.getSelectionModel().getSelectedItem().getValue();
            for(Object[] link : linkList){
                if((String)link[1] == selectedNode){
                    controller.artworkToBid((Artwork) link[0]);
                    foundArtwork = true;
                }
            }

            if(foundArtwork) {
                Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
                Stage editStage = new Stage();
                editStage.setScene(editScene);
                editStage.setTitle("Artwork");
                editStage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(){
        Utilities.closeWindow(rootPane);
    }



}
