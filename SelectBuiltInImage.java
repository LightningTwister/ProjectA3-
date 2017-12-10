
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hugh
 */
public class SelectBuiltInImage extends UploadImage {
    public SelectBuiltInImage() {
        builtInImages[0] = "loss.jpg";
        builtInImages[1] = "vangogh.png";
        builtInImages[2] = "grofit.png";
        //and so on and so forth, you get the idea
    }
    private static final String[] builtInImages = new String[10];
    
    private String getInbuiltLocation(int i) {
        return builtInImages[i];
    }
    
    public BufferedImage getInbuiltImage(int i) {
        String tempstore1 = getInbuiltLocation(i);
        return uploadImage(tempstore1);
    }
}
