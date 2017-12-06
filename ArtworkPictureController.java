import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ArtworkPictureController {

    private ArrayList<Image> artworkList ;
    private int index ;
    @FXML
    Pane rootPane;

   @FXML
   Button btnNext, btnPrevious, btnRemove;

    @FXML
    ScrollPane ScrollPane;


    /**
     *  When the artwork add page is opened this method links buttons to methods and radio buttons
     */
    public void initialize() {
        btnNext.setOnAction(e ->{
            index ++;
            if (index == artworkList.size()-1){
                btnNext.setVisible(false);
            }
            ScrollPane.setContent(new ImageView(artworkList.get(index)));
            btnPrevious.setVisible(true);
        });
        btnPrevious.setOnAction(e ->{
            index --;
            if(index == 0){
                btnPrevious.setVisible(false);
            }
            ScrollPane.setContent(new ImageView(artworkList.get(index)));
            btnNext.setVisible(true);
        });
        btnRemove.setOnAction(e -> {

        });

    }

    public void showPictures(ArrayList<String> artworkStrings){
        ArrayList<Image> newArtworkList = new ArrayList<>();
        for(String a: artworkStrings){

            newArtworkList.add(Utilities.getImage(a));

        }
        artworkList = newArtworkList;

        ScrollPane.setContent(new ImageView(artworkList.get(index)));
        btnPrevious.setVisible(false);
        if (artworkList.size() > 1){
            btnNext.setVisible(true);
        }else{
            btnNext.setVisible(false);
        }


    }
}
