package spinner.paint;

import java.awt.*;

public interface Tool
{
    void pressed(Graphics g, int x, int y);

    void dragged(Graphics g, int x, int y);

    void preview(Graphics g);

    void released(Graphics g, int x, int y);

}
