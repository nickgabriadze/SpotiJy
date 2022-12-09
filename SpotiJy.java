package pgdp;


public class SpotiJy {

    private Artist[] artists;


    public SpotiJy(){
        this.artists = new Artist[]{};
    }

    public void addArtists(Artist[] artists){
        PersonalArray<Artist> artistsNew = new PersonalArray<>(this.artists);

        for(Artist artist: artists){
            int count = 0;
            for(int i = 0; i < artistsNew.size(); i++){
                if(artistsNew.get(i).isEqual(artist)){
                    count++;
                }
            }
            if(count == 0){
                artistsNew.append(artist);
            }
        }

        Artist[] freshArtists = new Artist[artistsNew.size()];
        for(int i = 0; i < freshArtists.length; i++){
            freshArtists[i] = artistsNew.get(i);
        }

        this.artists = freshArtists;
    }

    public Song getTopTrendingSong(){
        Song trendingSong = null;
        int most = Integer.MIN_VALUE;
        for(Artist artist: this.artists){
            int mostLikedSongLikes = artist.mostLikedSong().getLikes();
            if(mostLikedSongLikes > most){
                most = mostLikedSongLikes;
                trendingSong = artist.mostLikedSong();
            }
        }
        return trendingSong;
    }
    public Album getTopTrendingAlbum(){
        Album topTrendingAlbum = null;
        int forComparison = Integer.MIN_VALUE;

        for(Artist artist: this.artists){
            Album popularAlbum = artist.getMostLikedAlbum();
            if(popularAlbum.totalAlbumLikes() > forComparison){
                forComparison = popularAlbum.totalAlbumLikes();
                topTrendingAlbum = popularAlbum;
            }
        }

        return topTrendingAlbum;
    }

    public String[] getTopTrendingArtist(){
        Artist popularArtist = null;
        int mostLikes = Integer.MIN_VALUE;
        for(Artist artist: this.artists){
            int totalLikes = artist.totalLikes();
            if(totalLikes > mostLikes){
                mostLikes = totalLikes;
                popularArtist = artist;
            }
        }
        assert popularArtist != null;
        String firstName = popularArtist.getFirstName();
        String lastName = popularArtist.getSecondName();

        return new String[]{firstName, lastName};
    }



    public Artist[] getArtists() {
        return artists;
    }
}
