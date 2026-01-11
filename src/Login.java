import java.nio.file.*;

public class Login {
    public static void main(String[] args) throws Exception {
        String RegisterUsername = "";
        Boolean UsernameExist = false;

        String Options = IO.readln("Choose R = Register | L = Login: ");

        if (Options.equalsIgnoreCase("R")) {
            while (UsernameExist) {
                RegisterUsername = IO.readln("Type in your username: ");
                String CheckUsernames = Files.readString(Paths.get("Userdata.xml"));

                if (CheckUsernames.toLowerCase().contains("name\"" + RegisterUsername.toLowerCase() + "\"")) {
                    System.out.println("Benutzername Existiert Bereits!");
                    UsernameExist = true;
                } else if (RegisterUsername.equals("")) {
                    System.out.println("Benutzername ist Ungültig!");
                    UsernameExist = true;
                } else {
                    UsernameExist = false;
                }
            }

            String RegisterPassword = IO.readln("Type in your Password: ");
            Path path = Paths.get("UserData.xml");
            if (Files.exists(path)) {
                String xml = Files.readString(path);
                String endTag = "</users>";
                int idx = xml.lastIndexOf(endTag);
                if (idx < 0) throw new IllegalStateException("kein Root-Endtag gefunden");

                String newUser = "\t<user name=\"" + RegisterUsername + "\">\n" +
                        "\t\t<password algorithm=\"bcrypt\">" + RegisterPassword + "</password>\n" +
                        "\t</user>\n";

                String out = xml.substring(0, idx) + newUser + endTag;

                Files.writeString(path, out);
                System.out.println("Erfolgreich Registriert!");

            } else {
                String UserDataText = "<users>\n" +
                        "\t<user name=\"" + RegisterUsername + "\">\n" +
                        "\t\t<password algorithm=\"bcrypt\">" + RegisterPassword + "</password>\n" +
                        "\t</user>\n" +
                        "</user>";

                Files.write(path, UserDataText.getBytes());

            }


        } else if (Options.equalsIgnoreCase("L")) {
            String LoginUsername = IO.readln("Type in your username: ");
            String LoginPassword = IO.readln("Type in your Password: ");
        }
    }
}
