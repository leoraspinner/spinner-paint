package spinner.paint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.mockito.Mockito.*;

class EraserToolTest {
    private BufferedImage image;
    private Graphics2D g;

    @BeforeEach
    void setUp()
    {
        image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        g = mock(Graphics2D.class);
    }

    @Test
    void pressed() {
        // Given
        EraserTool tool = new EraserTool();

        // When
        tool.pressed(image, g, 100, 200);

        // Then
        verify(g).setColor(Color.WHITE);
        verify(g).fillRect(95, 195, 10, 10);
    }

    @Test
    void dragged()
    {
        // Given
        EraserTool tool = new EraserTool();

        // When
        tool.pressed(image, g, 50, 100);
        tool.dragged(g, 60, 110);

        // Then
        verify(g, times(2)).setColor(Color.WHITE);  // pressed() + dragged()
        verify(g).fillRect(45, 95, 10, 10);  // Initial press
        verify(g).fillRect(55, 105, 10, 10); // Drag position}
    }

    @Test
    void released() {
        // Given
        EraserTool tool = new EraserTool();

        // When
        tool.released(g, 100, 200);

        // Then
        verify(g).setColor(Color.WHITE);
        verify(g).fillRect(95, 195, 10, 10);
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
        verify(g).drawRect(25, 35, 10, 10); // (30-5, 40-5, 10, 10)
    }
}
