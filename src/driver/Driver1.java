import java.util.Scanner;

public class Driver1 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String kode;
        int porsiButet;
        int totalBayar = 0;

        System.out.println("Menu\t\tPorsi\tHarga\tTotal");
        System.out.println("==============================================");

        while (true) {
            kode = input.nextLine();

            if (kode.equals("END")) {
                break;
            }

            porsiButet = Integer.parseInt(input.nextLine());

            int harga = 0;
            String namaMenu = "";

            switch (kode) {
                case "NGS":
                    namaMenu = "Nasi Goreng";
                    harga = 15000;
                    break;
                case "AP":
                    namaMenu = "Ayam Penyet";
                    harga = 20000;
                    break;
                case "SA":
                    namaMenu = "Sate Ayam";
                    harga = 25000;
                    break;
                case "BU":
                    namaMenu = "Bakso Urat";
                    harga = 18000;
                    break;
                case "MAP":
                    namaMenu = "Mie Ayam";
                    harga = 15000;
                    break;
                case "GG":
                    namaMenu = "Gado-Gado";
                    harga = 15000;
                    break;
                case "SAM":
                    namaMenu = "Soto Ayam";
                    harga = 17000;
                    break;
                case "RD":
                    namaMenu = "Rendang";
                    harga = 25000;
                    break;
                case "IB":
                    namaMenu = "Ikan Bakar";
                    harga = 35000;
                    break;
                case "NUK":
                    namaMenu = "Nasi Uduk";
                    harga = 20000;
                    break;
                default:
                    System.out.println("Kode tidak valid!");
                    continue;
            }

            // Rasio berat 2:1
            int totalPorsi = porsiButet + (2 * porsiButet);

            int totalHarga = harga * totalPorsi;

            totalBayar += totalHarga;

            System.out.println(namaMenu + "\t" + totalPorsi + "\t" + harga + "\t" + totalHarga);
        }

        System.out.println("==============================================");
        System.out.println("Total Pembayaran\t\t\t" + totalBayar);
    }
}