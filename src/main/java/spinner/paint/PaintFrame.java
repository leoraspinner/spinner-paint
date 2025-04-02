package spinner.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PaintFrame extends JFrame
{
    private final DrawingComponent canvas = new DrawingComponent();
    private Tool tool = new LineTool();

    public PaintFrame()
    {
        setTitle("Paint");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);

        canvas.setTool(tool);

        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics g = canvas.getImage().getGraphics();
                g.setColor(Color.BLACK);
                tool.dragged(canvas.getImage().getGraphics(), e.getX(), e.getY());
                canvas.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // No action needed
            }
        });

        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Graphics g = canvas.getImage().getGraphics();
                g.setColor(Color.BLACK);
                tool.pressed(canvas.getImage().getGraphics(), e.getX(), e.getY());
                canvas.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Graphics g = canvas.getImage().getGraphics();
                g.setColor(Color.BLACK);
                tool.released(canvas.getImage().getGraphics(), e.getX(), e.getY());
                canvas.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // No action needed
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // No action needed
            }
        });
    }

    public static void main(String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}