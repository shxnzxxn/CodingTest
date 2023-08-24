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
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int res = 1000002;

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

        BFS();
        System.out.println(res);
    }

    private static void BFS() {
        Queue<int[]> q = new LinkedList<>();
        distance[0][0][0] = 1;
        q.add(new int[]{0, 0, 0, 1, 1}); // 0에 도착했을 때는 밤이라고 생각해야함. 그래야 다음 턴이 낮이니까.

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int power = tmp[2];
            int noon = tmp[3];
            int plusAmount = tmp[4];
            int nextNoon = noon==0?1:0;
            boolean canBreak = noon != 0;

            if(x==M-1 && y==N-1){
                res = Math.min(distance[x][y][power], res);
                return;
            }

            for(int i=0; i<dx.length; i++){
                int xNew = x + dx[i];
                int yNew = y + dy[i];

                if(xNew >= 0 && xNew < M && yNew >= 0 && yNew < N){
                    if(distance[xNew][yNew][power]==0 && input[xNew][yNew]==0){
                        distance[xNew][yNew][power] = distance[x][y][power]+plusAmount;
                        q.add(new int[]{xNew, yNew, power, nextNoon, 1});
                    }

                    if(power < K) {
                        if (distance[xNew][yNew][power + 1] == 0 && input[xNew][yNew] == 1 && canBreak) {
                            distance[xNew][yNew][power + 1] = distance[x][y][power] + plusAmount;
                            q.add(new int[]{xNew, yNew, power+1, nextNoon, 1});
                        } else if (distance[xNew][yNew][power + 1] == 0 && input[xNew][yNew] == 1 && !canBreak) {
                            q.add(new int[]{x, y, power, nextNoon, plusAmount+1});
                        }
                    }
                }
            }
        }
        res = -1;
    }
}