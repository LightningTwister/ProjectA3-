import com.sun.deploy.util.StringUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


import java.util.ArrayList;

/**
 * Created by LT on 06/12/2017.
 */


public class registerController {
    @FXML
    private TextField username, firstname, surname, phone, postcode;

    @FXML
    private TextArea adress;

    @FXML
    private Pane rootPane;

    @FXML
    private Button submit, cancel;


    public void initialize() {
        submit.setOnAction(e -> {
            sendNudes();
        });

        cancel.setOnAction(e -> {
            Utilities.cancelled();
            Utilities.closeWindow(rootPane);
        });
    }

    private void sendNudes(){
        try{

            String userName = username.getText();
            String fName = firstname.getText();
            String sName = surname.getText();

            String postCode = String.valueOf(postcode.getText());

            String phone1 = String.valueOf(phone.getText());
            String addressArray[] = StringUtils.splitString(adress.getText(),"\n");
            ArrayList<String> addressList = new ArrayList<>();
            for(String a: addressArray){
                addressList.add(a);
            }

            ArrayList<Object> user= new ArrayList<>();
            user.add(userName);
            user.add(fName);
            user.add(sName);
            user.add(postCode);
            user.add(phone1);
            user.add(addressList);

            Run.database.createUser(user);

            Utilities.savedInput();
            Run.database.saveUsers();
            Utilities.closeWindow(rootPane);

        }catch (Exception e){
            Utilities.wrongInputFound();
        }
    }
}






