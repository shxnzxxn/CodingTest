import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static int[][] fourWay = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int[][] input = new int[m][n];

        System.out.println(BFS(input));
    }

    private static int BFS(int[][] input) {
        Queue<int[]> q = new LinkedList<>();
        int[][][] visited = new int[m][n][2]; // 마지막은 0과 1로 나타내진다.

        q.add(new int[]{0, 0, 0}); // 초기에는 벽을 부시지 않은 상태이므로 마지막 채널에 0을 넣어준다.
        visited[0][0][0] = 1;       // 문제에서는 (1, 1)에서부터 1로 카운트하므로, 소요시간을 1로 넣어준다.

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int crush = tmp[2];
            // x와 y는 위치를 나타내며, crush는 0과 1로 0은 아직 벽을 부시지 않은 상태. 1은 벽을 부신 상태이다.

            if(x==m-1 && y == n-1) return visited[x][y][crush];
            // 만약 (m, n)에 도달했다면, 해당 위치까지의 소요 시간을 반환한다.

            for(int i=0; i< fourWay.length; i++){
                int xNew = x + fourWay[i][0];
                int yNew = y + fourWay[i][1];
                // 상하좌우로 도달할 위치를 나타내는 변수가 xNew, yNew이다.

                if(xNew >= 0 && xNew < m && yNew >= 0 && yNew < n) {
                    // 상하좌우로 도달할 위치가 범위 내에 있을 때,

                    if (visited[xNew][yNew][crush] == 0) {
                        // 도달할 위치를 아직 방문하지 않은 상태일 때,

                        if ((input[xNew][yNew] == 1) && (crush == 0)) {
                            // 도달할 위치가 벽(input[xNew][yNew] == 1)이고,
                            // 아직 벽을 부시지 않은(crush == 0) 상태일 때

                            visited[xNew][yNew][1] = visited[x][y][crush] + 1;
                            q.add(new int[]{xNew, yNew, 1});
                            // 해당 위치에 도달하고, 벽을 부신 상태로 넘어간다.

                        }else if(input[xNew][yNew] == 0){
                            // 도달할 위치가 벽이 아닐 때

                            visited[xNew][yNew][crush] = visited[x][y][crush] + 1;
                            q.add(new int[]{xNew, yNew, crush});
                            // 해당 위치에 도달하고, 벽을 부시지 않은 상태를 유지한다.
                        }

                        // 위 상황을 제외한, 아래와 같은 상황들은 다루지 않는다.
                        // * 다음으로 도달할 위치가 벽이고, 이전에 벽을 이미 부신 상태

                    }
                }
            }
        }
        return -1;
        // 그럼에도 불구하고, (m, n)을 도달하지 못했다면 -1을 반환한다.
    }
}