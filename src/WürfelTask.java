import javax.swing.*;

public class WürfelTask {
    public static void main(String[] args) {
        double myRand1, max1 = 1;
        myRand1 = Math.random() * max1;
        System.out.println(Math.round(myRand1));

        for (int i = 0; i < 1000; i++) {
            double myRand2, min2 = 1, max2 = 5;
            myRand2 = Math.random() * max2;
            System.out.println(Math.round(myRand2 + min2));
            if ((myRand2 + min2) > 6 || (myRand2 + min2) <= 0) {
                System.out.println("Fehler");
                break;
            }
        }

        int minNum = 1;
        int maxNum;

        maxNum = Integer.parseInt(JOptionPane.showInputDialog("Gib deine Würfel größte Würfel Zahl an:"));

        double myRand3;
        myRand3 = Math.random() * maxNum;
        System.out.println(Math.round(myRand3 + minNum));
    }
}
