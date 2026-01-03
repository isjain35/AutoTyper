import javax.swing.SwingUtilities;

public class CountdownTimer {
    private AutoTyperUI ui;

    public CountdownTimer(AutoTyperUI ui) {
        this.ui = ui;
    }

    public void startCountdown(int seconds, Runnable onComplete) {
        new Thread(() -> {
            try {
                for (int i = seconds; i > 0; i--) {
                    final int secondsLeft = i;
                    SwingUtilities.invokeLater(() -> ui.setTimerText(secondsLeft + "..."));
                    Thread.sleep(1000);
                }
                SwingUtilities.invokeLater(() -> ui.resetTimer());
                onComplete.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
} 