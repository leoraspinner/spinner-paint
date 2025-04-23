package spinner.paint;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PaintController {
    private Tool currentTool;
    private final DrawingComponent canvas;

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
        return currentTool;
    }

    public void mousePressed(MouseEvent e) {
        Graphics g = canvas.getImage().getGraphics();
        g.setColor(getToolColor());
        currentTool.pressed(g, e.getX(), e.getY());
        g.dispose();
        canvas.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        Graphics g = canvas.getImage().getGraphics();
        g.setColor(getToolColor());
        currentTool.dragged(g, e.getX(), e.getY());
        g.dispose();
        canvas.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        Graphics g = canvas.getImage().getGraphics();
        g.setColor(getToolColor());
        currentTool.released(g, e.getX(), e.getY());
        g.dispose();
        canvas.repaint();
    }

    private Color getToolColor() {
        return (currentTool instanceof EraserTool) ? Color.WHITE : Color.BLACK;
    }
}
