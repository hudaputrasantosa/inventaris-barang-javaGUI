/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris.barang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import inventaris.barang.koneksi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Huda Putra
 */
public class managedata extends javax.swing.JFrame {
private DefaultTableModel _tabel;
    Connection _Cnn;
    koneksi getCnn = new koneksi();
    String kd_petugas, nama_petugas, jk_petugas, alamat_petugas, tlp_petugas, username, password, hak_akses;
    String kd_alat,model_alat,merk_alat,thn_buat_alat,kd_kategori,jenis_alat,cari_data;
    private String kdpetugas;
    private Class[] types;
    /**
     * Creates new form managedata
     */
    public managedata(String akses) {
        initComponents();
          hak_akses = akses;
        LoadData();
        LoadData1();
        LoadData2();
        form_clear();
        form_clear1();
        form_clear2();
    }
    private void LoadData(){
    String[] kolom = {"Kode", "Nama", "Jenis Kelamin", "Alamat","Telepon","Username","Password","Hak Akses"};
    _tabel = new DefaultTableModel(null,kolom){
       Class[] types = new Class [] {
           java.lang.String.class,
           java.lang.String.class,
           java.lang.String.class,
           java.lang.String.class,
           java.lang.String.class,
           java.lang.String.class,
           java.lang.String.class,
           java.lang.String.class
       };
       
       public Class getColumnClass(int columnIndex){
           return types [columnIndex];
       }
       public boolean isCellEditable(int row, int col){
           int cola = _tabel.getColumnCount();
           return (col < cola) ? false : true;
       }
    };
    
    tbpetugas.setModel(_tabel);
    try {
        _Cnn = null;
        koneksi getCnn = new koneksi();
        _Cnn = getCnn.getConnection();
        HapusTabel();
        String sql = "" +
                " SELECT kd_petugas, nama_petugas, jk_petugas, alamat_petugas, tlp_petugas, username, password, hak_akses from petugas order by kd_petugas asc";
        Statement stat = _Cnn.createStatement();
        ResultSet res = stat.executeQuery(sql);
        
        while(res.next()){
             String xkd_petugas = res.getString(1);
             String xnama_petugas = res.getString(2);
             String xjk_petugas= res.getString(3);
             String xalamat_petugas = res.getString(4);
             String xtlp_petugas = res.getString(5);
             String xusername = res.getString(6);
             String xpassword = res.getString(7);
             String xhak_akses = res.getString(8);
             Object[] data = {xkd_petugas,xnama_petugas,xjk_petugas,xalamat_petugas,xtlp_petugas,xusername,xpassword,xhak_akses};
             _tabel.addRow(data);
         }
         
        
         } catch (Exception ex) {
         JOptionPane.showMessageDialog(this,"Error : " + ex);
    }
    }
    
