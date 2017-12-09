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
    private ArrayList<Artwork> artworksToBidOn = new ArrayList<Artwork>();

    private static ArrayList<Artwork> scrollList = new ArrayList<>();
    private int index = 0;
    private Artwork currentArtwork;
    private boolean painting, sculpture;
    @FXML
    private Button btnPrev, btnNext, btnCompleted, btnWon, btnView;
    @FXML
    private Label labelOf, labelTitle;
    @FXML
    private TextArea descBox;

    @FXML
    private CheckBox checkPainting, checkSculpture;
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

        });

        checkPainting.setOnAction(e -> {
            if (checkPainting.isSelected()) {
                painting = true;

            } else {

                painting = false;
            }
            updateScrollList();
        });
        checkSculpture.setOnAction(e -> {
            if (checkSculpture.isSelected()) {
                sculpture = true;
            } else {
                sculpture = false;
            }
            updateScrollList();
        });
        checkPainting.setSelected(true);
        checkSculpture.setSelected(true);


    }

    private void updateScrollList() {
        scrollList.clear();

        index = 0;
        if ((!painting) && (!sculpture)) {

            noArtworkFound();

        } else {
            for (Artwork artwork : artworksToBidOn) {
                if (painting) {
                    if (artwork instanceof Painting) {
                        scrollList.add(artwork);
                    }
                }
                if (sculpture) {
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
        painting = true;
        sculpture = true;


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
