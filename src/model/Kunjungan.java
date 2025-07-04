package model;

public class Kunjungan {
    private int idKunjungan;
    private int idPasien;
    private int idDokter;
    private String tanggal;
    private String keluhan;

    public Kunjungan() {}

    public Kunjungan(int idKunjungan, int idPasien, int idDokter, String tanggal, String keluhan) {
        this.idKunjungan = idKunjungan;
        this.idPasien = idPasien;
        this.idDokter = idDokter;
        this.tanggal = tanggal;
        this.keluhan = keluhan;
    }

    public int getIdKunjungan() { return idKunjungan; }
    public void setIdKunjungan(int idKunjungan) { this.idKunjungan = idKunjungan; }
    public int getIdPasien() { return idPasien; }
    public void setIdPasien(int idPasien) { this.idPasien = idPasien; }
    public int getIdDokter() { return idDokter; }
    public void setIdDokter(int idDokter) { this.idDokter = idDokter; }
    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public String getKeluhan() { return keluhan; }
    public void setKeluhan(String keluhan) { this.keluhan = keluhan; }
}
