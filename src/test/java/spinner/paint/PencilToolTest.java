package spinner.paint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PencilToolTest
{
    private BufferedImage image;
    private Graphics2D g;

    @BeforeEach
    void setUp() {
        image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        g = mock(Graphics2D.class); // Initialize the mock here
    }


    @Test
    void pressed() {
        // Given
        PencilTool tool = new PencilTool();

        // When
        tool.pressed(image, g, 50, 100);

        // Then
        assertEquals(50, tool.getX());
        assertEquals(100, tool.getY());
        verify(g).drawLine(50, 100, 50, 100);
    }

    @Test
    void dragged()
    {
        // given
        PencilTool tool = new PencilTool();
        tool.pressed(image, g, 50, 100);

        // when
        tool.dragged( g, 200, 150);

        // then
        assertEquals(200, tool.getX());
        assertEquals(150, tool.getY());
        verify(g).drawLine(50, 100, 200, 150);
    }

    @Test
    void released()
    {
        //given
        PencilTool tool = new PencilTool();

        //when
        tool.released( g, 100, 200);

        //then
        verifyNoMoreInteractions(g);
    }
}