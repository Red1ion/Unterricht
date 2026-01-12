import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    /*public static void loginHandle() throws Exception {


        String RegisterUsername = "";
        String Options = "";
        String CheckUsernames = "";

        boolean UsernameExist = true;

        Path path = Paths.get("UserData.xml");

        if (Files.exists(path)) {
            CheckUsernames = Files.readString(Paths.get("Userdata.xml"));
        }

        while (!(Options.equalsIgnoreCase("R") || Options.equalsIgnoreCase("L"))) {
            Options = IO.readln("Choose R = Register | L = Login: ");
            if (!(Options.equalsIgnoreCase("R") || Options.equalsIgnoreCase("L"))) {
                IO.println("Wrong input!");
            }
        }


        if (Options.equalsIgnoreCase("R")) {
            while (UsernameExist) {
                RegisterUsername = IO.readln("Type in your username: ");
                try {

                    if (CheckUsernames.toLowerCase().contains("name=\"" + RegisterUsername.toLowerCase() + "\"") || RegisterUsername.isEmpty()) {
                        IO.println("Username allready exists!");
                    } else {
                        UsernameExist = false;
                    }
                } catch (Exception e) {
                    UsernameExist = false;
                }
            }

            String RegisterPassword = IO.readln("Type in your Password: ");
            if (Files.exists(path)) {
                String xml = Files.readString(path);
                String endTag = "</users>";
                int idx = xml.lastIndexOf(endTag);
                if (idx < 0) throw new IllegalStateException("no Root-Endtag found");

                String newUser = "\t<user name=\"" + RegisterUsername + "\">\n" +
                        "\t\t<password algorithm=\"bcrypt\">" + RegisterPassword + "</password>\n" +
                        "\t</user>\n";

                String out = xml.substring(0, idx) + newUser + endTag;

                Files.writeString(path, out);
                IO.println("Successfully registerd!");

            } else {
                String UserDataText = "<users>\n" +
                        "\t<user name=\"" + RegisterUsername + "\">\n" +
                        "\t\t<password algorithm=\"bcrypt\">" + RegisterPassword + "</password>\n" +
                        "\t</user>\n" +
                        "</users>";

                Files.write(path, UserDataText.getBytes());

            }


        } else if (Options.equalsIgnoreCase("L")) {

            String LoginUsername = IO.readln("Type in your username: ");
            String LoginPassword = IO.readln("Type in your Password: ");

            if (!Files.exists(path)) {
                IO.println("Incorrect username or password.");

            } else if (CheckUsernames.contains("\t<user name=\"" + LoginUsername + "\">\n\t\t<password algorithm=\"bcrypt\">" + LoginPassword + "</password>")) {
                IO.println("Login successfully");
            } else {
                IO.println("Incorrect username or password.");
            }
        }
    }*/

    private void createAndShowGui() throws Exception {

        final String[] RegisterUsername = {""};
        //String Options = "";
        String CheckUsernames;

        final boolean[] UsernameExist = {true};

        Path path = Paths.get("UserData.xml");

        if (Files.exists(path)) {
            CheckUsernames = Files.readString(Paths.get("Userdata.xml"));
        } else {
            CheckUsernames = "";
        }

        JFrame frame = new JFrame("Login Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 Zeilen, 2 Spalten, Abstand 10px

// Username
        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        panel.add(usernameField);

// Password
        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

// Buttons
        JButton buttonLogin = new JButton("Sign in");
        JButton buttonRegister = new JButton("Sign up");

        panel.add(buttonLogin);
        panel.add(buttonRegister);

        buttonRegister.addActionListener(e -> {
            while (UsernameExist[0]) {
                RegisterUsername[0] = usernameField.getText();
                try {

                    if (CheckUsernames.toLowerCase().contains("name=\"" + RegisterUsername[0].toLowerCase() + "\"") || RegisterUsername[0].isEmpty()) {
                        IO.println("Username allready exists!");
                    } else {
                        UsernameExist[0] = false;
                    }
                } catch (Exception a) {
                    UsernameExist[0] = false;
                }
            }

            String RegisterPassword = passwordField.getText();
            if (Files.exists(path)) {
                String xml = null;
                try {
                    xml = Files.readString(path);
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
                    Files.writeString(path, out);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                IO.println("Successfully registerd!");

            } else {
                String UserDataText = "<users>\n" +
                        "\t<user name=\"" + RegisterUsername[0] + "\">\n" +
                        "\t\t<password algorithm=\"bcrypt\">" + RegisterPassword + "</password>\n" +
                        "\t</user>\n" +
                        "</users>";

                try {
                    Files.write(path, UserDataText.getBytes());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        buttonLogin.addActionListener(e -> {

            String LoginUsername = usernameField.getText();
            String LoginPassword = passwordField.getText();

            if (!Files.exists(path)) {
                IO.println("Incorrect username or password.");

            } else if (CheckUsernames.contains("\t<user name=\"" + LoginUsername + "\">\n\t\t<password algorithm=\"bcrypt\">" + LoginPassword + "</password>")) {
                IO.println("Login successfully");
            } else {
                IO.println("Incorrect username or password.");
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
