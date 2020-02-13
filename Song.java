public class Song {
	
	private int id;
	private String title;
	private int likes;
	
	public Song() {
		
	}
	
	public Song(int id, String title, int likes) {
		this.id = id;
		this.title = title;
		this.likes = likes;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void printSong() {
		System.out.println(getId() + " " + getTitle() + " " + getLikes());
	}
}
	
	
	