import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    /**
     * When this class is started this links the buttons on the main menu page
     * to matching classes to change current screen.
     */
    public void initialize() {
        btnProfile.setOnAction(e -> {
            userPage();
        });
        btnBid.setOnAction(e -> {
            Utilities.classNotImplemented();
        });
        btnArtworks.setOnAction(e -> {
            artworkPage();
        });

    }

    /**
     * Method that trys to open the user page using the current logged in user.
     */
    private void userPage(){
        //ArrayList<String> address = new ArrayList<>();
        //address.add("21 catherine St");
        //address.add("Swansea");
        //address.add("W Glamorgan");


        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userPage.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            userController controller = fxmlLoader.<userController>getController();

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

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("artworkViewer.fxml"));
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

    /**
     *  Closes this window once it is not required.
     */
    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }




}