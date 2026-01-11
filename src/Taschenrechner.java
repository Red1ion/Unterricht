public class Taschenrechner {
    public static void main(String[] args) {
        String number1 = IO.readln("Gib eine Zahl ein: ");
        String operator = IO.readln("1: Addition | 2: Subraktion | 3: Multiplikation | 4: Division :: ");
        String number2 = IO.readln("Gib eine Zahl ein: ");

        double num1 = Double.parseDouble(number1);
        int op = Integer.parseInt(operator);
        double num2 = Double.parseDouble(number2);
        double solution;

        switch (op) {
            case 1:
                solution = num1 + num2;
                System.out.println("Das Ergebnis ist: " + solution);
                break;
            case 2:
                solution = num1 - num2;
                System.out.println("Das Ergebnis ist: " + solution);
                break;
            case 3:
                solution = num1 * num2;
                System.out.println("Das Ergebnis ist: " + solution);
                break;
            case 4:
                solution = num1 / num2;
                System.out.println("Das Ergebnis ist: " + solution);
                break;

        }
    }
}
