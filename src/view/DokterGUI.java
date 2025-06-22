package view;

import dao.DokterDAO;
import model.Dokter;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DokterGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtNama, txtSpesialis, txtNoTelepon;
    private DokterDAO dao = new DokterDAO();
    private int selectedId = -1;

    public DokterGUI() {
        setTitle("Manajemen Dokter");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Nama", "Spesialis", "No Telepon"}, 0);
        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedId = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString());
                txtNama.setText(model.getValueAt(table.getSelectedRow(), 1).toString());
                txtSpesialis.setText(model.getValueAt(table.getSelectedRow(), 2).toString());
                txtNoTelepon.setText(model.getValueAt(table.getSelectedRow(), 3).toString());
            }
        });
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        txtNama = new JTextField(); txtSpesialis = new JTextField(); txtNoTelepon = new JTextField();
        form.add(new JLabel("Nama")); form.add(txtNama);
        form.add(new JLabel("Spesialis")); form.add(txtSpesialis);
        form.add(new JLabel("No Telepon")); form.add(txtNoTelepon);

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
                Dokter d = new Dokter(0, txtNama.getText(), txtSpesialis.getText(), txtNoTelepon.getText());
                dao.insert(d);
                loadData();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        btnUpdate.addActionListener(e -> {
            try {
                Dokter d = new Dokter(selectedId, txtNama.getText(), txtSpesialis.getText(), txtNoTelepon.getText());
                dao.update(d);
                loadData();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        btnDelete.addActionListener(e -> {
            try {
                dao.delete(selectedId);
                loadData();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        loadData();
    }

    private void loadData() {
        try {
            model.setRowCount(0);
            for (Dokter d : dao.getAll()) {
                model.addRow(new Object[]{d.getIdDokter(), d.getNama(), d.getSpesialis(), d.getNoTelepon()});
            }
            clearForm();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void clearForm() {
        txtNama.setText("");
        txtSpesialis.setText("");
        txtNoTelepon.setText("");
        selectedId = -1;
    }

    public static void main(String[] args) {
        new DokterGUI().setVisible(true);
    }
}
