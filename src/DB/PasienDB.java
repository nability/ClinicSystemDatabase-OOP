package DB;

import java.sql.*;
import java.util.*;
import model.Pasien;
import util.Koneksi;

public class PasienDB {
    public void insert(Pasien p) throws SQLException {
        String sql = "INSERT INTO Pasien (nama, umur, alamat, jenis_kelamin) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setInt(2, p.getUmur());
            ps.setString(3, p.getAlamat());
            ps.setString(4, p.getJenisKelamin());
            ps.executeUpdate();
        }
    }

    public void update(Pasien p) throws SQLException {
        String sql = "UPDATE Pasien SET nama=?, umur=?, alamat=?, jenis_kelamin=? WHERE id_pasien=?";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setInt(2, p.getUmur());
            ps.setString(3, p.getAlamat());
            ps.setString(4, p.getJenisKelamin());
            ps.setInt(5, p.getIdPasien());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Pasien WHERE id_pasien=?";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Pasien> getAll() throws SQLException {
        List<Pasien> list = new ArrayList<>();
        String sql = "SELECT * FROM Pasien";
        try (Connection conn = Koneksi.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pasien p = new Pasien(
                    rs.getInt("id_pasien"),
                    rs.getString("nama"),
                    rs.getInt("umur"),
                    rs.getString("alamat"),
                    rs.getString("jenis_kelamin")
                );
                list.add(p);
            }
        }
        return list;
    }
}