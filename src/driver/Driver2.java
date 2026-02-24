import java.util.Scanner;

public class Driver2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] nilai = new int[N];

        for (int i = 0; i < N; i++) {
            nilai[i] = sc.nextInt();
        }

        int K = sc.nextInt(); // kode kelompok

        int index = 0;
        int ukuranKelompok = 1;
        int kelompokSekarang = 1;
        int total = 0;

        while (index < N) {

            int jumlahElemenKelompok = 0;

            for (int i = 0; i < ukuranKelompok && index < N; i++) {
                if (kelompokSekarang == K) {
                    total += nilai[index];
                }
                index++;
                jumlahElemenKelompok++;
            }

            if (kelompokSekarang == K) {
                break;
            }

            ukuranKelompok++;
            kelompokSekarang++;
        }

        System.out.println(total);
    }
}