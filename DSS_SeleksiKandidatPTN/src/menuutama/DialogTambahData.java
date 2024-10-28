
package menuutama;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JRootPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;

/**
 *
 * @author NaufalFadilaSandromeda
 */
public class DialogTambahData extends javax.swing.JDialog {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    /**
     * Creates new form DialogTambahData
     */
    public DialogTambahData(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //add Panel, add panel(sebuah panel)
        Pane.add(PanelKandidat);
        Pane.repaint();
        Pane.revalidate();
    }
    
    
   
    public void setDataTabel(String id, String nama, String jekel, String noHp, String alamat, String nilai_semester, String prestasi, String tingkat_kehadiran, String sikap, String keaktifan_organisasi){
        tNoId.setText(id);
        tNama.setText(nama);
        if(jekel.equals("Laki-Laki")){
            rbLaki.setSelected(true);
            rbPerempuan.setSelected(false);
        }else if(jekel.equals("Perempuan")){
            rbLaki.setSelected(false);
            rbPerempuan.setSelected(true);
        }
        tNoHp.setText(noHp);
        tAlamat.setText(alamat);
        tNilaiSMT.setText(nilai_semester);
        tPrestasi.setText(prestasi);
        cbKehadiran.setSelectedItem(tingkat_kehadiran);
        cbSikap.setSelectedItem(sikap);
        cbOrganisasi.setSelectedItem(keaktifan_organisasi);
    }
    
    protected void kosong(){
        tNoId.setText("");
        tNama.setText("");
        btnG.clearSelection();
        tAlamat.setText("");
        tNoHp.setText("");
        tNilaiSMT.setText("");
        tPrestasi.setText("");
        cbKehadiran.setSelectedIndex(0);
        cbSikap.setSelectedIndex(0);
        cbOrganisasi.setSelectedIndex(0);
    }
    
    private void insertDataSiswa(){        
        String sql = "INSERT INTO data_siswa VALUES (?,?,?,?,?,?,?,?,?,?)";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, tNoId.getText());
                stat.setString(2, tNama.getText());

