package sistem.reservasi.perpustakaan;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

class Buku{
    private String judulBuku;
    private String penulisBuku;
    private String statusBuku;

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public void setPenulisBuku(String penulisBuku) {
        this.penulisBuku = penulisBuku;
    }

    public void setStatusBuku(String statusBuku) {
        this.statusBuku = statusBuku;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public String getPenulisBuku() {
        return penulisBuku;
    }

    public String getStatusBuku() {
        return statusBuku;
    }
    
    public Buku(String judulBuku, String penulisBuku, String statusBuku){
        this.judulBuku = judulBuku;
        this.penulisBuku = penulisBuku;
        this.statusBuku = statusBuku;
    }
    
    public void tampilkanData(){
        System.out.println("Judul Buku\t: " + judulBuku);
        System.out.println("Penulis Buku\t: " + penulisBuku);
        System.out.println("Status Buku\t: " + statusBuku);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}

class Anggota{
    private String namaAnggota;
    private String idAnggota;
    private String statusAnggota;

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }

    public void setStatusAnggota(String statusAnggota) {
        this.statusAnggota = statusAnggota;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public String getStatusAnggota() {
        return statusAnggota;
    }
    
    public Anggota(String namaAnggota, String idAnggota, String statusAnggota){
        this.namaAnggota = namaAnggota;
        this.idAnggota = idAnggota;
        this.statusAnggota = statusAnggota;
    }
    
    public void tampilkanData(){
        System.out.println("Nama Anggota\t: " + namaAnggota);
        System.out.println("ID Anggota\t: " + idAnggota);
        System.out.println("Status Anggota\t: " + statusAnggota);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}

class Perpustakaan{
    private ArrayList<Buku> dataBuku = new ArrayList<>();
    private ArrayList<Anggota> dataAnggota = new ArrayList<>();
    
    public Perpustakaan(ArrayList<Buku> dataBuku, ArrayList<Anggota> dataAnggota){
        this.dataBuku = dataBuku;
        this.dataAnggota = dataAnggota;
    }
    
    public void tambahBuku(Buku buku){
        dataBuku.add(buku);
        System.out.println("Data Buku Yang Ditambahkan:");
        buku.tampilkanData();
    }
    
    public void tambahAnggota(Anggota anggota){
        String id = "";
        if((dataBuku.size() + 1) <= 10){
            id = "0";
        }
        anggota.setIdAnggota("ID_" + id + (dataAnggota.size() + 1));
        dataAnggota.add(anggota);
        System.out.println("Data Anggota Yang Ditambahkan:");
        anggota.tampilkanData();
    }
    
    public void pinjamBuku(Scanner input){
        System.out.print("Mausukan ID Anggota: ");
        String idAnggota = input.nextLine();
        for(int j = 0; j < dataAnggota.size(); j++){
            if(idAnggota.equalsIgnoreCase(dataAnggota.get(j).getIdAnggota())){
                if(!dataAnggota.get(j).getStatusAnggota().equalsIgnoreCase("tidak meminjam")){
                    System.out.println("Anda Terdata Sedang Meminjam Buku Lain");
                    System.out.println("Harap Kembalikan Buku Sebelumnya Untuk Meminjam Buku Lain");
                    return;
                }
                System.out.print("Mausukan Judul Buku Yang Ingin Dipinjam: ");
                String judulBuku = input.nextLine();
                for(int i = 0; i < dataBuku.size(); i++){
                    if(judulBuku.equalsIgnoreCase(dataBuku.get(i).getJudulBuku())){
                        if(!dataBuku.get(i).getStatusBuku().equalsIgnoreCase("tidak dipinjam")){
                            System.out.println("Buku " + judulBuku + " Sedang Dipinjam Orang Lain");
                            System.out.println("Anda Bisa Meminjam Buku Yang Lainnya");
                            return;
                        }
                        dataBuku.get(i).setStatusBuku("sedang dipinjam oleh: " + dataAnggota.get(j).getNamaAnggota());
                        dataAnggota.get(j).setStatusAnggota("sedang meminjam buku: " + dataBuku.get(i).getJudulBuku());
                        System.out.println("Buku Berhasil Dipinjam");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Data Anggota Yang Meminjam:");
                        dataAnggota.get(j).tampilkanData();
                        System.out.println("Data Buku Yang Dipinjam:");
                        dataBuku.get(i).tampilkanData();
                        return;
                    }
                }
                System.out.println("Judul Buku Tidak Ditemukan");
                return;
            }
        }
        System.out.println("ID Anggota Tidak Ditemukan");
    }
    
    public void kembalikanBuku(Scanner input){
        System.out.print("Mausukan ID Anggota: ");
        String idAnggota = input.nextLine();
        for(int j = 0; j < dataAnggota.size(); j++){
            if(idAnggota.equalsIgnoreCase(dataAnggota.get(j).getIdAnggota())){
                if(dataAnggota.get(j).getStatusAnggota().equalsIgnoreCase("tidak meminjam")){
                    System.out.println("Anda Terdata Tidak Sedang Meminjam Buku Apapun");
                    return;
                }
                System.out.print("Mausukan Judul Buku Yang Ingin Dikembalikan: ");
                String judulBuku = input.nextLine();
                for(int i = 0; i < dataBuku.size(); i++){
                    if(judulBuku.equalsIgnoreCase(dataBuku.get(i).getJudulBuku())){
                        if(!dataBuku.get(i).getStatusBuku().equalsIgnoreCase("sedang dipinjam oleh: " + dataAnggota.get(j).getNamaAnggota())){
                            System.out.println("Buku " + judulBuku + " Terdata Tidak Sedang Anda Pinjam");
                            return;
                        }
                        dataBuku.get(i).setStatusBuku("tidak dipinjam");
                        dataAnggota.get(j).setStatusAnggota("tidak meminjam");
                        System.out.println("Buku Berhasil Dikembalikan");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Data Anggota Yang Mengembalikan:");
                        dataAnggota.get(j).tampilkanData();
                        System.out.println("Data Buku Yang Dikembalikan:");
                        dataBuku.get(i).tampilkanData();
                        return;
                    }
                }
                System.out.println("Judul Buku Tidak Ditemukan");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                return;
            }
        }
        System.out.println("ID Anggota Tidak Ditemukan");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    public void tampilkanDaftarBuku(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i = 0; i < dataBuku.size(); i++){
            dataBuku.get(i).tampilkanData();
        }
    }
    
    public void tampilkanDaftarAnggota(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i = 0; i < dataAnggota.size(); i++){
            dataAnggota.get(i).tampilkanData();
        }
    }
    
    public void cariBuku(Scanner input){
        System.out.print("Masukan Kata Kunci: ");
        String kataKunci = input.nextLine();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        boolean ada = false;
        for(int i = 0; i < dataBuku.size(); i++){
            if(dataBuku.get(i).getJudulBuku().toLowerCase().contains(kataKunci.toLowerCase())){
                dataBuku.get(i).tampilkanData();
                ada = true;
            }
        }
        if(!ada){
            System.out.println("Judul Buku Dengan Kata Kunci \"" + kataKunci + "\" Tidak Ditemukan");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
    
    public void cariAnggota(Scanner input){
        System.out.print("Masukan Kata Kunci: ");
        String kataKunci = input.nextLine();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        boolean ada = false;
        for(int i = 0; i < dataAnggota.size(); i++){
            if(dataAnggota.get(i).getNamaAnggota().toLowerCase().contains(kataKunci.toLowerCase())){
                dataAnggota.get(i).tampilkanData();
                ada = true;
            }
        }
        if(!ada){
            System.out.println("Nama Anggota Dengan Kata Kunci \"" + kataKunci + "\" Tidak Ditemukan");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
}

public class SistemReservasiPerpustakaan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean looping = true;
        ArrayList<Buku> dataBuku = new ArrayList<>(Arrays.asList(
            new Buku("Bumi Manusia", "Pramoedya Ananta Toer", "tidak dipinjam"),
            new Buku("Cantik Itu Luka", "Eka Kurniawan", "tidak dipinjam"),
            new Buku("Dilan", "Pidi Baiq", "tidak dipinjam"),
            new Buku("Bumi", "Tere Liye", "tidak dipinjam"),
            new Buku("Laskar Pelangi", "Andrea Hirata", "tidak dipinjam"),
            new Buku("Laut Bercerita", "Leila S. Chudori", "tidak dipinjam"),
            new Buku("Negeri 5 Menara", "Ahmad Fuadi", "tidak dipinjam"),
            new Buku("Rindu", "Tere Liye", "tidak dipinjam"),
            new Buku("Rembulan Tenggelam Di Wajahmu", "Tere Liye", "tidak dipinjam")
        ));
        ArrayList<Anggota> dataAnggota = new ArrayList<>(Arrays.asList(
            new Anggota("Daffa Muhammad Al Fatih", "ID_01", "tidak meminjam"),
            new Anggota("Budi Budiman", "ID_02", "tidak meminjam"),
            new Anggota("Asep Septiawan", "ID_03", "tidak meminjam"),
            new Anggota("Joko Widodo", "ID_04", "tidak meminjam"),
            new Anggota("Gibran Rakabuming", "ID_05", "tidak meminjam")
        ));
        
        Perpustakaan perpus = new Perpustakaan(dataBuku, dataAnggota);
        String judulBuku;
        String penulisBuku;
        String idAnggota;
        String namaAnggota;
        
        while(looping){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("| SELAMT DATANG DI PERPUSTAKAAN NASIONAL |");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1. Tambah Buku Baru");
            System.out.println("2. Tambah Anggota Baru");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Tampilkan Daftar Buku");
            System.out.println("6. Tampilkan Daftar Anggota");
            System.out.println("7. Cari Buku");
            System.out.println("8. Cari Anggota");
            System.out.println("9. Keluar");
            System.out.print("Pilih Opsi: ");
            int opsi = input.nextInt();
            input.nextLine();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            switch(opsi){
                case 1:
                    System.out.println("TAMBAH BUKU BARU");
                    System.out.print("Masukan Judul Buku: ");
                    judulBuku = input.nextLine();
                    System.out.print("Masukan Nama Penulis: ");
                    penulisBuku = input.nextLine();
                    System.out.println("Buku Berhasil Ditambahkan");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    Buku buku = new Buku(judulBuku, penulisBuku, "tidak dipinjam");
                    perpus.tambahBuku(buku);
                    break;
                case 2:
                    System.out.println("TAMBAH ANGGOTA BARU");
                    System.out.print("Masukan Nama Calon Anggota: ");
                    namaAnggota = input.nextLine();
                    System.out.println("Anggota Berhasil Ditambahkan");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    Anggota anggota = new Anggota(namaAnggota, "-", "tidak meminjam");
                    perpus.tambahAnggota(anggota);
                    break;
                case 3:
                    System.out.println("PINJAM BUKU");
                    perpus.pinjamBuku(input);
                    break;
                case 4:
                    System.out.println("KEMBALIKAN BUKU");
                    perpus.kembalikanBuku(input);
                    break;
                case 5:
                    System.out.println("DAFTAR BUKU PERPUSTAKAAN");
                    perpus.tampilkanDaftarBuku();
                    break;
                case 6:
                    System.out.println("DAFTAR ANGGOTA PERPUSTAKAAN");
                    perpus.tampilkanDaftarAnggota();
                    break;
                case 7:
                    System.out.println("CARI BUKU");
                    perpus.cariBuku(input);
                    break;
                case 8:
                    System.out.println("CARI ANGGOTA");
                    perpus.cariAnggota(input);
                    break;
                case 9:
                    looping = false;
                    System.out.println("TERIMAKASI SUDAH BERKUNJUNG");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid");
            }
        }
    }
    
}
