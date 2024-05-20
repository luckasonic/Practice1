import javax.swing.*;
import java.awt.*;

/**
 * The Program class extends JFrame and creates a window to display a graph based on the given mathematical functions.
 * The graph is determined by the equations:
 * x = sin(At) + cos(At)
 * y = sin(Bt) + cos(Bt)
 * with t in the interval [tMin, tMax].
 */
public class Program extends JFrame {
    private JPanel controlsPanel;
    private JLabel funct;
    private JPanel chartPanel;

    double A;
    double B;
    double tMin;
    double tMax;
    double deltaT;

    /**
     * Constructs a Program window with the specified parameters for the graph.
     *
     * @param A      The coefficient A in the equation x = sin(At) + cos(At).
     * @param B      The coefficient B in the equation y = sin(Bt) + cos(Bt).
     * @param tMin   The minimum value of t.
     * @param tMax   The maximum value of t.
     * @param deltaT The increment step for t.
     */
    public Program(double A, double B, double tMin, double tMax, double deltaT) {
        this.A = A;
        this.B = B;
        this.tMin = tMin;
        this.tMax = tMax;
        this.deltaT = deltaT;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1620, 900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        funct = new JLabel("Дорошенко Лука Олексійович. Варіант 15. f(x,y): x = sin(At) + cos(At);\n " +
                " y = sin(Bt) + cos(Bt)");
        funct.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        controlsPanel = new ControlsPanel(this);

        add(controlsPanel, BorderLayout.WEST);
        chartPanel = new ChartPanel(A, B, tMin, tMax, deltaT);
        add(chartPanel, BorderLayout.CENTER);
        setVisible(true);
        add(funct, BorderLayout.NORTH);
    }

    /**
     * Refreshes the chart panel with new parameters.
     *
     * @param A      The new coefficient A in the equation x = sin(At) + cos(At).
     * @param B      The new coefficient B in the equation y = sin(Bt) + cos(Bt).
     * @param tMin   The new minimum value of t.
     * @param tMax   The new maximum value of t.
     * @param deltaT The new increment step for t.
     */
    public void refresh(double A, double B, double tMin, double tMax, double deltaT) {
        remove(chartPanel);
        chartPanel = new ChartPanel(A, B, tMin, tMax, deltaT);
        add(chartPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }
}
