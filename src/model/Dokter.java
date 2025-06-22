package model;

public class Dokter {
    private int idDokter;
    private String nama;
    private String spesialis;
    private String noTelepon;

    public Dokter() {}

    public Dokter(int idDokter, String nama, String spesialis, String noTelepon) {
        this.idDokter = idDokter;
        this.nama = nama;
        this.spesialis = spesialis;
        this.noTelepon = noTelepon;
    }

    public int getIdDokter() { 
        return idDokter; 
    }

    public void setIdDokter(int idDokter) { 
        this.idDokter = idDokter; 
    }

    public String getNama() { 
        return nama; 
    }

    public void setNama(String nama) { 
        this.nama = nama; 
    }

    public String getSpesialis() { 
        return spesialis; 
    }

    public void setSpesialis(String spesialis) { 
        this.spesialis = spesialis; 
    }
    
    public String getNoTelepon() { 
        return noTelepon; 
    }

    public void setNoTelepon(String noTelepon) { 
        this.noTelepon = noTelepon; 
    }
}
