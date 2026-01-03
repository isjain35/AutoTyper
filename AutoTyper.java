import javax.swing.SwingUtilities;

public class AutoTyper {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AutoTyperUI().setVisible(true);
        });
    }
} 