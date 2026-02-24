import java.util.*;
import java.time.*;

enum Hari {
    SENIN, SELASA, RABU, KAMIS, JUMAT, SABTU, MINGGU
}

class Asrama {
    String nama;
    Hari hariLaundry;

    Asrama(String nama, Hari hariLaundry) {
        this.nama = nama;
        this.hariLaundry = hariLaundry;
    }
}

class Mahasiswa {
    String nim;
    String nama;
    Asrama asrama;

    Mahasiswa(String nim, String nama, Asrama asrama) {
        this.nim = nim;
        this.nama = nama;
        this.asrama = asrama;
    }
}

class Transaksi {
    Mahasiswa mahasiswa;
    double berat;
    double total;

    static final double HARGA_PER_KG = 7000;

    Transaksi(Mahasiswa mahasiswa, double berat) {
        this.mahasiswa = mahasiswa;
        this.berat = berat;
        this.total = berat * HARGA_PER_KG;
    }

    void tampil() {
        System.out.println("NIM      : " + mahasiswa.nim);
        System.out.println("Nama     : " + mahasiswa.nama);
        System.out.println("Asrama   : " + mahasiswa.asrama.nama);
        System.out.println("Berat    : " + berat + " kg");
        System.out.println("Total    : Rp " + total);
        System.out.println("----------------------------");
    }
}

public class Driver3 {

    static Scanner sc = new Scanner(System.in);
    static List<Transaksi> daftarTransaksi = new ArrayList<>();

    static List<Asrama> daftarAsrama = Arrays.asList(
        new Asrama("Lovelace", Hari.RABU),
        new Asrama("Simon Laplace", Hari.SELASA),
        new Asrama("Max Planck", Hari.RABU),
        new Asrama("Nikola Tesla", Hari.SELASA),
        new Asrama("Archimedes", Hari.JUMAT),
        new Asrama("Louis Pasteur", Hari.JUMAT),
        new Asrama("Rusun 3", Hari.JUMAT)
    );

    public static void main(String[] args) {

        while (true) {
            System.out.println("=== SISTEM LAUNDRY DEL ===");
            System.out.println("Hari ini: " + getHariSekarang());
            System.out.println("1. Tambah Transaksi");
            System.out.println("2. Lihat Semua Transaksi");
            System.out.println("3. Lihat Rekap Hari Ini");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1:
                    tambahTransaksi();
                    break;
                case 2:
                    lihatTransaksi();
                    break;
                case 3:
                    rekapHariIni();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.\n");
            }
        }
    }

    static Hari getHariSekarang() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        switch (today) {
            case MONDAY: return Hari.SENIN;
            case TUESDAY: return Hari.SELASA;
            case WEDNESDAY: return Hari.RABU;
            case THURSDAY: return Hari.KAMIS;
            case FRIDAY: return Hari.JUMAT;
            case SATURDAY: return Hari.SABTU;
            default: return Hari.MINGGU;
        }
    }

    static void tambahTransaksi() {

        System.out.print("Masukkan NIM: ");
        String nim = sc.nextLine();

        if (sudahLaundryMingguIni(nim)) {
            System.out.println("Mahasiswa sudah laundry minggu ini!\n");
            return;
        }

        System.out.print("Masukkan Nama: ");
        String nama = sc.nextLine();

        tampilkanAsrama();
        System.out.print("Pilih nomor asrama: ");
        int pilihan = sc.nextInt();
        sc.nextLine();

        if (pilihan < 1 || pilihan > daftarAsrama.size()) {
            System.out.println("Asrama tidak valid.\n");
            return;
        }

        Asrama asramaDipilih = daftarAsrama.get(pilihan - 1);

        if (asramaDipilih.hariLaundry != getHariSekarang()) {
            System.out.println("Hari ini bukan jadwal laundry untuk asrama "
                    + asramaDipilih.nama + "!\n");
            return;
        }

        System.out.print("Masukkan berat (kg): ");
        double berat = sc.nextDouble();
        sc.nextLine();

        if (berat < 5) {
            System.out.println("Minimal laundry adalah 5 kg per mahasiswa!\n");
            return;
        }

        Mahasiswa mhs = new Mahasiswa(nim, nama, asramaDipilih);
        Transaksi trx = new Transaksi(mhs, berat);
        daftarTransaksi.add(trx);

        System.out.println("Transaksi berhasil ditambahkan!\n");
    }

    static void tampilkanAsrama() {
        for (int i = 0; i < daftarAsrama.size(); i++) {
            System.out.println((i + 1) + ". " 
                + daftarAsrama.get(i).nama 
                + " (Hari: " 
                + daftarAsrama.get(i).hariLaundry + ")");
        }
    }

    static boolean sudahLaundryMingguIni(String nim) {
        for (Transaksi t : daftarTransaksi) {
            if (t.mahasiswa.nim.equals(nim)) {
                return true;
            }
        }
        return false;
    }

    static void lihatTransaksi() {
        if (daftarTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.\n");
            return;
        }

        for (Transaksi t : daftarTransaksi) {
            t.tampil();
        }
    }

    static void rekapHariIni() {
        double totalBerat = 0;
        double totalPendapatan = 0;

        for (Transaksi t : daftarTransaksi) {
            totalBerat += t.berat;
            totalPendapatan += t.total;
        }

        System.out.println("=== REKAP HARI INI ===");
        System.out.println("Total Berat      : " + totalBerat + " kg");
        System.out.println("Total Pendapatan : Rp " + totalPendapatan);
        System.out.println();
    }
}
