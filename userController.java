import com.sun.deploy.util.StringUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 *  This class controls the user page gui
 *  @author Tim Watson 880158
 *  @version 1
 */
public class userController {

        private UserProfiles user;

        @FXML
        private TextField userBox,fNameBox,sNameBox,postcodeBox,phoneBox;

        @FXML
        private TextArea addressBox;

        @FXML
        private ComboBox fuserDrop;

        @FXML
        private Button btnSave;

        @FXML
        Pane rootPane;

    /**
     * Method that gets the current user of the program and loads the relevant information into the editing boxes
     *
     * @param user the current user of the program.
     */
    public void getUser(UserProfiles user) {
            this.user= user;
            String a = "";

            userBox.setText(this.user.getUserName());
            fNameBox.setText(this.user.getFirstName());
            sNameBox.setText(this.user.getLastName());
            for(String c: this.user.getAddress()) {
                a += c +"\n";
            }
            addressBox.setText(a);
            postcodeBox.setText(this.user.getPostCode());
            phoneBox.setText(String.valueOf(this.user.getPhoneNumber()));

        }

    /**
     * When the user page is opened save button is linked to the save method
     */
    public void initialize() {
            btnSave.setOnAction(e -> {
                saveChanges();
            });
        }

    /**
     * When the save button is pressed this method updates the current object into the users database
     */
    private void saveChanges(){
            try{

                String userName = userBox.getText();
                String fName = fNameBox.getText();
                String sName = sNameBox.getText();

                String postCode = String.valueOf(postcodeBox.getText());

                String phone = String.valueOf(phoneBox.getText());
                String addressArray[] = StringUtils.splitString(addressBox.getText(),"\n");
                ArrayList<String> addressList = new ArrayList<>();
                for(String a: addressArray){
                    addressList.add(a);
                }

                Utilities.saveUser(this.user,userName,fName,sName,phone,addressList,postCode,
                        "/path",this.user.getId());

                Utilities.savedInput();
                Run.database.saveUsers();
                Utilities.closeWindow(rootPane);

            }catch (Exception e){
                Utilities.wrongInputFound();
            }

        }




    }


