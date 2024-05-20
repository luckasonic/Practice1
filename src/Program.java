import javax.swing.*;
import java.awt.*;
//         x = sin(At) + cos(At)
//         y = sin(Bt) + cos(Bt)
//         t∈[0;2π]

public class Program extends JFrame {
    private JPanel controlsPanel;
    private JLabel funct;

    private JPanel chartPanel;
    double A;
    double B;
    double tMin;
    double tMax;
    double deltaT;

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
        chartPanel = new ChartPanel(A, B,tMin,tMax, deltaT);
        add(chartPanel, BorderLayout.CENTER);
        setVisible(true);
        add(funct,BorderLayout.NORTH);
    }
    public void refresh(double A, double B, double tMin, double tMax, double deltaT){
            remove(chartPanel);
            chartPanel = new ChartPanel(A, B, tMin, tMax, deltaT);
            add(chartPanel, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
    }

}
