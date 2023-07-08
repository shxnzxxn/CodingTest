import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int num = 1;
        Stack<Integer> s = new Stack<>();
        for(int i=0; i<n; i++) {
            int x = Integer.parseInt(br.readLine());
            if(s.contains(x) && x == s.peek()){
                bw.write("-\n");
                s.pop();
            }else if((s.contains(x) && x != s.peek()) || num > x){
                System.out.println("NO");
                System.exit(0);
            }else if(num <= x){
                while(num <= x){
                    s.push(num++);
                    bw.write("+\n");
                }
                s.pop();
                bw.write("-\n");
            }
        }
        bw.flush(); bw.close();
    }
}
