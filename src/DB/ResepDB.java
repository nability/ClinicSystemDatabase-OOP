package DB;

import model.Resep;
import util.Koneksi;
import java.sql.*;
import java.util.*;

public class ResepDB {
    public void insert(Resep r) throws SQLException {
        String sql = "INSERT INTO resep (id_kunjungan, nama_obat, dosis) VALUES (?, ?, ?)";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, r.getIdKunjungan());
            ps.setString(2, r.getNamaObat());
            ps.setString(3, r.getDosis());
            ps.executeUpdate();
        }
    }

    public void update(Resep r) throws SQLException {
        String sql = "UPDATE resep SET id_kunjungan=?, nama_obat=?, dosis=? WHERE id_resep=?";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, r.getIdKunjungan());
            ps.setString(2, r.getNamaObat());
            ps.setString(3, r.getDosis());
            ps.setInt(4, r.getIdResep());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM resep WHERE id_resep=?";
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Resep> getAll() throws SQLException {
        List<Resep> list = new ArrayList<>();
        String sql = "SELECT * FROM resep";
        try (Connection conn = Koneksi.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Resep r = new Resep(
                    rs.getInt("id_resep"),
                    rs.getInt("id_kunjungan"),
                    rs.getString("nama_obat"),
                    rs.getString("dosis")
                );
                list.add(r);
            }
        }
        return list;
    }
}
