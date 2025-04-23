package spinner.paint;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PaintController {
    private Tool currentTool;
    private final DrawingComponent canvas;
    private Color currentColor = Color.BLACK;

    public PaintController(DrawingComponent canvas, Tool initialTool) {
        this.canvas = canvas;
        this.currentTool = initialTool;
        this.canvas.setTool(currentTool);
    }

    public void setCurrentTool(Tool tool) {
        this.currentTool = tool;
        this.canvas.setTool(tool);
    }

    public Tool getCurrentTool() {
        return this.currentTool;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public Color getCurrentColor() {
        return this.currentColor;
    }

    public void mousePressed(MouseEvent e) {
        Graphics g = canvas.getImage().getGraphics();
        g.setColor(currentColor);
        currentTool.pressed(g, e.getX(), e.getY());
        g.dispose();
        canvas.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        Graphics g = canvas.getImage().getGraphics();
        g.setColor(currentColor);
        currentTool.dragged(g, e.getX(), e.getY());
        g.dispose();
        canvas.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        Graphics g = canvas.getImage().getGraphics();
        g.setColor(currentColor);
        currentTool.released(g, e.getX(), e.getY());
        g.dispose();
        canvas.repaint();
    }

}
