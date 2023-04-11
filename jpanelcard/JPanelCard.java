/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jpanelcard;

import com.mysql.jdbc.PreparedStatement;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Gumilang
 */
public class JPanelCard extends javax.swing.JFrame {

    /**
     * Creates new form JPanelCard
     */
    private dbConnection db;
    private Boolean isUpdate = false;
    // untuk keperluan foto
    String filename = null;
    byte[] image = null;
    byte[] fotooo = null;
    
    public JPanelCard() {
        initComponents();
        db = new dbConnection();
        setPanel();
        tampil_combo();
        btnDelete.setVisible(false);
    }
    
    public void insertData() {
        // Get Data from Form
        String judul = txtJudul.getText();
        String deskripsi = txtDeskripsi.getText();
        String pelukis = cmbPelukis.getSelectedItem().toString();
        // Add New Data
        try {
            // pengecekan apakah form kosong atau tidak
            if(judul.isEmpty() || deskripsi.isEmpty() || pelukis.isEmpty() || image == null){
                JOptionPane.showMessageDialog(null, "Data belum lengkap");
            }else{
                // jika tidak koosng lgsg di masukkan ke database
                String sql = "INSERT INTO lukisan(judul, deskripsi, id_pelukis, image) VALUES (?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) db.conn.prepareStatement(sql);

                pst.setString(1, judul);
                pst.setString(2, deskripsi);
                pst.setString(3, pelukis);
                pst.setBytes(4, image);
                pst.executeUpdate();

                resetForm();
                // Show Information
                System.out.println("Insert Success!");
                JOptionPane.showMessageDialog(null, "Data berhasil Ditambahkan");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(JPanelCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        // update set panel
        setPanel();
    }
    
    public void updateData() throws SQLException {
        // Get Data from Form
        String id = hiddenText.getText();
        String judul = txtJudul.getText();
        String deskripsi = txtDeskripsi.getText();
        String pelukis = cmbPelukis.getSelectedItem().toString();
        
        // sql update
        String sql = "UPDATE lukisan SET judul = ?, deskripsi = ?, id_pelukis = ?, image = ? WHERE  kode = '"+id+"'";
        PreparedStatement pst = (PreparedStatement) db.conn.prepareStatement(sql);

        pst.setString(1, judul);
        pst.setString(2, deskripsi);
        pst.setString(3, pelukis);
        // jika tombol choose tidak dipencet
        if(image == null){
            pst.setBytes(4, fotooo);
        // jika tombol choose dipencet
        }else{
            pst.setBytes(4, image);
        }
        pst.executeUpdate();
        // Reset Form
        resetForm();
        
        // update set panel
        setPanel();

        
        // Show Information
        System.out.println("Update Success!");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
    }
    
    public void deleteData() {
        // Remove Data from List
        String id = hiddenText.getText();
        String sql = "DELETE FROM lukisan WHERE kode = '"+id+"'";
        db.updateQuery(sql);
        // Reset Form
        resetForm();
        // Show Information
        System.out.println("Delete Success!");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        setPanel();
    }
    
    public void resetForm() {
        // Set All Value Form to Empty
        txtJudul.setText("");
        txtDeskripsi.setText("");
        cmbPelukis.setSelectedIndex(0);
//        foto = null;
    }
    
    public void tampil_combo(){
        // untuk menampilkan semua id yang ada di tabel pelukis
        ResultSet res = db.selectQuery("SELECT * FROM pelukis");
        try {
            while(res.next()){
                cmbPelukis.addItem(res.getString("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JPanelCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTextLabel(String id, String judul, String deskripsi, String idPelukis, byte [] fotoo){
        txtJudul.setText(judul);
        txtDeskripsi.setText(deskripsi);
        cmbPelukis.setSelectedItem(idPelukis);
        // menampilkan foto di form
        if(image == null){
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(fotoo).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            foto.setIcon(imageIcon);
        }else{
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            foto.setIcon(imageIcon);
        }
        txtJudul.setText(judul);
        hiddenText.setText(id);
        btnDelete.setVisible(true);
        btnAdd.setText("UPDATE");
        this.isUpdate = true;
        // untuk mengambil nilai image ketika akan mengupdate suatu data tetapi foto tidak diganti
        fotooo = fotoo;
    }
    
    public void setPanel() {
        mainPanel.removeAll();
        mainPanel.setLayout(new GridLayout(0, 1));
        // tampilkan data dalam card
        ResultSet res = db.selectQuery("SELECT * FROM lukisan ORDER BY kode ASC");
        try {
            // Get Cell Value
            while(res.next()){
                mainPanel.add(new Card(res.getString("kode"), res.getString("judul"), res.getString("deskripsi"), res.getString("id_pelukis"), res.getBytes("image")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hiddenText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        labelTest1 = new javax.swing.JLabel();
        labelTest3 = new javax.swing.JLabel();
        labelTest4 = new javax.swing.JLabel();
        txtDeskripsi = new javax.swing.JTextField();
        txtJudul = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        foto = new javax.swing.JLabel();
        btnChoose = new javax.swing.JButton();
        cmbPelukis = new javax.swing.JComboBox<>();
        logout = new javax.swing.JButton();
        pelukis = new javax.swing.JButton();

        hiddenText.setText("jTextField1");
        hiddenText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hiddenTextActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(mainPanel);

        labelTest1.setText("Judul");

        labelTest3.setText("Deskripsi");

        labelTest4.setText("Id_Pelukis");

        txtDeskripsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDeskripsiActionPerformed(evt);
            }
        });

        txtJudul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJudulActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.setMaximumSize(new java.awt.Dimension(72, 27));
        btnAdd.setMinimumSize(new java.awt.Dimension(72, 27));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.setMaximumSize(new java.awt.Dimension(72, 27));
        btnDelete.setMinimumSize(new java.awt.Dimension(72, 27));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        foto.setBackground(new java.awt.Color(1, 242, 242));

        btnChoose.setText("Choose");
        btnChoose.setMaximumSize(new java.awt.Dimension(72, 27));
        btnChoose.setMinimumSize(new java.awt.Dimension(72, 27));
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        cmbPelukis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECT--" }));
        cmbPelukis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPelukisActionPerformed(evt);
            }
        });

        logout.setText("Log Out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        pelukis.setText("Lihat Pelukis");
        pelukis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pelukisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDeskripsi)
                    .addComponent(txtJudul)
                    .addComponent(labelTest1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTest3)
                    .addComponent(labelTest4)
                    .addComponent(cmbPelukis, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(logout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pelukis))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logout)
                    .addComponent(pelukis))
                .addGap(7, 7, 7)
                .addComponent(labelTest1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelTest3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDeskripsi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelTest4))
                    .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPelukis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDeskripsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeskripsiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeskripsiActionPerformed

    private void txtJudulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJudulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJudulActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if(isUpdate == false){
            insertData();
        }else{
            try {
                updateData();
            } catch (SQLException ex) {
                Logger.getLogger(JPanelCard.class.getName()).log(Level.SEVERE, null, ex);
            }
            btnAdd.setText("Add");
            btnDelete.setVisible(false);
            this.isUpdate = false;
        }
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        // konfirmasi delete
        int opt = JOptionPane.showConfirmDialog(null, "Do you want delete this data ?", "Delete", JOptionPane.YES_NO_OPTION);
        if(opt == 0){
            if(isUpdate == true){
                deleteData();
                btnAdd.setText("Add");
                btnDelete.setVisible(false);
                this.isUpdate = false;
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        // TODO add your handling code here:
        // proses pengambilan foto
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        filename = f.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH));
        foto.setIcon(imageIcon);
        
        try {
            File images = new File(filename);
            FileInputStream fis = new FileInputStream(images);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            
            for(int i; (i = fis.read(buff)) != -1;){
                bos.write(buff, 0, i);
            }
            
            image = bos.toByteArray();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnChooseActionPerformed

    private void hiddenTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hiddenTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hiddenTextActionPerformed

    private void cmbPelukisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPelukisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPelukisActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        // pindah halaman login ketika menekan logout
        dispose();
        Login loginPage = new Login();
        loginPage.show();
    }//GEN-LAST:event_logoutActionPerformed

    private void pelukisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pelukisActionPerformed
        // TODO add your handling code here:
        // pindah halaman pelukis ketika menekan pelukis
        dispose();
        DaftarPelukis pelukisPage = new DaftarPelukis();
        pelukisPage.show();
    }//GEN-LAST:event_pelukisActionPerformed

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
            java.util.logging.Logger.getLogger(JPanelCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JPanelCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JPanelCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JPanelCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JPanelCard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JComboBox<String> cmbPelukis;
    private javax.swing.JLabel foto;
    private javax.swing.JTextField hiddenText;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTest1;
    private javax.swing.JLabel labelTest3;
    private javax.swing.JLabel labelTest4;
    private javax.swing.JButton logout;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton pelukis;
    private javax.swing.JTextField txtDeskripsi;
    private javax.swing.JTextField txtJudul;
    // End of variables declaration//GEN-END:variables
}
