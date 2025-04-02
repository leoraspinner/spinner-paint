package spinner.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * Draw the BufferedImage to the screen
 * */

public class DrawingComponent extends JComponent {
    //should only be drawing other methods to the screen
    public static final String DRAW = "Draw";
    public static final String LINE = "Line";
    public static final String ERASER = "Eraser";

    private final BufferedImage image = new BufferedImage(
            800,
            600,
            BufferedImage.TYPE_INT_RGB);
    private int oldx = -1;
    private int oldy = -1;
    private int startX = -1; // For Line Tool
    private int startY = -1; // For Line Tool
    private int currentX = -1; // for preview
    private int currentY = -1; //or preview
    private String currentTool = DRAW;
    private Color drawingColor = Color.BLACK;
    //private Tool currentTool;
    //Private PencilTool penciltool =

    public DrawingComponent() {
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }
    public

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);

        // preview line for LINE
        if (currentTool.equals(LINE) && startX != -1 && startY != -1 && currentX != -1 && currentY != -1) {
            g.setColor(drawingColor);
            g.drawLine(startX, startY, currentX, currentY); // Temporary line
        }
    }

    public void handleMousePressed(int x, int y) {
        if (currentTool.equals(LINE)) {
            startX = x;
            startY = y;
        } else if (currentTool.equals(DRAW) || currentTool.equals(ERASER)) {
            oldx = x;
            oldy = y; // Save previous point for freehand/eraser drawing
        }
    }

    public void handleMouseDragged(int x, int y) {
        if (currentTool.equals(LINE)) {
            currentX = x;
            currentY = y; // Update current position for preview
        } else if (currentTool.equals(DRAW)) {
            Graphics g = image.getGraphics();
            g.setColor(drawingColor);
            if (oldx != -1 && oldy != -1) {
                g.drawLine(oldx, oldy, x, y); // drawing line
            }
            oldx = x;
            oldy = y;
        } else if (currentTool.equals(ERASER)) {
            Graphics g = image.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(x - 5, y - 5, 10, 10); //small rectangle eraser
        }
        repaint();
    }

    public void handleMouseReleased(int x, int y) {
        Graphics g = image.getGraphics();
        if (currentTool.equals(LINE)) {
            g.setColor(drawingColor);
            g.drawLine(startX, startY, x, y);
            startX = startY = currentX = currentY = -1;
        } else if (currentTool.equals(DRAW) || currentTool.equals(ERASER)) {
            oldx = -1;
            oldy = -1;
        }
        repaint(); //update canvas
    }

    public void setCurrentTool(String tool) {
        this.currentTool = tool; // Change  current tool
    }

    public Color getDrawingColor() {
        return drawingColor; // Return drawing color
    }

    public void setDrawingColor(Color color) {
        this.drawingColor = color; // Updates the drawing color
    }

    public void clearCanvas() { // Erases the whole canvas
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        repaint();
    }
}
