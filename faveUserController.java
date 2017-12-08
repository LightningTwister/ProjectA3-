
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;





/**
 *  This class controls the fave user page gui
 *  @author Tim Watson 880158
 *  @version 1
 */
public class faveUserController {
        private int id;

        @FXML
        private TextField userBox;

        @FXML
        ImageView imgProfile;

        @FXML
        private Button btnCancel, btnFaveUser, addButton;

        @FXML
        Pane rootPane;

    /**
     * Method that gets the current user of the program and loads the relevant information into the editing boxes
     *
     * @param user the current user of the program.
     */
    public void getUser(UserProfiles user) {

        userBox.setText(user.getUserName());
        id = user.getId();
        Image profileImage = Utilities.getImage(Run.database.getUser(id).getProfilePicture());
        imgProfile.setImage(profileImage);

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
            addButton.setOnAction(e ->{

                addFaveUser();
                Utilities.closeWindow(rootPane);
            });


        }

        private void addFaveUser(){
        Run.database.getCurrentUser().toggleFaveUser(id);
        }

    private void removeFaveUser(){
        Run.database.getCurrentUser().toggleFaveUser(id);
    }



    }


