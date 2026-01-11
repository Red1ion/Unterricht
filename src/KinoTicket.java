import java.util.Scanner;

public class KinoTicket {
    public static void main(String[] args) {
        Scanner scanner_title = new Scanner(System.in);
        System.out.println("Enter Film Title");
        String titel = scanner_title.nextLine();

        Scanner scanner_row = new Scanner(System.in);
        System.out.println("Enter Row");
        String row = scanner_row.nextLine();

        Scanner scanner_movistile = new Scanner(System.in);
        System.out.println("Enter 2D/3D");
        String movistyle = scanner_movistile.nextLine() + (" Vorstellung");

        Scanner scanner_price = new Scanner(System.in);
        System.out.println("Enter Price");
        double price = scanner_price.nextDouble();

        Scanner scanner_place = new Scanner(System.in);
        System.out.println("Enter Place");
        int place = scanner_place.nextInt();

        Scanner scanner_watchtime = new Scanner(System.in);
        System.out.println("Enter Watchtime");
        int Itime = scanner_watchtime.nextInt();

        Scanner scanner_date = new Scanner(System.in);
        System.out.println("Enter Date");
        String date = scanner_date.nextLine();

        Scanner scanner_time = new Scanner(System.in);
        System.out.println("Enter Time");
        String watchTime = scanner_time.nextLine();


        System.out.println(titel);
        System.out.println(date);
        System.out.println(watchTime);
        System.out.println(movistyle);
        System.out.println("Reihe: " + row);
        System.out.println("Sitz: " + place);
        System.out.println(Itime + " Minuten");
        System.out.println(price + "€");
    }
}