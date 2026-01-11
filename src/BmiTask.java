import javax.swing.*;

public class BmiTask {
    public static void main(String[] args) {
        String input1 = "";
        String input2 = "";
        String output = "";
        float solution = 0;

        while (solution == 0) {
            input1 = JOptionPane.showInputDialog("Gib dein Körpergewicht an:");
            if (input1 == null) {
                break;
            }
            input2 = JOptionPane.showInputDialog("Gib deine Körpergröße an:");
            if (input2 == null) {
                break;
            }

            input1 = input1.replaceAll(",", ".");
            input2 = input2.replaceAll(",", ".");

            try {
                float weight = Float.parseFloat(input1);
                float size = Float.parseFloat(input2);

                solution = weight / (size * size);

                if (solution < 18.5) output = "Untergewicht";
                else if (solution > 25) output = "Übergewicht";
                else output = "Normalgewicht";

                JOptionPane.showMessageDialog(null, "Dein BMI beträgt: " + solution + "\n" + output);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Inkorrekte eingabe");
            }

        }

    }
}
