package billboard.model;

public class Billboard {

	int rank;
	String song;
	String artist;
	
	
	
	public Billboard() {
		super();
	}


	@Override
	public String toString() {
		return "Billboard [rank=" + rank + ", song=" + song + ", artist="
				+ artist + "]";
	}


	public Billboard(int rank, String song, String artist) {
		super();
		this.rank = rank;
		this.song = song;
		this.artist = artist;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public String getSong() {
		return song;
	}


	public void setSong(String song) {
		this.song = song;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	
	
	
	
}
