
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *  This class controls the fave user page gui
 *  @author Tim Watson 880158
 *  @version 1.1
 */
public class faveUserController {
        private int id;

        @FXML
        private TextField userBox;
        @FXML
        private ImageView imgProfile;
        @FXML
        private Button btnCancel, btnFaveUser, addButton;
        @FXML
        private Pane rootPane;

    /**
     * Method that gets the current user of the program and loads the relevant information into the editing boxes
     *
     * @param user the current user of the program.
     */
    public void getUser(UserProfiles user) {

        userBox.setText(user.getUserName());
        id = user.getId();
        Image profileImage = Utilities.getImage(Main.database.getUser(id).getProfilePicture());
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

    /**
     * Add a Favourite user when the fave user button is clicked
     */
    private void addFaveUser(){
        Main.database.getCurrentUser().toggleFaveUser(id);
        }

    /**
     * Remove a favourite user the delete button is pressed
     */
    private void removeFaveUser(){
        Main.database.getCurrentUser().toggleFaveUser(id);
    }



    }