                String jkel = "";
                if(rbLaki.isSelected()){
                    jkel="Laki-Laki";
                }else if(rbPerempuan.isSelected()){
                    jkel="Perempuan";
                }
                stat.setString(3, jkel);
                stat.setString(4, tNoHp.getText());
                stat.setString(5, tAlamat.getText());
                stat.setString(6, tNilaiSMT.getText());
                stat.setString(7, tPrestasi.getText());
                stat.setString(8, cbKehadiran.getSelectedItem().toString());
                stat.setString(9, cbSikap.getSelectedItem().toString());
                stat.setString(10, cbOrganisasi.getSelectedItem().toString());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
                kosong();
                tNoId.requestFocus();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan Biodata"+e);
            }
    }
    
    private void editDataSiswa(){
        try{
            String sql = "UPDATE data_siswa set nama=?, jeniskel=?,  no_hp=?, alamat=?, nilai_semester=?, prestasi=?, tingkat_kehadiran=?, sikap=?, keaktifan_organisasi=?  WHERE no_id=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tNama.getText());
            String jkel = "";
                if(rbLaki.isSelected()){
                    jkel="Laki-Laki";
                }else if(rbPerempuan.isSelected()){
                    jkel="Perempuan";
                }
            stat.setString(2, jkel);
            stat.setString(3, tNoHp.getText());
            stat.setString(4, tAlamat.getText());
            stat.setString(5, tNilaiSMT.getText());
            stat.setString(6, tPrestasi.getText());
            stat.setString(7, cbKehadiran.getSelectedItem().toString());
            stat.setString(8, cbSikap.getSelectedItem().toString());
            stat.setString(9, cbOrganisasi.getSelectedItem().toString());
            stat.setString(10, tNoId.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil diUbah ");
            kosong();
            tNoId.requestFocus();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Data Gagal DiUbah " + e);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnG = new javax.swing.ButtonGroup();
        PanelKandidat = new javax.swing.JPanel();
        tombolEdit = new javax.swing.JLabel();
        judul = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        tPrestasi = new javax.swing.JTextField();
        cbKehadiran = new javax.swing.JComboBox<>();
        cbSikap = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        cbOrganisasi = new javax.swing.JComboBox<>();
        tNilaiSMT = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tNama = new javax.swing.JTextField();
        tNoHp = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tAlamat = new javax.swing.JTextArea();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        tNoId = new javax.swing.JTextField();
        tombolTambah = new javax.swing.JLabel();
        IsiKosong = new javax.swing.JOptionPane();
        Pane = new javax.swing.JPanel();

        tombolEdit.setBackground(new java.awt.Color(0, 102, 51));
        tombolEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombolEdit.setForeground(new java.awt.Color(255, 255, 255));
        tombolEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tombolEdit.setText("Edit Data");
        tombolEdit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tombolEdit.setOpaque(true);
        tombolEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolEditMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tombolEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tombolEditMouseExited(evt);
            }
        });

        judul.setBackground(new java.awt.Color(51, 51, 51));
        judul.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        judul.setForeground(new java.awt.Color(255, 255, 255));
        judul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        judul.setText("Tambah Data Siswa");
        judul.setOpaque(true);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Penilaian Bobot Kandidat Siswa Terbaik"));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Prestasi");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Tingkat Kehadiran");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Kecakapan Sikap");

        cbKehadiran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Tingkat Kehadiran -", "Sangat Rajin", "Rajin", "Cukup" }));

        cbSikap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Sikap -", "Sikap A+ > 3 Semester", "Sikap A > 3 Semester", "Sikap B+ > 3 Semester ", "Sikap B > 3 Semester", " " }));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Keaktifan Organisasi");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Nilai Semester");

        jButton1.setText("?");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("?");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("?");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("?");
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("?");
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        cbOrganisasi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Keaktifan Siswa -", "Sangat Aktif", "Aktif", "Tidak Aktif" }));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addGap(23, 23, 23)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbSikap, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbKehadiran, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tPrestasi, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbOrganisasi, 0, 196, Short.MAX_VALUE)
                    .addComponent(tNilaiSMT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jButton1)
                    .addComponent(tNilaiSMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(tPrestasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cbKehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(cbSikap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jButton5)
                    .addComponent(cbOrganisasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Biodata Siswa"));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Nama");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Jenis Kelamin");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Alamat");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("No. Hp");

        tAlamat.setColumns(20);
        tAlamat.setRows(5);
        jScrollPane3.setViewportView(tAlamat);

        btnG.add(rbLaki);
        rbLaki.setText("Laki - Laki");

        btnG.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("No. ID");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tNama)
                            .addComponent(jScrollPane3)
                            .addComponent(tNoHp)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(rbLaki)
                                .addGap(18, 18, 18)
                                .addComponent(rbPerempuan)
                                .addGap(0, 85, Short.MAX_VALUE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(105, 105, 105)
                        .addComponent(tNoId)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tNoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rbLaki)
                    .addComponent(rbPerempuan))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tombolTambah.setBackground(new java.awt.Color(0, 102, 51));
        tombolTambah.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tombolTambah.setForeground(new java.awt.Color(255, 255, 255));
        tombolTambah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tombolTambah.setText("Tambah Data");
        tombolTambah.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tombolTambah.setOpaque(true);
        tombolTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolTambahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tombolTambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tombolTambahMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelKandidatLayout = new javax.swing.GroupLayout(PanelKandidat);
        PanelKandidat.setLayout(PanelKandidatLayout);
        PanelKandidatLayout.setHorizontalGroup(
            PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKandidatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKandidatLayout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(tombolTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 289, Short.MAX_VALUE))
                    .addGroup(PanelKandidatLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelKandidatLayout.setVerticalGroup(
            PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKandidatLayout.createSequentialGroup()
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tombolTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Pane.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(888, 577));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tombolTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahMouseClicked
        // TODO add your handling code here:
        if(!tNama.getText().equals("") && btnG.getSelection() != null 
                && !tAlamat.getText().equals("") && !tNoHp.getText().equals("") 
                && !tNilaiSMT.getText().equals("") && !tPrestasi.getText().equals("") 
                && cbKehadiran.getSelectedIndex() != 0 && cbSikap.getSelectedIndex() != 0
                && cbOrganisasi.getSelectedIndex() != 0 ){    
            insertDataSiswa();
            dispose();
        }else{
            IsiKosong.showMessageDialog(rootPane, "Mohon isi semua kolom isian pada form !", "Error", ERROR_MESSAGE );
        }
    }//GEN-LAST:event_tombolTambahMouseClicked

    private void tombolTambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahMouseEntered
        // TODO add your handling code here:
        tombolTambah.setBackground(new Color(0,102,101));
        tombolTambah.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tombolTambahMouseEntered

    private void tombolTambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahMouseExited
        // TODO add your handling code here:
        tombolTambah.setBackground(new Color(0,102,51));
    }//GEN-LAST:event_tombolTambahMouseExited

    private void tombolEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolEditMouseClicked
        // TODO add your handling code here:
        editDataSiswa();
        dispose();
    }//GEN-LAST:event_tombolEditMouseClicked

    private void tombolEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolEditMouseEntered
        // TODO add your handling code here:
        tombolEdit.setBackground(new Color(0,102,101));
        tombolEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tombolEditMouseEntered

    private void tombolEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolEditMouseExited
        // TODO add your handling code here:
        tombolEdit.setBackground(new Color(0,102,51));
    }//GEN-LAST:event_tombolEditMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(null, "Masukan Rata Rata Nilai Semester \n \t\t\t\t 0 - 100");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         JOptionPane.showMessageDialog(null, "Masukan Banyak Prestasi \n \t\t\t\t 0 - 100 Sertifikat Nasional");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(null, 
                "\t\\t\\t Sangat Rajin = Total Ketidakhadrian < 10 Hari <  "
                + "\\n Rajin = Total Ketidakhadrian > 15 Hari "
                + "\\n Tidak Aktif = Total Ketidakhadrian Melebihi 30 hari");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JOptionPane.showMessageDialog(null, "Masukan Kecakapan Sikap Sesuai Rapot");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JOptionPane.showMessageDialog(null, "\t\t\t Aktif = Mengikuti OSIS/MPK hingga kelas 12 \n Aktif = Mengikuti OSIS/MPK Setidaknya Satu Tahun \n Tidak Aktif = Tidak Pernah Bergabung Organisasi Manapun");
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogTambahData dialog = new DialogTambahData(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JOptionPane IsiKosong;
    private javax.swing.JPanel Pane;
    private javax.swing.JPanel PanelKandidat;
    private javax.swing.ButtonGroup btnG;
    private javax.swing.JComboBox<String> cbKehadiran;
    private javax.swing.JComboBox<String> cbOrganisasi;
    private javax.swing.JComboBox<String> cbSikap;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel judul;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTextArea tAlamat;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tNilaiSMT;
    private javax.swing.JTextField tNoHp;
    private javax.swing.JTextField tNoId;
    private javax.swing.JTextField tPrestasi;
    private javax.swing.JLabel tombolEdit;
    private javax.swing.JLabel tombolTambah;
    // End of variables declaration//GEN-END:variables

    void show(JRootPane rootPane) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
