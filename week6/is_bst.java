import java.util.*;
import java.io.*;

public class is_bst {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;
        int root;
        List<Integer> result;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        public void findRoot(int index){
    		boolean found = false;
    		for(int a=0; a<nodes; a++){
    			if(tree[a].left==index||tree[a].right==index){
    				findRoot(a);
    				found = true;
    			}
    		}
    		if(found==false)
    			this.root = index;
    	}
    
        public void inOrderCalculate(int index){
        	if(tree[index].left!=-1)
        		inOrderCalculate(tree[index].left);
        	result.add(tree[index].key);
        	if(tree[index].right!=-1)
        		inOrderCalculate(tree[index].right);
        	return;
        }
        boolean isBinarySearchTree() {
        	result = new ArrayList<Integer>();
        	if(nodes==0)
        		return true;
        	inOrderCalculate(root);
        	for(int a=0; a<result.size()-1;a++){
        		if(result.get(a)>result.get(a+1))
        			return false;
        	}
          // Implement correct algorithm here
          return true;
        }
    } 
   
    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
