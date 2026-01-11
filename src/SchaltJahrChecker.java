public class SchaltJahrChecker {
    public static void main(String[] args) {


        while (true) {
        String eingabe = IO.readln("Gib eine Jahreszahl ein: ");
        int year = Integer.parseInt(eingabe);

        int a = year%4;
        int b = year%100;
        int c = year%400;

        if ((a == 0 && b != 0) || (b == 0 && c == 0)) {
            System.out.println("Das Jahr ist ein Schaltjahr");
        } else {
            System.out.println("Das Jahr ist kein Schaltjahr");
        }
        }
    }
}
