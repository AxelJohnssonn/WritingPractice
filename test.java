

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class test {

    public static void main(String[] args) {
        JTextPane textPane = new JTextPane();

        String correctText = "Hej jag heter Axel";

        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet blackAttrs = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.BLACK);
        AttributeSet greenAttrs = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.GREEN);
        AttributeSet redAttrs = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.RED);

        textPane.setText(correctText); // Set the initial text

        textPane.addKeyListener(new KeyAdapter() {
            int currentIndex = 0; // index för den aktuella bokstaven i correctText

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    StyledDocument doc = textPane.getStyledDocument();
                    if (currentIndex > 0) {
                        try {
                            doc.setCharacterAttributes(currentIndex - 1, 1, blackAttrs, true);
                            currentIndex--;
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    e.consume();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                StyledDocument doc = textPane.getStyledDocument();

                if (currentIndex < correctText.length() && typedChar == correctText.charAt(currentIndex)) {
                    // Rätt bokstav inmatad
                    doc.setCharacterAttributes(currentIndex, 1, greenAttrs, true);
                    currentIndex++;
                } else {
                    // Fel bokstav inmatad
                    doc.setCharacterAttributes(currentIndex, 1, redAttrs, true);

                }

                // Om hela texten har skrivits korrekt
                if (currentIndex == correctText.length()) {
                    try {
                        doc.insertString(doc.getLength(), "\nAll correct!", blackAttrs);
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }

                e.consume();  // prevent the typed char from being added normally
            }
        });

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(textPane));
        frame.pack();
        frame.setVisible(true);
    }
     }