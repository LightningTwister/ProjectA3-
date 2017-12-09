import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Class that controls how the artwork viewing gui works
 *
 * @author Tim Watson 880158
 * @version 2
 */
public class artworkViewerController {
    private static ArrayList<Artwork> artworksForUser = new ArrayList<Artwork>();

    @FXML
    private Button btnEdit, btnAdd;
    @FXML
    private ListView<String> lstArtworks;


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
    private void changeScreen() {

        int selectedIndex = lstArtworks.getSelectionModel().getSelectedIndex();


        // Check if user selected an item
        if (selectedIndex < 0) {
            Utilities.nothingSelected();
            return;
        }
        if (artworksForUser.get(selectedIndex) instanceof Painting) {
            Painting selectedPainting = (Painting) artworksForUser.get(selectedIndex);

            editPaintingPage(selectedPainting);
        } else if (artworksForUser.get(selectedIndex) instanceof Sculpture) {
            Sculpture selectedSculpture = (Sculpture) artworksForUser.get(selectedIndex);

            editSculpturePage(selectedSculpture);
        } else {
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
    public void loadArtworks(ArrayList<Artwork> artworkList) {
        this.artworksForUser = new ArrayList<>(artworkList);


        refreshArtworkList();
    }

    /**
     * Method that updates the artwork list
     */
    private void refreshArtworkList() {
        // Clear the displayed list
        lstArtworks.getItems().clear();
        //artworksForUser.clear();

        // Add each artwork to the displayed list
        for (Artwork c : Run.database.getAllArtworks()) {
            // Puts selected data into the list view.

            if (c.getArtworkSeller() == (Run.database.getCurrentUser().getId())) {
                artworksForUser.add(c);
                lstArtworks.getItems().add(String.format("%-30s%-30s%-5s", "Title: " + c.getArtworkTitle(), "Creator: "
                        + c.getArtworkCreator(), "Reserve: " + c.getReservePrice()));


            }
        }
    }

    /**
     * When the add button is pressed this method opens an edit screen
     */
    private void addPage() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/ArtworkPage.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            ArtworkController controller = fxmlLoader.<ArtworkController>getController();

            Painting newPainting = new Painting();
            Sculpture newSculpture = new Sculpture();

            int index = (Run.database.getAllArtworks().get(Run.database.getAllArtworks().size() - 1).getId() + 1);

            // load the edit page controller with a new painting and sculpture object with the new id number.
            controller.artworkToAdd(newPainting, newSculpture, index);

            Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(Run.EDITP_WINDOW_TITLE);
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

            // Checks what object is being saved by the user, it will either be a painting or sculpture.
            if (newPainting.getArtworkCreator() == null &&
                    !(newSculpture.getArtworkCreator() == null)) {
                artworksForUser.add(newSculpture);
            } else if (!(newPainting.getArtworkCreator() == null) &&
                    (newSculpture.getArtworkCreator() == null)) {
                artworksForUser.add(newPainting);
            }


            refreshArtworkList();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * When a painting object is selected in the list, this method opens the appropriate window with
     * the correct editing boxes
     *
     * @param selectedPainting Painting object selected to be edited.
     */
    private void editPaintingPage(Painting selectedPainting) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/ArtworkPage.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            ArtworkController controller = fxmlLoader.<ArtworkController>getController();
            controller.getArtwork(selectedPainting);

            Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(Run.EDITP_WINDOW_TITLE);
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

            refreshArtworkList();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * When a sculpture object is selected in the list, this method opens the appropriate window with
     * the correct editing boxes
     *
     * @param selectedSculpture Sculpture object selected to be edited
     */
    private void editSculpturePage(Sculpture selectedSculpture) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/ArtworkPage.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            ArtworkController controller = fxmlLoader.<ArtworkController>getController();

            controller.getArtwork(selectedSculpture);

            Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(Run.EDITS_WINDOW_TITLE);
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

            refreshArtworkList();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show all the artworks the current user has sold in list format
     */
    public void soldView() {
        btnAdd.setVisible(false);
        btnEdit.setVisible(false);
        artworksForUser.clear();
        lstArtworks.getItems().clear();
        artworksForUser = Run.database.getCompletedAuctions();

        for (Artwork c : artworksForUser) {


            lstArtworks.getItems().add(c.getArtworkTitle() + "  " + c.getHighestBid().getAmount() + "  " +
                    Run.database.getUser(c.getHighestBid().getUserID()).getUserName());

        }

    }

    /**
     * Show all artworks the current user has won in list format
     */
    public void wonView() {
        btnAdd.setVisible(false);
        btnEdit.setVisible(false);
        artworksForUser.clear();
        lstArtworks.getItems().clear();

        for (Integer a : Run.database.getCurrentUser().getWonArtworks()) {
            System.out.println(a);
            artworksForUser.add(Run.database.getArtwork(a));
            lstArtworks.getItems().add(Run.database.getArtwork(a).getArtworkTitle());
        }

    }

}


