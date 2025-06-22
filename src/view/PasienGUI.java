package view;

import DB.PasienDB;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import model.Pasien;

public class PasienGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtNama, txtUmur, txtAlamat, txtJK;
    private PasienDB dao = new PasienDB();
    private int selectedId = -1;

    public PasienGUI() {
        setTitle("Manajemen Pasien Klinik");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table setup
        model = new DefaultTableModel(new String[]{"ID", "Nama", "Umur", "Alamat", "JK"}, 0);
        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedId = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString());
                txtNama.setText(model.getValueAt(table.getSelectedRow(), 1).toString());
                txtUmur.setText(model.getValueAt(table.getSelectedRow(), 2).toString());
                txtAlamat.setText(model.getValueAt(table.getSelectedRow(), 3).toString());
                txtJK.setText(model.getValueAt(table.getSelectedRow(), 4).toString());
            }
        });
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Form input
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        txtNama = new JTextField(); 
        txtUmur = new JTextField(); 
        txtAlamat = new JTextField(); 
        txtJK = new JTextField();

        form.add(new JLabel("Nama")); form.add(txtNama);
        form.add(new JLabel("Umur")); form.add(txtUmur);
        form.add(new JLabel("Alamat")); form.add(txtAlamat);
        form.add(new JLabel("Jenis Kelamin")); form.add(txtJK);

        JPanel tombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        JButton btnAdd = new JButton("Tambah");
        JButton btnUpdate = new JButton("Ubah");
        JButton btnDelete = new JButton("Hapus");

        tombol.add(btnAdd); 
        tombol.add(btnUpdate); 
        tombol.add(btnDelete);

        JPanel bawah = new JPanel(new BorderLayout());
        bawah.add(form, BorderLayout.CENTER);
        bawah.add(tombol, BorderLayout.SOUTH);

        add(bawah, BorderLayout.SOUTH);

        // Action
        btnAdd.addActionListener(e -> {
            try {
                Pasien p = new Pasien(0, txtNama.getText(), Integer.parseInt(txtUmur.getText()), txtAlamat.getText(), txtJK.getText());
                dao.insert(p);
                loadData();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnUpdate.addActionListener(e -> {
            try {
                Pasien p = new Pasien(selectedId, txtNama.getText(), Integer.parseInt(txtUmur.getText()), txtAlamat.getText(), txtJK.getText());
                dao.update(p);
                loadData();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnDelete.addActionListener(e -> {
            try {
                dao.delete(selectedId);
                loadData();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        loadData();
    }

    private void loadData() {
        try {
            model.setRowCount(0);
            for (Pasien p : dao.getAll()) {
                model.addRow(new Object[]{p.getIdPasien(), p.getNama(), p.getUmur(), p.getAlamat(), p.getJenisKelamin()});
            }
            clearForm();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearForm() {
        txtNama.setText("");
        txtUmur.setText("");
        txtAlamat.setText("");
        txtJK.setText("");
        selectedId = -1;
    }

    public static void main(String[] args) {
        new PasienGUI().setVisible(true);
    }
}
