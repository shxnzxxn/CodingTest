import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] obj;
    static boolean[][] sink;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        obj = new int[N][N];
        int max_height = 0; // 건물의 최대 높이
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                obj[i][j] = Integer.parseInt(st.nextToken());
                if(max_height < obj[i][j]) max_height = obj[i][j];
            }
        }
        int res = 0;
        for(int k=0; k<= max_height; k++){ // 물의 높이를 나타냄.
            int tmp = 0;
            sink = new boolean[N][N];
            visited = new boolean[N][N];
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(obj[i][j] <= k) sink[i][j] = true;
                }
            }
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(!sink[i][j] && !visited[i][j]){ // 잠기지 않고, 아직 방문하지 않은 영역만을 검사
                        countNotSink(i, j);
                        tmp++;
                    }
                }
            }
            if(res < tmp) res = tmp;
        }
        System.out.println(res);
    }
    static void countNotSink(int i, int j){
        if(i<0 || j<0 || i>= N || j>=N) return;
        if(visited[i][j] || sink[i][j]) return;

        visited[i][j] = true;

        countNotSink(i-1, j);
        countNotSink(i+1, j);
        countNotSink(i, j-1);
        countNotSink(i, j+1);
    }
}