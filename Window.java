import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class Window {
    public Window() {
        SwingUtilities.invokeLater(() -> createWindow());
    }

    public static void createWindow() {
        JFrame frame = new JFrame("Writing Practice");
        frame.setSize(1080, 720);

        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());

        panel1.setPreferredSize(new Dimension(1080, 380));
        panel2.setPreferredSize(new Dimension(1080, 300));
        panel3.setPreferredSize(new Dimension(1080, 40));

        panel1.setBackground(Color.red);
        panel2.setBackground(Color.blue);
        panel3.setBackground(Color.green);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);

        frame.setVisible(true);

    }
}
