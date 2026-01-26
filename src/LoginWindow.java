import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void createAndShowGui() {
        JFrame Launcher = new JFrame("Launcher");
        Launcher.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Launcher.setSize(800, 500);
        Launcher.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 50));
        panel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login.open(username -> {
                    System.out.println("Eingeloggt als: " + username);
                    System.out.println(username);

                    JLabel testlabel = new JLabel(username);
                    testlabel.setPreferredSize(new Dimension(100, 50));
                    panel.add(testlabel, gbc);
                    panel.remove(loginButton);
                    panel.revalidate();
                    panel.repaint();
                });

            }
        });

        Launcher.setContentPane(panel);
        Launcher.setVisible(true);
    }

}