    private void caridata(){
    DefaultTableModel _tabel = new DefaultTableModel();
    _tabel.addColumn("Kode");
    _tabel.addColumn("Nama");
    _tabel.addColumn("Jenis Kelamin");
    _tabel.addColumn("Alamat ");
   _tabel.addColumn("Telepon");
       _tabel.addColumn("Username");
    _tabel.addColumn("Password");
    _tabel.addColumn("Hak Akses");
        try{
           _Cnn = null;
        koneksi getCnn = new koneksi();
        _Cnn = getCnn.getConnection();
            String sql = "Select * from petugas where kd_petugas like '%" + txtcari.getText() + "%'" +
            "or nama_petugas like '%" + txtcari.getText() + "%'";
           Statement stat = _Cnn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
            _tabel.addRow(new Object[]{
            rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
             rs.getString(6),
              rs.getString(7),
               rs.getString(8),
            });
            }
            tbpetugas.setModel(_tabel);

                }catch (Exception e){
            }
    }
    
       private void LoadData1(){
    String[] kolom = {"Kode Alat", "Model Alat", "Merk Alat", "Tahun Buat alat", "Kode Kategori"};
    _tabel = new DefaultTableModel(null,kolom){
       Class[] types = new Class [] {
           java.lang.String.class,
           java.lang.String.class,
           java.lang.String.class,
           java.lang.String.class,
           java.lang.String.class
       };
       
       public Class getColumnClass(int columnIndex){
           return types [columnIndex];
       }
       public boolean isCellEditable(int row, int col){
           int cola = _tabel.getColumnCount();
           return (col < cola) ? false : true;
       }
    };
    
    tbalat.setModel(_tabel);
    try {
        _Cnn = null;
        koneksi getCnn = new koneksi();
        _Cnn = getCnn.getConnection();
        HapusTabel();
        String sql = "" +
                " SELECT * from alat order by kd_alat";
        Statement stat = _Cnn.createStatement();
        ResultSet res = stat.executeQuery(sql);
        
         while(res.next()){
             String xkd_alat = res.getString(1);
            String xmodel_alat = res.getString(2);
             String xmerk_alat= res.getString(3);
              String xthn_buat_alat = res.getString(4);
             String xkd_kategori = res.getString(5);
             
             
             Object[] data = {xkd_alat,xmodel_alat,xmerk_alat,xthn_buat_alat,xkd_kategori};
             _tabel.addRow(data);
         }

         } catch (Exception ex) {
         JOptionPane.showMessageDialog(this,"Error : " + ex);
    }
    }
       
       
       private void LoadData2(){
    String[] kolom = {"Kode Kategori", "Jenis Alat"};
    _tabel = new DefaultTableModel(null,kolom){
       Class[] types = new Class [] {
           java.lang.String.class,
           java.lang.String.class
       };
       
       public Class getColumnClass(int columnIndex){
           return types [columnIndex];
       }
       public boolean isCellEditable(int row, int col){
           int cola = _tabel.getColumnCount();
           return (col < cola) ? false : true;
       }
    };
    
    tbkategori.setModel(_tabel);
    try {
        _Cnn = null;
        koneksi getCnn = new koneksi();
        _Cnn = getCnn.getConnection();
        HapusTabel();
        String sql = "" +
                "   SELECT * from kategori order by kd_kategori";
        Statement stat = _Cnn.createStatement();
        ResultSet res = stat.executeQuery(sql);
        
         while(res.next()){
             String xkd_kategori = res.getString(1);
             String xjenis_alat = res.getString(2);
             
             Object[] data = {xkd_kategori,xjenis_alat};
             _tabel.addRow(data);
         }
         

         } catch (Exception ex) {
         JOptionPane.showMessageDialog(this,"Error : " + ex);
    }
    }
public void  HapusTabel(){
    int row = _tabel.getRowCount();
    for (int i = 0;i < row;i++){
        _tabel.removeRow(0);
    }
}

private void form_clear(){
    txtkode.setEnabled(true);
    btnsimpan.setEnabled(true);
    btnubah.setEnabled(false);
    btnhapus.setEnabled(false);
    txtkode.setText("");
    txtnama.setText("");
    txtjk.setText("");
    txtalamat.setText("");
    txttelepon.setText("");
    txtusername.setText("");
    txtpassword.setText("");
}

private void form_clear1(){
    txtkodealat.setEnabled(true);
    btnsimpanalat.setEnabled(true);
    btnubahalat.setEnabled(false);
    btnhapusalat.setEnabled(false);
    txtkodealat.setText("");
    txtkodekategori.setText("");
    txtmodelalat.setText("");
    txtmerkalat.setText("");
    txttahunbuat.setText("");
}

