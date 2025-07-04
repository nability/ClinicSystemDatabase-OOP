package view;

import DB.KunjunganDB;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Kunjungan;

public class KunjunganGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtTanggal, txtKeluhan, txtIdPasien, txtIdDokter;
    private KunjunganDB DB = new KunjunganDB();
    private int selectedId = -1;

    public KunjunganGUI() {
        setTitle("Manajemen Kunjungan Pasien");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "ID Pasien", "ID Dokter", "Tanggal", "Keluhan"}, 0);
        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedId = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString());
                txtIdPasien.setText(model.getValueAt(table.getSelectedRow(), 1).toString());
                txtIdDokter.setText(model.getValueAt(table.getSelectedRow(), 2).toString());
                txtTanggal.setText(model.getValueAt(table.getSelectedRow(), 3).toString());
                txtKeluhan.setText(model.getValueAt(table.getSelectedRow(), 4).toString());
            }
        });
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        txtIdPasien = new JTextField();
        txtIdDokter = new JTextField();
        txtTanggal = new JTextField();
        txtKeluhan = new JTextField();
        form.add(new JLabel("ID Pasien")); form.add(txtIdPasien);
        form.add(new JLabel("ID Dokter")); form.add(txtIdDokter);
        form.add(new JLabel("Tanggal")); form.add(txtTanggal);
        form.add(new JLabel("Keluhan")); form.add(txtKeluhan);

        JPanel tombol = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Tambah");
        JButton btnUpdate = new JButton("Ubah");
        JButton btnDelete = new JButton("Hapus");
        tombol.add(btnAdd); tombol.add(btnUpdate); tombol.add(btnDelete);

        JPanel bawah = new JPanel(new BorderLayout());
        bawah.add(form, BorderLayout.CENTER);
        bawah.add(tombol, BorderLayout.SOUTH);
        add(bawah, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
            try {
                Kunjungan k = new Kunjungan(0,
                        Integer.parseInt(txtIdPasien.getText()),
                        Integer.parseInt(txtIdDokter.getText()),
                        txtTanggal.getText(),
                        txtKeluhan.getText());
                DB.insert(k);
                loadData();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        btnUpdate.addActionListener(e -> {
            try {
                Kunjungan k = new Kunjungan(selectedId,
                        Integer.parseInt(txtIdPasien.getText()),
                        Integer.parseInt(txtIdDokter.getText()),
                        txtTanggal.getText(),
                        txtKeluhan.getText());
                DB.update(k);
                loadData();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        btnDelete.addActionListener(e -> {
            try {
                DB.delete(selectedId);
                loadData();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        loadData();
    }

    private void loadData() {
        try {
            model.setRowCount(0);
            for (Kunjungan k : DB.getAll()) {
                model.addRow(new Object[]{k.getIdKunjungan(), k.getIdPasien(), k.getIdDokter(), k.getTanggal(), k.getKeluhan()});
            }
            clearForm();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void clearForm() {
        txtIdPasien.setText("");
        txtIdDokter.setText("");
        txtTanggal.setText("");
        txtKeluhan.setText("");
        selectedId = -1;
    }

    public static void main(String[] args) {
        new KunjunganGUI().setVisible(true);
    }
}