import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Class that controls the log in page of the gui
 * @author Tim Watson 880158
 * @version 2
 */
public class logInController{
    @FXML
    private Button btnLogIn;
    @FXML
    Pane rootPane;
    @FXML
    private TextField usernameBox;

    /**
     * When the program is started this method is called to link buttons and the controller
     */
    public void initialize() {
        btnLogIn.setOnAction(e -> {
            ChangeScreen();
        });
    }

    /**
     *  Method that when clicked checks username entered and if it is accepted navigate to the menu page.
     */
    @FXML
    private void ChangeScreen(){
        if (Utilities.checkUsername(usernameBox.getText())){
            Run.database.setCurrentUser(Run.database.getUser(usernameBox.getText()));
            try{

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/menu.fxml"));
                AnchorPane root = (AnchorPane) fxmlLoader.load();

                Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
                Stage editStage = new Stage();
                editStage.setScene(editScene);
                editStage.setTitle("Main Menu");
                editStage.initModality(Modality.APPLICATION_MODAL);

                editStage.show();
                Utilities.closeWindow(rootPane);

            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            Utilities.userNotFound();
        }




    }




}
