import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int K;
    static int[][] input;
    static int[][][] distance;
    static int way = 10000000;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        input = new int[M][N];
        distance = new int[M][N][K+1];
        for(int i=0; i<M; i++){
            String[] tmp = br.readLine().split("");
            for (int j = 0; j < N; j++) input[i][j] = Integer.parseInt(tmp[j]);
        }

        BFS(0, 0, 0);
        System.out.println(way);
    }

    private static void BFS(int a, int b, int k) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, b, k});
        distance[a][b][k] = 1;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int power = tmp[2];

            if(x==M-1 && y==N-1){
                way = Math.min(way, distance[x][y][power]);
                return;
            }

            for(int i=0; i<dx.length; i++){
                int xNew = x+dx[i];
                int yNew = y+dy[i];
                if(xNew >= 0 && xNew < M && yNew >=0 && yNew < N){
                    if(distance[xNew][yNew][power] == 0 && input[xNew][yNew] == 0){
                        distance[xNew][yNew][power] = distance[x][y][power] +1;
                        q.add(new int[]{xNew, yNew, power});
                    }else if(power < K){ // 아직 부실 수 있다!
                        if(distance[xNew][yNew][power+1] == 0 && input[xNew][yNew] == 1){
                            distance[xNew][yNew][power+1] = distance[x][y][power]+1;
                            q.add(new int[]{xNew, yNew, power+1});
                        }
                    }
                }
            }
        }
        way = -1;
    }
}