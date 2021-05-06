package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void testImageWriter() {

        ImageWriter writer = new ImageWriter("basic test", 800, 500);

        Color c;
        int w = 800 / 16;
        int h = 500 / 10;
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                if ((i + 1) % h == 0 || (j + 1) % w == 0)
                    c = new Color(java.awt.Color.PINK);
                else c = new Color(java.awt.Color.BLUE);

                writer.writePixel(i, j, c);
            }
        }

        writer.writeToImage();

    }
}