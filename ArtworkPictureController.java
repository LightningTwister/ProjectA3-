import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

/**
 *  Controller for the viewing picture window
 */
public class ArtworkPictureController {

    private ArrayList<Image> artworkList ;
    private ArrayList<String> artworkPaths;
    private int index ;
    private String artwork;
    @FXML
    private Pane rootPane;

   @FXML
   private Button btnNext, btnPrevious, btnRemove, btnAdd, btnSave;
   @FXML
   private ScrollPane ScrollPane;

    @FXML
    private Label labelOf;


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

    /**
     * Method that saves changes made to the pictures for this object
     */
    private void savePictures(){
        //Don't let a painting have more than one image
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

    /**
     *  Add new paths to images for this object
     */
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

    /**
     *  Reset the gui elements when a picture is changed.
     */
    private void restart(){
        index = 0;
        ScrollPane.setContent(new ImageView(artworkList.get(index)));
        recheckButtons();

    }

    /**
     * Update buttons on page to show the correct buttons for scrolling
     */
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

    /**
     *  Method that shows the pictures for an artwork
     * @param artworkStrings Paths to the pictures for this Artwork
     * @param artwork String to show if this is a painting or a sculpture
     * @param bid Boolean to decide wether or not this page has been opened from the bid viewing page
     */
    public void showPictures(ArrayList<String> artworkStrings, String artwork, boolean bid){
        if(bid){
            btnSave.setVisible(false);
            btnAdd.setVisible(false);
            btnRemove.setVisible(false);

        }
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

    /**
     * Remove a path to a picture for this artwork picture
     */
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