private void form_clear2(){
    txtkodekategori2.setEnabled(true);
    btnsimpankategori.setEnabled(true);
    btnubahkategori.setEnabled(false);
    btnhapuskategori.setEnabled(false);
    txtkodekategori2.setText("");
    txtjenisalat.setText("");
   
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        tabpetugas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbpetugas = new javax.swing.JTable();
        txtkode = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtjk = new javax.swing.JTextField();
        txttelepon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtalamat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnsimpan = new javax.swing.JButton();
        btnubah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        cbhakakses = new javax.swing.JComboBox<>();
        txtusername = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtcari = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btncari = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbalat = new javax.swing.JTable();
        txtkodealat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtkodekategori = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtmodelalat = new javax.swing.JTextField();
        txtmerkalat = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txttahunbuat = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnsimpanalat = new javax.swing.JButton();
        btnubahalat = new javax.swing.JButton();
        btnhapusalat = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtcarialat = new javax.swing.JTextField();
        btncarialat = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbkategori = new javax.swing.JTable();
        txtkodekategori2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtjenisalat = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnsimpankategori = new javax.swing.JButton();
        btnubahkategori = new javax.swing.JButton();
        btnhapuskategori = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Management Data");

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(240, 240, 240));
        jLabel6.setText("Management Petugas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel6)
                .addContainerGap(257, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        tbpetugas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbpetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpetugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbpetugas);

        jLabel1.setText("Kode ");

        jLabel2.setText("Nama");

        jLabel3.setText("Jenis Kelamin");

        jLabel4.setText("Telepon");

        jLabel5.setText("Alamat");

        btnsimpan.setBackground(new java.awt.Color(255, 51, 51));
        btnsimpan.setForeground(new java.awt.Color(240, 240, 240));
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnubah.setBackground(new java.awt.Color(255, 51, 51));
        btnubah.setForeground(new java.awt.Color(240, 240, 240));
        btnubah.setText("Ubah");
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        btnhapus.setBackground(new java.awt.Color(255, 51, 51));
        btnhapus.setForeground(new java.awt.Color(240, 240, 240));
        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel16.setText("Hak Akses");

        cbhakakses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USER", "ADMIN", " " }));
        cbhakakses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbhakaksesActionPerformed(evt);
            }
        });

        jLabel17.setText("Username");

        jLabel18.setText("Password");

        jPanel7.setBackground(new java.awt.Color(255, 0, 0));

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("PENCARIAN");

        btncari.setText("Cari Petugas");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btncari)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari))
                .addContainerGap())
        );

        jButton1.setText("CETAK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtjk, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbhakakses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(204, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtjk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbhakakses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabpetugas.addTab("Petugas", jPanel1);

        jPanel9.setBackground(new java.awt.Color(255, 0, 0));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setText("Management Alat");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 102, 102));

        tbalat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbalat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbalatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbalat);

        txtkodealat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkodealatActionPerformed(evt);
            }
        });

        jLabel8.setText("Kode Alat ");

        jLabel9.setText("Kode Kategori");

        jLabel10.setText("Model ");

        jLabel11.setText("Merek");

        jLabel12.setText("Tahun Buat");

        btnsimpanalat.setBackground(new java.awt.Color(255, 51, 51));
        btnsimpanalat.setForeground(new java.awt.Color(240, 240, 240));
        btnsimpanalat.setText("Simpan");
        btnsimpanalat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanalatActionPerformed(evt);
            }
        });

        btnubahalat.setBackground(new java.awt.Color(255, 51, 51));
        btnubahalat.setForeground(new java.awt.Color(240, 240, 240));
        btnubahalat.setText("Ubah");
        btnubahalat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahalatActionPerformed(evt);
            }
        });

        btnhapusalat.setBackground(new java.awt.Color(255, 51, 51));
        btnhapusalat.setForeground(new java.awt.Color(240, 240, 240));
        btnhapusalat.setText("Hapus");
        btnhapusalat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusalatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnhapusalat, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsimpanalat, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnubahalat, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnsimpanalat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnubahalat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnhapusalat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 0, 0));

        txtcarialat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcarialatActionPerformed(evt);
            }
        });

        btncarialat.setText("Cari Alat");
        btncarialat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarialatActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("PENCARIAN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(40, 40, 40)
                .addComponent(txtcarialat, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btncarialat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncarialat)
                    .addComponent(txtcarialat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtkodealat, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttahunbuat, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmerkalat, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmodelalat, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtkodekategori, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtkodealat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmodelalat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmerkalat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttahunbuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtkodekategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabpetugas.addTab("Alat", jPanel8);

        jPanel13.setBackground(new java.awt.Color(255, 0, 0));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(240, 240, 240));
        jLabel13.setText("Management Kategori");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel13)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 102, 102));

        tbkategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbkategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkategoriMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbkategori);

        jLabel14.setText("Kode Kategori");

        jLabel15.setText("Jenis Alat");

        btnsimpankategori.setBackground(new java.awt.Color(255, 51, 51));
        btnsimpankategori.setForeground(new java.awt.Color(240, 240, 240));
        btnsimpankategori.setText("Simpan");
        btnsimpankategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpankategoriActionPerformed(evt);
            }
        });

        btnubahkategori.setBackground(new java.awt.Color(255, 51, 51));
        btnubahkategori.setForeground(new java.awt.Color(240, 240, 240));
        btnubahkategori.setText("Ubah");
        btnubahkategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahkategoriActionPerformed(evt);
            }
        });

        btnhapuskategori.setBackground(new java.awt.Color(255, 51, 51));
        btnhapuskategori.setForeground(new java.awt.Color(240, 240, 240));
        btnhapuskategori.setText("Hapus");
        btnhapuskategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapuskategoriActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnhapuskategori, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsimpankategori, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnubahkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnsimpankategori, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnubahkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnhapuskategori, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(txtjenisalat, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(txtkodekategori2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtkodekategori2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtjenisalat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabpetugas.addTab("Kategori", jPanel12);

        jButton2.setText("KEMBALI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabpetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabpetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbhakaksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbhakaksesActionPerformed
        String akses;
              if (cbhakakses.getSelectedIndex()==1) {
                  akses="ADMIN";
              }else {
                  
                  akses = "USER";
        } // TODO add your handling code here:
    }//GEN-LAST:event_cbhakaksesActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        kd_petugas = txtkode.getText();
        nama_petugas = txtnama.getText();
        jk_petugas = txtjk.getText();
        alamat_petugas = txtalamat.getText();
        tlp_petugas = txttelepon.getText();
        username = txtusername.getText();
        password = txtpassword.getText();
        String cbakses = cbhakakses.getSelectedItem().toString();
        hak_akses = cbakses;
//        hak_akses = cbhakakses.getSelectedItem().toString();
    
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlinsert=" INSERT INTO petugas VALUES('"+kd_petugas+"','"+nama_petugas+"','"+jk_petugas+"',"
                    + "'"+alamat_petugas+"','"+tlp_petugas+"','"+username+"','"+password+"','"+hak_akses+"')";
            Statement stat = _Cnn.createStatement();
            stat.executeUpdate(sqlinsert);
            
            form_clear();
            LoadData();
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            } 
        catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Data tidak berhasil disimpan!", "Informasi",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
String nama_petugas = tbpetugas.getValueAt(tbpetugas.getSelectedRow(), 1).toString();
        
        int jawab = JOptionPane.showConfirmDialog(this, "Yakin untuk menghapus data ini? nama petugas : "+nama_petugas,"Konfirmasi",
                JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            try {
                _Cnn = null;
                _Cnn = getCnn.getConnection();
                String sqldelete = " DELETE FROM petugas where kd_petugas ='"+txtkode.getText()+"'";
                Statement state = _Cnn.createStatement();
                state.executeUpdate(sqldelete);
                form_clear();
                LoadData();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                }catch (SQLException e) {
                     JOptionPane.showConfirmDialog(null, "Ada kesalahan input", "Informasi",
                             JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
            }  }      // TODO add your handling code here:
    }//GEN-LAST:event_btnhapusActionPerformed

    private void tbpetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpetugasMouseClicked
txtkode.setEnabled(false);
        btnsimpan.setEnabled(false);
        btnhapus.setEnabled(true);
        btnubah.setEnabled(true);
        
        String kdpetugas = tbpetugas.getValueAt(tbpetugas.getSelectedRow(), 0).toString();
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sql = " SELECT * FROM petugas WHERE kd_petugas='"+kdpetugas+"'";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            if (res.first()) {
                txtkode.setText(res.getString(1));
                txtnama.setText(res.getString(2));
                txtjk.setText(res.getString(3));
                txtalamat.setText(res.getString(4));
                 txttelepon.setText(res.getString(5));
                  txtusername.setText(res.getString(6));
                   txtpassword.setText(res.getString(7));
                   cbhakakses.setSelectedItem(res.getString(8));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tbpetugasMouseClicked

    private void tbalatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbalatMouseClicked
       txtkodealat.setEnabled(false);
        btnsimpanalat.setEnabled(false);
        btnhapusalat.setEnabled(true);
        btnubahalat.setEnabled(true);
        
        String kdalat = tbalat.getValueAt(tbalat.getSelectedRow(), 0).toString();
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sql = " SELECT * FROM alat WHERE kd_alat='"+kdalat+"'";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            if (res.first()) {
                txtkodealat.setText(res.getString(1));
                txtmodelalat.setText(res.getString(2));
                txtmerkalat.setText(res.getString(3));
                 txttahunbuat.setText(res.getString(4));
                 txtkodekategori.setText(res.getString(5));
                 
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }  
        // TODO add your handling code here:
    }//GEN-LAST:event_tbalatMouseClicked

    private void tbkategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkategoriMouseClicked
       txtkodekategori2.setEnabled(false);
        btnsimpankategori.setEnabled(false);
        btnhapuskategori.setEnabled(true);
        btnubahkategori.setEnabled(true);
        
        String kdkategori = tbkategori.getValueAt(tbkategori.getSelectedRow(), 0).toString();
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sql = " SELECT * FROM kategori WHERE kd_kategori='"+kdkategori+"'";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            if (res.first()) {
                txtkodekategori2.setText(res.getString(1));
                txtjenisalat.setText(res.getString(2));
              
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }     // TODO add your handling code here:
    }//GEN-LAST:event_tbkategoriMouseClicked

    private void btnsimpanalatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanalatActionPerformed
