import com.sun.deploy.util.StringUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import java.util.ArrayList;

/**
 *  This class controls the fave user page gui
 *  @author Tim Watson 880158
 *  @version 1
 */
public class faveUserController {
        private int id;

        @FXML
        private TextField userBox,fNameBox,sNameBox,phoneBox;



        @FXML
        private Button btnCancel, btnFaveUser;

        @FXML
        Pane rootPane;

    /**
     * Method that gets the current user of the program and loads the relevant information into the editing boxes
     *
     * @param user the current user of the program.
     */
    public void getUser(UserProfiles user) {


            userBox.setText(user.getUserName());
            fNameBox.setText(user.getFirstName());
            sNameBox.setText(user.getLastName());
            phoneBox.setText(String.valueOf(user.getPhoneNumber()));
            id = user.getId();



        }

    /**
     * When the user page is opened save button is linked to the save method
     */
    public void initialize() {
            btnCancel.setOnAction(e -> {
                Utilities.closeWindow(rootPane);

            });
            btnFaveUser.setOnAction(e -> {
                removeFaveUser();
                Utilities.closeWindow(rootPane);
            });

        }


    private void removeFaveUser(){
        Run.database.getCurrentUser().deleteFaveUser(id);
    }



    }


