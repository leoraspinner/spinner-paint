package spinner.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintFrame extends JFrame {
    private final DrawingComponent canvas = new DrawingComponent();
    private final PaintController controller;

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

    public static void main(String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}