kd_alat = txtkodealat.getText();
        kd_kategori = txtkodekategori.getText();
        model_alat = txtmodelalat.getText();
        merk_alat = txtmerkalat.getText();
        thn_buat_alat = txttahunbuat.getText();
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlinsert = " INSERT INTO alat VALUES ('"+kd_alat+"',"
                    + "'"+model_alat+"','"+merk_alat+"','"+thn_buat_alat+"','"+kd_kategori+"')";
            
            Statement stat = _Cnn.createStatement();
            stat.executeUpdate(sqlinsert);
            form_clear1();
            LoadData1();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Data tidak berhasil disimpan!", "Informasi",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnsimpanalatActionPerformed

    private void btnhapusalatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusalatActionPerformed
      String kdalat = tbalat.getValueAt(tbalat.getSelectedRow(), 0).toString();
        
        int jawab = JOptionPane.showConfirmDialog(this, "Yakin untuk menghapus data ini? kode  "+kdalat,"Konfirmasi",
                JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            try {
                _Cnn = null;
                _Cnn = getCnn.getConnection();
                String sqldelete = " DELETE FROM alat where kd_alat ='"+txtkodealat.getText()+"'";
                Statement state = _Cnn.createStatement();
                state.executeUpdate(sqldelete);
                form_clear1();
                LoadData1();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                }catch (SQLException e) {
                     JOptionPane.showConfirmDialog(null, "Ada kesalahan input", "Informasi",
                             JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
            }  }   // TODO add your handling code here:
    }//GEN-LAST:event_btnhapusalatActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
 kd_petugas= txtkode.getText();
        nama_petugas = txtnama.getText();
        jk_petugas = txtjk.getText();
        alamat_petugas = txtalamat.getText();
        tlp_petugas = txttelepon.getText();
        username = txtusername.getText();
        password = txtpassword.getText();
        String cbakses = cbhakakses.getSelectedItem().toString();
        hak_akses = cbakses;
        try{
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlupdate = "update petugas set kd_petugas = '"+kd_petugas+"',nama_petugas= '"+nama_petugas+"', "
            + "jk_petugas= '"+jk_petugas+"',alamat_petugas = '"+alamat_petugas+"',tlp_petugas='"+tlp_petugas+"',username='"+username+"',password='"+password+"',"
            + "hak_akses='"+hak_akses+"' where kd_petugas ='"+kd_petugas+"'";

            Statement state = _Cnn.createStatement();
            state.executeUpdate(sqlupdate);
            form_clear();
            LoadData();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            }catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Data tidak berhasil disimpan!", "Informasi",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnubahActionPerformed

    private void btnubahalatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahalatActionPerformed
