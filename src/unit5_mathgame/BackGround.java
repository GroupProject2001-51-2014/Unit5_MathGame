
package unit5_mathgame;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Creates a Panel that draws the background
 * @author John Mambo <john.mambo@gmail.com>
 */
public class BackGround extends JPanel{
    private Image imageOrg = null;  // Original Image
    private Image image = null;     // Adjusted Image
    
    /**
     * Constructor for the BackGround class
     * @param frame That will be used as the display container.
     */
    public BackGround(final JFrame frame){        
        Container panel = frame.getContentPane();
        
        try {
            imageOrg = ImageIO.read(this.getClass().getResource("/Image/background_1.jpg"));
            image = imageOrg.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        /**
         * Using anonymous method to add a listener to the frame 
         * which will be used to adjust the dimensions of the image
         * based on changes of the same to the frame.
         */
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final int width = frame.getWidth();
                final int height = frame.getHeight();
                image = width > 0 && height > 0 ? imageOrg.getScaledInstance(width, height, Image.SCALE_SMOOTH) : imageOrg;
                frame.repaint();
            }
        });
    }//end ctor
    
    /**
     * Draws the image on to the panel
     * @param g Graphics object that we use to draw the image
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (image != null)
            g.drawImage(image, 0, 0, null);
    }
}
