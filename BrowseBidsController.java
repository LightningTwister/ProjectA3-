import java.awt.*;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * @author Tim Watson 880158
 * @version 1
 */
public class BrowseBidsController {
    private ArrayList<Artwork> artworksToBidOn = new ArrayList<>();

    private static ArrayList<Artwork> scrollList = new ArrayList<>();
    private static ArrayList<Artwork> faveUserList = new ArrayList<>();
    private int index = 0;
    private Artwork currentArtwork;

    @FXML
    private Button btnPrev, btnNext, btnCompleted, btnWon, btnView;
    @FXML
    private Label labelOf, labelTitle;
    @FXML
    private TextArea descBox;

    @FXML
    private CheckBox checkPainting, checkSculpture, checkFave;
    @FXML
    private ImageView imgView;


    public void initialize() {
        btnPrev.setOnAction(e -> {
            index = index - 1;
            currentArtwork = scrollList.get(index);
            nextArtwork();

        });
        btnNext.setOnAction(e -> {
            index = index + 1;
            currentArtwork = scrollList.get(index);
            nextArtwork();
        });
        btnCompleted.setOnAction(e -> {

        });
        btnWon.setOnAction(e -> {

        });
        btnView.setOnAction(e -> {
            viewSelectedArtwork();

        });

        checkPainting.setOnAction(e -> {

            scrollList.clear();
            updateScrollList(artworksToBidOn);
        });
        checkSculpture.setOnAction(e -> {

            scrollList.clear();
            updateScrollList(artworksToBidOn);
        });
        checkFave.setOnAction(e ->{
            scrollList.clear();
            faveUserList.clear();
            faveUsersOnly();

        });
        checkPainting.setSelected(true);
        checkSculpture.setSelected(true);
        checkFave.setSelected(false);


    }
    private void viewSelectedArtwork(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/ArtworkPage.fxml"));
            BorderPane root = (BorderPane) fxmlLoader.load();

            ArtworkController controller = fxmlLoader.<ArtworkController>getController() ;




            // load the edit page controller with a new painting and sculpture object with the new id number.
            controller.artworkToBid(currentArtwork);

            Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(Run.EDITP_WINDOW_TITLE);
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();




        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void faveUsersOnly(){

            if (checkFave.isSelected()){
                for (Artwork artwork: artworksToBidOn){
                    if(Run.database.getCurrentUser().getFaveUsers().contains(artwork.getArtworkSeller()) ){
                        faveUserList.add(artwork);
                    }
                }
                updateScrollList(faveUserList);
            }else{
                updateScrollList(artworksToBidOn);
        }

    }


    private void updateScrollList(ArrayList<Artwork> checkList) {


        index = 0;
        if ((!checkPainting.isSelected()) && (!checkSculpture.isSelected())) {

            noArtworkFound();

        } else {
            for (Artwork artwork : checkList) {
                if (checkPainting.isSelected()) {
                    if (artwork instanceof Painting) {
                        scrollList.add(artwork);
                    }
                }
                if (checkSculpture.isSelected()) {
                    if (artwork instanceof Sculpture) {

                        scrollList.add(artwork);
                    }
                }
            }
            if (scrollList.size() < 1) {
                noArtworkFound();
            } else {

                currentArtwork = scrollList.get(index);
                nextArtwork();
            }
        }
    }

    private void noArtworkFound() {
        labelTitle.setText("Title: NO ARTWORK COULD BE FOUND");
        imgView.setImage(Utilities.getImage(Run.database.NO_IMAGE_PATH));
        descBox.setText("");
        btnPrev.setVisible(false);
        btnNext.setVisible(false);
        labelOf.setText("0 of 0");
    }


    public void loadArtworks(ArrayList<Artwork> artworkList) {

        for (Artwork art : artworkList) {
            if (!(art.getArtworkSeller() == Run.database.getCurrentUser().getId()) && (art.getNumOfBids() > 0)) {

                this.artworksToBidOn.add(art);
            }
        }

        scrollList = new ArrayList<>(artworksToBidOn);

        if (scrollList.get(index) == null) {
            Utilities.artworkSelectionFailed();

        } else {

            currentArtwork = scrollList.get(index);
            nextArtwork();
        }

    }

    private void nextArtwork() {
        labelTitle.setText("Title: " + currentArtwork.getArtworkTitle());
        imgView.setImage(Utilities.getImage(currentArtwork.getPicture()));
        descBox.setText(currentArtwork.getArtworkDescription());
        btnPrev.setVisible(false);
        recheckButtons();

    }

    private void recheckButtons() {
        if (index == 0) {
            btnPrev.setVisible(false);
        } else {
            btnPrev.setVisible(true);
        }
        if (scrollList.size() - 1 == index) {
            btnNext.setVisible(false);
        } else {
            btnNext.setVisible(true);
        }
        labelOf.setText(index + 1 + " of " + scrollList.size());
    }


}
