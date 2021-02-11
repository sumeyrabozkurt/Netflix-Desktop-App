package netflix;

public class User_Movies_Table {
    
    int user_id;
    int movie_id;
    String u_m_date;
    int u_m_lastTime;
    int u_m_lastEpisode;
    int u_m_isWatched;
    
    public User_Movies_Table(){
        
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getU_m_date() {
        return u_m_date;
    }

    public void setU_m_date(String u_m_date) {
        this.u_m_date = u_m_date;
    }

    public int getU_m_lastTime() {
        return u_m_lastTime;
    }

    public void setU_m_lastTime(int u_m_lastTime) {
        this.u_m_lastTime = u_m_lastTime;
    }

    public int getU_m_lastEpisode() {
        return u_m_lastEpisode;
    }

    public void setU_m_lastEpisode(int u_m_lastEpisode) {
        this.u_m_lastEpisode = u_m_lastEpisode;
    }

    public int getU_m_isWatched() {
        return u_m_isWatched;
    }

    public void setU_m_isWatched(int u_m_isWatched) {
        this.u_m_isWatched = u_m_isWatched;
    }
    
}
