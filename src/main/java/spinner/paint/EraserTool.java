package spinner.paint;

import java.awt.*;

public class EraserTool implements Tool {
    private int prevX;
    private int prevY;
    private final int size = 10; // Eraser diameter

    @Override
    public void pressed(Graphics g, int x, int y) {
        prevX = x;
        prevY = y;
        erase(g, x, y);
    }

    @Override
    public void dragged(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.drawLine(prevX, prevY, x, y);
        prevX = x;
        prevY = y;
    }


    private void erase(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillRect(x - 5, y - 5, 10, 10);
    }

    @Override
    public void preview(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(prevX - size/2, prevY - size/2, size, size);
    }

    @Override
    public void released(Graphics g, int x, int y) {
        erase(g, x, y);
    }
}
