package spinner.paint;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Tool
{
    void pressed(BufferedImage image, Graphics2D g, int x, int y);

    void dragged(Graphics2D g, int x, int y);

    void preview(Graphics2D g);

    void released(Graphics2D g, int x, int y);

}
