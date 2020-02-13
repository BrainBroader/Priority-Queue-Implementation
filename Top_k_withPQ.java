import java.io.*;
import java.util.*;


public class Top_k_withPQ {
	
	public void topK_PQ(String data, int k) {
		PQ pq = new PQ(2 * k);
		SongComparator cmp = new SongComparator();
		int result = 0;
		int lines = 0;
		int counter = 0;
		File f = null;
		BufferedReader reader = null; 
		String line;

        try {
            f = new File(data);
        } catch (NullPointerException e) {
            System.err.println("File not found.");
        }

        try {
            reader = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        }

        try {
			while (reader.readLine() != null) {
				lines++;
			}
			reader.close();
			reader = new BufferedReader(new FileReader(f));
			if (k > lines) {
				return;
			}
	
			line = reader.readLine();
			while(line != null) {
				int pos = line.indexOf(" ");
				int id = Integer.parseInt(line.substring(0,pos));
                int pos2 = line.lastIndexOf(" ");
				String title = line.substring(pos + 1, pos2);
				int likes = Integer.parseInt(line.substring(pos2+1));
				Song song = new Song(id, title, likes);
				if (pq.size() < k ) {
					pq.insert(song);
				} else if (pq.size() > k) {
					return;
					
				} else { 	//pq.size() == k 
					if ( cmp.compare(pq.max(), song) > 0) {
						pq.getMax();
						pq.insert(song);
					}
				}
				line = reader.readLine();
				counter++;
			}
			
			for (int i = 0; i < k; i++ ) {
				System.out.println(pq.getMax().getTitle());
			}
			
        } catch (IOException e) {
            System.out.println("Error reading line " + counter + ".");
        }

        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
    }
	
	public static void main(String args[]) {
		Top_k_withPQ pq = new Top_k_withPQ();
		pq.topK_PQ(args[1], Integer.parseInt(args[0]));
	}
}