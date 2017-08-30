import java.util.*;
import java.io.*;

public class tree_orders {
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

	public class TreeOrders {
		int n;
		int[] key, left, right;
		ArrayList<Integer> result;
		int root;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}
		
		public void inOrderCalculate(int vertex){
			if(left[vertex]!=-1)
				inOrderCalculate(left[vertex]);
			result.add(key[vertex]);
			if(right[vertex]!=-1)
				inOrderCalculate(right[vertex]);
			return;
		}
		
		public void preOrderCalculate(int vertex){
			result.add(key[vertex]);
			if(left[vertex]!=-1)
				preOrderCalculate(left[vertex]);
			if(right[vertex]!=-1)
				preOrderCalculate(right[vertex]);
			return;
		}
		
		public void postOrderCalculate(int vertex){
			if(left[vertex]!=-1)
				postOrderCalculate(left[vertex]);
			if(right[vertex]!=-1)
				postOrderCalculate(right[vertex]);
			result.add(key[vertex]);
			return;
		}

		List<Integer> inOrder() {
			result = new ArrayList<Integer>();
			inOrderCalculate(root);
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
                        
			return result;
		}

		List<Integer> preOrder() {
			result = new ArrayList<Integer>();
			preOrderCalculate(root);
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
                        
			return result;
		}

		List<Integer> postOrder() {
			result = new ArrayList<Integer>();
			postOrderCalculate(root);
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
                        
			return result;
		}
		
		public void findRoot(int vertex){
			boolean found = false;
			for(int a=0; a<n; a++){
				if(left[a]==vertex||right[a]==vertex){
					findRoot(a);
					found = true;
				}
			}
			if(found==false)
				this.root = vertex;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	
	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		tree.findRoot(0);
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
