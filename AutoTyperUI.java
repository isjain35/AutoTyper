import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class AutoTyperUI extends JFrame {
    private JTextArea textArea;
    private JButton submitButton;
    private JTextField delayField;
    private JLabel timerLabel;
    private AutoTyperController controller;

    public AutoTyperUI() {
        controller = new AutoTyperController(this);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Auto Typer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        createComponents();
        setupLayout();
        setupEventListeners();
    }

    private void createComponents() {
        textArea = new JTextArea(10, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        
        submitButton = new JButton("Submit & Type");
        delayField = new JTextField("5", 5);
        timerLabel = new JLabel("5");
    }

    private void setupLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Delay (seconds):"));
        buttonPanel.add(delayField);
        buttonPanel.add(timerLabel);
        buttonPanel.add(submitButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void setupEventListeners() {
        submitButton.addActionListener(e -> controller.onSubmit());
        
        // Update timer label when delay field changes
        delayField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { updateTimerLabel(); }
            public void removeUpdate(DocumentEvent e) { updateTimerLabel(); }
            public void insertUpdate(DocumentEvent e) { updateTimerLabel(); }
        });
    }

    private void updateTimerLabel() {
        String delayText = delayField.getText();
        if (!delayText.isEmpty()) {
            try {
                int delay = Integer.parseInt(delayText);
                timerLabel.setText(String.valueOf(delay));
            } catch (NumberFormatException e) {
                timerLabel.setText("0");
            }
        } else {
            timerLabel.setText("0");
        }
    }

    // Getters for controller access
    public String getText() {
        return textArea.getText();
    }

    public String getDelayText() {
        return delayField.getText();
    }

    public void setTimerText(String text) {
        timerLabel.setText(text);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void resetTimer() {
        updateTimerLabel();
    }
} 