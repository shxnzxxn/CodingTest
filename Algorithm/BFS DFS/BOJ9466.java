import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int iter = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < iter; k++) {
            int n = Integer.parseInt(br.readLine());
            cnt = n;
            int[] input = new int[n];
            boolean[] visited = new boolean[n];
            boolean[] finished = new boolean[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) input[i] = Integer.parseInt(st.nextToken())-1;

            for (int i = 0; i < input.length; i++) {
                if (!visited[i]) DFS(input, visited, finished, i);
            }
            sb.append(cnt+"\n");
        }
        System.out.println(sb);
    }

    private static void DFS(int[] input, boolean[] visited, boolean[] finished, int now) {
        int next = input[now];
        visited[now] = true;
        if(visited[next] && !finished[next]){ // 만약 다음에 방문할 위치를 이미 방문했지만 순환에 등록되지 않았다면
            // DFS를 처음 호출했을 때부터, 지금까지 중에 순환이 있다는 것!
            // 순환을 찾기 위해서는 next부터 now가 나올 때까지 count를 해주면 된다.
            int x = next;
            while(now != x){
                x = input[x];
                cnt--;
            }
            cnt--;
        }else if(!visited[next]){
            DFS(input, visited, finished, next);
        }
        finished[now] = true;
    }
}