import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Stack<int[]> stack = new Stack<>(); // 첫 번째는 인덱스, 그 다음은 높이
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            int x = Integer.parseInt(st.nextToken());
            if(stack.isEmpty()) {
                stack.push(new int[]{i+1, x});
                sb.append(0+" ");
                continue;
            }
            while(!stack.isEmpty() && stack.peek()[1] < x) stack.pop();
            if(stack.isEmpty()) sb.append(0+" ");
            else sb.append(stack.peek()[0]+" ");

            stack.push(new int[]{i+1, x});
        }
        System.out.println(sb);

    }
}