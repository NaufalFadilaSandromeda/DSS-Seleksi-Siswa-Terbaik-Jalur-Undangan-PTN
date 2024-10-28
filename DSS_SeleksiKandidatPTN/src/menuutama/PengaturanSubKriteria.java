
package menuutama;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;

/**
 *
 * @author NaufalFadilaSandromeda
 */
public class PengaturanSubKriteria extends javax.swing.JPanel {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    /**
     * Creates new form Pengaturan
     */
    public PengaturanSubKriteria() {
        initComponents();
        updateDataTabel();
    }
    
    protected void kosong(){
        cbSubNilSemester1.setSelectedIndex(0);
        cbSubNilSemester2.setSelectedIndex(0);
        cbSubNilSemester3.setSelectedIndex(0);
        cbSubNilSemester4.setSelectedIndex(0);
        cbSubPrestasi1.setSelectedIndex(0);
        cbSubPrestasi2.setSelectedIndex(0);
        cbSubPrestasi3.setSelectedIndex(0);
        cbSubKehadiran1.setSelectedIndex(0);
        cbSubKehadiran2.setSelectedIndex(0);
        cbSubKehadiran3.setSelectedIndex(0);
        cbSubSikap1.setSelectedIndex(0);
        cbSubSikap2.setSelectedIndex(0);
        cbSubSikap3.setSelectedIndex(0);
        cbSubSikap4.setSelectedIndex(0);
        cbSubOrganisasi1.setSelectedIndex(0);
        cbSubOrganisasi2.setSelectedIndex(0);
        cbSubOrganisasi3.setSelectedIndex(0);
    }
    
