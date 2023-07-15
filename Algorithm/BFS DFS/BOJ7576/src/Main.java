import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int[][] box;
    static int[][] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        box = new int[M][N];
        distance = new int[M][N];
        int[][] fourWay = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        LinkedList<int[]> q = new LinkedList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                distance[i][j] = -1;
                if(box[i][j] == 1){
                    int[] tmp = {i, j};
                    q.add(tmp);
                    distance[i][j] = 0;
                }
            }
        }
        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i<fourWay.length; i++){
                int x_adj = x+fourWay[i][0];
                int y_adj = y+fourWay[i][1];

                if(x_adj < 0 || y_adj < 0 || x_adj >= M || y_adj >= N) continue;
                if(box[x_adj][y_adj] == 0 && distance[x_adj][y_adj] == -1){
                    q.add(new int[]{x_adj, y_adj});
                    distance[x_adj][y_adj] = distance[x][y] +1;
                    box[x_adj][y_adj] = 1;
                }
            }
        }

        int max = 0;
        for(int i=0; i<distance.length; i++){
            for(int j=0; j<distance[i].length; j++){
                if(distance[i][j] == -1 && box[i][j] != -1){
                    System.out.println(-1);
                    System.exit(0);
                }
                else if(max < distance[i][j]) max = distance[i][j];
            }
        }
        System.out.println(max);
    }
}