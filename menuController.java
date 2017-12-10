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
 * This class controls the main menu page
 *
 * @author Tim Watson 880158
 * @version 1
 */
public class menuController {
    @FXML
    private Button btnProfile, btnBid, btnArtworks, btnSold, btnWon;
    @FXML
    private Pane rootPane;
    @FXML
    private ImageView imgBanner, imgIcon;
    @FXML
    private Label labelLastLogIn;


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
        btnSold.setOnAction(e -> {
            openSold();
        });
        btnWon.setOnAction(e -> {
            openWon();
        });
        imgBanner.setImage(Utilities.getImage(Main.database.BANNER_PATH));
        imgIcon.setImage(Utilities.getImage(Main.database.ICON_PATH));
        labelLastLogIn.setText("You Last Logged in: " + Main.database.getCurrentUser().getLogInDate());
        Main.database.getCurrentUser().setLogInDate(Utilities.getDate());
        Main.database.saveUsers();

    }

    /**
     * Open the window to show the artworks the user has managed to sell
     */
    private void openSold() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/artworkViewer.fxml"));
            Pane root = fxmlLoader.load();

            artworkViewerController controller = fxmlLoader.<artworkViewerController>getController();

            controller.soldView();
            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("Sold Artworks");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Open a window to list all the artworks this user has won
     */
    private void openWon() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/artworkViewer.fxml"));
            Pane root = fxmlLoader.load();

            artworkViewerController controller = fxmlLoader.<artworkViewerController>getController();
            controller.wonView();

            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("Won Artworks");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the window to browse available artworks that can be bid on
     */
    private void openBidWindow() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/browseBids.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            BrowseBidsController controller = fxmlLoader.<BrowseBidsController>getController();


            controller.loadArtworks(Main.database.getAllArtworks());


            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("Browse Bids");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Method that trys to open the user page using the current logged in user.
     */
    private void userPage() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/User_Page.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            User_Controller controller = fxmlLoader.<User_Controller>getController();

            controller.getUser(Main.database.getCurrentUser());


            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("User Page");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * When the artwork button is pressed this method trys to open a viewing page of the artworks
     * this user is linked too
     */
    private void artworkPage() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/artworkViewer.fxml"));
            Pane root =  fxmlLoader.load();

            artworkViewerController controller = fxmlLoader.<artworkViewerController>getController();

            controller.loadArtworks(Main.database.getAllArtworks());


            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH,Main.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("Artwork Page");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
