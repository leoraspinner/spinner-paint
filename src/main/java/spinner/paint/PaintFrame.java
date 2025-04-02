package spinner.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static spinner.paint.DrawingComponent.*;



public class PaintFrame extends JFrame {

    private final DrawingComponent canvas = new DrawingComponent();
    private final String[] toolNames = {DRAW, LINE, ERASER};
    private PencilTool pencilTool = new PencilTool();

    public PaintFrame() {
        setTitle("Paint");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);

        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(toolPanel, BorderLayout.NORTH);

        //Basic color buttons
        Color[] basicColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BLACK};
        for (Color color : basicColors) {
            JButton colorButton = new JButton();
            colorButton.setBackground(color);
            colorButton.setPreferredSize(new Dimension(25, 25)); // Smaller buttons
            colorButton.setOpaque(true);
            colorButton.setBorderPainted(false);
            colorButton.addActionListener(e -> canvas.setDrawingColor(color));
            toolPanel.add(colorButton);
        }

        // More Colors button
        JButton colorChooserButton = new JButton("More Colors");
        colorChooserButton.addActionListener(this::openColorChooser);
        toolPanel.add(colorChooserButton);

        // Tool buttons
        for (String toolName : toolNames) {
            JButton toolButton = createToolButton(toolName);
            toolPanel.add(toolButton);
        }

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> canvas.clearCanvas());
        toolPanel.add(clearButton);

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //canvas.handleMousePressed(e.getX(), e.getY());
                pencilTool.pressed(canvas.getImage().getGraphics(), e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                canvas.handleMouseReleased(e.getX(), e.getY());
            }
        });

        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
//                canvas.handleMouseDragged(e.getX(), e.getY());
                Graphics g = canvas.getImage().getGraphics();
                g.setColor(Color.BLACK);
                pencilTool.dragged(g, e.getX(), e.getY());
                canvas.repaint();

            }
        });
    }

    private JButton createToolButton(String toolName) {
        JButton button = new JButton(toolName);
        button.addActionListener(e -> canvas.setCurrentTool(toolName));
        return button;
    }

    private void openColorChooser(ActionEvent e) {
        Color chosenColor = JColorChooser.showDialog(this, "Select Color", canvas.getDrawingColor());
        if (chosenColor != null) {
            canvas.setDrawingColor(chosenColor);
        }
    }

    public static void main(String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}