package spinner.paint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.mockito.Mockito.*;

class EraserToolTest {
    private BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    private Graphics2D g = mock(Graphics2D.class);


    @Test
    void pressed() {
        // Given
        EraserTool tool = new EraserTool();

        // When
        tool.pressed(image, g, 100, 200);

        // Then
        verify(g).setColor(Color.WHITE);
        verify(g, atLeastOnce()).setStroke(any(BasicStroke.class));
        verify(g).drawLine(100, 200, 100, 200);
    }

    @Test
    void dragged()
    {
        // Given
        EraserTool tool = new EraserTool();

        // When
        tool.pressed(null, g, 100, 200);
        tool.dragged(g, 110, 210);

        // Then
        verify(g, times(2)).setColor(Color.WHITE);  // pressed() + dragged()
        verify(g, atLeastOnce()).setStroke(any(BasicStroke.class));
        verify(g).drawLine(100, 200, 110, 210);
    }

    @Test
    void released() {
        // Given
        EraserTool tool = new EraserTool();

        // When
        tool.pressed(null, g, 100, 200);
        tool.released(g, 120, 220);

        // Then
        verify(g, times(2)).setColor(Color.WHITE);
        verify(g, atLeastOnce()).setStroke(any(BasicStroke.class));
        verify(g).drawLine(100, 200, 120, 220);
    }


    @Test
    void preview() {
        // Given
        EraserTool tool = new EraserTool();
        tool.pressed(image, g, 30, 40); // Set initial position

        // When
        tool.preview(g);

        // Then
        verify(g).setColor(Color.LIGHT_GRAY);
        verify(g).drawOval(25, 35, 10, 10);  // (30-5, 40-5, 10, 10)
    }
}
