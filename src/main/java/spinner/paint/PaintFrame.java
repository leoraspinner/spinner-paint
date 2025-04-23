package spinner.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintFrame extends JFrame {
    private final DrawingComponent canvas = new DrawingComponent();
    private final PaintController controller;
    private final Color[] predefinedColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK};

    public PaintFrame() {
        setTitle("Paint");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);

        controller = new PaintController(canvas, new LineTool());

        // Tool buttons
        JPanel toolPanel = new JPanel();
        addToolButton(toolPanel, "Line", new LineTool());
        addToolButton(toolPanel, "Pencil", new PencilTool());
        addToolButton(toolPanel, "Eraser", new EraserTool());

        // Color buttons
        for (Color color : predefinedColors) {
            toolPanel.add(createColorButton(color));
        }

        // More Colors button
        JButton moreColors = new JButton("More Colors");
        moreColors.addActionListener(this::openColorChooser);
        toolPanel.add(moreColors);

        add(toolPanel, BorderLayout.NORTH);

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                controller.mousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                controller.mouseReleased(e);
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                controller.mouseDragged(e);
            }
        });
    }

    private void addToolButton(JPanel panel, String text, Tool tool) {
        JButton btn = new JButton(text);
        btn.addActionListener(e -> controller.setCurrentTool(tool));
        panel.add(btn);
    }

    private JButton createColorButton(Color color) {
        JButton btn = new JButton();
        btn.setBackground(color);
        btn.setPreferredSize(new Dimension(30, 30));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.addActionListener(e -> controller.setCurrentColor(color));
        return btn;
    }

    private void openColorChooser(ActionEvent e) {
        Color chosenColor = JColorChooser.showDialog(
                this,
                "Select Color",
                controller.getCurrentColor()
        );

        if (chosenColor != null) {
            controller.setCurrentColor(chosenColor);
        }
    }

    public static void main(String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}
