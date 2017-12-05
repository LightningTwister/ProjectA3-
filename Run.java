import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Run extends Application {
    private static final int MAIN_WINDOW_WIDTH = 600;
    private static final int MAIN_WINDOW_HEIGHT = 400;
    private static final String WINDOW_TITLE = "Log In";

    // Constants for the edit window.
    // We make them public and allow the controllers to access these,
    // so that we keep all window constants in one place.
    public static final int EDIT_WINDOW_WIDTH = 700;
    public static final int EDIT_WINDOW_HEIGHT = 400;
    public static final String EDITP_WINDOW_TITLE = "Edit Painting";
    public static final String EDITS_WINDOW_TITLE = "Edit Sculpture";

    public static Database database;


    public static void main(String[] args) {
        database = new Database();
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {


        try {
            //Parent root = FXMLLoader.load(getClass().getResource("/Fxml/logInPage.fxml"));

            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/logInPage.fxml"));

            Scene scene = new Scene(root,MAIN_WINDOW_WIDTH,MAIN_WINDOW_HEIGHT);
            primaryStage.setScene(scene);
            primaryStage.setTitle(WINDOW_TITLE);
            primaryStage.show();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
