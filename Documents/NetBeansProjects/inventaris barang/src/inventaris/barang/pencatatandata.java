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
public class pencatatandata extends javax.swing.JFrame {
private DefaultTableModel _tabel;
    Connection _Cnn;
    koneksi getCnn = new koneksi();
    String kd_inventaris, kd_petugas,kd_alat,tgl_pinjam;
    String model_alat,merk_alat,thn_buat_alat,kd_kategori,jenis_alat,hak_akses,cari_data,tgl_kembali;
    private Class[] types;
    /**
     * Creates new form pencatatandata
     */
    public pencatatandata(String akses) {
        initComponents();
         hak_akses = akses;
          LoadData();
          LoadData1();
          LoadData2();
        form_clear();
        form_clear2();
    }
private void LoadData(){
    String[] kolom = {"Kode Inventaris", "Kode Petugas", "Kode Alat", "Tanggal Pinjam"};
    _tabel = new DefaultTableModel(null,kolom){
       Class[] types = new Class [] {
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
    
    tbinventaris.setModel(_tabel);
    try {
        _Cnn = null;
        koneksi getCnn = new koneksi();
        _Cnn = getCnn.getConnection();
        HapusTabel();
        String sql = "" +
                "   SELECT * from inventaris order by tgl_pinjam";
        Statement stat = _Cnn.createStatement();
        ResultSet res = stat.executeQuery(sql);
        
         while(res.next()){
             String xkdinventaris = res.getString(1);
             String xkdpetugas = res.getString(2);
             String xkdalat = res.getString(3);
             String xtgl_pinjam = res.getString(4);
             Object[] data = {xkdinventaris,xkdpetugas,xkdalat,xtgl_pinjam};
             _tabel.addRow(data);
         }
         } catch (Exception ex) {
         JOptionPane.showMessageDialog(this,"Error : " + ex);
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
    
    tbalat2.setModel(_tabel);
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
    String[] kolom = {"Kode Inventaris", "Tanggal Kembali"};
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
    
    tbkembali.setModel(_tabel);
    try {
        _Cnn = null;
        koneksi getCnn = new koneksi();
        _Cnn = getCnn.getConnection();
        HapusTabel();
        String sql = "" +
                " SELECT * from kembali order by tgl_kembali";
        Statement stat = _Cnn.createStatement();
        ResultSet res = stat.executeQuery(sql);
        
         while(res.next()){
             String xkd_inventaris = res.getString(1);
            String xtgl_kembali = res.getString(2);
             
             Object[] data = {xkd_inventaris,xtgl_kembali};
             _tabel.addRow(data);
         }} catch (Exception ex) {
         JOptionPane.showMessageDialog(this,"Error : " + ex);
    }}

 
private void form_clear(){
    txtkodeinventaris.setEnabled(true);
    btnsimpan.setEnabled(true);
    btnubah.setEnabled(false);
    btnhapus.setEnabled(false);
    txtkodeinventaris.setText("");
    txtkodepetugas.setText("");
    txtkodealat.setText("");
    txttgl_pinjam.setText("");
}

private void form_clear2(){
    txtkodeinventaris2.setEnabled(true);
    btnsimpan2.setEnabled(true);
    btnubah2.setEnabled(false);
    btnhapus2.setEnabled(false);
    txtkodeinventaris2.setText("");
    txttgl_kembali.setText("");
}

public void  HapusTabel(){
    int row = _tabel.getRowCount();
    for (int i = 0;i < row;i++){
        _tabel.removeRow(0);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbinventaris = new javax.swing.JTable();
        txtkodeinventaris = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtkodepetugas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbalat2 = new javax.swing.JTable();
        txtkodealat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txttgl_pinjam = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnsimpan = new javax.swing.JButton();
        btnubah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbkembali = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtkodeinventaris2 = new javax.swing.JTextField();
        txttgl_kembali = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnsimpan2 = new javax.swing.JButton();
        btnubah2 = new javax.swing.JButton();
        btnhapus2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventaris Data");

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(240, 240, 240));
        jLabel6.setText("PENCATATAN DATA INVENTARIS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        tbinventaris.setModel(new javax.swing.table.DefaultTableModel(
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
        tbinventaris.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbinventarisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbinventaris);

        jLabel1.setText("Kode Inventaris");

        jLabel2.setText("Kode Petugas");

        tbalat2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbalat2);

        jLabel8.setText("Kode Alat");

        jLabel9.setText("Tanggal Pinjam");

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnubah.setText("Ubah");
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

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
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnubah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnsimpan)
                .addGap(18, 18, 18)
                .addComponent(btnubah)
                .addGap(18, 18, 18)
                .addComponent(btnhapus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbkembali.setModel(new javax.swing.table.DefaultTableModel(
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
        tbkembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkembaliMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbkembali);

        jLabel3.setText("Kode Inventaris");

        jLabel4.setText("Tanggal Kembali");

        btnsimpan2.setText("Simpan");
        btnsimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpan2ActionPerformed(evt);
            }
        });

        btnubah2.setText("Ubah");
        btnubah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubah2ActionPerformed(evt);
            }
        });

        btnhapus2.setText("Hapus");
        btnhapus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapus2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnubah2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsimpan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnhapus2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnsimpan2)
                .addGap(18, 18, 18)
                .addComponent(btnubah2)
                .addGap(18, 18, 18)
                .addComponent(btnhapus2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("CETAK DATA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PENCARIAN");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtkodeinventaris, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtkodepetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtkodealat)
                            .addComponent(txttgl_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43)
                                        .addComponent(txtcari))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtkodeinventaris2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttgl_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(42, 42, 42))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtkodealat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(txttgl_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtkodeinventaris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtkodepetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtkodeinventaris2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttgl_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
try{
    JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("reportinventaris.jasper"), null, Koneksi2.getConnection());
    JasperViewer.viewReport(jp, false);
} catch(Exception e){
    JOptionPane.showMessageDialog(rootPane, e);
}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbinventarisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbinventarisMouseClicked
txtkodeinventaris.setEnabled(false);
        btnsimpan.setEnabled(false);
        btnhapus.setEnabled(true);
        btnubah.setEnabled(true);
        
        String kdinventaris = tbinventaris.getValueAt(tbinventaris.getSelectedRow(), 0).toString();
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sql = " SELECT * FROM inventaris WHERE kd_inventaris='"+kdinventaris+"'";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            if (res.first()) {
                txtkodeinventaris.setText(res.getString(1));
                txtkodepetugas.setText(res.getString(2));
                 txtkodealat.setText(res.getString(3));
                  txttgl_pinjam.setText(res.getString(4));
              
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tbinventarisMouseClicked

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
    cari_data = txtcari.getText();
    
    
    DefaultTableModel _tabel = new DefaultTableModel();
 _tabel.addColumn("Kode Inventaris");
    _tabel.addColumn("Kode petugas");
    _tabel.addColumn("Kode Alat");
    _tabel.addColumn("Tanggal Pinjam");

   
        try {
        _Cnn = null;
        koneksi getCnn = new koneksi();
        _Cnn = getCnn.getConnection();
        String sql = "SELECT * FROM inventaris where kd_inventaris like '%"+cari_data+"%' ";
        Statement stat = _Cnn.createStatement();
        ResultSet res = stat.executeQuery(sql);
        while(res.next()){
             _tabel.addRow(new Object[]{
             res.getString(1),
                 res.getString(2),
                 res.getString(3),
                 res.getString(4),
                
                 
             });
         }
        tbinventaris.setModel(_tabel);
         } catch(Exception e){
         }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        String kdinventaris = tbinventaris.getValueAt(tbinventaris.getSelectedRow(), 0).toString();

        int jawab = JOptionPane.showConfirmDialog(this, "Yakin untuk menghapus data ini? Id barang : "+kdinventaris,"Konfirmasi",
            JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            try {
                _Cnn = null;
                _Cnn = getCnn.getConnection();
                String sqldelete = " DELETE FROM inventaris where kd_inventaris ='"+txtkodeinventaris.getText()+"'";
                Statement state = _Cnn.createStatement();
                state.executeUpdate(sqldelete);
                form_clear();
                LoadData();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            }catch (SQLException e) {
                JOptionPane.showConfirmDialog(null, "Ada kesalahan input", "Informasi",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
            }  }        // TODO add your handling code here:
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        kd_inventaris = txtkodeinventaris.getText();
        kd_petugas = txtkodepetugas.getText();
        kd_alat = txtkodealat.getText();
        tgl_pinjam = txttgl_pinjam.getText();

        try{
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlupdate = "update inventaris set kd_inventaris = '"+kd_inventaris+"',kd_petugas = '"+kd_petugas+"', "
            + "kd_alat= '"+kd_alat+"',tgl_pinjam = '"+tgl_pinjam+"' where kd_inventaris ='"+kd_inventaris+"'";

            Statement state = _Cnn.createStatement();
            state.executeUpdate(sqlupdate);
            form_clear();
            LoadData();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        }catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Data tidak berhasil disimpan!", "Informasi",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }            // TODO add your handling code here:
    }//GEN-LAST:event_btnubahActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        kd_inventaris = txtkodeinventaris.getText();
        kd_petugas = txtkodepetugas.getText();
        kd_alat = txtkodealat.getText();
        tgl_pinjam = txttgl_pinjam.getText();
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlinsert = " INSERT INTO inventaris VALUES ('"+kd_inventaris+"',"
            + "'"+kd_petugas+"','"+kd_alat+"','"+tgl_pinjam+"')";

            Statement stat = _Cnn.createStatement();
            stat.executeUpdate(sqlinsert);
            form_clear();
            LoadData();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Data tidak berhasil disimpan!", "Informasi",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void tbkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkembaliMouseClicked
txtkodeinventaris2.setEnabled(false);
        btnsimpan2.setEnabled(false);
        btnhapus2.setEnabled(true);
        btnubah2.setEnabled(true);
        
        String kdinventaris = tbkembali.getValueAt(tbkembali.getSelectedRow(), 0).toString();
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sql = " SELECT * FROM kembali WHERE kd_inventaris='"+kdinventaris+"'";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            if (res.first()) {
                txtkodeinventaris2.setText(res.getString(1));
                txttgl_kembali.setText(res.getString(2));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tbkembaliMouseClicked

    private void btnsimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpan2ActionPerformed
kd_inventaris = txtkodeinventaris2.getText();
        tgl_kembali = txttgl_kembali.getText();
       
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlinsert = " INSERT INTO kembali VALUES ('"+kd_inventaris+"','"+tgl_kembali+"')";

            Statement stat = _Cnn.createStatement();
            stat.executeUpdate(sqlinsert);
            form_clear2();
            LoadData2();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Data tidak berhasil disimpan!", "Informasi",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnsimpan2ActionPerformed

    private void btnubah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubah2ActionPerformed
     kd_inventaris = txtkodeinventaris2.getText();
        tgl_kembali = txttgl_kembali.getText();

        try{
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlupdate = "update kembali set kd_inventaris = '"+kd_inventaris+"', tgl_kembali = '"+tgl_kembali+"' where kd_inventaris ='"+kd_inventaris+"'";

            Statement state = _Cnn.createStatement();
            state.executeUpdate(sqlupdate);
            form_clear2();
            LoadData2();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        }catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Data tidak berhasil disimpan!", "Informasi",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_btnubah2ActionPerformed

    private void btnhapus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapus2ActionPerformed
  String kdinventaris = tbkembali.getValueAt(tbkembali.getSelectedRow(), 0).toString();

        int jawab = JOptionPane.showConfirmDialog(this, "Yakin untuk menghapus data ini? Kode : "+kdinventaris,"Konfirmasi",
            JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            try {
                _Cnn = null;
                _Cnn = getCnn.getConnection();
                String sqldelete = " DELETE FROM kembali where kd_inventaris ='"+txtkodeinventaris2.getText()+"'";
                Statement state = _Cnn.createStatement();
                state.executeUpdate(sqldelete);
                form_clear2();
                LoadData2();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            }catch (SQLException e) {
                JOptionPane.showConfirmDialog(null, "Ada kesalahan input", "Informasi",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
            }  }        // TODO add your handling code here:
    }//GEN-LAST:event_btnhapus2ActionPerformed

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
            java.util.logging.Logger.getLogger(pencatatandata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pencatatandata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pencatatandata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pencatatandata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnhapus2;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btnsimpan2;
    private javax.swing.JButton btnubah;
    private javax.swing.JButton btnubah2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbalat2;
    private javax.swing.JTable tbinventaris;
    private javax.swing.JTable tbkembali;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtkodealat;
    private javax.swing.JTextField txtkodeinventaris;
    private javax.swing.JTextField txtkodeinventaris2;
    private javax.swing.JTextField txtkodepetugas;
    private javax.swing.JTextField txttgl_kembali;
    private javax.swing.JTextField txttgl_pinjam;
    // End of variables declaration//GEN-END:variables
}
