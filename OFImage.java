import javafx.scene.image.*;
import javafx.scene.paint.Color;

/**
 * OFImage is a class that defines an image in OF (Objects First) format.
 * It is a writable image that adds getPixel and setPixel methods to the 
 * standard JavaFX images classes.
 * 
 * @author Michael Kölling
 * @version 2.1
 */
public class OFImage extends WritableImage
{
    /**
     * Create an OFImage copied from a BufferedImage.
     * @param image The image to copy.
     */
    public OFImage(Image image)
    {
         super(image.getPixelReader(), (int)image.getWidth(), (int)image.getHeight());
    }

    /**
     * Create an OFImage with specified size and unspecified content.
     * @param width The width of the image.
     * @param height The height of the image.
     */
    public OFImage(int width, int height)
    {
        super(width, height);
    }
}

