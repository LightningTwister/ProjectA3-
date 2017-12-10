
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.stage.Modality;
import javafx.stage.Stage;


import java.util.ArrayList;

/**
 * This class controls the user page gui
 *
 * @author Tim Watson 880158
 * @version 1.1
 */
public class User_Controller {
    private UserProfiles user;
    private String picturePath;
    @FXML
    private TextField userBox, fNameBox, sNameBox, postcodeBox, phoneBox;
    @FXML
    private TextArea addressBox;
    @FXML
    private ComboBox fuserDrop;
    @FXML
    private Button btnSave, btnFave, btnChangeImage, bidHistories, btnDraw;
    @FXML
    private ImageView imgProfile;

    /**
     * Method that gets the current user of the program and loads the relevant information into the editing boxes
     *
     * @param user the current user of the program.
     */
    public void getUser(UserProfiles user) {
        this.user = user;
        String a = "";


        userBox.setText(this.user.getUserName());
        fNameBox.setText(this.user.getFirstName());
        sNameBox.setText(this.user.getLastName());
        for (String c : this.user.getAddress()) {
            a += c + "\n";
        }
        addressBox.setText(a);
        postcodeBox.setText(this.user.getPostCode());
        phoneBox.setText(String.valueOf(this.user.getPhoneNumber()));
        this.picturePath = user.getProfilePicture();

        populateCombo();
    }

    /**
     * Method that puts all the favourite users for the current user into a combo box
     */
    private void populateCombo() {
        fuserDrop.getItems().clear();

        for (Integer u : this.user.getFaveUsers()) {
            fuserDrop.getItems().add(Main.database.getUser(u).getUserName());
        }
    }

    /**
     * When the user page is opened save button is linked to the save method
     */
    public void initialize() {
        btnSave.setOnAction(e -> {
            Stage editScene = (Stage) ((Node)e.getSource()).getScene().getWindow();
            saveChanges();
            backToMainMenu(editScene);
        });

        btnFave.setOnAction(e -> {
            viewSelection();
        });
        btnChangeImage.setOnAction(e -> {
            changeImage();
        });

        bidHistories.setOnAction(e -> {
            goToBidHistory();
        });

        btnDraw.setOnAction(e -> {
            openDrawWindow();
        });

        imgProfile.setImage(Utilities.getImage(Main.database.getCurrentUser().getProfilePicture()));


    }

    /**
     * Opens the custome drawing window for the user to create their own profile picture
     */
    private void openDrawWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/Drawing.fxml"));
            Pane root = (Pane) fxmlLoader.load();

            DrawingController controller = fxmlLoader.<DrawingController>getController();


            Scene editScene = new Scene(root);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("Draw Your Own Profile Picture");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

        } catch (Exception e) {
            Utilities.cancelled();
        }
        picturePath = Main.database.getCurrentUser().getProfilePicture();
        imgProfile.setImage(Utilities.getImage(picturePath));

    }

    /**
     * Opens a file selection window to browse and select a new profile image
     */
    private void changeImage() {

        String fileLocation = Utilities.changeImage("Select a profile picture", "Data/ProfilePictures");
        if (fileLocation.equals("FAILED")) {
            Utilities.noImageFound();
        } else {
            imgProfile.setImage((Utilities.getImage("file:" + fileLocation)));
            this.picturePath = "file:" + fileLocation;
        }

    }

    /**
     * Logs you out
     */
    private void backToMainMenu(Stage editStage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/menu.fxml"));
            AnchorPane root =  fxmlLoader.load();

            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            editStage.setScene(editScene);
            editStage.setTitle("Main Menu");

            editStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * When a favourite user is selected from the combo box a view window is opened
     */
    private void viewSelection() {
        try {


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/faveUserPage.fxml"));
            Pane root = (Pane) fxmlLoader.load();

            faveUserController controller = fxmlLoader.<faveUserController>getController();


            controller.getUser(Main.database.getUser(fuserDrop.getValue().toString()));

            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("Favourite User");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();


        } catch (Exception e) {
            Utilities.nothingSelected();

        }

        fuserDrop.getItems().clear();
        populateCombo();

    }

    /**
     * Open this users bid history
     */
    @FXML
    private void goToBidHistory() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/UserBidHistory.fxml"));
            Pane root = fxmlLoader.load();

            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();

            editStage.setScene(editScene);
            editStage.setTitle("BidHistory");
            editStage.initModality(Modality.APPLICATION_MODAL);

            editStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * When the save button is pressed this method updates the current object into the users database
     */
    private void saveChanges() {
        try {

            String userName = userBox.getText();
            String fName = fNameBox.getText();
            String sName = sNameBox.getText();

            String postCode = String.valueOf(postcodeBox.getText());

            // int phoneNum = Integer.valueOf(phoneBox.getText());
            String phone = String.valueOf(phoneBox.getText());


            String addressArray[] = addressBox.getText().split("\\r?\\n");
            if (!(addressArray.length == 3)) {
                Utilities.addressSizeWarning();
                return;
            }
            ArrayList<String> addressList = new ArrayList<>();
            for (String a : addressArray) {
                addressList.add(a);
            }

            ArrayList<Object> info = new ArrayList<>();
            info.add(userName);
            info.add(fName);
            info.add(sName);
            info.add(postCode);
            info.add(phone);
            info.add(addressArray);


            if (!(Utilities.checkUserDetails(info))) {
                Utilities.wrongInputFound();
            } else {
                Utilities.saveUser(this.user, userName, fName, sName, phone, addressList, postCode,
                        picturePath, this.user.getId());


                Utilities.savedInput();
                Main.database.saveUsers();
                Main.database.saveUsers();
            }


        } catch (Exception e) {
            e.printStackTrace();
            Utilities.wrongInputFound();
        }

    }

}


