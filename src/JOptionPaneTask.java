import javax.swing.*;

public class JOptionPaneTask {
    public static void main(String[] args) {
        String eingabe;
        double ergebnis;
        eingabe = JOptionPane.showInputDialog("wie heißt du");
        JOptionPane.showMessageDialog(null, "Hi " + eingabe);

        String input1, input2;
        input1 = JOptionPane.showInputDialog("Gib den 1. Faktor ein:");
        input2 = JOptionPane.showInputDialog("Gib den 2. Faktor ein:");
        double num1 = Double.parseDouble(input1);
        double num2 = Double.parseDouble(input2);

        ergebnis = num1 * num2;

        JOptionPane.showMessageDialog(null, ergebnis);
    }
}
