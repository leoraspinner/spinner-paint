package spinner.paint;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PencilToolTest
{
    private Graphics g = mock();

    @Test
    void pressed()
    {
        // given
        PencilTool tool = new PencilTool();

        // when
        tool.pressed(g, 50, 100);

        // then
        assertEquals(50, tool.getX());
        assertEquals(100, tool.getY());
        verify(g).drawLine(50, 100, 50, 100);
    }

    @Test
    void dragged()
    {
        // given
        PencilTool tool = new PencilTool();
        tool.pressed(g, 50, 100);

        // when
        tool.dragged(g, 200, 150);

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
        tool.released(g, 100, 200);

        //then
        verifyNoMoreInteractions(g);
    }
}