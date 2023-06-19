import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window {
    private static String correctText;

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

        panel1.setBackground(Color.GRAY);
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.green);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);

        WordHandler wh = new WordHandler(
                new FileHandler(new File("/Users/axeljohnsson/Documents/WritingPractice/English200.txt")));

        JTextPane textPane = new JTextPane();
        textPane.setCaret(new DefaultCaret() {
            @Override
            protected synchronized void damage(Rectangle r) {
                // Förhindra att markören blinkar genom att inte göra något
            }
        });

        textPane.setCaretPosition(0);

        textPane.setPreferredSize(new Dimension(1080, 380));
        textPane.setMaximumSize(new Dimension(1080, 380));
        textPane.setFont(new Font("ARIAL", Font.BOLD, 60));
        correctText = wh.getStringTenWords();
        // correctText = "123456789a123456789b123456789c123456789d123456789";
        JScrollPane scrollPane = new JScrollPane(textPane);

        StyleContext styleContext = StyleContext.getDefaultStyleContext();

        AttributeSet whiteAttrs = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,
                Color.WHITE);
                
        AttributeSet greenAttrs = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,
                Color.BLACK);
        AttributeSet redAttrs = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,
                Color.RED);

        textPane.setText(correctText); // Set the initial text
        textPane.setForeground(Color.WHITE);
        textPane.setBackground(Color.GRAY);
        textPane.addKeyListener(new KeyAdapter() {
            int currentIndex = 0; // index för den aktuella bokstaven i correctText
            boolean pressedWrong = false;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    return;
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                StyledDocument doc = textPane.getStyledDocument();
                
                if (typedChar == '\b' || typedChar == '\n') {
                    e.consume();
                    return;
                }

                if (correctText.charAt(currentIndex) == '\n') {
                    currentIndex++;
                }

                if (currentIndex < correctText.length() && typedChar == correctText.charAt(currentIndex)
                        || correctText.charAt(currentIndex) == '\u2423' && typedChar == ' ') {
                    // Rätt bokstav inmatad
                    doc.setCharacterAttributes(currentIndex, 1, greenAttrs, true);
                    if (pressedWrong) {
                        pressedWrong = false;
                        doc.setCharacterAttributes(currentIndex, 1, redAttrs, true);
                    }
                    currentIndex++;
                    // textPane.setCaretPosition(currentIndex);

                } else {
                    // Fel bokstav inmatad
                    // doc.setCharacterAttributes(currentIndex, 1, redAttrs, true);
                    pressedWrong = true;
                }
                textPane.setCaretPosition(currentIndex);
                // Om hela texten har skrivits korrekt
                if (currentIndex == correctText.length()) {
                    currentIndex = 0;
                    correctText = wh.getStringTenWords();
                    textPane.setText(correctText);
                    textPane.setBackground(Color.GRAY);
                    doc.setCharacterAttributes(0, correctText.length(), whiteAttrs, true);
                }

                e.consume(); // prevent the typed char from being added normally
            }
        });

        JLabel text = new JLabel("STATS:");
        text.setPreferredSize(new Dimension(1080, 100));
        text.setFont(new Font("ARIAL", Font.BOLD, 50));

        panel1.add(scrollPane);

        panel2.add(text, BorderLayout.NORTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
