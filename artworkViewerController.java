import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;


/**
 *  Class that controls how the artwork viewing gui works
 *  @author Tim Watson 880158
 *  @version 1
 */
public class artworkViewerController{
    private static ArrayList<Artwork> artworks = new ArrayList<Artwork>();
    @FXML
    private Button btnEdit,btnAdd;
    @FXML
    ListView<String> lstArtworks;


    /**
     * When the artwork viewer page is opened the two buttons are linked with methods
     * And the list of artworks is populated.
     */
    public void initialize() {
        btnEdit.setOnAction(e -> {
                changeScreen();
            });

        btnAdd.setOnAction(e -> {
                addPage();
        });
        refreshArtworkList();
    }

    /**
     * When the edit button is pressed this method checks to see if an artwork is selected
     * and then opens an edit screen for that specific artwork and artwork type.
     */
    @FXML
    private void changeScreen(){

        int selectedIndex = lstArtworks.getSelectionModel().getSelectedIndex();

        // Check if user selected an item
        if (selectedIndex < 0) {
            Utilities.noArtworkSelected();
            return;
        }
        if (artworks.get(selectedIndex) instanceof Painting){
            Painting selectedPainting = (Painting)artworks.get(selectedIndex);
            editPaintingPage(selectedPainting);
        }else if (artworks.get(selectedIndex) instanceof  Sculpture){
            Sculpture selectedSculpture = (Sculpture)artworks.get(selectedIndex);
            editSculpturePage(selectedSculpture);
        }else{
            Utilities.artworkSelectionFailed();
            return;
        }

    }

    /**
     * When the artwork viewer button is pressed in the main menu the artwork list of the user is loaded into
     * the select box.
     *
     * @param artworkList the artwork list of this user.
     */
    public void loadArtworks(ArrayList<Artwork> artworkList){
        this.artworks = artworkList;
        refreshArtworkList();
        System.out.println(Run.database.getAllArtworks().size());
    }

    /**
     * Method that updates the artwork list
     */
    private void refreshArtworkList() {
        // Clear the displayed list
        lstArtworks.getItems().clear();

        // Add each artwork to the displayed list
        for (Artwork c : artworks) {
            // Puts selected data into the list view.
            lstArtworks.getItems().add(c.getArtworkTitle()+ "  :" + c.getArtworkSeller() );
        }

    }

    /**
     *  When the add button is pressed this method opens an edit screen
     */
    private void addPage(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPage.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            AddController controller = fxmlLoader.<AddController>getController();

            Painting newPainting = new Painting();
            Sculpture newSculpture = new Sculpture();

            // load the edit page controller with a new painting and sculpture object with the new id number.
            controller.artworkToAdd(newPainting,newSculpture,artworks.size()-1 );

            Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(Run.EDITP_WINDOW_TITLE);
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

            // Checks what object is being saved by the user, it will either be a painting or sculpture.
            if(newPainting.getArtworkCreator() == null &&
                    !(newSculpture.getArtworkCreator() == null)){
                artworks.add(newSculpture);
            }else if (!(newPainting.getArtworkCreator() == null) &&
                    (newSculpture.getArtworkCreator() == null)){
                artworks.add(newPainting);
            }


            refreshArtworkList();


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  When a painting object is selected in the list, this method opens the appropriate window with
     *  the correct editing boxes
     * @param selectedPainting Painting object selected to be edited.
     */
    private void editPaintingPage(Painting selectedPainting ){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("paintingPage.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            PaintingController controller = fxmlLoader.<PaintingController>getController() ;

            controller.paintingToEditing(selectedPainting);

            Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(Run.EDITP_WINDOW_TITLE);
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

            refreshArtworkList();


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     *  When a sculpture object is selected in the list, this method opens the appropriate window with
     *  the correct editing boxes
     * @param selectedSculpture Sculpture object selected to be edited
     */
    private void editSculpturePage(Sculpture selectedSculpture){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SculpturePage.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            SculptureController controller = fxmlLoader.<SculptureController>getController() ;

            controller.sculptureToEdit(selectedSculpture);

            Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(Run.EDITS_WINDOW_TITLE);
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

            refreshArtworkList();


        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
