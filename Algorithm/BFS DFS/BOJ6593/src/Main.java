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
    static String[][][] input;
    static int[][][] distance;
    static int[] start;
    static int[] goal;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(true){
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            input = new String[K][M][N];
            distance = new int[K][M][N];

            res = 100000;

            if(K == 0 && M == 0 && N == 0) break;

            for(int k=0; k<K; k++){
                for(int i=0; i<M; i++){
                    input[k][i] = br.readLine().split("");
                    for(int j=0; j<N; j++){
                        if(input[k][i][j].equals("S")) start = new int[]{k, i, j};
                        if(input[k][i][j].equals("E")) goal = new int[]{k, i, j};
                    }
                }
                br.readLine();
            }

            BFS(start);

            sb.append(res==-1?"Trapped!\n":"Escaped in "+res+" minute(s).\n");
        }
        System.out.println(sb);
    }

    private static void BFS(int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int z = tmp[0];
            int x = tmp[1];
            int y = tmp[2];

            if(input[z][x][y].equals("E")){
                res = Math.min(res, distance[z][x][y]);
                return;
            }

            for(int i=0; i<dx.length; i++){
                int xNew = x + dx[i];
                int yNew = y + dy[i];
                int zNew = z + dz[i];

                if(xNew >= 0 && xNew < M && yNew >= 0 && yNew < N && zNew >= 0 && zNew < K){
                    if(distance[zNew][xNew][yNew] == 0 && !input[zNew][xNew][yNew].equals("#")){
                        distance[zNew][xNew][yNew] = distance[z][x][y]+1;
                        q.add(new int[]{zNew, xNew, yNew});
                    }
                }
            }
        }
        res = -1;
    }
}