kd_alat= txtkodealat.getText();
        model_alat = txtmodelalat.getText();
        merk_alat = txtmerkalat.getText();
        thn_buat_alat = txttahunbuat.getText();
        kd_kategori = txtkodekategori.getText();
       
        try{
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlupdate = "update alat set kd_alat = '"+kd_alat+"',model_alat= '"+model_alat+"', "
            + "merk_alat= '"+merk_alat+"',thn_buat_alat = '"+thn_buat_alat+"',kd_kategori='"+kd_kategori+"' where kd_alat ='"+kd_alat+"'";

            Statement state = _Cnn.createStatement();
            state.executeUpdate(sqlupdate);
            form_clear1();
            LoadData1();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            }catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Data tidak berhasil disimpan!", "Informasi",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_btnubahalatActionPerformed

    private void btnsimpankategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpankategoriActionPerformed

        kd_kategori = txtkodekategori2.getText();
        jenis_alat = txtjenisalat.getText();
       
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlinsert = " INSERT INTO kategori VALUES ('"+kd_kategori+"','"+jenis_alat+"')";
            
            Statement stat = _Cnn.createStatement();
            stat.executeUpdate(sqlinsert);
            form_clear2();
            LoadData2();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Data tidak berhasil disimpan!", "Informasi",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }      // TODO add your handling code here:
    }//GEN-LAST:event_btnsimpankategoriActionPerformed

    private void btnhapuskategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapuskategoriActionPerformed
