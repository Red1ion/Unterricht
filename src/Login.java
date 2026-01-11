void main() throws Exception {
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
}
