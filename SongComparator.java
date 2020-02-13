import java.util.Comparator;

final class SongComparator implements Comparator<Song> {
	
	public int compare(Song o1, Song o2) {
		
		if (o1.getLikes() == o2.getLikes()) {
			if (o1.getTitle().compareTo(o2.getTitle()) < 0) {
				return -1;
				
			} else if (o1.getTitle().compareTo(o2.getTitle()) > 0) {
				return 1;
				
			} else {
				
				return 0;
			}
		} else if (o1.getLikes() < o2.getLikes()) {
			return 1;
		
		} else {
			return -1;
			
		}
	
	}
}