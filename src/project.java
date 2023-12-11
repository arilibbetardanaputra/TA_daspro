import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class project {
    static class Event {
        String event;
        String tanggal;
        String lokasi;
        String waktu;

        int kuotaTicket;
        int availableTicket;
        double harga;
        String namaPendaftar;

        Event(String event, String tanggal, String lokasi, String waktu, int kuotaTicket, int availableTicket, double harga) {
            this.event = event;
            this.tanggal = tanggal;
            this.lokasi = lokasi;
            this.waktu = waktu;
            this.kuotaTicket = kuotaTicket;
            this.availableTicket = availableTicket;
            this.harga = harga;
            this.namaPendaftar = "";
        }

        public String toString() {
            return event + " - " + tanggal + " - " + lokasi + " - " + waktu + " - Kuota: " + availableTicket + "/" + kuotaTicket + " - Harga: " + harga + " per tiket(satuan K)";
        }        

        double daftarTicket(int jumlah, String namaPendaftar) {
            if (availableTicket >= jumlah) {
                availableTicket -= jumlah;
                this.namaPendaftar = namaPendaftar;
                return jumlah * harga;
            } else {
                System.out.println("Maaf, tiket tidak cukup.");
                return 0;
            }
        }
    }

    static List<Event> availableEvent = new ArrayList<>();

    public static void main(String[] args) {
        availableEvent.add(new Event("Gebyar Konser Musik Tahun Baru", "1 Januari 2024", "Balai Kota Malang", "19.00 - 00.00 WIB", 400, 2, 50.0));
        availableEvent.add(new Event("Workshop FullStack Developer", "25 Februari 2024", "Gedung Fakultas Ilmu Komputer Universitas Brawijaya", "10.00 - 13.00 WIB", 50, 5, 25.0));
        availableEvent.add(new Event("UtsuruFest", "24 Agustus 2024", "Rooftop Mall Dinoyo City", "09.00 - 17.00 WIB", 250, 25, 25.0));

        Scanner input = new Scanner(System.in);
        System.out.println("Selamat Datang di Aplikasi Management Event");
        System.out.println("Berikut adalah peran yang disediakan: ");

        while (true) {
            System.out.println("1. Pencari Informasi Event");
            System.out.println("2. Penyelenggara Event");
            System.out.println("0. Keluar");
            System.out.print("Silahkan pilih opsi anda : ");

            int pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {
                case 0:
                    System.out.println("Terima Kasih telah menggunakan aplikasi Management Event");
                    return;
                case 1:
                    cariEvent(input);
                    break;
                case 2:
                    buatEvent(input);
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silahkan pilih sesuai yang sudah tertera.");
                    break;
            }
        }
    }

    static void cariEvent(Scanner cari) {
        boolean detail = true;
        System.out.println("\nEvent yang tersedia:");
        System.out.println("============================================");
        System.out.println("No.  Event                          Kuota  ");
        System.out.println("============================================");

        for (int i = 0; i < availableEvent.size(); i++) {
            Event event = availableEvent.get(i);
            String formatEvent = String.format("%-4d %-30s %d/%-4d", (i + 1), event.event, event.availableTicket, event.kuotaTicket);
            System.out.println(formatEvent);
        }

        System.out.println("============================================");
        System.out.print("\nApakah Anda ingin melihat detail dari suatu event? (y/t)");
        String input = cari.nextLine().toLowerCase();

        if (input.equals("y")) {
            System.out.print("Masukkan nama event yang ingin Anda lihat detailnya: ");
            String namaEvent = cari.nextLine();

            Event eventDipilih = null;
            for (Event event : availableEvent) {
                if (event.event.equalsIgnoreCase(namaEvent)) {
                    eventDipilih = event;
                    break;
                }
            }

            if (eventDipilih != null) {
                System.out.println("\n===========================================");
                System.out.println("               Detail Event                ");
                System.out.println("===========================================");
                System.out.println("Nama Event      : " + eventDipilih.event);
                System.out.println("Tanggal         : " + eventDipilih.tanggal);
                System.out.println("Lokasi          : " + eventDipilih.lokasi);
                System.out.println("Waktu           : " + eventDipilih.waktu);
                System.out.println("Kuota Tersedia  : " + eventDipilih.availableTicket + "/" + eventDipilih.kuotaTicket);
                System.out.println("Harga Tiket     : " + eventDipilih.harga + " per tiket(satuan K)");
                System.out.println("===========================================");

                while (detail) {
                    System.out.print("\nApakah Anda ingin melihat detail dari event lain? (y/t)");
                    String lanjutkan = cari.nextLine().toLowerCase();
                    if (lanjutkan.equals("t")) {
                        detail = false;
                        break;
                    } else if (!lanjutkan.equals("y")) {
                        System.out.println("Input tidak valid. Masukkan 'y' untuk melanjutkan atau 't' untuk berhenti.");
                    }

                    System.out.print("Masukkan nama event yang ingin Anda lihat detailnya: ");
                    namaEvent = cari.nextLine();

                    eventDipilih = null;
                    for (Event event : availableEvent) {
                        if (event.event.equalsIgnoreCase(namaEvent)) {
                            eventDipilih = event;
                            break;
                        }
                    }

                    if (eventDipilih != null) {
                        System.out.println("\n===========================================");
                        System.out.println("               Detail Event                ");
                        System.out.println("===========================================");
                        System.out.println("Nama Event      : " + eventDipilih.event);
                        System.out.println("Tanggal         : " + eventDipilih.tanggal);
                        System.out.println("Lokasi          : " + eventDipilih.lokasi);
                        System.out.println("Waktu           : " + eventDipilih.waktu);
                        System.out.println("Kuota Tersedia  : " + eventDipilih.availableTicket + "/" + eventDipilih.kuotaTicket);
                        System.out.println("Harga Tiket     : " + eventDipilih.harga + " per tiket(satuan K)");
                        System.out.println("===========================================");
                    } else {
                        System.out.println("Event tidak ditemukan.");
                    }
                }
            } else {
                System.out.println("Event tidak ditemukan.");
            }
        }

        System.out.print("\nApakah Anda ingin mendaftar pada salah satu event? (y/t)");
        String jawab = cari.nextLine().toLowerCase();

        if (jawab.equals("y")) {
            System.out.print("Masukkan nama event yang ingin anda ikuti: ");
            String namaEvent = cari.nextLine();

            Event eventDipilih = null;
            for (Event event : availableEvent) {
                if (event.event.equalsIgnoreCase(namaEvent)) {
                    eventDipilih = event;
                    break;
                }
            }

            if (eventDipilih != null) {
                System.out.print("Masukkan nama Anda untuk mendaftar: ");
                String namaPendaftar = cari.nextLine();

                System.out.print("Berapa tiket yang ingin anda beli? (Angka): ");
                int jumlahTicket = cari.nextInt();
                cari.nextLine();

                double totalHarga = eventDipilih.daftarTicket(jumlahTicket, namaPendaftar);
                if (totalHarga > 0) {
                    System.out.println("\n===========================================");
                    System.out.println("Pendaftaran pada event " + eventDipilih.event + " Berhasil!");
                    System.out.println("-------------------------------------------");
                    System.out.println("Pendaftar: " + namaPendaftar);
                    System.out.println("Total harga yang harus dibayar: " + totalHarga);
                    System.out.println("===========================================");
                    System.out.println();
                }
            } else {
                System.out.println("Event tidak ditemukan");
            }
        }
    }

    static void buatEvent(Scanner buat) {
        System.out.println("\nAnda telah memilih peran sebagai penyelenggara event");
        System.out.println("Masukkan detail event yang ingin anda buat: ");
        System.out.print("Nama Event: ");
        String event = buat.nextLine();
        System.out.print("Tanggal: ");
        String tanggal = buat.nextLine();
        System.out.print("Lokasi: ");
        String lokasi = buat.nextLine();
        System.out.print("Waktu: ");
        String waktu = buat.nextLine();
        System.out.print("Kuota tiket: ");
        int kuotaTicket = buat.nextInt();
        buat.nextLine();
        System.out.print("Harga tiket: ");
        double hargaTicket = buat.nextDouble();
        buat.nextLine();

        Event eventBaru = new Event(event, tanggal, lokasi, waktu, kuotaTicket, kuotaTicket, hargaTicket);
        availableEvent.add(eventBaru);

        System.out.println("\n===========================================");
        System.out.println("Event anda berhasil dibuat: " + eventBaru.event);
        System.out.println("Kuota tiket yang tersedia: " + kuotaTicket);
        System.out.println("===========================================");
        System.out.println();
        
    }
}
