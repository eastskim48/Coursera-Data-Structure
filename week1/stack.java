import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.*;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class Problem1 {
    public static void main(String[] args) throws IOException {
    	
    	int pos = -1;
    	boolean result = true;
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
    	
//    	Scanner sc = new Scanner(System.in);
//    	String text = sc.next();Q
    	
    	
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        Loop1 : for (int position = 0; position < text.length(); ++position) {
        	char next = text.charAt(position);
            if (next == '(' || next == '[' || next == '{') {
            	if(opening_brackets_stack.empty())
            		pos=position;
            	Bracket pushbox = new Bracket(next, position);
            	opening_brackets_stack.push(pushbox);
                // Process opening bracket, write your code here
            }

            if (next == ')' || next == ']' || next == '}') {
            	if(opening_brackets_stack.empty()==true){
            		if(result == true){
            			pos = position;
            			result = false;
            			break Loop1;
            		}
            	}else{
            		Bracket popbox = opening_brackets_stack.pop();
            		if(!popbox.Match(next)){
            			pos = position;
            			result=false;
            			break Loop1;
            		}
            	}
            	// Process closing bracket, write your code here
            }
            
        }
               
//        sc.close();
        
        if(!opening_brackets_stack.empty()){
        	result = false;
        }
        
        if(result==false)
        	System.out.println(pos+1);
        else
        	System.out.println("Success");
        
        // Printing answer, write your code here
    }
}
