import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
    private static ArrayList<UserProfiles> profiles = new ArrayList<>();

    @FXML
    private Button btnEdit, btnAdd, btnViewUser, backButton;
    @FXML
    private ListView<String> lstArtworks;

    @FXML
    private Label typeOfList;


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
        btnViewUser.setOnAction(e -> {
            openUser();
        });

        backButton.setOnAction(e -> {
            Stage editScene = (Stage) ((Node)e.getSource()).getScene().getWindow();
            backToMainMenu(editScene);
        });

        //refreshArtworkList();
    }

    /**
     * Gets the selected user from the list view and opens a window of limited details to be viewed
     */
    private void openUser() {
        int selectedIndex = lstArtworks.getSelectionModel().getSelectedIndex();


        // Check if user selected an item
        if (selectedIndex < 0) {
            Utilities.nothingSelected();
            return;
        } else {
            try {


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/faveUserPage.fxml"));
                Pane root = (Pane) fxmlLoader.load();

                faveUserController controller = fxmlLoader.<faveUserController>getController();


                controller.getUser(profiles.get(selectedIndex));

                Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
                Stage editStage = new Stage();
                editStage.setScene(editScene);
                editStage.setTitle("Favourite User");
                editStage.initModality(Modality.APPLICATION_MODAL);
                editStage.showAndWait();


            } catch (Exception e) {
                Utilities.nothingSelected();

            }

        }

    }

    /**
     * If the users are being viewed this method loads all users that aren't the current user of the system
     */
    public void loadUsers() {
        typeOfList.setText("All Users: ");
        btnEdit.setVisible(false);
        btnAdd.setVisible(false);

        for (UserProfiles a : Main.database.getAllUsers()) {
            if (a.getId() != Main.database.getCurrentUser().getId()) {
                profiles.add(a);
                lstArtworks.getItems().add(a.getUserName());
            }
        }
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
     * When the artwork viewer button is pressed in the main menu the artwork list of the user is loaded into
     * the select box.
     *
     * @param artworkList the artwork list of this user.
     */
    public void loadArtworks(ArrayList<Artwork> artworkList) {
        this.artworksForUser = new ArrayList<>(artworkList);
        btnViewUser.setVisible(false);

        refreshArtworkList();
    }

    /**
     * Method that updates the artwork list
     */
    private void refreshArtworkList() {
        // Clear the displayed list
        lstArtworks.getItems().clear();
        artworksForUser.clear();

        if (Main.database.getAllArtworks().size() < 1) {
            Utilities.noArtworksInDatabase();

        } else {

            // Add each artwork to the displayed list
            for (Artwork c : Main.database.getAllArtworks()) {

                if (c.getArtworkSeller() == (Main.database.getCurrentUser().getId())) {
                    artworksForUser.add(c);
                    lstArtworks.getItems().add(String.format("%-30s%-30s%-5s", "Title: " + c.getArtworkTitle(), "Creator: "
                            + c.getArtworkCreator(), "Reserve: " + c.getReservePrice()));


                }
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

            int index;
            if (Main.database.getAllArtworks().size() == 0) {
                index = 0;
            } else {
                index = Main.database.getAllArtworks().get(Main.database.getAllArtworks().size() - 1).getId() + 1;
            }


            // load the edit page controller with a new painting and sculpture object with the new id number.
            controller.artworkToAdd(newPainting, newSculpture, index);

            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(Main.EDITP_WINDOW_TITLE);
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();

            // Checks what object is being saved by the user, it will either be a painting or sculpture.
            if (newPainting.getArtworkCreator() == null &&
                    !(newSculpture.getArtworkCreator() == null)) {
                Main.database.addArtwork((newSculpture));
            } else if (!(newPainting.getArtworkCreator() == null) &&
                    (newSculpture.getArtworkCreator() == null)) {
                Main.database.addArtwork(newPainting);
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

            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(Main.EDITP_WINDOW_TITLE);
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

            Scene editScene = new Scene(root, Main.EDIT_WINDOW_WIDTH, Main.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(Main.EDITS_WINDOW_TITLE);
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
        typeOfList.setText("Sold Artworks: ");
        btnViewUser.setVisible(false);
        btnAdd.setVisible(false);
        btnEdit.setVisible(false);
        artworksForUser.clear();
        lstArtworks.getItems().clear();
        artworksForUser = Main.database.getCompletedAuctions();

        for (Artwork c : artworksForUser) {


            lstArtworks.getItems().add(c.getArtworkTitle() + "  " + c.getHighestBid().getAmount() + "  " +
                    Main.database.getUser(c.getHighestBid().getUserID()).getUserName());

        }

    }

    /**
     * Show all artworks the current user has won in list format
     */
    public void wonView() {
        typeOfList.setText("Won Artworks: ");
        btnViewUser.setVisible(false);
        btnAdd.setVisible(false);
        btnEdit.setVisible(false);
        artworksForUser.clear();
        lstArtworks.getItems().clear();

        for (Integer a : Main.database.getCurrentUser().getWonArtworks()) {
            System.out.println(a);
            artworksForUser.add(Main.database.getArtwork(a));
            lstArtworks.getItems().add(Main.database.getArtwork(a).getArtworkTitle());
        }

    }

}


