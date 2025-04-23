package spinner.paint;

import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EraserToolTest {
    private final Graphics g = mock(Graphics.class);

    @Test
    void pressed() {
        // Given
        EraserTool tool = new EraserTool();
        Graphics g = mock(Graphics.class);

        // When
        tool.pressed(g, 100, 200);

        // Then
        verify(g).setColor(Color.WHITE);
        verify(g).fillRect(95, 195, 10, 10);
    }

    @Test
    void dragged() {
        // Given
        EraserTool tool = new EraserTool();
        tool.pressed(g, 50, 100); // Initialize previous position

        // When
        tool.dragged(g, 60, 110);

        // Then
        verify(g).drawLine(50, 100, 60, 110); // Verify line drawing
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
        tool.pressed(g, 30, 40); // Set initial position

        // When
        tool.preview(g);

        // Then
        verify(g).setColor(Color.LIGHT_GRAY);
        verify(g).drawRect(25, 35, 10, 10); // (30-5, 40-5, 10, 10)
    }
}