String namakategori = tbkategori.getValueAt(tbkategori.getSelectedRow(), 1).toString();
        
        int jawab = JOptionPane.showConfirmDialog(this, "Yakin untuk menghapus data ini? Nama "+namakategori,"Konfirmasi",
                JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            try {
                _Cnn = null;
                _Cnn = getCnn.getConnection();
                String sqldelete = " DELETE FROM kategori where kd_kategori ='"+txtkodekategori2.getText()+"'";
                Statement state = _Cnn.createStatement();
                state.executeUpdate(sqldelete);
                form_clear2();
                LoadData2();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                }catch (SQLException e) {
                     JOptionPane.showConfirmDialog(null, "Ada kesalahan input", "Informasi",
                             JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
            }  }         // TODO add your handling code here:
    }//GEN-LAST:event_btnhapuskategoriActionPerformed

    private void btnubahkategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahkategoriActionPerformed
kd_kategori= txtkodekategori2.getText();
        jenis_alat = txtjenisalat.getText();
       
        try{
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlupdate = " update kategori set kd_kategori = '"+kd_kategori+"',jenis_alat ='"+jenis_alat+"' where kd_kategori ='"+kd_kategori+"'";

            Statement state = _Cnn.createStatement();
            state.executeUpdate(sqlupdate);
            form_clear2();
            LoadData2();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            }catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Data tidak berhasil disimpan!", "Informasi",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_btnubahkategoriActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
    cari_data = txtcari.getText();
    
    
    DefaultTableModel _tabel = new DefaultTableModel();
 _tabel.addColumn("Kode");
    _tabel.addColumn("Nama");
    _tabel.addColumn("Jenis Kelamin");
    _tabel.addColumn("Alamat ");
   _tabel.addColumn("Telepon");
       _tabel.addColumn("Username");
    _tabel.addColumn("Password");
    _tabel.addColumn("Hak Akses");
   
        try {
        _Cnn = null;
        koneksi getCnn = new koneksi();
        _Cnn = getCnn.getConnection();
        String sql = "SELECT * FROM petugas where kd_petugas like '%"+cari_data+"%' or nama_petugas like '%"+cari_data+"%'";
        Statement stat = _Cnn.createStatement();
        ResultSet res = stat.executeQuery(sql);
        while(res.next()){
             _tabel.addRow(new Object[]{
             res.getString(1),
                 res.getString(2),
                 res.getString(3),
                 res.getString(4),
                 res.getString(5),
                 res.getString(6),
                 res.getString(7),
                 res.getString(8),
                
                 
             });
         }
        tbpetugas.setModel(_tabel);
         } catch(Exception e){
         }
    }//GEN-LAST:event_txtcariActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
     caridata();      // TODO add your handling code here:
    }//GEN-LAST:event_btncariActionPerformed

    private void txtcarialatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcarialatActionPerformed
   cari_data = txtcarialat.getText();
        DefaultTableModel _tabel = new DefaultTableModel();
 _tabel.addColumn("Kode Alat");
    _tabel.addColumn("Model Alat"); 
    _tabel.addColumn("Merk Alat");
    _tabel.addColumn("Tahun Buat alat");
   _tabel.addColumn("Kode Kategori");
    
   
        try {
        _Cnn = null;
        koneksi getCnn = new koneksi();
        _Cnn = getCnn.getConnection();
        String sql = "SELECT * FROM alat where kd_alat like '%"+cari_data+"%' or model_alat like '%"+cari_data+"%'";
        Statement stat = _Cnn.createStatement();
        ResultSet res = stat.executeQuery(sql);
        while(res.next()){
             _tabel.addRow(new Object[]{
             res.getString(1),
                 res.getString(2),
                 res.getString(3),
                 res.getString(4),
                 res.getString(5),
                
                
                 
             });
         }
        tbalat.setModel(_tabel);
         } catch(Exception e){
         }        // TODO add your handling code here:
    }//GEN-LAST:event_txtcarialatActionPerformed

    private void btncarialatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarialatActionPerformed
