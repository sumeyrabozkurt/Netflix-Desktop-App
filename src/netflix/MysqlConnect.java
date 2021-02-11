package netflix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class MysqlConnect {
    
    private static final String dbName ="jdbc:mysql://localhost:3306/netflix_database?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
    private static final String dbUserName ="root";
    private static final String dbPassword ="1234";
    
    //Tabloların oluşturulduğu netflix_database e bağlanma
    public static Connection ConnectDB() throws SQLException{
        
        try {
            Class.forName("java.sql.Connection");
            Connection conn = DriverManager.getConnection(dbName, dbUserName, dbPassword);
            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //Kullanıcı login kontrolü
    public static boolean searchUser(String pEmail, String pPassword){
        
        Connection myConnection;
        PreparedStatement prSearch;
        ResultSet rsSearch;
        
        
        String sqlSearch ="Select *"
                    + " from user_table"
                    + " where"+" user_table.u_email="+"'"+pEmail+"'"+" and"+" user_table.u_password="+"'"+pPassword+"'";
        
        System.out.println(sqlSearch);
        
        try {
            
            myConnection = MysqlConnect.ConnectDB();
            prSearch = myConnection.prepareStatement(sqlSearch);
            rsSearch = prSearch.executeQuery();
            
            if(rsSearch.next()){
                
                return true;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //Kullanıcıyı db ye kayıt etme
    public static boolean insertUser(String name, String email, String password, String birthday, 
            String favourite1, String favourite2, String favourite3){
        
        Connection myConnection;
        PreparedStatement pr;
        int last_id = MysqlConnect.setCurrentUserId();
        try{
            myConnection = MysqlConnect.ConnectDB();

            String sqlWrite ="INSERT INTO netflix_database.user_table "
                    + "(u_id,u_name,u_email,u_password,u_birthday,u_favourite1,u_favourite2,u_favourite3)"
                    +" VALUES("+"'"+last_id+"',"+"'"+name+"',"+"'"+email+"',"+"'"+password+"',"+"'"+ birthday +"',"
                    +"'"+favourite1+"',"+"'"+favourite2+"',"+"'"+favourite3+"'"+")";
            System.out.println(sqlWrite);
            
            pr = myConnection.prepareStatement(sqlWrite);
            int count = pr.executeUpdate();
            if(count>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KayitSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    //Kullanıcı kaydı için unique id belirleme
    public static int setCurrentUserId(){
        
        Connection myConnection;
        PreparedStatement prSearch;
        ResultSet rsSearch;
        
        String sqlSearch="select last_insert_id() as last_id from user_table";
        System.out.println(sqlSearch);
        
        try {
            myConnection = MysqlConnect.ConnectDB();
            prSearch = myConnection.prepareStatement(sqlSearch);
            rsSearch = prSearch.executeQuery();
            
            int count = 0;
            while(rsSearch.next()){
                count++;
            }
            
            int last_id = count+1;
            return last_id;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
        
    }
    
    //Kullanıcı Yönetim Sayfasındaki film arama sorguları
    public static ArrayList searchMovies(int arama,String movieName,String categoryName,String favouriteCategoryName){
        
        Connection myConnection;
        PreparedStatement prSearch;
        ResultSet rsSearch;
        try{
            myConnection = MysqlConnect.ConnectDB();
            
            String sqlSearch = null;
            
            switch(arama){
                case 1:{ //Aranan program title ına ait filmi getirmek için yapılan sorgu
                    sqlSearch ="Select *" 
                    +" From netflix_database.movie_table "
                    +" where m_name = '"+movieName+"'";
                    break;
                }
                case 2:{//Seçilen kategoriye göre film bulmak için yapılan sorgu
                    sqlSearch="SELECT * "
                    + "FROM netflix_database.movie_category_table as MCT "
                    + "INNER JOIN netflix_database.movie_table as MT ON MCT.m_id = MT.m_id "
                    + "INNER JOIN netflix_database.category_table as CT ON MCT.c_id = CT.c_id "
                    + "where CT.c_name = '"+categoryName+"'"; 
                    break;
                }
                case 3:{//Kayıt olurken seçilen favori kategorilere göre 2 şer film getirmek için yapılan sorgu
                    sqlSearch="SELECT * "
                    + "FROM netflix_database.movie_category_table as MCT "
                    + "INNER JOIN netflix_database.movie_table as MT ON MCT.m_id = MT.m_id "
                    + "INNER JOIN netflix_database.category_table as CT ON MCT.c_id = CT.c_id "
                    + "where CT.c_name = '"+favouriteCategoryName+"' ORDER BY MT.m_score DESC limit 2";
                }
            }
               
            
            System.out.println(sqlSearch);
            prSearch = myConnection.prepareStatement(sqlSearch);
            rsSearch = prSearch.executeQuery();
            
            ArrayList<MovieClass> moviesList;
            
            moviesList = MysqlConnect.getMovieList(rsSearch);
            
            return moviesList;
            

        }catch (SQLException ex) {
            
            Logger.getLogger(KullaniciYonetimSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return null;
        
    }
    
    //Db den gelen filmleri arraylistte tutma
    public static ArrayList getMovieList(ResultSet rsSearch) throws SQLException{
        
        ArrayList<MovieClass> list = new ArrayList<>();
            
            while(rsSearch.next()) {
                
                System.out.println("FİLM BULUNDU =="+ rsSearch.getString("m_name"));
                MovieClass currentMovie = new MovieClass();
                currentMovie.setId(rsSearch.getInt("m_id"));
                currentMovie.setName(rsSearch.getString("m_name"));
                currentMovie.setType(rsSearch.getString("m_type"));
                currentMovie.setCategory(MysqlConnect.getCategoryOfMovie(rsSearch.getInt("m_id")));
                currentMovie.setEpisode(rsSearch.getInt("m_episode"));
                currentMovie.setTime(rsSearch.getInt("m_time"));
                currentMovie.setScore(rsSearch.getInt("m_score"));
                list.add(currentMovie);
            }
            return list;
            
    }
    
    //Başlangıçta seçilen favori kategorilere göre 2 şer film getirme
    public static ArrayList getListToFavouriteMovies(ArrayList<String> favouritesCtg){
        
        String fvt1 = favouritesCtg.get(0);
        String fvt2 = favouritesCtg.get(1);
        String fvt3 = favouritesCtg.get(2);
        
        ArrayList<MovieClass> list1,list2,list3;
        list1 = MysqlConnect.searchMovies(3, dbName, dbUserName,fvt1);
        list2 = MysqlConnect.searchMovies(3, dbName, dbUserName,fvt2);
        list3 = MysqlConnect.searchMovies(3, dbName, dbUserName,fvt3);
        
        list1.addAll(list2);
        list1.addAll(list3);
        
        return list1;
        
    }
    
    //Kuulanıcının seçtiği favori kategorilere erişme
    public static ArrayList getFavouriteCategories(int id){
        
        Connection myConnection;
        PreparedStatement prSearch;
        ResultSet rsSearch;
        
        
        String sqlSearch ="Select *"
                    + " from user_table"
                    + " where"+" user_table.u_id="+"'"+id+"'";
        
        System.out.println(sqlSearch);
        
        try {
            
            myConnection = MysqlConnect.ConnectDB();
            prSearch = myConnection.prepareStatement(sqlSearch);
            rsSearch = prSearch.executeQuery();
            
            if(rsSearch.next()){
                String str1,str2,str3;
                str1 = rsSearch.getString("u_favourite1");
                str2 = rsSearch.getString("u_favourite2");
                str3 = rsSearch.getString("u_favourite3");
                
                ArrayList<String> list = new ArrayList<>();
                list.add(str1);
                list.add(str2);
                list.add(str3);
                
                return list;
                
            }else{
                
                System.out.println("Kullanıcı Bulunamadı!");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    //Bir filmin sahip olduğu tüm kategorileri bulan sorgu
    public static String getCategoryOfMovie(int movie_id){
        
        Connection myConnection;
        PreparedStatement prSearch;
        ResultSet rsSearch;
        
        String sqlSearch ="SELECT CT.c_name" 
                +" FROM netflix_database.movie_category_table as MCT" 
                +" INNER JOIN netflix_database.movie_table as MT ON MCT.m_id= MT.m_id" 
                +" INNER JOIN netflix_database.category_table as CT ON CT.c_id = MCT.c_id" 
                +" WHERE  MT.m_id = '"+movie_id+"'";
        
        System.out.println(sqlSearch);
        
        try {
            
            myConnection = MysqlConnect.ConnectDB();
            prSearch = myConnection.prepareStatement(sqlSearch);
            rsSearch = prSearch.executeQuery();
            
            String category="";
            
            while(rsSearch.next()) {
                
                category =  rsSearch.getString("c_name")+ ", " +category ;
                
            }
            return category;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    //Kullanıcın filmi izleme,izlediği tarih,kaldığı zaman gibi bilgilerinin kaydı
    public static void insertUserMovieTable(User_Movies_Table umt){
        
        Connection myConnection;
        PreparedStatement pr;
        try{
            myConnection = MysqlConnect.ConnectDB();
            
            String sqlWrite ="INSERT INTO netflix_database.user_movie_table "
                    +" (user_id,movie_id,u_m_date,u_m_lastTime,u_m_lastEpisode,u_m_isWatched)"
                    +" VALUES("+"'"+umt.getUser_id()+"',"+"'"+umt.getMovie_id()+"',"+"'"+umt.getU_m_date()+"',"
                    +" '"+umt.getU_m_lastTime()+"',"+"'"+ umt.getU_m_lastEpisode() +"',"+"'"+umt.getU_m_isWatched()+"')";
            
            System.out.println(sqlWrite);
            
            pr = myConnection.prepareStatement(sqlWrite);
            int count = pr.executeUpdate();
            if(count>0){
                System.out.println("DB KAYIT İŞLEMİ OLDU..");
            }else{
                System.out.println("DB KAYIT EDİLEMEDİ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(KayitSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Kullanıcının filmlere kaldığı yerden devam edebilmesi için getirilen bilgiler
    public static User_Movies_Table getUserMovieTableItem(int user_id, int movie_id){
        
        Connection myConnection;
        PreparedStatement prSearch;
        ResultSet rsSearch;
        
        String sqlSearch="SELECT *" 
                +" FROM netflix_database.user_movie_table as UMT" 
                +" WHERE UMT.user_id ="+"'"+user_id+"'"+" and UMT.movie_id ="+"'"+movie_id+"'";
        
        
        System.out.println(sqlSearch);
        
        try {
            myConnection = MysqlConnect.ConnectDB();
            prSearch = myConnection.prepareStatement(sqlSearch);
            rsSearch = prSearch.executeQuery();
            
            User_Movies_Table umtRow = new User_Movies_Table();
            if(rsSearch.next()){
                System.out.println("USER_MOVİE_TABLE_ROW");
                umtRow.setUser_id(rsSearch.getInt("user_id"));
                umtRow.setMovie_id(rsSearch.getInt("movie_id"));
                umtRow.setU_m_date(rsSearch.getString("u_m_date"));
                umtRow.setU_m_lastTime(rsSearch.getInt("u_m_lastTime"));
                umtRow.setU_m_lastEpisode(rsSearch.getInt("u_m_lastEpisode"));
                umtRow.setU_m_isWatched(rsSearch.getInt("u_m_isWatched"));
                
                return umtRow;
                
            }else{
                System.out.println("USER_MOVİE_TABLE_ROW BULUNAMADI");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return null;
        
    }
    
    //User_Movie_Table güncellemesi
    public static void updateUserMovieTable(User_Movies_Table umt){
        
        Connection myConnection;
        PreparedStatement pr;
        try{
            myConnection = MysqlConnect.ConnectDB();
            
            String sqlWrite ="UPDATE netflix_database.user_movie_table" 
                    +" SET u_m_date='"+umt.getU_m_date()+"', u_m_lastTime= '"+umt.getU_m_lastTime()+"',"
                    +"u_m_lastEpisode = '"+umt.getU_m_lastEpisode()+"', u_m_isWatched = '"+umt.getU_m_isWatched()+"'"
                    +" WHERE user_id = '"+umt.getUser_id()+"' and movie_id ='"+umt.getMovie_id()+"'";
            
            System.out.println(sqlWrite);
            
            pr = myConnection.prepareStatement(sqlWrite);
            int count = pr.executeUpdate();
            if(count>0){
                System.out.println("USER MOVİE TABLE GÜNCELLENDİ..");
            }else{
                System.out.println("USER MOVİE TABLE GÜNCELLENEMEDİ!..");
            }
        } catch (SQLException ex) {
            Logger.getLogger(KayitSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Tabloda izlendi/izleniyor/izlenmedi/kayıt yok bilgilerinin gösterilmesi için yapılan sorgu
    public static int isMovieWatched(int user_id,int movie_id){
        
        Connection myConnection;
        PreparedStatement prSearch;
        ResultSet rsSearch;
        
        String sqlSearch="SELECT *" 
                +" FROM netflix_database.user_movie_table as UMT\n" 
                +" WHERE UMT.user_id ="+"'"+user_id+"'"+" and UMT.movie_id ="+"'"+movie_id+"'";
        
        
        System.out.println(sqlSearch);
        
        try {
            myConnection = MysqlConnect.ConnectDB();
            prSearch = myConnection.prepareStatement(sqlSearch);
            rsSearch = prSearch.executeQuery();
            
            if(rsSearch.next()){
                switch (rsSearch.getInt("u_m_isWatched")) {
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    case 3:
                        return 3;
                    default:
                        break;
                }
            }else{
                System.out.println("USER_MOVİE_TABLE_ROW KAYIT BULUNAMADI");
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return 0;
        
    }
    
    //Giriş yapan kullanıcının id si
    public static int getUserId(String email){
        
        Connection myConnection;
        PreparedStatement prSearch;
        ResultSet rsSearch;
        try{
            myConnection = MysqlConnect.ConnectDB();
            
            String sqlSearch ="Select *" 
                    +" From netflix_database.user_table as UT"
                    +" where UT.u_email ='"+email+"' ";
            
            System.out.println(sqlSearch);
            prSearch = myConnection.prepareStatement(sqlSearch);
            rsSearch = prSearch.executeQuery();
            if(rsSearch.next()){
                System.out.println("Kullanıcı ID si Bulundu");
                System.out.println("İalydanın idsi==="+ rsSearch.getInt("u_id"));
                return rsSearch.getInt("u_id");
            }else{
                System.out.println("Kullanıcı ID si Bulunanamadı");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
        
    }
    
    public static String getUserName(String email){
        
        Connection myConnection;
        PreparedStatement prSearch;
        ResultSet rsSearch;
        try{
            myConnection = MysqlConnect.ConnectDB();
            
            String sqlSearch ="Select *" 
                    +" From netflix_database.user_table as UT"
                    +" where UT.u_email ='"+email+"' ";
            
            System.out.println(sqlSearch);
            prSearch = myConnection.prepareStatement(sqlSearch);
            rsSearch = prSearch.executeQuery();
            if(rsSearch.next()){
                return rsSearch.getString("u_name");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    //Kullanıcı filmi puanladıktan sonra movie_table daki movie nin score bilgisinin güncellenmesi
    public static void setMovieScore(int id, int mark){
        
        Connection myConnection;
        PreparedStatement pr;
        ResultSet rs;
        try{
            myConnection = MysqlConnect.ConnectDB();
            
            String sqlSearch ="SELECT m_score"
                    +" FROM netflix_database.movie_table"
                    +" WHERE m_id ='"+id+"'";
            
            pr = myConnection.prepareStatement(sqlSearch);
            rs = pr.executeQuery();
            if(rs.next()){
                mark += rs.getInt("m_score");
            }
            
            String sqlWrite ="UPDATE netflix_database.movie_table" 
                    +" SET m_score='"+mark+"'" 
                    +" WHERE m_id = '"+id+"'";
            
            System.out.println(sqlWrite);
            
            pr = myConnection.prepareStatement(sqlWrite);
            int count = pr.executeUpdate();
            if(count>0){
                System.out.println("MOVİE SCORE U GÜNCELLENDİ..");
            }else{
                System.out.println("MOVİE SCORE U GÜNCELLENEMEDİ!..");
            }
        } catch (SQLException ex) {
            Logger.getLogger(KayitSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
