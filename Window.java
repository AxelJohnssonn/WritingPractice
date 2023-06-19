import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;


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

        WordHandler wh = new WordHandler(new FileHandler(new File("/Users/axeljohnsson/Documents/WritingPractice/ord.txt")));

        JTextField textarea = new JTextField(10);
        JLabel displayLabel = new JLabel();
        textarea.addKeyListener(new KeyAdapter() {
            String correctText = wh.getStringTenWords(); // Detta ska vara den text som användaren ska skriva
            int currentIndex = 0; // index för den aktuella bokstaven i correctText

            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                System.out.println(typedChar);
                if (typedChar == correctText.charAt(currentIndex)) {
                    // Rätt bokstav inmatad
                    displayLabel.setForeground(Color.GREEN);
                    displayLabel.setText("Correct: " + typedChar);
                    currentIndex++;
                } else {
                    // Fel bokstav inmatad
                    displayLabel.setForeground(Color.RED);
                    displayLabel.setText("Incorrect: " + typedChar);
                }

                // Om hela texten har skrivits korrekt
                if (currentIndex == correctText.length()) {
                    displayLabel.setForeground(Color.BLUE);
                    displayLabel.setText("All correct!");
                }
            }
        });

        panel1.add(textarea,BorderLayout.NORTH);
        panel1.add(displayLabel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }
}
