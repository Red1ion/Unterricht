import javax.swing.*;
import java.awt.*;

public class LoginWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new LoginWindow().createAndShowGui();
            } catch (Exception e) {
                throw new RuntimeException(e);

            }

        });
    }
    private void createAndShowGui() {
        JFrame Launcher = new JFrame("Launcher");
        Launcher.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Launcher.setSize(800, 500);
        Launcher.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        Launcher.setLayout(new GridLayout(3, 2, 10, 10));

        Launcher.setContentPane(panel);
        Launcher.setVisible(true);
    }
}