package netflix;

import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class KullaniciYonetimSayfasi extends javax.swing.JFrame {
    
    private static String email = "" ;
    private static int currentUserId = 0 ;
    private static String currentUserName = "";
    String choosenCategory= "Aksiyon ve Macera";
    ArrayList<String> usersFavouriteCategorys;
    ArrayList<MovieClass> favouriteMovieList;
    
    public KullaniciYonetimSayfasi(String mail, int user_id, String u_name) {
        this.email = mail;
        this.currentUserId = user_id;
        this.currentUserName = u_name;
        this.usersFavouriteCategorys = MysqlConnect.getFavouriteCategories(currentUserId);
        this.favouriteMovieList = MysqlConnect.getListToFavouriteMovies(usersFavouriteCategorys);
        initComponents();
        setTable();
        setIcon();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        categorySearch_button = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        titleSearch_button = new javax.swing.JButton();
        accountPage_movieName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        MovieTable = new javax.swing.JTable();
        accountPage_categoryName = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Anasayfa");
        setLocation(new java.awt.Point(200, 100));

        categorySearch_button.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        categorySearch_button.setText("ARA");
        categorySearch_button.setPreferredSize(new java.awt.Dimension(60, 25));
        categorySearch_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorySearch_buttonActionPerformed(evt);
            }
        });

        jLabel3.setText("Tür");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        jLabel1.setText("YA DA");

        titleSearch_button.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        titleSearch_button.setText("ARA");
        titleSearch_button.setPreferredSize(new java.awt.Dimension(60, 25));
        titleSearch_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleSearch_buttonActionPerformed(evt);
            }
        });

        accountPage_movieName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        accountPage_movieName.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel2.setText("Film Adı");

        jLabel4.setText("Hoşgeldin "+ currentUserName);

        MovieTable.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        MovieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null, null},
                {"", null, null, "", null, null, null},
                {"", null, null, null, null, null, null},
                {"", null, null, null, null, null, null},
                {"", null, null, null, null, null, null},
                {"", null, null, null, null, null, null}
            },
            new String [] {
                "İzlenme", "Film Adı", "Type", "Kategori", "Bölüm Sayısı", "Bölüm Süresi", "Puan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        MovieTable.setRowHeight(20);
        MovieTable.setSelectionBackground(new java.awt.Color(204, 204, 255));
        MovieTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MovieTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(MovieTable);

        accountPage_categoryName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aksiyon ve Macera", "Bilim Kurgu", "Fantastik", "Romantik", "Dram", "Çocuk ve Aile", "Belgesel", "Komedi", "Korku", "Bilim ve Doğa", "Gerilim", "Anime", "Reality Program" }));
        accountPage_categoryName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountPage_categoryNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(accountPage_movieName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleSearch_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(accountPage_categoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(categorySearch_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(42, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accountPage_categoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(titleSearch_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(accountPage_movieName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(categorySearch_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void titleSearch_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleSearch_buttonActionPerformed
        
        String movieName = accountPage_movieName.getText();
        
        ArrayList<MovieClass> myMovie ;
        myMovie = MysqlConnect.searchMovies(1, movieName , "","");
        
        updateTable(myMovie);
        
        
        
    }//GEN-LAST:event_titleSearch_buttonActionPerformed

    private void categorySearch_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorySearch_buttonActionPerformed
       
        
            ArrayList<MovieClass> myMovieList;
            myMovieList = MysqlConnect.searchMovies(2,"",choosenCategory,"");
            updateTable(myMovieList);
        
        
    }//GEN-LAST:event_categorySearch_buttonActionPerformed

    private void accountPage_categoryNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountPage_categoryNameActionPerformed

        JComboBox cb = (JComboBox) evt.getSource();
        String str = (String) cb.getSelectedItem();
        setChoosenCategory(str);

    }//GEN-LAST:event_accountPage_categoryNameActionPerformed

    private void MovieTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MovieTableMouseClicked

        int i = MovieTable.getSelectedRow();
        System.out.println("Seçilen satır: "+ i);
        int id = favouriteMovieList.get(i).getId();
        System.out.println("SEÇİLEN FİLLMİN İD Sİ=="+id);
        
        switch (MysqlConnect.isMovieWatched(currentUserId, id)) {
                case 0:
                    System.out.println("USER_MOVİE_TABLE KAYIT YOK");
                    User_Movies_Table umt1 = new User_Movies_Table();
                    umt1.setMovie_id(id);
                    umt1.setUser_id(currentUserId);
                    umt1.setU_m_date("");
                    umt1.setU_m_lastTime(0);
                    umt1.setU_m_lastEpisode(1);
                    umt1.setU_m_isWatched(1);
                    MysqlConnect.insertUserMovieTable(umt1);
                    new DialogMovie(currentUserId,id,0,favouriteMovieList.get(i).getTime(),favouriteMovieList.get(i).getEpisode(),1).setVisible(true);
                    break;
                case 1:
                    break;
                case 2:
                    User_Movies_Table umt2 = MysqlConnect.getUserMovieTableItem(currentUserId, id);
                    new DialogMovie(currentUserId,id,umt2.getU_m_lastTime(),favouriteMovieList.get(i).getTime()
                            ,favouriteMovieList.get(i).getEpisode(),umt2.getU_m_lastEpisode()).setVisible(true);
                    break;
                case 3:
                    System.out.println("FİLM İZLENMİŞ----");
                    break;
                default:
                    break;
            }
        
        
    }//GEN-LAST:event_MovieTableMouseClicked

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
            java.util.logging.Logger.getLogger(KullaniciYonetimSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KullaniciYonetimSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KullaniciYonetimSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KullaniciYonetimSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new KullaniciYonetimSayfasi(email,currentUserId,currentUserName).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MovieTable;
    private javax.swing.JComboBox<String> accountPage_categoryName;
    private javax.swing.JTextField accountPage_movieName;
    private javax.swing.JButton categorySearch_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton titleSearch_button;
    // End of variables declaration//GEN-END:variables

    public void setChoosenCategory(String choosenCategory) {
        
        this.choosenCategory = choosenCategory;
        
    }
    
    private void setIcon() {
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("giriş.png")));
        
    }

    private void setTable() {
        
        DefaultTableModel model = (DefaultTableModel) MovieTable.getModel();
        
        for (int i = 0; i < 6; i++) {
            
            MovieClass movie = favouriteMovieList.get(i);
            
            System.out.println("İS WATCHED =="+MysqlConnect.isMovieWatched(currentUserId, movie.getId()));
            
            switch (MysqlConnect.isMovieWatched(currentUserId, movie.getId())) {
                case 0:
                    model.setValueAt("İZLE/FAVORİ", i, 0);
                    break;
                case 1:
                    model.setValueAt("İZLE/FAVORİ", i, 0);
                    break;
                case 2:
                    model.setValueAt("İZLENİYOR/FAVORİ", i, 0);
                    break;
                case 3:
                    model.setValueAt("İZLENDİ/FAVORİ", i, 0);
                    break;
                default:
                    break;
            }
            
            
            model.setValueAt(movie.getName(), i, 1);
            model.setValueAt(movie.getType(), i, 2);
            model.setValueAt(movie.getCategory(), i, 3);
            model.setValueAt(movie.getEpisode(), i, 4);
            model.setValueAt(movie.getTime(), i, 5);
            model.setValueAt(movie.getScore(), i, 6);
            
        }
        
    }

    private void updateTable(ArrayList<MovieClass> myMovies) {
        
        System.out.println("TABLOYA EKLEME İŞLERİ");
        
        DefaultTableModel model = (DefaultTableModel) MovieTable.getModel();
        
        int currentRows = model.getRowCount();
        System.out.println("TABLODAKİ SATIR SAYISI----"+currentRows);
        int arananFilmler = myMovies.size();
        System.out.println("BULUNAN FİLM SAYISI-----"+arananFilmler);
        int count =0;
        
        
        
        for ( int i = currentRows ; i < currentRows+myMovies.size(); i++) {
            
            MovieClass movie = myMovies.get(count);
            
            Object[] row = new Object[7];
            
            switch (MysqlConnect.isMovieWatched(currentUserId, movie.getId())) {
                case 0:
                    row[0] = "İZLE";
                    break;
                case 1:
                    row[0] = "İZLE";
                    break;
                case 2:
                    row[0] = "İZLENİYOR";
                    break;
                case 3:
                    row[0]= "İZLENDİ";
                    break;
                default:
                    break;
            }
            
            row[1] = movie.getName();
            row[2] = movie.getType();
            row[3] = movie.getCategory();
            row[4] = movie.getEpisode();
            row[5] = movie.getTime();
            row[6] = movie.getScore();
        
            model.addRow(row);
            count++;
            }
        favouriteMovieList.addAll(myMovies);
        
        
        
    }
    
    
    
}