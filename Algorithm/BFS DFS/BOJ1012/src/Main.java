import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int[][] obj;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int iter = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int k=0; k<iter; k++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            obj = new int[M][N];
            int n = Integer.parseInt(st.nextToken());

            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                obj[y][x] = 1;
            }

            for(int i=0; i<M; i++){
                for(int j=0; j<N; j++){
                    if(obj[i][j] == 0) continue;
                    cnt++;
                    BFS(i, j);
                }
            }
            System.out.println(cnt);
            cnt = 0;
        }
    }

    public static void BFS(int i, int j){
        if(i >= M || i < 0 || j >= N || j < 0) return;
        if(obj[i][j] == 0) return;
        obj[i][j] = 0;
        BFS(i+1, j);
        BFS(i-1, j);
        BFS(i, j-1);
        BFS(i, j+1);
    }
}