cari_data = txtcarialat.getText();
        DefaultTableModel _tabel = new DefaultTableModel();
 _tabel.addColumn("Kode Alat");
    _tabel.addColumn("Model Alat"); 
    _tabel.addColumn("Merk Alat");
    _tabel.addColumn("Tahun Buat alat");
   _tabel.addColumn("Kode Kategori");
    
   
        try {
        _Cnn = null;
        koneksi getCnn = new koneksi();
        _Cnn = getCnn.getConnection();
        String sql = "SELECT * FROM alat where kd_alat like '%"+cari_data+"%' or model_alat like '%"+cari_data+"%'";
        Statement stat = _Cnn.createStatement();
        ResultSet res = stat.executeQuery(sql);
        while(res.next()){
             _tabel.addRow(new Object[]{
             res.getString(1),
                 res.getString(2),
                 res.getString(3),
                 res.getString(4),
                 res.getString(5),
                
                
                 
             });
         }
        tbalat.setModel(_tabel);
         } catch(Exception e){
         }        // TODO add your handling code here:
    }//GEN-LAST:event_btncarialatActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
try{
    JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("reportpetugas.jasper"), null, Koneksi2.getConnection());
    JasperViewer.viewReport(jp, false);
} catch(Exception e){
    JOptionPane.showMessageDialog(rootPane, e);
}// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
menu y = new menu(hak_akses);
this.dispose();
y.show();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtkodealatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkodealatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkodealatActionPerformed

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
            java.util.logging.Logger.getLogger(managedata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(managedata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(managedata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(managedata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncari;
    private javax.swing.JButton btncarialat;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnhapusalat;
    private javax.swing.JButton btnhapuskategori;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btnsimpanalat;
    private javax.swing.JButton btnsimpankategori;
    private javax.swing.JButton btnubah;
    private javax.swing.JButton btnubahalat;
    private javax.swing.JButton btnubahkategori;
    private javax.swing.JComboBox<String> cbhakakses;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane tabpetugas;
    private javax.swing.JTable tbalat;
    private javax.swing.JTable tbkategori;
    private javax.swing.JTable tbpetugas;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtcarialat;
    private javax.swing.JTextField txtjenisalat;
    private javax.swing.JTextField txtjk;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtkodealat;
    private javax.swing.JTextField txtkodekategori;
    private javax.swing.JTextField txtkodekategori2;
    private javax.swing.JTextField txtmerkalat;
    private javax.swing.JTextField txtmodelalat;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtpassword;
    private javax.swing.JTextField txttahunbuat;
    private javax.swing.JTextField txttelepon;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
