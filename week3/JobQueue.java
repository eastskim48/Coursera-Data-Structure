import java.io.*;
import java.util.StringTokenizer;


public class JobQueue {
    private int numWorkers;
    private int[] jobs;
    private int cursor;

    private FastScanner in;
    private PrintWriter out;
    long[] WorksHeap;
	int[] Index;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
//            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }
    
    private int siftUp(int cursor){
    	long temp;
    	
    	temp = WorksHeap[cursor];
		WorksHeap[cursor] = WorksHeap[(cursor-1)/2];
		WorksHeap[(cursor-1)/2] = temp;
		
		temp = Index[cursor];
		Index[cursor] = Index[(cursor-1)/2];
		Index[(cursor-1)/2] = (int)temp; //주의   
		
		return (cursor-1)/2;
    }
    
    private void siftDown(){
    	long temp;
    	int cursor = 0;
    	int left = 2*cursor+1;
    	int right = 2*cursor+2;
    	int min = cursor;
    	
    	Loop1 : while(min==cursor&&right<=WorksHeap.length){
    		left = 2*cursor+1;
        	right = 2*cursor+2;
        	
    		if(left>=numWorkers) //child가 없음    
    			break Loop1;
        	else if(left == numWorkers-1) //left child 만 존재   
    			min = left;
    		else if(WorksHeap[left]<WorksHeap[right]||(WorksHeap[left]==WorksHeap[right]&&Index[left]<Index[right]))
    			min = left;
    		else
    			min = right;
    	
    		if(WorksHeap[cursor]>WorksHeap[min]||(WorksHeap[cursor]==WorksHeap[min]&&Index[min]<Index[cursor])){
    			temp = WorksHeap[cursor];
    			WorksHeap[cursor] = WorksHeap[min];
    			WorksHeap[min] = temp;
    			
    			temp = Index[cursor];
    			Index[cursor] = Index[min];
    			Index[min] = (int)temp;
    			
    			cursor = min; //cursor가 바뀜  
    		}
    	}
    }
    
    private void assignJobs() {
    	int temp;
    	Index = new int[numWorkers];
    	WorksHeap = new long[numWorkers];
    	
    	for(int a=0; a<numWorkers; a++){
    		Index[a] = a;
    		WorksHeap[a] = jobs[a];
    		cursor = a;
    		temp = -1;
    		
    		while(cursor>0 &&cursor!=temp){
    			temp = cursor;
    			if(WorksHeap[cursor]<WorksHeap[(cursor-1)/2]){
    				cursor = siftUp(cursor);
    			}
    			else if(WorksHeap[cursor]==WorksHeap[(cursor-1)/2]){
    				if(Index[cursor]<Index[(cursor-1)/2]){
    					cursor = siftUp(cursor);
    				}
    			}
    			//insert와 동시에 힙 구조 형성    
    		}
    		System.out.println(a+" 0");
    	}
    	
    	for(int a=numWorkers; a<jobs.length; a++){
//    		System.out.println(a);
    		System.out.println(Index[0]+" "+WorksHeap[0]);
    		WorksHeap[0]+=jobs[a];
    		siftDown();
    	}
    	
    	
        // TODO: replace this code with a faster algorithm.
        
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
