package spinner.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintFrame extends JFrame {

    private final DrawingComponent canvas = new DrawingComponent();
    private final Color[] predefinedColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK};
    private final String[] toolNames = {"Draw", "Line", "Eraser", "Clear"};

    public PaintFrame() {
        setTitle("Paint");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);

        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(toolPanel, BorderLayout.NORTH);

        // Color buttons
        for (Color color : predefinedColors) {
            JButton colorButton = createColorButton(color);
            toolPanel.add(colorButton);
        }

        // More Colors button
        JButton moreColorsButton = new JButton("More Colors");
        moreColorsButton.addActionListener(this::openColorChooser);
        toolPanel.add(moreColorsButton);

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
                canvas.handleMousePressed(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                canvas.handleMouseReleased(e.getX(), e.getY());
            }
        });

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                canvas.handleMouseDragged(e.getX(), e.getY());
            }
        });
    }

    private JButton createColorButton(Color color) {
        JButton button = new JButton();
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(30, 30));
        button.addActionListener(e -> canvas.setDrawingColor(color));
        return button;
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