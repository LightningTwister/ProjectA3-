import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;


import java.util.ArrayList;

public class ArtworkPictureController {

    private ArrayList<Image> artworkList ;
    private ArrayList<String> artworkPaths;
    private int index ;
    private String artwork;
    @FXML
    Pane rootPane;

   @FXML
   Button btnNext, btnPrevious, btnRemove, btnAdd, btnSave;

    @FXML
    ScrollPane ScrollPane;

    @FXML
    Label labelOf;


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
            deletePicture();

        });
        btnAdd.setOnAction(e -> {
            addPicture();
        });
        btnSave.setOnAction(e -> {
            savePictures();

        });

    }
    private void savePictures(){
        if (artwork.equals("painting")) {
            if (artworkList.size() > 1) {
                Utilities.maximumPicturesReached();
                return;
            }
        }

        if(artworkList.size() == 1 && artworkPaths.get(0).equals(Run.database.NO_IMAGE_PATH)){
            Utilities.noImageFound();
            return;
        }else{
            Utilities.savedInput();
            Utilities.closeWindow(rootPane);
        }
    }
    private void addPicture(){
        if(artwork.equals("painting")&& artworkPaths.size() >= 1
                && !(artworkPaths.get(0).equals(Run.database.NO_IMAGE_PATH))){
            Utilities.maximumPicturesReached();

        }else{
            String fileLocation = Utilities.changeImage("Select an artwork picture", "Data/ArtworkPictures");
            if (fileLocation.equals("FAILED")){
                Utilities.noImageFound();
            }else{
                if(artworkPaths.size() == 1){
                    //If a user is adding a picture
                    //and the only picture stored is the default no image picture
                    //remove default picture
                    if (artworkPaths.get(0).equals( Run.database.NO_IMAGE_PATH)){
                        artworkPaths.remove(0);
                        artworkList.remove(0);
                    }
                }
                artworkList.add(Utilities.getImage("file:"+fileLocation));
                artworkPaths.add("file:"+fileLocation);
            }
            restart();
        }


    }
    private void restart(){
        index = 0;
        ScrollPane.setContent(new ImageView(artworkList.get(index)));
        recheckButtons();

    }
    private void recheckButtons(){
        if (index == 0){
            btnPrevious.setVisible(false);
        }else{
            btnPrevious.setVisible(true);
        }
        if (artworkList.size() ==1){
            btnNext.setVisible(false);
        }
        else{
            btnNext.setVisible(true);
        }
        labelOf.setText(index+ 1+" of "+ artworkList.size());
    }

    public void showPictures(ArrayList<String> artworkStrings, String artwork){
        ArrayList<Image> newArtworkList = new ArrayList<>();
        this.artwork = artwork;
        this.index = 0;
        this.artworkPaths = artworkStrings;

        if (artworkPaths.size() ==0){

            newArtworkList.add(Utilities.getImage(Run.database.NO_IMAGE_PATH));
            artworkPaths.add(Run.database.NO_IMAGE_PATH);


        }else{

            for(String a: artworkStrings){
                newArtworkList.add(Utilities.getImage(a));

            }
        }

        artworkList = newArtworkList;

        ScrollPane.setContent(new ImageView(artworkList.get(index)));
        btnPrevious.setVisible(false);
        if (artworkList.size() > 1){
            btnNext.setVisible(true);
        }else{
            btnNext.setVisible(false);
        }
        labelOf.setText(index +1 +" of "+ artworkList.size());

    }
    private void deletePicture(){
        this.artworkPaths.remove(index);
        this.artworkList.remove(index);
        index = 0;

        if (artworkList.size() == 0){
            this.artworkPaths.add(Run.database.NO_IMAGE_PATH);
            this.artworkList.add(Utilities.getImage(artworkPaths.get(index)));
            btnAdd.setVisible(true);
        }
        restart();

    }
}
