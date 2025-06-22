package dao;

import model.Dokter;
import util.Koneksi;
import java.sql.*;
import java.util.*;

public class DokterDAO {
    public void insert(Dokter d) throws SQLException {
        String sql = "INSERT INTO dokter (nama, spesialis, no_telepon) VALUES (?, ?, ?)";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getNama());
            ps.setString(2, d.getSpesialis());
            ps.setString(3, d.getNoTelepon());
            ps.executeUpdate();
        }
    }

    public void update(Dokter d) throws SQLException {
        String sql = "UPDATE dokter SET nama=?, spesialis=?, no_telepon=? WHERE id_dokter=?";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getNama());
            ps.setString(2, d.getSpesialis());
            ps.setString(3, d.getNoTelepon());
            ps.setInt(4, d.getIdDokter());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM dokter WHERE id_dokter=?";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Dokter> getAll() throws SQLException {
        List<Dokter> list = new ArrayList<>();
        String sql = "SELECT * FROM dokter";
        try (Connection conn = Koneksi.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Dokter d = new Dokter(
                    rs.getInt("id_dokter"),
                    rs.getString("nama"),
                    rs.getString("spesialis"),
                    rs.getString("no_telepon")
                );
                list.add(d);
            }
        }
        return list;
    }
}