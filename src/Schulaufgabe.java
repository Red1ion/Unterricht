public class Schulaufgabe {
    public static void main(String[] args) {
        int i = 0;
        int a = 0;

        while (i < 50) {
            if (!(i%2==0)) {
                a = a + i;
            }
            i++;
        }
        System.out.println("Die Zahl ist: " + a);
    }
}
