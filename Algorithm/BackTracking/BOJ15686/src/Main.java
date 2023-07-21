import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static LinkedList<int[]> chicken;
    static LinkedList<int[]> home;
    static int distance = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        chicken = new LinkedList<>();
        home = new LinkedList<>();

        for(int i=0; i<N; i++){ // 치킨가게 위치랑 집 위치를 각 리스트에 저장함.
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] =Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    home.add(new int[]{i, j});
                }else if(map[i][j] == 2){
                    chicken.add(new int[]{i, j});
                }
            }
        }

        int[][] dist = calculateDist(chicken, home);
        boolean[] visited = new boolean[chicken.size()];
        backTracking(chicken, dist, visited, 0, chicken.size(), M); // M개를 뽑는 연산 시행
        System.out.println(distance);
    }

    static void backTracking(LinkedList<int[]> chicken, int[][] dist, boolean[] visited, int start, int m, int n){
        if(n==0){
            int tmp = choose(chicken, dist, visited, m);
            distance = Math.min(distance, tmp);
            return;
        }

        for(int i=start; i<m; i++){
            if(!visited[i]) {
                visited[i] = true;
                backTracking(chicken, dist, visited, start + 1, m, n - 1);
                visited[i] = false;
            }
        }
    }
    static int choose(LinkedList<int[]> chicken, int[][] dist, boolean[] visited, int m){
        int[][] tmp = new int[M][home.size()];
        int idx = 0;
        for(int i=0; i<m; i++){
            if(visited[i]){
                tmp[idx++] = dist[i];
            }
        }
        int res = 0;
        for(int i=0; i<home.size(); i++){
            int small = 10000000;
            for(int j=0; j<M; j++) {
                small = Math.min(small, tmp[j][i]);
            }
            res += small;
        }
        return res;
    }

    static int[][] calculateDist(LinkedList<int[]> chicken, LinkedList<int[]> home){
        int[][] res = new int[chicken.size()][home.size()];
        for(int i=0; i< chicken.size(); i++){
            for(int j=0; j<home.size(); j++){
                res[i][j] = calculate(chicken.get(i), home.get(j));
            }
        }
        return res;
    }

    static int calculate(int[] x, int[] y){
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }
}