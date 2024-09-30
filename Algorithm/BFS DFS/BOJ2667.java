import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static int[][] input;
    static boolean[][] visited;
    static int cnt = 0;
    static ArrayList<Integer> area = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0; i<N; i++){
            String[] tmp = br.readLine().split("");
            for(int j=0; j<N; j++) input[i][j] = Integer.parseInt(tmp[j]);
        }

        for(int i=0;i <N; i++){
            for(int j=0; j<N; j++){
                if(input[i][j]==1 && !visited[i][j]) BFS(i, j);
            }
        }

        Collections.sort(area);
        StringBuilder sb = new StringBuilder();
        sb.append(cnt + "\n");
        for (int i = 0; i < area.size(); i++) sb.append(area.get(i)+"\n");
        System.out.println(sb);
    }

    private static void BFS(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        visited[i][j] = true;
        q.add(new int[]{i, j});
        cnt++;

        int sqaure = 1;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for(int k=0; k<dx.length; k++){
                int xNew = x+dx[k];
                int yNew = y+dy[k];
                if(xNew >= 0 && xNew < N && yNew >=0 && yNew < N){
                    if(!visited[xNew][yNew] && input[xNew][yNew] == 1){
                        q.add(new int[]{xNew, yNew});
                        visited[xNew][yNew] = true;
                        sqaure++;
                    }
                }
            }
        }
        area.add(sqaure);
    }
}