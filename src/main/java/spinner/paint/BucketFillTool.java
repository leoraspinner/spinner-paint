package spinner.paint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BucketFillTool implements Tool
{
    private Color targetColor;
    private Color fillColor;
    private BufferedImage image;
    private boolean [][] visited; //tracks the visited pixels

    @Override
    public void pressed(BufferedImage image, Graphics2D g, int x, int y)
    {
        this.image = image;
        this.fillColor = g.getColor();
        this.targetColor = new Color(image.getRGB(x, y));
        this.visited = new boolean[image.getWidth()][image.getHeight()];

        //flood fill *What is this?
        floodFill(x, y);
        canvasRepaint();


    }

    private void floodFill(int x, int y) {
        //boundary check
        if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight()) {
            return;
        }

        //Color check
        int currentColor = image.getRGB(x, y);
        if (currentColor != targetColor.getRGB() || currentColor == fillColor.getRGB()) {
            return;
        }

        //fill pixel
        image.setRGB(x, y, fillColor.getRGB());

        //recursion for 4 directions
        floodFill(x + 1, y); //right
        floodFill(x - 1, y); //left
        floodFill(x, y + 1); //down
        floodFill(x, y - 1); //up
    }

    private void canvasRepaint()
    {
    }

    @Override
    public void dragged(Graphics2D g, int x, int y)
    {

    }

    @Override
    public void preview(Graphics2D g)
    {

    }

    @Override
    public void released(Graphics2D g, int x, int y)
    {

    }
}
