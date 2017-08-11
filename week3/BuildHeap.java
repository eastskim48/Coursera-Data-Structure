import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Random;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    static public void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
//        	Random random = new Random();
        	data[i] = in.nextInt();
        	//data[i] = random.nextInt(999999999)+1;
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      int n = data.length;
      int Siftpt=0;
      int smallPt=0;
      int tmp=0;
      // The following naive implementation just sorts 
      // the given sequence using selection sort algorithm
      // and saves the resulting sequence of swaps.
      // This turns the given array into a heap, 
      // but in the worst case gives a quadratic number of swaps.
      //
      // TODO: replace by a more efficient implementation
      
      for(int i=(n-1)/2;i>=0;i--){
    	 Siftpt=i;
    	 LOOP1 : while(2*Siftpt+1<=n-1){
    		 
    		 if(2*Siftpt+2<=n-1 && data[2*Siftpt+2]<=data[2*Siftpt+1]){ //오른아들 존재하고 더 작
    			smallPt = 2*Siftpt+2;
    		  }
    		 else{ //나머지 경우  다 왼아들이 다고 가정 
    			smallPt = 2*Siftpt+1;
    		 }
    		 //System.out.println(small);
    		 if(data[Siftpt]>data[smallPt]){
    			 tmp = data[smallPt];
    			 data[smallPt] = data[Siftpt];
    			 data[Siftpt]=tmp;
    			 swaps.add(new Swap(Siftpt,smallPt));
    			 Siftpt = smallPt;
    		 }
    		 else
    			 break LOOP1;
    	 }
      }
      
      
      
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
