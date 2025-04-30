package spinner.paint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class LineToolTest {
    private BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);

    @Test
    void basicLineDrawing() {
        // Given
        LineTool tool = new LineTool();
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.BLACK);

        // When
        tool.pressed(image, g, 10, 10);
        tool.dragged(g, 30, 40);
        tool.released(g, 30, 40);

        // Then
        assertEquals(Color.BLACK.getRGB(), image.getRGB(10, 10));
        assertEquals(Color.BLACK.getRGB(), image.getRGB(30, 40));
    }

    @Test
    void coordinatesUpdateOnDrag() {
        // Given
        LineTool tool = new LineTool();
        Graphics2D dummy = (Graphics2D) new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics();

        // When
        tool.pressed(image, dummy, 5, 5);
        tool.dragged(dummy, 15, 25);

        // Then
        assertEquals(5, tool.getX1());
        assertEquals(5, tool.getY1());
        assertEquals(15, tool.getX2());
        assertEquals(25, tool.getY2());
    }
}