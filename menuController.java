import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.layout.Pane;



/**
 * This class controls the main menu page
 *
 * @author Tim Watson 880158
 * @version 1
 */
public class menuController {
    @FXML
    private Button btnProfile, btnBid, btnArtworks, btnSold, btnWon, btnViewUsers, logout;
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
            Stage editScene = (Stage) ((Node)e.getSource()).getScene().getWindow();
            userPage(editScene);
        });
        btnBid.setOnAction(e -> {
            Stage editScene = (Stage) ((Node)e.getSource()).getScene().getWindow();
            openBidWindow(editScene);
        });
        btnArtworks.setOnAction(e -> {
            Stage editScene = (Stage) ((Node)e.getSource()).getScene().getWindow();
            artworkPage(editScene);
        });
        btnSold.setOnAction(e -> {
            Stage editScene = (Stage) ((Node)e.getSource()).getScene().getWindow();
            openSold(editScene);
        });
        btnWon.setOnAction(e -> {
            Stage editScene = (Stage) ((Node)e.getSource()).getScene().getWindow();
            openWon(editScene);
        });
        btnViewUsers.setOnAction(e -> {
            Stage editScene = (Stage) ((Node)e.getSource()).getScene().getWindow();
            openBrowse(editScene);
        });
        logout.setOnAction(e -> {
            Stage editScene = (Stage) ((Node)e.getSource()).getScene().getWindow();
            openLoginWindow(editScene);
        });

        imgBanner.setImage(Utilities.getImage(Main.database.BANNER_PATH));
        imgIcon.setImage(Utilities.getImage(Main.database.ICON_PATH));
        labelLastLogIn.setText("You Last Logged in: " + Main.database.getCurrentUser().getLogInDate());
        Main.database.getCurrentUser().setLogInDate(Utilities.getDate());
        Main.database.saveUsers();

    }

    /**
     * Method that opens the window for the user to browse all other accounts on the system
     */
    private void openBrowse(Stage editStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/artworkViewer.fxml"));
            Pane root =  fxmlLoader.load();

            artworkViewerController controller = fxmlLoader.<artworkViewerController>getController();

            controller.loadUsers();
            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            editStage.setScene(editScene);
            editStage.setTitle("Browse Users");
            editStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Open the window to show the artworks the user has managed to sell
     */
    private void openSold(Stage editStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/artworkViewer.fxml"));
            Pane root = fxmlLoader.load();

            artworkViewerController controller = fxmlLoader.<artworkViewerController>getController();

            controller.soldView();
            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            editStage.setScene(editScene);
            editStage.setTitle("Sold Artworks");
            editStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Open a window to list all the artworks this user has won
     */
    private void openWon(Stage editStage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/artworkViewer.fxml"));
            Pane root =  fxmlLoader.load();

            artworkViewerController controller = fxmlLoader.<artworkViewerController>getController();
            controller.wonView();

            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            editStage.setScene(editScene);
            editStage.setTitle("Won Artworks");
            editStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the window to browse available artworks that can be bid on
     */
    private void openBidWindow(Stage editStage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/browseBids.fxml"));
            BorderPane root =  fxmlLoader.load();

            BrowseBidsController controller = fxmlLoader.<BrowseBidsController>getController();


            controller.loadArtworks(Main.database.getAllArtworks());


            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);

            editStage.setScene(editScene);
            editStage.setTitle("Browse Bids");
            editStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Method that trys to open the user page using the current logged in user.
     */
    private void userPage(Stage editStage) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/User_Page.fxml"));
            BorderPane root = fxmlLoader.load();

            User_Controller controller = fxmlLoader.<User_Controller>getController();

            controller.getUser(Main.database.getCurrentUser());


            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            editStage.setScene(editScene);
            editStage.setTitle("User Page");
            editStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs you out
     */
    private void openLoginWindow(Stage editStage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/logInPage.fxml"));
            Parent root = fxmlLoader.load();


            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);

            editStage.setScene(editScene);
            editStage.setTitle("Browse Bids");
            editStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * When the artwork button is pressed this method trys to open a viewing page of the artworks
     * this user is linked too
     */
    private void artworkPage(Stage editStage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/artworkViewer.fxml"));
            Pane root =  fxmlLoader.load();

            artworkViewerController controller = fxmlLoader.<artworkViewerController>getController();

            controller.loadArtworks(Main.database.getAllArtworks());


            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            editStage.setScene(editScene);
            editStage.setTitle("Artwork Page");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
