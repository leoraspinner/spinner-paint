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
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);

        if(tool != null) tool.preview(g);//tool.preview(g);
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
