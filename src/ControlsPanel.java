import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The ControlsPanel class represents a control panel for adjusting parameters of a function
 * and saving the plotted chart as an image.
 */
public class ControlsPanel extends JPanel {
    private JButton save;
    private JButton refresh;
    private JTextField xValue;
    private JTextField yValue;
    private JTextField tMinValue;
    private JTextField tMaxValue;
    private JTextField deltaT;
    private JPanel ab;

    /**
     * Constructs a ControlsPanel with specified Program frame.
     *
     * @param frame The Program frame containing the function and its parameters.
     */
    public ControlsPanel(Program frame) {
        setLayout(new GridLayout(3, 1));
        ab = new JPanel();
        ab.setLayout(new GridLayout(5, 2));

        JLabel x = new JLabel("A = ");
        x.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        xValue = new JTextField(String.valueOf(frame.A));
        ab.add(x);
        ab.add(xValue);

        JLabel y = new JLabel("B = ");
        y.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        yValue = new JTextField(String.valueOf(frame.B));
        ab.add(y);
        ab.add(yValue);

        JLabel tMin = new JLabel("tMin = ");
        tMin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        tMinValue = new JTextField(String.valueOf(frame.tMin));
        ab.add(tMin);
        ab.add(tMinValue);

        JLabel tMax = new JLabel("tMax = ");
        tMax.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        tMaxValue = new JTextField(String.valueOf(frame.tMax));
        ab.add(tMax);
        ab.add(tMaxValue);

        JLabel dT = new JLabel("Крок = ");
        deltaT = new JTextField(String.valueOf(frame.deltaT));
        dT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        ab.add(dT);
        ab.add(deltaT);

        add(ab);

        save = new CoolButton("Зберегти");
        refresh = new CoolButton("Оновити");
        add(refresh);
        add(save);

        refresh.addActionListener(e -> {
            try {
                double a = getA();
                double b = getB();
                double delta = getDeltaT();

                if (delta <= 0) {
                    JOptionPane.showMessageDialog(frame, "Крок має бути додатнім числом.", "Помилка введення", JOptionPane.ERROR_MESSAGE);
                } else if (getTMax() <= getTMin()) {
                    JOptionPane.showMessageDialog(frame, "tMax має бути більше за tMin.", "Помилка введення", JOptionPane.ERROR_MESSAGE);
                } else if (getTMax() <= getDeltaT() && -getTMax() <= getDeltaT()) {
                    JOptionPane.showMessageDialog(frame, "Крок завеликий.", "Помилка введення", JOptionPane.ERROR_MESSAGE);
                } else {
                    frame.refresh(a, b, getTMin(), getTMax(), delta);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Введіть коректні числові значення.", "Помилка введення", JOptionPane.ERROR_MESSAGE);
            }
        });

        save.addActionListener(e -> {
            xValue.setText(String.valueOf(frame.A));
            yValue.setText(String.valueOf(frame.B));
            tMinValue.setText(String.valueOf(frame.tMin));
            tMaxValue.setText(String.valueOf(frame.tMax));
            deltaT.setText(String.valueOf(frame.deltaT));
            JLabel res = new JLabel("A = " + frame.A + "; B = " + frame.B + "; t∈[" + frame.tMin + ";" + frame.tMax + "]; крок: " + frame.deltaT);
            Container c = frame.getContentPane();
            c.setLayout(new BorderLayout());
            BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
            c.paint(im.getGraphics());
            frame.repaint();
            try {
                String fileName = "imgs\\" + new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date()) + ".png";
                ImageIO.write(im, "PNG", new File(fileName));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Gets the value of A from the text field.
     *
     * @return The value of A as a double.
     */
    private double getA() {
        return Double.parseDouble(xValue.getText());
    }

    /**
     * Gets the value of B from the text field.
     *
     * @return The value of B as a double.
     */
    private double getB() {
        return Double.parseDouble(yValue.getText());
    }

    /**
     * Gets the minimum value of t from the text field.
     *
     * @return The minimum value of t as a double.
     */
    private double getTMin() {
        return Double.parseDouble(tMinValue.getText());
    }

    /**
     * Gets the maximum value of t from the text field.
     *
     * @return The maximum value of t as a double.
     */
    private double getTMax() {
        return Double.parseDouble(tMaxValue.getText());
    }

    /**
     * Gets the step value (deltaT) from the text field.
     *
     * @return The step value (deltaT) as a double.
     */
    private double getDeltaT() {
        return Double.parseDouble(deltaT.getText());
    }
}
