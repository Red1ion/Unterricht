import com.sun.source.tree.CaseTree;

import java.util.Objects;
import java.util.Random;

public class SSP {
    public static void main(String[] args) {

        boolean loop = true;

        while (loop) {

            System.out.println("<1>\tSchere\n<2>\tStein\n<3>\tPapier");
            int playervalue = Integer.parseInt(IO.readln("Wähle eine Zahl zwisch 1-3: "));

            switch (playervalue) {
                case 1:
                    System.out.println("Du hast Schere gewählt!");
                    break;

                case 2:
                    System.out.println("Du hast Stein gewählt!");
                    break;

                case 3:
                    System.out.println("Du hast Papier gewählt!");
                    break;

            }

            Random r = new Random();
            int pcvalue = r.nextInt(3) + 1;  // 1–3

            switch (pcvalue) {
                case 1:
                    System.out.println("Der Pc hat Schere gewählt!");
                    break;

                case 2:
                    System.out.println("Der Pc hat Stein gewählt!");
                    break;

                case 3:
                    System.out.println("Der Pc hat Papier gewählt!");
                    break;

            }

            if (playervalue == pcvalue) {
                System.out.println("Unendschieden");
                String playAgain = IO.readln("Willst du nochmal Spielen (Y/N)");
                loop = Objects.equals(playAgain, "Y");
            } else if ((playervalue == 1 && pcvalue == 2) || (playervalue == 2 && pcvalue == 3) || (playervalue == 3 && pcvalue == 1)) {
                System.out.println("Leider Verloren der Pc Gewinnt");
                String playAgain = IO.readln("Willst du nochmal Spielen (Y/N)");
                loop = Objects.equals(playAgain, "Y");
            } else if ((playervalue == 2 && pcvalue == 1) || (playervalue == 3 && pcvalue == 2) || (playervalue == 1 && pcvalue == 3)) {
                System.out.println("Du Gewinnst");
                String playAgain = IO.readln("Willst du nochmal Spielen (Y/N)");
                loop = Objects.equals(playAgain, "Y");
            }
        }

    }
}
