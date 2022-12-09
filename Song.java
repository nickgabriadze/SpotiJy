package pgdp;

public class Song {
    private final String title;
    private final int releaseYear;
    private int duration;
    private int likes;


    public Song(String title, int releaseYear, int duration, int likes){
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.likes = likes;
    }

    public Song(String title, int releaseYear){
        this.title = title;
        this.releaseYear = releaseYear;
        this.likes = 0;
        this.duration = 60;
    }

    public Song(String title, int releaseYear, int duration){
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.likes = 0;
    }

    public boolean isEqual(Song other){
        return (this.title == other.title &&
                this.releaseYear == other.releaseYear &&
                this.duration == other.duration);
    }

    public Boolean changeDuration(int duration){
        if(duration < 0 || duration > 720 || this.duration == duration){
            return false;
        }else{
            this.duration = duration;
            return true;
        }
    }
    public float coverIntoMinutes(){
        return ((float) this.duration) / 60;
    }

    public void like(){
        this.likes++;
    }
    public void unlike(){
        if(this.likes != 0){
            this.likes--;
        }
    }

    public int getDuration() {
        return duration;
    }

    public int getLikes() {
        return likes;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public String toString(){
        return "Title:" + this.title +",Duration:" + this.coverIntoMinutes() +" minutes,Release year:"+this.releaseYear +
                ",Likes:" + this.likes;
    }
}
