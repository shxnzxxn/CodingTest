import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int res = 0;
        for(int k=0; k<n; k++) {
            String[] input = br.readLine().split("");
            Stack<String> stack = new Stack<>();
            stack.push(input[0]);
            for (int i = 1; i < input.length;i++){
                if(stack.isEmpty()) stack.push(input[i]);
                else if(stack.peek().equals(input[i])){
                    stack.pop();
                }else{
                    stack.push(input[i]);
                }
            }
            if(stack.isEmpty()) res++;
        }
        System.out.println(res);
    }
}