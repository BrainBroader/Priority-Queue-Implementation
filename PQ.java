import java.util.Comparator;

public class PQ {
	
    private Song[] heap;
    
    private int size;
    
    protected Comparator cmp;
	
	
    public PQ(int capacity) {
        this(capacity, new SongComparator());
    }
	
    public PQ(int capacity, Comparator cmp) {
        if (capacity < 1) throw new IllegalArgumentException();
        this.heap = new Song[capacity+1];
        this.size = 0;
        this.cmp = cmp;
    }
	
	public void insert(Song object) {
        // Ensure object is not null
        if (object == null) throw new IllegalArgumentException();
        // Check available space
        if (size == heap.length-1) throw new IllegalStateException();
        // Place object at the next available position
        heap[++size] = object;
        // Let the newly added object swim
        swim(size);
		
		if (size > 0.75 * heap.length) {
			resize(heap.length);
		}
    }
	
    public Song max() {
        // Ensure not empty
        if (size == 0) throw new IllegalStateException();
        // Keep a reference to the root object
        Song object = heap[1];
        // Return the object removed
        return object;
    }
	
    public Song getMax() {
        // Ensure not empty
        if (size == 0) throw new IllegalStateException();
        // Keep a reference to the root object
        Song object = heap[1];
        // Replace root object with the one at rightmost leaf
        if (size > 1) heap[1] = heap[size];
        // Dispose the rightmost leaf
        heap[size--] = null;
        // Sink the new root element
        sink(1);
        // Return the object removed
        return object;
    }
	
    private void swim(int i) {
        while (i > 1) {  //if i root (i==1) return
            int p = i/2;  //find parent
            int result = cmp.compare(heap[i], heap[p]);  //compare parent with child
            if (result <= 0) {
				return;				//if child <= parent return
			}
            swap(i, p);                 //else swap and i=p
            i = p;
        }
    }
	
    private void sink(int i){
        int left = 2*i, right = left+1, max = left;
        // If 2*i >= size, node i is a leaf
        while (left <= size) {
            // Determine the largest children of node i
            if (right <= size) {
                max = cmp.compare(heap[left], heap[right]) < 0 ? right : left;
            }
            // If the heap condition holds, stop. Else swap and go on.
            if (cmp.compare(heap[i], heap[max]) >= 0) return;
            swap(i, max);
            i = max; left = 2*i; right = left+1; max = left;
        }
    }
	
    private void swap(int i, int j) {
        Song tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
	
    public void print() {
		for (int i=1; i <= size; i++){
            System.out.println(heap[i].getTitle() + " ");
        }
        System.out.println();
    }
	
	public boolean isEmpty(){
        return size == 0;
    }
	
	public int size() {
		return size;
	}
	
	public void resize(int size) {
		int capacity = 2 * size;
		Song[] n_heap = new Song[capacity];
		for(int i = 0; i < size; i++) {
			n_heap[i] = heap[i];
		}
		heap = n_heap;
	}
	
	public Song remove(int id) {
		if (size == 0) throw new IllegalStateException();
		Song r = null;
		int removed = 0;
		int s = size;
		for (int i = 1; i <= s; i++ ) {
			removed = heap[i].getId();
			if (removed == id) {
				r = heap[i];
				if (size > 1) heap[i] = heap[size];
				heap[size--] = null;
				sink(i);
				break;
			}
		}
		return r;
	}
	
	public static void main(String args[]){
        PQ pq = new PQ(5);
        Song s1 = new Song(114, "Fuego", 51);
		Song s2 = new Song(313, "Despacito", 63);
		Song s3 = new Song(22, "Bitter Sweet Symphony", 71);
		Song s4 = new Song(812, "The Passenger", 63);
		Song s5 = new Song(9, "Daddy Cool", 78);
        pq.insert(s1);
		pq.insert(s2);
		pq.insert(s3);
		pq.insert(s4);
		pq.insert(s5);
		Song r = pq.remove(812);
		r.printSong();
		System.out.println("\n");
		int k = pq.size();
		for (int i = 0; i < k; i++) {
			pq.getMax().printSong();
		}
		
		
    }
}
	
	
	
	
	
	
	