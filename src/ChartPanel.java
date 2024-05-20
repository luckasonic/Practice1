import javax.swing.*;
import java.awt.*;

/**
 * The ChartPanel class is a custom JPanel used for plotting a parametric function
 * defined by the given parameters A, B, tMin, tMax, and deltaT.
 */
public class ChartPanel extends JPanel {
    private double A;
    private double B;
    private double tMin;
    private double tMax;
    private double deltaT;

    /**
     * Constructs a ChartPanel with the specified parameters.
     *
     * @param A The coefficient for the sine and cosine functions for the x-coordinate.
     * @param B The coefficient for the sine and cosine functions for the y-coordinate.
     * @param tMin The minimum value of the parameter t.
     * @param tMax The maximum value of the parameter t.
     * @param deltaT The increment step for the parameter t.
     */
    public ChartPanel(double A, double B, double tMin, double tMax, double deltaT) {
        this.A = A;
        this.B = B;
        this.tMin = tMin;
        this.tMax = tMax;
        this.deltaT = deltaT;
    }

    /**
     * Overrides the paintComponent method to draw the parametric function and the axes.
     *
     * @param g The Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        int prevX = 1, prevY = 1;

        // Draw X-axis and Y-axis
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, height / 2, width, height / 2); // X-axis
        g2d.drawLine(width / 2, 0, width / 2, height); // Y-axis

        g2d.drawString("0", width / 2 - 5, height / 2 + 15);

        // Draw X-axis labels and marks
        for (double x = -2; x <= 2; x += 0.5) {
            int labelX = (int) (x * width / 4 + width / 2);
            if (x != 0) {
                g2d.drawString(Double.toString(x), labelX - 5, height / 2 + 15);
                g2d.drawLine(labelX, height / 2 - 3, labelX, height / 2 + 3); // Mark on X-axis
            }
        }

        // Draw Y-axis labels and marks
        for (double y = -2; y <= 2; y += 0.5) {
            int labelY = (int) (-y * height / 4 + height / 2);
            if (y != 0) {
                g2d.drawString(Double.toString(y), width / 2 - 20, labelY + 5);
                g2d.drawLine(width / 2 - 3, labelY, width / 2 + 3, labelY); // Mark on Y-axis
            }
        }

        // Set rendering hints for high quality
        g2d.setColor(Color.BLUE);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        // Plot the parametric function
        for (double t = tMin; t <= tMax; t += deltaT) {
            double x = Math.sin(A * t) + Math.cos(A * t);
            double y = Math.sin(B * t) + Math.cos(B * t);

            int plotX = (int) (x * width / 4 + width / 2);
            int plotY = (int) (-y * height / 4 + height / 2);

            if (t == tMin) {
                prevX = plotX;
                prevY = plotY;
            }

            g2d.drawLine(prevX, prevY, plotX, plotY);

            prevX = plotX;
            prevY = plotY;
        }
    }
}
