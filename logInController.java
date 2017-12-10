import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Class that controls the log in page of the gui
 *
 * @author Tim Watson 880158
 * @version 3
 */
public class logInController {
    @FXML
    private Button btnLogIn, createUser;
    @FXML
    private Pane rootPane;
    @FXML
    private TextField usernameBox;
    @FXML
    private ImageView bannerImg;


    /**
     * When the program is started this method is called to link buttons and the controller
     */
    public void initialize() {
        btnLogIn.setOnAction(e -> {
            Stage editScene = (Stage) ((Node)e.getSource()).getScene().getWindow();
            ChangeScreen(editScene);
        });

        createUser.setOnAction(e -> {
            registerNewUser();
        });

        bannerImg.setImage(Utilities.getImage(Main.database.BANNER_PATH));
    }


    /**
     * Method that when clicked checks username entered and if it is accepted navigate to the menu page.
     */
    @FXML
    private void ChangeScreen(Stage editStage ) {
        if (Utilities.checkUsername(usernameBox.getText())) {
            Main.database.setCurrentUser(Main.database.getUser(usernameBox.getText()));
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/menu.fxml"));
                AnchorPane root = (AnchorPane) fxmlLoader.load();

                Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
                editStage.setScene(editScene);
                editStage.setTitle("Main Menu");

                editStage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Utilities.userNotFound();
        }


    }

    /**
     * Method that opens up a new window to create a new user
     */
    @FXML
    private void registerNewUser() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/CreateUser.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("Registration");
            editStage.initModality(Modality.APPLICATION_MODAL);

            editStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
