package model;

public class Resep {
    private int idResep;
    private int idKunjungan;
    private String namaObat;
    private String dosis;

    public Resep() {}

    public Resep(int idResep, int idKunjungan, String namaObat, String dosis) {
        this.idResep = idResep;
        this.idKunjungan = idKunjungan;
        this.namaObat = namaObat;
        this.dosis = dosis;
    }

    public int getIdResep() { return idResep; }
    public void setIdResep(int idResep) { this.idResep = idResep; }
    public int getIdKunjungan() { return idKunjungan; }
    public void setIdKunjungan(int idKunjungan) { this.idKunjungan = idKunjungan; }
    public String getNamaObat() { return namaObat; }
    public void setNamaObat(String namaObat) { this.namaObat = namaObat; }
    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }
}