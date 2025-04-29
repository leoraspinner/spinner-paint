package spinner.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Draw the BufferedImage to the screen.
 */

public class DrawingComponent extends JComponent
{

    private final BufferedImage image = new BufferedImage(
            800,
            600,
            BufferedImage.TYPE_INT_RGB
    );

    private Tool tool;

    public DrawingComponent()
    {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(image, 0, 0, null);

        tool.preview(g2);
    }

    public void setTool(Tool tool)
    {
        this.tool = tool;
    }

    public BufferedImage getImage()
    {
        return image;
    }

}
