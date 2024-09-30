import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int num = 1; // 스택에 바로 push되는 수
        Stack<Integer> s = new Stack<>();
        for(int i=0; i<n; i++) {
            int x = Integer.parseInt(br.readLine());
            if(s.contains(x) && x == s.peek()){
                sb.append("-\n");
                s.pop();
            }else if(!s.contains(x) && num <= x){
                while(num <= x){
                    s.push(num++);
                    sb.append("+\n");
                }
                s.pop();
                sb.append("-\n");
            }else {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println(sb);
    }
}
