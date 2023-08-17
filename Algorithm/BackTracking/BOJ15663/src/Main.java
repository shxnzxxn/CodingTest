import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static LinkedList<Integer> input = new LinkedList<>();
    static int[] res;
    static int[][] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new int[N][2]; // 각 인덱스마다 가능한 방문 횟수와 현재 방문한 횟수를 표기
        res = new int[M];
        int[] cnt = new int[100001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            cnt[x]++;
            if(!input.contains(x)) input.add(x);
        }
        Collections.sort(input);
        N = input.size();

        for(int i=0; i<N; i++){
            int x = input.get(i);
            int count = cnt[x];
            visited[i] = new int[]{0, count};
        }

        BackTracking(0);

        System.out.println(sb);
    }

    private static void BackTracking(int x) {
        if(x==M){
            for(int i : res) sb.append(i+" ");
            sb.append("\n");
            return;
        }

        for(int i=0; i<N; i++){
            if(visited[i][0] < visited[i][1]){
                visited[i][0]++;
                res[x] = input.get(i);
                BackTracking(x+1);
                visited[i][0]--;
            }
        }
    }
}