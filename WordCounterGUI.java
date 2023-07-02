
package wordcountergui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCounterGUI extends JFrame implements ActionListener {
    private JLabel wordCountLabel;
    private JTextArea textArea;

    public WordCounterGUI() {
        setTitle("Word Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        wordCountLabel = new JLabel("Total number of words: 0");
        add(wordCountLabel, BorderLayout.NORTH);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton countButton = new JButton("Count Words");
        countButton.addActionListener(this);
        add(countButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputText = textArea.getText();
        int wordCount = countWords(inputText);
        wordCountLabel.setText("Total number of words: " + wordCount);
    }

    public int countWords(String text) {
        String[] words = text.split("[\\p{Punct}\\s]+");
        return words.length;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WordCounterGUI wordCounterGUI = new WordCounterGUI();
                wordCounterGUI.setVisible(true);
            }
        });
    }
}

