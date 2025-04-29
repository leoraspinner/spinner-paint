package spinner.paint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EraserTool implements Tool {
    private int prevX;
    private int prevY;
    private final int size = 10;
    private BasicStroke eraserStroke;

    public EraserTool() {
        //eraser stroke
        eraserStroke = new BasicStroke(size, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND);
    }

    @Override
    public void pressed(BufferedImage image, Graphics2D g, int x, int y) {
        g.setColor(Color.WHITE);
        g.setStroke(eraserStroke);
        prevX = x;
        prevY = y;
        g.drawLine(x, y, x, y);
    }

    @Override
    public void dragged(Graphics2D g, int x, int y) {
        g.setColor(Color.WHITE);
        g.setStroke(eraserStroke);
        g.drawLine(prevX, prevY, x, y);
        prevX = x;
        prevY = y;
    }


    @Override
    public void preview(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        g.setStroke(new BasicStroke(1));
        g.drawOval(prevX - size / 2, prevY - size / 2, size, size);
    }

    // Changed from Graphics to Graphics2D
    @Override
    public void released(Graphics2D g, int x, int y) {
        g.setColor(Color.WHITE);
        g.setStroke(eraserStroke);
        g.drawLine(prevX, prevY, x, y);
    }
}
