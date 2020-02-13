import java.io.*;
import java.util.*;

public class Top_k {
	
	public void topK(String data, int k) { 
		int lines = 0;
		int counter = 0;
		Song[] s = new Song[lines];
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
			
			s = new Song[lines];
			
			line = reader.readLine();
			while(line != null) {
				
				int pos = line.indexOf(" ");
				int id = Integer.parseInt(line.substring(0,pos));
                int pos2 = line.lastIndexOf(" ");
				String title = line.substring(pos + 1, pos2);
				int likes = Integer.parseInt(line.substring(pos2+1));
                Song song = new Song(id, title, likes);
				s[counter] = song;
				line = reader.readLine();
				counter++;
			}
			sort(s, k);
			
        } catch (IOException e) {
            System.out.println("Error reading line " + counter + ".");
        }

        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
    }
	
	public void sort(Song[] array, int k) {
		SongComparator cmp = new SongComparator();
		Song temp= array[0];
		boolean fixed = false;
		while (fixed == false) {
			fixed = true;
			for (int i = 0; i < array.length -1; i++ ) {
				int result = cmp.compare(array[i], array[i+1]);
				if (result > 0) {
					temp = array[i + 1];
					array[i + 1] = array[i];
					array[i] = temp;
					fixed = false;
				}
			}
		}
		if (k <= array.length) {
			System.out.println("The top "+k+" songs are:\n");
			for (int i = 0; i < k; i++ ) {
				System.out.println(array[i].getTitle());
			}
		}
	}
	
	public static void main(String args[]){
		Top_k read = new Top_k();
		read.topK(args[1], Integer.parseInt(args[0]));
	}
	
}