    protected void updateDataTabel(){
        Object[] Baris = {
            "Kode Kriteria",
            "Nama Kriteria",
            "Nama SubKriteria",
            "Kepentingan SubKriteria"
        };
        tabmode = new DefaultTableModel(null, Baris);
        tabelSubKriteria.setModel(tabmode);
        String sql = "SELECT * FROM sub_kriteria ORDER BY kd_kriteria, no_sub";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("kd_kriteria");
                String b = hasil.getString("nama_kriteria");
                String c = hasil.getString("nama_sub_kriteria");
                String d = hasil.getString("prioritas_kepentingan");
                
                String[] data={a, b, c, d};
                tabmode.addRow(data);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    protected void getDataTabel(){
        String sql = "SELECT nama_sub_kriteria FROM sub_kriteria ORDER BY kd_kriteria, no_sub";
        int n = 1;
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nama_sub_kriteria");
                if(n==1){
                    cbSubNilSemester1.setSelectedItem(a);
                }else if(n==2){
                    cbSubNilSemester2.setSelectedItem(a);
                }else if(n==3){
                    cbSubNilSemester3.setSelectedItem(a);
                }else if(n==4){
                    cbSubNilSemester4.setSelectedItem(a);
                }else if(n==5){
                    cbSubPrestasi1.setSelectedItem(a);
                }else if(n==6){
                    cbSubPrestasi2.setSelectedItem(a);
                }else if(n==7){
                    cbSubPrestasi3.setSelectedItem(a);
                }else if(n==8){
                    cbSubKehadiran1.setSelectedItem(a);
                }else if(n==9){
                    cbSubKehadiran2.setSelectedItem(a);
                }else if(n==10){
                    cbSubKehadiran3.setSelectedItem(a);
                }else if(n==11){
                    cbSubSikap1.setSelectedItem(a);
                }else if(n==12){
                    cbSubSikap2.setSelectedItem(a);
                }else if(n==13){
                    cbSubSikap3.setSelectedItem(a);
                }else if(n==14){
                    cbSubSikap4.setSelectedItem(a);
                }else if(n==15){
                    cbSubOrganisasi1.setSelectedItem(a);
                }else if(n==16){
                    cbSubOrganisasi2.setSelectedItem(a);
                }else if(n==17){
                    cbSubOrganisasi3.setSelectedItem(a);
                } 
                n++;
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    //masukan data subkriteria
    protected void insertDataSubKriteria(){
        try{
        int n=1, nSemester=1, nPrestasi=1, nKehadiran=1, nSikap=1, nOrganisasi=1, i=1;
            do{
                String kepentingan;
                String sql = "INSERT INTO sub_kriteria VALUES (?,?,?,?,?)";
                PreparedStatement stat = conn.prepareStatement(sql);
                java.sql.Statement statCek = conn.createStatement();
                String sqlSub = "SELECT kd_kriteria, nama_kriteria FROM kriteria";
                String sqlSemester = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Nilai Semester'";
                String sqlPrestasi = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Prestasi'";
                String sqlKehadiran = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Tingkat Kehadiran'";
                String sqlSikap = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Kecakapan Sikap'";
                String sqlOrganisasi = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Keaktifan Organisasi'";
                ResultSet hasil = statCek.executeQuery(sqlSub);
                if(n==1){
                    hasil = statCek.executeQuery(sqlSemester);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nSemester == 1){
                        stat.setString(4, cbSubNilSemester1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nSemester == 2){
                        stat.setString(4, cbSubNilSemester2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nSemester == 3){
                        stat.setString(4, cbSubNilSemester3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(4, cbSubNilSemester4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nSemester++;
                }else if(n==2){
                    hasil = statCek.executeQuery(sqlPrestasi);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nPrestasi == 1){
                        stat.setString(4, cbSubPrestasi1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nPrestasi == 2){
                        stat.setString(4, cbSubPrestasi2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(4, cbSubPrestasi3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nPrestasi++;
                }else if(n==3){
                    hasil = statCek.executeQuery(sqlKehadiran);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nKehadiran == 1){
                        stat.setString(4, cbSubKehadiran1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nKehadiran == 2){
                        stat.setString(4, cbSubKehadiran2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(4, cbSubKehadiran3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nKehadiran++;
                }else if(n==4){

                    hasil = statCek.executeQuery(sqlSikap);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nSikap == 1){
                        stat.setString(4, cbSubSikap1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nSikap == 2){
                        stat.setString(4, cbSubSikap2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nSikap == 3){
                        stat.setString(4, cbSubSikap3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(4, cbSubSikap4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nSikap++;
                } else {
                    hasil = statCek.executeQuery(sqlOrganisasi);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nOrganisasi == 1){
                        stat.setString(4, cbSubKehadiran1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nOrganisasi == 2){
                        stat.setString(4, cbSubKehadiran2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(4, cbSubKehadiran3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nOrganisasi++;
                }
            }while(n<=5);   
            JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
            kosong();
            updateDataTabel();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
    }
    
    protected void hapusDataSubKriteria(){
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog",JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            String sql = "DELETE FROM sub_kriteria";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil diHapus ");
                kosong();
                updateDataTabel();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Data Gagal diHapus " + e);
            }
        }
    }
    
    protected void editDataSubKriteria(){
        try{
        int n=1, nSemester=1, nPrestasi=1, nKehadiran=1, nSikap=1, nOrganisasi=1, i=1;
            do{
                String kepentingan;
                String sql = "UPDATE sub_kriteria set kd_kriteria=?, nama_kriteria=?, nama_sub_kriteria=?, prioritas_kepentingan=? WHERE no_sub=?";
                PreparedStatement stat = conn.prepareStatement(sql);
                java.sql.Statement statCek = conn.createStatement();
                String sqlSub = "SELECT kd_kriteria, nama_kriteria FROM kriteria";
                String sqlSemester = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Nilai Semester'";
                String sqlPrestasi = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Prestasi'";
                String sqlKehadiran = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Tingkat Kehadiran'";
                String sqlSikap = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Kecakapan Sikap'";
                String sqlOrganisasi = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Keaktifan Organisasi'";
                ResultSet hasil = statCek.executeQuery(sqlSub);
                if(n==1){
                    hasil = statCek.executeQuery(sqlSemester);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nSemester == 1){
                        stat.setString(3, cbSubNilSemester1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nSemester == 2){
                        stat.setString(3, cbSubNilSemester2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nSemester == 3){
                        stat.setString(3, cbSubNilSemester3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(3, cbSubNilSemester4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nSemester++;
                }else if(n==2){
                    hasil = statCek.executeQuery(sqlPrestasi);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nPrestasi == 1){
                        stat.setString(3, cbSubPrestasi1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nPrestasi == 2){
                        stat.setString(3, cbSubPrestasi2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(3, cbSubPrestasi3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nPrestasi++;
                }else if(n==3){
                    hasil = statCek.executeQuery(sqlKehadiran);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nKehadiran == 1){
                        stat.setString(3, cbSubKehadiran1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nKehadiran == 2){
                        stat.setString(3, cbSubKehadiran2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(3, cbSubKehadiran3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nKehadiran++;
                    
                    }
                    else if(n==4){
                    hasil = statCek.executeQuery(sqlSikap);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nSikap == 1){
                        stat.setString(3, cbSubNilSemester1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nSikap == 2){
                        stat.setString(3, cbSubNilSemester2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nSikap == 3){
                        stat.setString(3, cbSubNilSemester3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(3, cbSubNilSemester4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nSikap++;
                }else{    
                    hasil = statCek.executeQuery(sqlOrganisasi);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nOrganisasi == 1){
                        stat.setString(3, cbSubSikap1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nOrganisasi == 2){
                        stat.setString(3, cbSubSikap2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nOrganisasi == 3){
                        stat.setString(3, cbSubSikap3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(3, cbSubSikap4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nOrganisasi++;
                }
            }while(n<=5);   
            JOptionPane.showMessageDialog(null, "DATA Berhasil DiUbah");
            kosong();
            updateDataTabel();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal DiUbah "+e);
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

        judul = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelSubKriteria = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        tombolSimpan = new javax.swing.JButton();
        tombolEdit = new javax.swing.JButton();
        tombolHapus = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cbSubNilSemester4 = new javax.swing.JComboBox<>();
        cbSubNilSemester3 = new javax.swing.JComboBox<>();
        cbSubNilSemester2 = new javax.swing.JComboBox<>();
        cbSubNilSemester1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbSubPrestasi3 = new javax.swing.JComboBox<>();
        cbSubPrestasi2 = new javax.swing.JComboBox<>();
        cbSubPrestasi1 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cbSubSikap4 = new javax.swing.JComboBox<>();
        cbSubSikap3 = new javax.swing.JComboBox<>();
        cbSubSikap2 = new javax.swing.JComboBox<>();
        cbSubSikap1 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cbSubKehadiran3 = new javax.swing.JComboBox<>();
        cbSubKehadiran2 = new javax.swing.JComboBox<>();
        cbSubKehadiran1 = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        cbSubOrganisasi3 = new javax.swing.JComboBox<>();
        cbSubOrganisasi2 = new javax.swing.JComboBox<>();
        cbSubOrganisasi1 = new javax.swing.JComboBox<>();

        judul.setBackground(new java.awt.Color(255, 255, 255));
        judul.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        judul.setForeground(new java.awt.Color(0, 102, 51));
        judul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judul.setText("  Pengaturan Bobot Kepentingan SubKriteria");
        judul.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tabelSubKriteria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Kriteria", "Nama Kriteria", "Nama SubKriteria", "Kepentingan SubKriteria"
            }
        ));
        tabelSubKriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSubKriteriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelSubKriteria);

        jLabel1.setText("Catatan : Edit data, klik data pada tabel terlebih dahulu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(271, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tombolSimpan.setBackground(new java.awt.Color(0, 102, 51));
        tombolSimpan.setForeground(new java.awt.Color(255, 255, 255));
        tombolSimpan.setText("Simpan");
        tombolSimpan.setBorder(null);
        tombolSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolSimpanActionPerformed(evt);
            }
        });

        tombolEdit.setBackground(new java.awt.Color(0, 102, 51));
        tombolEdit.setForeground(new java.awt.Color(255, 255, 255));
        tombolEdit.setText("Edit");
        tombolEdit.setBorder(null);
        tombolEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolEditActionPerformed(evt);
            }
        });

        tombolHapus.setBackground(new java.awt.Color(102, 0, 0));
        tombolHapus.setForeground(new java.awt.Color(255, 255, 255));
        tombolHapus.setText("Hapus");
        tombolHapus.setBorder(null);
        tombolHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolHapusActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Kepentingan Nilai Semester 1-5"));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Bobot Yang Sangat Penting ke-1");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Bobot Penting ke-2");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Bobot Cukup Penting ke-3");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Bobot biasa ke-4");

        cbSubNilSemester4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Rerata Niliai -", "Rerata Nilai Lebih dari 95", "Rerata Nilai Lebih dari 90", "Rerata Nilai Lebih dari 85", "Rerata Nilai Kurang dari 85" }));

        cbSubNilSemester3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Rerata Niliai -", "Rerata Nilai Lebih dari 95", "Rerata Nilai Lebih dari 90", "Rerata Nilai Lebih dari 85", "Rerata Nilai Kurang dari 85" }));

        cbSubNilSemester2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Rerata Niliai -", "Rerata Nilai Lebih dari 95", "Rerata Nilai Lebih dari 90", "Rerata Nilai Lebih dari 85", "Rerata Nilai Kurang dari 85" }));

        cbSubNilSemester1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Rerata Niliai -", "Rerata Nilai Lebih dari 95", "Rerata Nilai Lebih dari 90", "Rerata Nilai Lebih dari 85", "Rerata Nilai Kurang dari 85" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbSubNilSemester2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubNilSemester3, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubNilSemester4, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubNilSemester1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbSubNilSemester1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbSubNilSemester2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbSubNilSemester3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cbSubNilSemester4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Kepentingan Prestasi Siswa\n"));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Bobot Yang Sangat Penting ke-1");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Bobot Cukup Penting ke-2");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Bobot biasa ke-3");

        cbSubPrestasi3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Prestasi -", "Memiliki > 2 Sertifikat Standar Nasional", "Memiliki 1 Sertifikat Standar Nasional", "Tidak Memiliki Sertifikat " }));

        cbSubPrestasi2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Prestasi -", "Memiliki > 2 Sertifikat Standar Nasional", "Memiliki 1 Sertifikat Standar Nasional", "Tidak Memiliki Sertifikat " }));

        cbSubPrestasi1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Prestasi -", "Memiliki > 2 Sertifikat Standar Nasional", "Memiliki 1 Sertifikat Standar Nasional", "Tidak Memiliki Sertifikat " }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbSubPrestasi3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSubPrestasi2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSubPrestasi1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(cbSubPrestasi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbSubPrestasi3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbSubPrestasi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Kepentingan Sikap"));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Bobot Yang Sangat Penting ke-1");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Bobot Penting ke-2");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Bobot Cukup Penting ke-3");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Sikap biasa ke-4");

        cbSubSikap4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Sikap -", "Memiliki Sikap A > 3 Semester", "Memiliki Sikap A > 1 Semester", "Memiliki Sikap B+ Setidaknya  4 Semester", "Memiliki Sikap B Setidaknya 2 Semester" }));

        cbSubSikap3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Sikap -", "Memiliki Sikap A > 3 Semester", "Memiliki Sikap A > 1 Semester", "Memiliki Sikap B+ Setidaknya  4 Semester", "Memiliki Sikap B Setidaknya 2 Semester" }));

        cbSubSikap2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Sikap -", "Memiliki Sikap A > 3 Semester", "Memiliki Sikap A > 1 Semester", "Memiliki Sikap B+ Setidaknya  4 Semester", "Memiliki Sikap B Setidaknya 2 Semester" }));

        cbSubSikap1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Sikap -", "Memiliki Sikap A > 3 Semester", "Memiliki Sikap A > 1 Semester", "Memiliki Sikap B+ Setidaknya  4 Semester", "Memiliki Sikap B Setidaknya 2 Semester" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbSubSikap3, javax.swing.GroupLayout.Alignment.TRAILING, 0, 1, Short.MAX_VALUE)
                    .addComponent(cbSubSikap2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 1, Short.MAX_VALUE)
                    .addComponent(cbSubSikap1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSubSikap4, 0, 1, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbSubSikap1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbSubSikap2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cbSubSikap3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbSubSikap4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Kepentingan Tingkat Kehadiran"));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Bobot Yang Sangat Penting ke-1");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Bobot Cukup Penting ke-2");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Bobot biasa ke-3");

        cbSubKehadiran3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Tingkat Kehadiran -", "Sangat Rajin", "Rajin", "Cukup" }));

        cbSubKehadiran2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Tingkat Kehadiran -", "Sangat Rajin", "Rajin", "Cukup" }));

        cbSubKehadiran1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Tingkat Kehadiran -", "Sangat Rajin", "Rajin", "Cukup" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbSubKehadiran3, 0, 206, Short.MAX_VALUE)
                    .addComponent(cbSubKehadiran2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubKehadiran1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbSubKehadiran1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSubKehadiran2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSubKehadiran3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Kepentingan Keaktifan Organisasi"));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Bobot Yang Sangat Penting ke-1");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Bobot Cukup Penting ke-2");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("Bobot biasa ke-3");

        cbSubOrganisasi3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Keaktifan Organisasi -", "Sangat Aktif", "Aktif", "Tidak Aktif" }));

        cbSubOrganisasi2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Keaktifan Organisasi -", "Sangat Aktif", "Aktif", "Tidak Aktif" }));

        cbSubOrganisasi1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih SubKriteria Keaktifan Organisasi -", "Sangat Aktif", "Aktif", "Tidak Aktif" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbSubOrganisasi3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubOrganisasi2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubOrganisasi1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbSubOrganisasi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSubOrganisasi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSubOrganisasi3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tombolSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tombolHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tombolSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tombolHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("Kepentingan Nilai Semester1-5");

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tombolSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolSimpanActionPerformed
        // TODO add your handling code here:
        insertDataSubKriteria();
    }//GEN-LAST:event_tombolSimpanActionPerformed

    private void tombolEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolEditActionPerformed
        // TODO add your handling code here:
        editDataSubKriteria();
    }//GEN-LAST:event_tombolEditActionPerformed

    private void tombolHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusActionPerformed
        // TODO add your handling code here:
        hapusDataSubKriteria();
    }//GEN-LAST:event_tombolHapusActionPerformed

    private void tabelSubKriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSubKriteriaMouseClicked
        // TODO add your handling code here:
        getDataTabel();
    }//GEN-LAST:event_tabelSubKriteriaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbSubKehadiran1;
    private javax.swing.JComboBox<String> cbSubKehadiran2;
    private javax.swing.JComboBox<String> cbSubKehadiran3;
    private javax.swing.JComboBox<String> cbSubNilSemester1;
    private javax.swing.JComboBox<String> cbSubNilSemester2;
    private javax.swing.JComboBox<String> cbSubNilSemester3;
    private javax.swing.JComboBox<String> cbSubNilSemester4;
    private javax.swing.JComboBox<String> cbSubOrganisasi1;
    private javax.swing.JComboBox<String> cbSubOrganisasi2;
    private javax.swing.JComboBox<String> cbSubOrganisasi3;
    private javax.swing.JComboBox<String> cbSubPrestasi1;
    private javax.swing.JComboBox<String> cbSubPrestasi2;
    private javax.swing.JComboBox<String> cbSubPrestasi3;
    private javax.swing.JComboBox<String> cbSubSikap1;
    private javax.swing.JComboBox<String> cbSubSikap2;
    private javax.swing.JComboBox<String> cbSubSikap3;
    private javax.swing.JComboBox<String> cbSubSikap4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel judul;
    private javax.swing.JTable tabelSubKriteria;
    private javax.swing.JButton tombolEdit;
    private javax.swing.JButton tombolHapus;
    private javax.swing.JButton tombolSimpan;
    // End of variables declaration//GEN-END:variables
}
