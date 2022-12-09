package pgdp;


public class Artist {
    private final String firstName;
    private final String lastName;
    private final int birthYear;
    private final Album[] albums;
    private final Song[] singles;


    public Artist(String firstName, String lastName, int birthYear, Album[] albums, Song[] singles){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.albums = albums;
        this.singles = singles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Album[] getAlbums() {
        return albums;
    }

    public Song[] getSingles() {
        return singles;
    }

    public Song mostLikedSong(){
        int mostLikedAlbumSongLikes = Integer.MIN_VALUE;
        Song mostLikedAlbumSong = null;
        for(Album album: this.albums){
            for(Song song: album.getSongs()){
                if(song.getLikes() > mostLikedAlbumSongLikes){
                    mostLikedAlbumSongLikes = song.getLikes();
                    mostLikedAlbumSong = song;
                }
            }
        }
        int mostLikedSingleSongLikes = Integer.MIN_VALUE;
        Song mostLikedSingleSong = null;
        for(Song song: this.singles){
            if(song.getLikes() > mostLikedSingleSongLikes){
                mostLikedSingleSongLikes = song.getLikes();
                mostLikedSingleSong = song;
            }
        }

        if(mostLikedAlbumSongLikes > mostLikedSingleSongLikes){
            return mostLikedAlbumSong;
        }else{
            return mostLikedSingleSong;
        }
    }

    public Album getMostLikedAlbum(){
        int mostLikes = Integer.MIN_VALUE;
        Album mostLikedAlbum = null;
        for(Album album: this.albums){
           int albumLikes = album.totalAlbumLikes();
           if(albumLikes > mostLikes){
               mostLikes = albumLikes;
               mostLikedAlbum = album;
           }
        }

        return mostLikedAlbum;
    }

    public Song leastLikedSong(){
        int mostDislikedAlbumSongLikes = Integer.MAX_VALUE;
        Song mostDislikedAlbumSong = null;
        for(Album album: this.albums){
            for(Song song: album.getSongs()){
                if(song.getLikes() < mostDislikedAlbumSongLikes){
                    mostDislikedAlbumSongLikes = song.getLikes();
                    mostDislikedAlbumSong = song;
                }
            }
        }
        int mostDislikedSingleSongLikes = Integer.MAX_VALUE;
        Song mostDislikedSingleSong = null;
        for(Song song: this.singles){
            if(song.getLikes() < mostDislikedSingleSongLikes){
                mostDislikedSingleSongLikes = song.getLikes();
                mostDislikedSingleSong = song;
            }
        }

        if(mostDislikedAlbumSongLikes > mostDislikedSingleSongLikes){
            return mostDislikedSingleSong;
        }else{
            return mostDislikedAlbumSong;
        }
    }

    public int totalLikes(){

        int total = 0;
        for(Album album: this.albums){
            for(Song song: album.getSongs()){
                total += song.getLikes();
            }
        }

        for(Song single: this.singles){
            total += single.getLikes();
        }


        return total;
    }


    public boolean isEqual(Artist anotherArist){
        return (this.firstName == anotherArist.firstName &&
                this.lastName == anotherArist.lastName &&
                this.birthYear == anotherArist.birthYear);
    }



    public String toString(){
        return "Name:"+this.firstName+" " + this.lastName+",Birthyear:"+this.birthYear+"Total likes:"+this.totalLikes();
    }

}