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
 *
 *  @author Tim Watson 880158
 *  @version 1
 */
public class BrowseBidsController{
    private  ArrayList<Artwork> artworksToBidOn = new ArrayList<Artwork>();
    private  ArrayList<Artwork> fullList = new ArrayList<>();
    private static ArrayList<Artwork> scrollList = new ArrayList<>();
    private int index = 0;
    private Artwork currentArtwork;
    @FXML
    private Button btnPrev,btnNext,btnCompleted,btnWon,btnView;
    @FXML
    private Label labelOf, labelTitle,labelDesc;
    @FXML
    private TextArea descBox;
    @FXML
    ListView<String> lstArtworks;
    @FXML
    private CheckBox checkPainting, checkSculpture;
    @FXML
    private ImageView imgView;




    public void initialize() {
        btnPrev.setOnAction(e ->{

        });
        btnNext.setOnAction(e ->{

        });
        btnCompleted.setOnAction(e ->{

        });
        btnWon.setOnAction(e -> {

        });
        btnView.setOnAction(e ->{

        });

        checkPainting.setOnAction(e ->{
            Utilities.cancelled();
        });
        checkSculpture.setOnAction(e ->{
            Utilities.wrongInputFound();
        });
        checkPainting.setSelected(true);
        checkSculpture.setSelected(true);



    }




    public void loadArtworks(ArrayList<Artwork> artworkList){

        this.fullList = artworkList;

        for(Artwork art: artworkList){
            if(!(art.getArtworkSeller() ==Run.database.getCurrentUser().getId())&&(art.getNumOfBids() > 0)){
                this.artworksToBidOn.add(art);
            }
        }

        scrollList = artworksToBidOn;

        if(scrollList.get(index)== null){
            Utilities.artworkSelectionFailed();

        }else{

                currentArtwork = scrollList.get(index);
                labelTitle.setText("Title: "+currentArtwork.getArtworkTitle());
                imgView.setImage(Utilities.getImage(currentArtwork.getPicture()));
                descBox.setText(currentArtwork.getArtworkDescription());
                btnPrev.setVisible(false);
                recheckButtons();
        }

    }
    private void recheckButtons(){
        if (index == 0){
            btnPrev.setVisible(false);
        }else{
            btnPrev.setVisible(true);
        }
        if (scrollList.size() ==1){
            btnNext.setVisible(false);
        }
        else{
            btnNext.setVisible(true);
        }
        labelOf.setText(index+ 1+" of "+ scrollList.size());
    }



}
