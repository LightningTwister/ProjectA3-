
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


import java.util.ArrayList;

/**
 * @author Martin Cvetkov 915797
 */


public class registerController {
    private String picPath;
    @FXML
    private TextField username, firstName, surname, phone, postcode;

    @FXML
    private TextArea address;
    @FXML
    private Pane rootPane;
    @FXML
    private Button submit, cancel, btnPicture;


    /**
     * Initialise method that links gui elements to their respective method
     */
    public void initialize() {
        submit.setOnAction(e -> {
            saveProfile();
        });

        cancel.setOnAction(e -> {
            Utilities.cancelled();
            Utilities.closeWindow(rootPane);
        });

        btnPicture.setOnAction(e -> {
            getPicture();
        });
    }

    /**
     * Open a select picture gui, to select a default image for this profile
     */
    private void getPicture() {
        String fileLocation  = Utilities.changeImage("Select a profile picture", Main.database.DEFAULT_PROFILE_PATH);
        if (fileLocation.equals("FAILED")) {
            Utilities.noImageFound();
        } else {

            this.picPath = "file:" + fileLocation;
        }
    }

    /**
     * Method that checks all inputs are valid and then creates a new profile
     */
    private void saveProfile() {
        try {

            String userName = username.getText();
            String fName = firstName.getText();
            String sName = surname.getText();

            String postCode = String.valueOf(postcode.getText());

            String phone1 = String.valueOf(phone.getText());
            String addressArray[] = address.getText().split("\\r?\\n");
            ArrayList<String> addressList = new ArrayList<>();
            if (!(addressArray.length == 3)) {
                Utilities.addressSizeWarning();
                return;
            }
            for (String a : addressArray) {
                addressList.add(a);
            }

            ArrayList<Object> user = new ArrayList<>();
            user.add(userName);
            user.add(fName);
            user.add(sName);
            user.add(postCode);
            user.add(phone1);
            user.add(addressList);
            user.add(picPath);

            boolean emptyCheck = Main.database.createUser(user);

            if (emptyCheck == true) {
                Utilities.savedInput();
                Main.database.saveUsers();
                Utilities.closeWindow(rootPane);
            } else {
                Utilities.wrongInputFound();
            }


        } catch (Exception e) {
            //e.printStackTrace();
            Utilities.wrongInputFound();
        }
    }
}






