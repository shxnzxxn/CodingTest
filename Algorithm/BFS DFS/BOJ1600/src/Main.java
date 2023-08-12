import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int m;
    static int n;
    static int move = 200*200+1;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] dxHorse = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dyHorse = {-2, -1, 1, 2, -2, -1, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] input = new int[m][n]; // k까지 부식 수 있으니까! k번째에 오면 이제 더이상 부실 수 없다는 소리!
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS(input);
        int res = move == 200*200+1 ? -1 : move;
        System.out.println(res);
    }

    private static void BFS(int[][] input) {
        boolean[][][] visited = new boolean[m][n][k+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0, 0}); // x, y, distance, break 횟수
        visited[0][0][0] = true;


        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int dis = tmp[2];
            int power = tmp[3];


            if(x == m-1 && y == n-1){
                move = Math.min(move, dis);
            }

            for(int i=0; i< dx.length; i++){
                int xNew = x + dx[i];
                int yNew = y + dy[i];

                if(xNew >= 0 && xNew < m && yNew >= 0 && yNew < n){
                    if((input[xNew][yNew] == 0) && !visited[xNew][yNew][power]){
                        visited[xNew][yNew][power] = true;
                        q.add(new int[]{xNew, yNew, dis+1, power});
                    }
                }
            }
            if(power < k) { // 아직 뛰어넘을 수 있다!!
                for(int i = 0;  i< dxHorse.length; i++){
                    int xNew = x + dxHorse[i];
                    int yNew = y + dyHorse[i];

                    if(xNew >= 0 && xNew < m && yNew >= 0 && yNew < n){
                        if(!visited[xNew][yNew][power+1] && input[xNew][yNew] == 0){
                            visited[xNew][yNew][power+1] = true;
                            q.add(new int[]{xNew, yNew, dis+1, power+1});
                        }
                    }
                }
            }

        }

    }
}