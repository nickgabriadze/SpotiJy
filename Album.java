package pgdp;

import java.util.Random;

public class Album {

    private final String title;
    private final int releaseYear;
    private Song[] songs;

    public Album(String title, int releaseYear, Song[] songs){
        this.title = title;
        this.releaseYear = releaseYear;
        this.songs = songs;
    }


    public int addSongs(Song[] songs){
        int unique = 0;
        PersonalArray<Song> uniqueOnes = new PersonalArray<>(this.songs);

        PersonalArray<Song> newSongs = new PersonalArray<>(songs);


        for(int s = 0; s < newSongs.size() ; s++){
            int count = 0;
           for(int n = 0; n < uniqueOnes.size(); n++) {
                if (uniqueOnes.get(n).isEqual(newSongs.get(s))) {
                    count++;
                }
            }
            if(count == 0){
                uniqueOnes.append(newSongs.get(s));
                unique++;
            }

        }
        Song[] freshSongs = new Song[uniqueOnes.size()];
        for(int i = 0; i < freshSongs.length; i++){
            freshSongs[i] = uniqueOnes.get(i);
        }
        this.songs = freshSongs;
        return unique;
    }



    public static Song[] reverse(Song[] songs){
        Song[] reversedArray = new Song[songs.length];
        int start = 0;
        for(int i = songs.length-1; i >= 0; i--){
            reversedArray[start] = songs[i];
            start++;
        }

        return reversedArray;
    }

    public static void merge(Song[] songsArray, int left, int mid, int right,boolean d, boolean ry, boolean t, boolean p){
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        Song[] songsOnTheLeft = new Song[leftSize];
        Song[] songsOnTheRight = new Song[rightSize];

        System.arraycopy(songsArray, 0, songsOnTheLeft, 0, leftSize);
        for(int i = 0; i < rightSize; i++){
            songsOnTheRight[i] = songsArray[mid + 1 + i];
        }

        int leftIndex = 0, rightIndex = 0, mainSongArrayIndex = 0;

        while(leftIndex < songsOnTheLeft.length && rightIndex < songsOnTheRight.length){
            boolean comparison = true;

            if(d){
                comparison = songsOnTheLeft[leftIndex].getDuration() <= songsOnTheRight[rightIndex].getDuration();
            }else if(ry){
                comparison = songsOnTheLeft[leftIndex].getReleaseYear() <= songsOnTheRight[rightIndex].getReleaseYear();
            }else if(p){
                comparison = songsOnTheLeft[leftIndex].getLikes() <= songsOnTheRight[rightIndex].getLikes();
            }else if(t){
                int compared = songsOnTheLeft[leftIndex].getTitle().compareTo(songsOnTheRight[rightIndex].getTitle());
                if(compared > 0){
                    comparison = false;
                }
            }

            if(comparison){
                songsArray[mainSongArrayIndex] = songsOnTheLeft[leftIndex];
                leftIndex++;
            }else{
                songsArray[mainSongArrayIndex] = songsOnTheRight[rightIndex];
                rightIndex++;
            }
            mainSongArrayIndex++;
        }

        while(leftIndex < songsOnTheLeft.length){
            songsArray[mainSongArrayIndex] = songsOnTheLeft[leftIndex];
            leftIndex++;
            mainSongArrayIndex++;
        }
        while(rightIndex < songsOnTheRight.length){
            songsArray[mainSongArrayIndex] = songsOnTheRight[rightIndex];
            rightIndex++;
            mainSongArrayIndex++;
        }

    }

    public static Song[] mergeSortForAlbum(Song[] songsArray, int left, int right, boolean d, boolean ry, boolean t, boolean p){
            if(left < right) {
                int mid = left + (right - left) / 2;

                mergeSortForAlbum(songsArray, left, mid, d, ry, t, p);
                mergeSortForAlbum(songsArray, mid + 1, right, d, ry, t, p);

                merge(songsArray, left, mid, right,d, ry, t, p);
            }
        return songsArray;

    }


    public Song[] shuffle(){ 
        PersonalArray<Integer> usedInts = new PersonalArray<>(new Integer[]{});
        PersonalArray<Song> alreadyExistingSongs = new PersonalArray<>(new Song[]{});
        Random random = new Random();

        for (int i = 0; i < this.songs.length; i++) {
            int rn = random.nextInt(this.songs.length);

            while(usedInts.isItThere(rn)){
                rn = random.nextInt(this.songs.length);
            }

            usedInts.append(rn);
            alreadyExistingSongs.append(this.songs[rn]);

       
        }
        Song[] shuffledSongs = new Song[alreadyExistingSongs.size()];
        for(int i = 0; i < shuffledSongs.length; i++){
            shuffledSongs[i] = alreadyExistingSongs.get(i);
        }


        return shuffledSongs;
    }
    public Song[] sortByTitle(boolean isAscending){
        if(isAscending){
            return mergeSortForAlbum(this.songs, 0, this.songs.length - 1, false, false, true, false);
        }else{
            return reverse(mergeSortForAlbum(this.songs, 0, this.songs.length-1, false, true, false, false));
        }
    }

    public Song[] sortByDuration(boolean isAscending){
        if(isAscending){
            return mergeSortForAlbum(this.songs, 0, this.songs.length - 1, true, false, false, false);
        }else{
            return reverse(mergeSortForAlbum(this.songs, 0, this.songs.length-1, true, false, false, false));
        }
    }

    public Song[] sortByReleaseYear(boolean isAscending){
        if(isAscending){
            return mergeSortForAlbum(this.songs, 0, this.songs.length - 1, false, true, false, false);
        }else{
            return reverse(mergeSortForAlbum(this.songs, 0, this.songs.length-1, false, true, false, false));
        }
    }

    public Song[] sortByPopularity(boolean isAscending){
        if(isAscending){
            return mergeSortForAlbum(this.songs, 0, this.songs.length - 1, false, false, false, true);
        }else{
            return reverse(mergeSortForAlbum(this.songs, 0, this.songs.length-1, false, false, false, true));
        }

    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public Song[] getSongs() {
        return songs;
    }

    public int totalAlbumLikes(){
        int totalAlbumLikes = 0;
        for(Song song: this.songs){
            totalAlbumLikes += song.getLikes();
        }
        return totalAlbumLikes;
    }

    public String toString(){
        StringBuilder songsString = new StringBuilder();
        for(int s = 0; s < this.songs.length; s++){
            if(s != this.songs.length - 1) {
                songsString.append(this.songs[s].toString()).append("|");
            }else{
                songsString.append(this.songs[s].toString());
            }
        }
        return "Title:"+this.title +",Release year:" + this.releaseYear+",songs:{"+songsString+"}";
    }
}

