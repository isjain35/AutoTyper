public class AutoTyperController {
    private AutoTyperUI ui;
    private TypingService typingService;
    private CountdownTimer timer;

    public AutoTyperController(AutoTyperUI ui) {
        this.ui = ui;
        this.typingService = new TypingService();
        this.timer = new CountdownTimer(ui);
    }

    public void onSubmit() {
        String text = ui.getText();
        if (text.isEmpty()) {
            ui.showMessage("Please enter some text.");
            return;
        }
        
        int delaySeconds;
        try {
            delaySeconds = Integer.parseInt(ui.getDelayText());
        } catch (NumberFormatException e) {
            ui.showMessage("Please enter a valid number for delay.");
            return;
        }
        
        // Start countdown and typing
        timer.startCountdown(delaySeconds, () -> {
            try {
                typingService.typeText(text);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
} 