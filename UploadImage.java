
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author Hugh
 */
public class UploadImage {
    private BufferedImage toReturn;
    
    private BufferedImage returnImage() {
        return toReturn;
    }
    
    private void setImage(String location) {
        File f = new File(location);
        BufferedImage i;
        try {
            i = ImageIO.read(f);
            toReturn = i;
        } catch (IOException ex) {
            Logger.getLogger(UploadImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BufferedImage uploadImage(String location) {
        setImage(location);
        return returnImage();
    }
}
