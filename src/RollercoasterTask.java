public class RollercoasterTask {
    public static void main(String[] args) {
        while (true) {

            String sAge = IO.readln("Gib dein Alter ein: ");
            String sSize = IO.readln("Gibt deine Körpergröße ein: ");
            int iAge = Integer.parseInt(sAge);
            int iSize = Integer.parseInt(sSize);

            if (iAge < 7) {
                IO.println("Du bist zu jung, um mitzufahren!");
            } else if (iSize < 130) {
                IO.println("Du bist zu klein, um mitzufahren!");
            } else {
                IO.println("Du darfst mitfahren!");
                break;
            }
        }


    }
}
