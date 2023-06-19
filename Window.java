import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.text.AttributeSet;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        panel1.setBackground(Color.GRAY);
        panel2.setBackground(Color.blue);
        panel3.setBackground(Color.green);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);

        WordHandler wh = new WordHandler(
                new FileHandler(new File("/Users/axeljohnsson/Documents/WritingPractice/ord.txt")));

        JTextPane textPane = new JTextPane();
        textPane.setPreferredSize(new Dimension(1080, 380));
        textPane.setMaximumSize(new Dimension(1080, 380));
        textPane.setFont(new Font("ARIAL", Font.BOLD, 24));
        String correctText = wh.getStringTenWords();
        JScrollPane scrollPane = new JScrollPane(textPane);

        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet blackAttrs = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,
                Color.BLACK);
        AttributeSet greenAttrs = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,
                Color.GREEN);
        AttributeSet redAttrs = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,
                Color.RED);

        textPane.setText(correctText); // Set the initial text

        textPane.addKeyListener(new KeyAdapter() {
            int currentIndex = 0; // index för den aktuella bokstaven i correctText

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // ignore event
                    return;
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                StyledDocument doc = textPane.getStyledDocument();

                if (typedChar == '\b') {
                    // Ignore backspace
                    e.consume();
                    return;
                }

                if (currentIndex < correctText.length() && typedChar == correctText.charAt(currentIndex)
                        || correctText.charAt(currentIndex) == '\u2423' && typedChar == ' ') {
                    if (correctText.charAt(currentIndex) == ' ' && typedChar == '\u2423') {
                        doc.setCharacterAttributes(currentIndex, 1, greenAttrs, true);
                        currentIndex++;
                    }
                    // Rätt bokstav inmatad
                    doc.setCharacterAttributes(currentIndex, 1, greenAttrs, true);
                    currentIndex++;
                } else {
                    // Fel bokstav inmatad
                    doc.setCharacterAttributes(currentIndex, 1, redAttrs, true);

                }

                // Om hela texten har skrivits korrekt
                if (currentIndex == correctText.length()) {

                }

                e.consume(); // prevent the typed char from being added normally
            }
        });

        panel1.add(scrollPane);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
