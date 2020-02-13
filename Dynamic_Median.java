import java.util.*;
import java.io.*;

public class Dynamic_Median {
	public PQ pq = new PQ(250);
    public PQ n_pq = new PQ(250);
    public int counter = 0;
    public SongComparator cmp = new SongComparator();

      public void LoadFile(String data) {

        int result = 0;
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
            int i = 0;
            line = reader.readLine();
            while (line != null) {
                int pos = line.indexOf(" ");
                int id = Integer.parseInt(line.substring(0, pos));
                int pos2 = line.lastIndexOf(" ");
                String title = line.substring(pos + 1, pos2);
                int likes = Integer.parseInt(line.substring(pos2 + 1));
                Song song = new Song(id, title, likes);
                pq.insert(song);
                i++;
                if (i%5 == 0){
                    getMedian();
                }
                   
              
                line = reader.readLine();
            }
			int psize = n_pq.size();
			for (int j=0; j < psize; j++) {
				n_pq.getMax().printSong();
			}
			


            } catch(IOException e){
                System.out.println("Error reading line " + counter + ".");
            }

            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Error closing file.");
            }
        }

        public void getMedian () {

            if (pq.size() % 2 != 0) {
				int q = pq.size() /2;
				for (int j=0; j < q; j++) {
					n_pq.insert(pq.getMax());
				}
                System.out.println("Median = "+pq.max().getLikes()+" likes, achieved by Song: "+pq.max().getTitle());
            }
            else {
				int q2 = pq.size() /2;
				for (int j=0; j < q2; j++) {
					n_pq.insert(pq.getMax());
				}
                System.out.println("Median = "+pq.max().getLikes()+" likes, achieved by Song: "+pq.max().getTitle());
			}
			
			int size = pq.size();
			for (int j =0; j < size; j++) {
				n_pq.insert(pq.getMax());
			}
			
			pq = n_pq;
			n_pq = new PQ(250);

        }


        //main
        public static void main (String[] args){

            Dynamic_Median mid = new Dynamic_Median();
             mid.LoadFile(args [0]);

        }

    }




