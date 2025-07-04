package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardGUI extends JFrame {
    public DashboardGUI() {
        setTitle("Dashboard Klinik");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnPasien = new JButton("Manajemen Pasien");
        JButton btnDokter = new JButton("Manajemen Dokter");
        JButton btnKunjungan = new JButton("Manajemen Kunjungan");
        JButton btnResep = new JButton("Manajemen Resep");

        btnPasien.addActionListener(e -> new PasienGUI().setVisible(true));
        btnDokter.addActionListener(e -> new DokterGUI().setVisible(true));
        btnKunjungan.addActionListener(e -> new KunjunganGUI().setVisible(true));
        btnResep.addActionListener(e -> new ResepGUI().setVisible(true));

        add(btnPasien);
        add(btnDokter);
        add(btnKunjungan);
        add(btnResep);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardGUI().setVisible(true));
    }
}
