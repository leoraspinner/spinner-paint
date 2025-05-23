package spinner.paint;

import java.awt.*;

public class EraserTool implements Tool {
    private int prevX;
    private int prevY;
    private final int size = 10;

    @Override
    public void pressed(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        prevX = x;
        prevY = y;
        erase(g, x, y);
    }

    @Override
    public void dragged(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        erase(g, x, y);
        prevX = x;
        prevY = y;
    }

    private void erase(Graphics g, int x, int y) {
        g.fillRect(x - size / 2, y - size / 2, size, size);
    }

    @Override
    public void preview(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(prevX - size / 2, prevY - size / 2, size, size);
    }

    @Override
    public void released(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        erase(g, x, y);
    }
}
