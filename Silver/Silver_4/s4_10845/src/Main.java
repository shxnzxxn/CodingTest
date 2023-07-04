import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            switch(input){
                case "push":
                    int x = Integer.parseInt(st.nextToken());
                    q.add(x);
                    break;
                case "pop":
                    if(q.isEmpty()) System.out.println(-1);
                    else System.out.println(q.remove());
                    break;
                case "size":
                    System.out.println(q.size());
                    break;
                case "empty":
                    if (q.isEmpty()) System.out.println(1);
                    else System.out.println(0);
                    break;
                case "front":
                    if(q.isEmpty()) System.out.println(-1);
                    else System.out.println(q.peek());
                    break;
                case "back":
                    if(q.isEmpty()) System.out.println(-1);
                    else {
                        Queue<Integer> copy_q = new LinkedList<>();
                        while (q.size() != 1) copy_q.add(q.remove());
                        int res = q.remove();
                        copy_q.add(res);
                        System.out.println(res);
                        while (copy_q.size() > 0) q.add(copy_q.remove());
                    }
            }
        }
    }
}