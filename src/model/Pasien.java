package model;

public class Pasien {
    private int idPasien;
    private String nama;
    private int umur;
    private String alamat;
    private String jenisKelamin;

    // Constructor
    public Pasien() {}

    public Pasien(int idPasien, String nama, int umur, String alamat, String jenisKelamin) {
        this.idPasien = idPasien;
        this.nama = nama;
        this.umur = umur;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
    }

    // Getter dan Setter
    public int getIdPasien() { 
        return idPasien; 
    }
    public void setIdPasien(int idPasien) { 
        this.idPasien = idPasien; 
    }

    public String getNama() { 
        return nama; 
    }

    public void setNama(String nama) { 
        this.nama = nama; 
    }

    public int getUmur() { 
        return umur; 
    }

    public void setUmur(int umur) { 
        this.umur = umur; 
    }

    public String getAlamat() { 
        return alamat; 
    }

    public void setAlamat(String alamat) { 
        this.alamat = alamat; 
    }

    public String getJenisKelamin() { 
        return jenisKelamin; 
    }

    public void setJenisKelamin(String jenisKelamin) { 
        this.jenisKelamin = jenisKelamin; 
    }
    
} 
