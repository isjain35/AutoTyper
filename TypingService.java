import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.StringSelection;

public class TypingService {
    
    public void typeText(String text) throws AWTException {
        Robot robot = new Robot();
        for (char c : text.toCharArray()) {
            typeChar(robot, c);
            try {
                Thread.sleep(20); // Small delay between keystrokes
            } catch (InterruptedException ignored) {}
        }
    }

    private void typeChar(Robot robot, char c) {
        try {
            boolean upperCase = Character.isUpperCase(c);
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (keyCode == KeyEvent.VK_UNDEFINED) {
                return; // skip unsupported chars
            }
            if (upperCase) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            if (upperCase) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
        } catch (IllegalArgumentException e) {
            // For special characters, fallback to clipboard paste
            pasteCharWithClipboard(robot, c);
        }
    }

    private void pasteCharWithClipboard(Robot robot, char c) {
        StringSelection selection = new StringSelection(String.valueOf(c));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        try {
            Thread.sleep(10);
        } catch (InterruptedException ignored) {}
    }
} 