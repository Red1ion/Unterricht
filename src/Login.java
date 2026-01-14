import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;

public class Login {
    static void main() throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                new Login().createAndShowGui();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    static Path FilePath() {
        return Paths.get("UserData.xml");
    }

    private void createAndShowGui() throws Exception {

        final String[] RegisterUsername = {""};

        final boolean[] UsernameExist = {true};

        AtomicReference<String> CheckUsernames = new AtomicReference<>();

        if (Files.exists(FilePath())) {
            CheckUsernames.set(Files.readString(FilePath()));
        }

        JFrame frame = new JFrame("Login Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 Zeilen, 2 Spalten, Abstand 10px
        
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
            RegisterUsername[0] = usernameField.getText();

            if (Files.exists(FilePath())) {
                try {
                    CheckUsernames.set(Files.readString(Paths.get("UserData.xml")));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                CheckUsernames.set("");
            }
            
            try {
                if (CheckUsernames.get().toLowerCase().contains("name=\"" + RegisterUsername[0].toLowerCase() + "\"") || RegisterUsername[0].isEmpty()) {
                    UsernameExist[0] = true;
                    JOptionPane.showMessageDialog(frame, "Username already exist!");
                } else {
                    UsernameExist[0] = false;
                }
            } catch (Exception a) {
                UsernameExist[0] = false;
            }

            if (!UsernameExist[0]) {
                String RegisterPassword = passwordField.getText();
                
                if (Files.exists(FilePath())) {
                    String xml = null;
                    
                    try {
                        xml = Files.readString(FilePath());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    
                    String endTag = "</users>";
                    
                    int idx = xml.lastIndexOf(endTag);
                    
                    if (idx < 0) throw new IllegalStateException("no Root-Endtag found");

                    String newUser = "\t<user name=\"" + RegisterUsername[0] + "\">\n" +
                            "\t\t<password algorithm=\"bcrypt\">" + RegisterPassword + "</password>\n" +
                            "\t</user>\n";

                    String out = xml.substring(0, idx) + newUser + endTag;

                    try {
                        Files.writeString(FilePath(), out);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    
                    JOptionPane.showMessageDialog(frame, "Successfuly signed up");

                } else {
                    
                    String UserDataText = "<users>\n" +
                            "\t<user name=\"" + RegisterUsername[0] + "\">\n" +
                            "\t\t<password algorithm=\"bcrypt\">" + RegisterPassword + "</password>\n" +
                            "\t</user>\n" +
                            "</users>";

                    try {
                        Files.write(FilePath(), UserDataText.getBytes());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    JOptionPane.showMessageDialog(frame, "Successfuly signed up");

                }
            }
        });

        buttonLogin.addActionListener(e -> {

            String LoginUsername = usernameField.getText();
            String LoginPassword = passwordField.getText();

            if (Files.exists(FilePath())) {
                try {
                    CheckUsernames.set(Files.readString(FilePath()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if (CheckUsernames.get().contains("\t<user name=\"" + LoginUsername + "\">\n\t\t<password algorithm=\"bcrypt\">" + LoginPassword + "</password>")) {
                JOptionPane.showMessageDialog(frame,"Login successfully");

            } else if (!Files.exists(FilePath())) {
                JOptionPane.showMessageDialog(frame, "Incorrect username or password.");
            } else {
                JOptionPane.showMessageDialog(frame,"Incorrect username or password.");
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
