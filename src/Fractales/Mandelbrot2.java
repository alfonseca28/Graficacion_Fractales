package Fractales;

/**
 * ****************************************************************************
 * Compilation: javac Mandelbrot2.java Execution: java Mandelbrot xc yc size
 * Dependencies: StdDraw.java
 *
 * Plots the size-by-size region of the Mandelbrot set, centered on (xc, yc)
 *
 * % java Mandelbrot -0.5 0 2
 *
 *****************************************************************************
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Mandelbrot2 extends JFrame {

    private final int MAX_ITER = 1234;
    private final double ZOOM = 260;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;

    public Mandelbrot2() {
        super("Mandelbrot2 Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = zy = 0;
                cX = (x - 450) / ZOOM;
                cY = (y - 300) / ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx * zx * zx * zx * zx * zx * zx * zx * zx
                            - 20.0 * zx * zx * zx * zx * zx * zx * zx * zy * zy * zy
                            + 10.0 * zx * zx * zy * zy * zy * zy * zy * zy * zy * zy + cX;
                    zy = 10.0 * zx * zx * zx * zx * zx * zx * zx * zx * zy * zy
                            - 20.0 * zx * zx * zx * zx * zy * zy * zy * zy * zy * zy
                            + zy * zy * zy * zy * zy * zy * zy * zy * zy * zy + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y, iter | (iter << 14));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

    public static void main(String[] args) {
        new Mandelbrot2().setVisible(true);
    }
}
