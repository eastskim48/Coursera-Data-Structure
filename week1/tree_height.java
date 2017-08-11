import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}
  
	public class TreeHeight {
		int n;
		int root;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
				if(parent[i]==-1)
					root = i;
			}
		}
		
		int computeHeight(){
			int Height=0;
			int pep=0;
			int maxHeight=0;
			int[] count = new int[parent.length];
			for(int b=0; b<parent.length; b++){
				count[b]=0;
			}
			
//			for(int b=0; b<parent.length; b++){
//				if(parent[b]!=-1)
//					count[parent[b]]=1;
//			}
			
			for(int a=0; a<parent.length; a++){
//				if(count[a]!=1){
//					System.out.println(a);
					Height=0;
					pep=a;
					Loop1 : while(pep!=-1){
						pep = parent[pep];
						if(pep!=-1&&count[pep]>0){
							Height+=count[pep]+1;
							break Loop1;
						}
//						System.out.println("a");
						Height++;
					}
					if(Height>maxHeight)
						maxHeight = Height;
					count[a] = Height;
//				}
			}
			
			
			return maxHeight;
		}
	}
	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
