package view;

import DB.ResepDB;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Resep;

public class ResepGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtIdKunjungan, txtNamaObat, txtDosis;
    private ResepDB dao = new ResepDB();
    private int selectedId = -1;

    public ResepGUI() {
        setTitle("Manajemen Resep");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "ID Kunjungan", "Nama Obat", "Dosis"}, 0);
        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedId = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString());
                txtIdKunjungan.setText(model.getValueAt(table.getSelectedRow(), 1).toString());
                txtNamaObat.setText(model.getValueAt(table.getSelectedRow(), 2).toString());
                txtDosis.setText(model.getValueAt(table.getSelectedRow(), 3).toString());
            }
        });
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        txtIdKunjungan = new JTextField();
        txtNamaObat = new JTextField();
        txtDosis = new JTextField();
        form.add(new JLabel("ID Kunjungan")); form.add(txtIdKunjungan);
        form.add(new JLabel("Nama Obat")); form.add(txtNamaObat);
        form.add(new JLabel("Dosis")); form.add(txtDosis);

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
                Resep r = new Resep(0,
                        Integer.parseInt(txtIdKunjungan.getText()),
                        txtNamaObat.getText(),
                        txtDosis.getText());
                dao.insert(r);
                loadData();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        btnUpdate.addActionListener(e -> {
            try {
                Resep r = new Resep(selectedId,
                        Integer.parseInt(txtIdKunjungan.getText()),
                        txtNamaObat.getText(),
                        txtDosis.getText());
                dao.update(r);
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
            for (Resep r : dao.getAll()) {
                model.addRow(new Object[]{r.getIdResep(), r.getIdKunjungan(), r.getNamaObat(), r.getDosis()});
            }
            clearForm();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void clearForm() {
        txtIdKunjungan.setText("");
        txtNamaObat.setText("");
        txtDosis.setText("");
        selectedId = -1;
    }

    public static void main(String[] args) {
        new ResepGUI().setVisible(true);
    }
}
