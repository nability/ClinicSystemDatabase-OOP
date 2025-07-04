package DB;

import java.sql.*;
import java.util.*;
import model.Kunjungan;
import util.Koneksi;

public class KunjunganDB {
    public void insert(Kunjungan k) throws SQLException {
        String sql = "INSERT INTO kunjungan (id_pasien, id_dokter, tanggal_kunjungan, keluhan) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, k.getIdPasien());
            ps.setInt(2, k.getIdDokter());
            ps.setString(3, k.getTanggal());
            ps.setString(4, k.getKeluhan());
            ps.executeUpdate();
        }
    }

    public void update(Kunjungan k) throws SQLException {
        String sql = "UPDATE kunjungan SET id_pasien=?, id_dokter=?, tanggal_kunjungan=?, keluhan=? WHERE id_kunjungan=?";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, k.getIdPasien());
            ps.setInt(2, k.getIdDokter());
            ps.setString(3, k.getTanggal());
            ps.setString(4, k.getKeluhan());
            ps.setInt(5, k.getIdKunjungan());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM kunjungan WHERE id_kunjungan=?";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Kunjungan> getAll() throws SQLException {
        List<Kunjungan> list = new ArrayList<>();
        String sql = "SELECT * FROM kunjungan";
        try (Connection conn = Koneksi.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Kunjungan k = new Kunjungan(
                    rs.getInt("id_kunjungan"),
                    rs.getInt("id_pasien"),
                    rs.getInt("id_dokter"),
                    rs.getString("tanggal_kunjungan"),
                    rs.getString("keluhan")
                );
                list.add(k);
            }
        }
        return list;
    }
}