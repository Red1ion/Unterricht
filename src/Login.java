import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;

public class Login {

    public interface LoginResultListener {
        void onLoginSuccess(String username);
    }

    private final LoginResultListener listener;

    public Login(LoginResultListener listener) {
        this.listener = listener;
    }

    // Das ist die Methode, die du von außen aufrufst (z.B. aus LoginWindow)
    public static String open(LoginResultListener listener) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Login(listener).createAndShowGui();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return null;
    }

    static Path filePath() {
        return Paths.get("UserData.xml");
    }

    private void createAndShowGui() throws Exception {

        final String[] registerUsername = {""};
        final boolean[] usernameExist = {true};

        AtomicReference<String> checkUsernames = new AtomicReference<>("");

        if (Files.exists(filePath())) {
            checkUsernames.set(Files.readString(filePath()));
        }

        JFrame frame = new JFrame("Login Window");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton buttonLogin = new JButton("Sign in");
        JButton buttonRegister = new JButton("Sign up");

        panel.add(buttonLogin);
        panel.add(buttonRegister);

        buttonRegister.addActionListener(e -> {
            registerUsername[0] = usernameField.getText();

            if (Files.exists(filePath())) {
                try {
                    checkUsernames.set(Files.readString(filePath()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                checkUsernames.set("");
            }

            try {
                if (checkUsernames.get().toLowerCase().contains("name=\"" + registerUsername[0].toLowerCase() + "\"")
                        || registerUsername[0].isEmpty()) {
                    usernameExist[0] = true;
                    JOptionPane.showMessageDialog(frame, "Username already exist!");
                } else {
                    usernameExist[0] = false;
                }
            } catch (Exception a) {
                usernameExist[0] = false;
            }

            if (!usernameExist[0]) {
                String registerPassword = new String(passwordField.getPassword());

                if (Files.exists(filePath())) {
                    String xml;
                    try {
                        xml = Files.readString(filePath());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    String endTag = "</users>";
                    int idx = xml.lastIndexOf(endTag);
                    if (idx < 0) throw new IllegalStateException("no Root-Endtag found");

                    String newUser =
                            "\t<user name=\"" + registerUsername[0] + "\">\n" +
                                    "\t\t<password algorithm=\"bcrypt\">" + registerPassword + "</password>\n" +
                                    "\t</user>\n";

                    String out = xml.substring(0, idx) + newUser + endTag;

                    try {
                        Files.writeString(filePath(), out);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    JOptionPane.showMessageDialog(frame, "Successfully signed up");

                } else {
                    String userDataText =
                            "<users>\n" +
                                    "\t<user name=\"" + registerUsername[0] + "\">\n" +
                                    "\t\t<password algorithm=\"bcrypt\">" + registerPassword + "</password>\n" +
                                    "\t</user>\n" +
                                    "</users>";

                    try {
                        Files.writeString(filePath(), userDataText);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    JOptionPane.showMessageDialog(frame, "Successfully signed up");
                }
            }
        });

        buttonLogin.addActionListener(e -> {
            String loginUsername = usernameField.getText();
            String loginPassword = new String(passwordField.getPassword());

            if (Files.exists(filePath())) {
                try {
                    checkUsernames.set(Files.readString(filePath()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            boolean ok = checkUsernames.get().contains(
                    "\t<user name=\"" + loginUsername + "\">\n\t\t<password algorithm=\"bcrypt\">" + loginPassword + "</password>");

            if (ok) {
                JOptionPane.showMessageDialog(frame, "Login successfully");

                if (listener != null) {
                    listener.onLoginSuccess(loginUsername);
                }
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect username or password.");
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
