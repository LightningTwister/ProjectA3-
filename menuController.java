import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 *  This class controls the main menu page
 *  @author Tim Watson 880158
 *  @version 1
 */
public class menuController{
    @FXML
    private Button btnProfile,btnBid,btnArtworks;
    @FXML
    Pane rootPane;
    @FXML
    ImageView imgBanner,imgIcon;
    @FXML
    Label labelLastLogIn;


    /**
     * When this class is started this links the buttons on the main menu page
     * to matching classes to change current screen.
     */
    public void initialize() {
        btnProfile.setOnAction(e -> {
            userPage();
        });
        btnBid.setOnAction(e -> {
            openBidWindow();
        });
        btnArtworks.setOnAction(e -> {
            artworkPage();
        });
        imgBanner.setImage(Utilities.getImage(Run.database.BANNER_PATH));
        imgIcon.setImage(Utilities.getImage(Run.database.ICON_PATH));
        labelLastLogIn.setText("You Last Logged in: "+ Run.database.getCurrentUser().getLogInDate());

    }

    private void openBidWindow(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/browseBids.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            BrowseBidsController controller = fxmlLoader.<BrowseBidsController>getController();


            controller.loadArtworks(Run.database.getAllArtworks());




            Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("Browse Bids");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Method that trys to open the user page using the current logged in user.
     */
    private void userPage(){

        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/User_Page.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            User_Controller controller = fxmlLoader.<User_Controller>getController();

            controller.getUser(Run.database.getCurrentUser());


            Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("User Page");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * When the artwork button is pressed this method trys to open a viewing page of the artworks
     * this user is linked too
     */
    private void artworkPage(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/artworkViewer.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            artworkViewerController controller = fxmlLoader.<artworkViewerController>getController();

            controller.loadArtworks(Run.database.getAllArtworks());


            Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("Artwork Page");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();




        }catch(Exception e){
            e.printStackTrace();
        }
    }





}
