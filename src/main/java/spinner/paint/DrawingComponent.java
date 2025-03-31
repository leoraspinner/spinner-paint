package spinner.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingComponent extends JComponent {
    public final String Draw = "Draw";
    public final String Line = "Line";
    public final String Eraser = "Eraser";

    private final BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
    private int oldx = -1;
    private int oldy = -1;
    private int startX = -1; // For Line Tool
    private int startY = -1; // For Line Tool
    private String currentTool = "Draw";
    private Color drawingColor = Color.BLACK; // Default drawing color

    public DrawingComponent() {
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public void handleMousePressed(int x, int y) {
        if (currentTool.equals(Line)) {
            startX = x;
            startY = y; // Save starting point for the line
        } else if (currentTool.equals(Draw) || currentTool.equals("Eraser")) {
            oldx = x;
            oldy = y; // Save previous point for freehand/eraser drawing
        }
    }

    public void handleMouseDragged(int x, int y) {
        Graphics g = image.getGraphics();

        if (currentTool.equals(Draw)) {
            g.setColor(drawingColor);
            if (oldx != -1 && oldy != -1) {
                g.drawLine(oldx, oldy, x, y); // Draw freehand line
            }
            oldx = x;
            oldy = y;

        } else if (currentTool.equals(Eraser)) {
            g.setColor(Color.WHITE); // Eraser uses white color (background)
            g.fillRect(x - 5, y - 5, 10, 10); // Draw a small rectangle to erase
            oldx = x;
            oldy = y;

        }

        repaint();
    }

    public void handleMouseReleased(int x, int y) {
        Graphics g = image.getGraphics();

        if (currentTool.equals(Line)) {
            g.setColor(drawingColor);
            g.drawLine(startX, startY, x, y); // draws straight line from startX/startY to current position
            repaint();

        } else if (currentTool.equals(Draw) || currentTool.equals(Eraser)) {
            oldx = -1;
            oldy = -1; // Resets previous points for freehand and eraser drawing
        }
    }

    public void setCurrentTool(String tool) {
        this.currentTool = tool; // Change the current tool
    }

    public Color getDrawingColor() {
        return drawingColor; // Return the current drawing color
    }

    public void setDrawingColor(Color color) {
        this.drawingColor = color; // Updates the drawing color
    }

    public void clearCanvas() { //erases the whole canvas
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        repaint();
    }
}
