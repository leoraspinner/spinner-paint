package spinner.paint;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

public class LineToolTest {

    @Test
    void basicLineDrawing() {
        // Given
        LineTool tool = new LineTool();
        BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);

        // When
        tool.pressed(g, 10, 10);
        tool.dragged(g, 30, 40);
        tool.released(g, 30, 40);

        // Then
        assertEquals(Color.BLACK.getRGB(), img.getRGB(10, 10));
        assertEquals(Color.BLACK.getRGB(), img.getRGB(30, 40));
    }

    @Test
    void coordinatesUpdateOnDrag() {
        // Given
        LineTool tool = new LineTool();
        Graphics dummy = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics();

        // When
        tool.pressed(dummy, 5, 5);
        tool.dragged(dummy, 15, 25);

        // Then
        assertEquals(5, tool.getX1());
        assertEquals(5, tool.getY1());
        assertEquals(15, tool.getX2());
        assertEquals(25, tool.getY2());
    }
}
