import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static LinkedList<int[]>[][] lightLoc;
    static int cnt = 1; // (0, 0)은 무조건 켜져있음
    static boolean[][] turnOn;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lightLoc = new LinkedList[N][N];
        turnOn = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) lightLoc[i][j] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int objX = Integer.parseInt(st.nextToken()) - 1;
            int objY = Integer.parseInt(st.nextToken()) - 1;

            lightLoc[x][y].add(new int[]{objX, objY});
        }
        turnOn[0][0] = true;
        BFS(0, 0);
        System.out.println(cnt);
    }

    private static void BFS(int a, int b) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, b});
        boolean[][] visited = new boolean[N][N];
        visited[a][b] = true;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            LinkedList<int[]> lst = lightLoc[x][y];
            for (int[] ints : lst) {
                int tmpX = ints[0];
                int tmpY = ints[1];

                if (!turnOn[tmpX][tmpY]) {
                    turnOn[tmpX][tmpY] = true;

                    // 만약 불을 킨 곳 사방에 이미 방문했던 곳이 있으면, 이 곳은 다시 갈 수 있음.
                    for (int i = 0; i < dx.length; i++) {
                        int tmpNewX = tmpX + dx[i];
                        int tmpNewY = tmpY + dy[i];

                        if (tmpNewX < 0 || tmpNewX >= N || tmpNewY < 0 || tmpNewY >= N) continue;
                        if (visited[tmpNewX][tmpNewY]) {
                            q.add(new int[]{tmpX, tmpY});
                            visited[tmpX][tmpY] = true;
                            break;
                        }
                    }
                    cnt++;
                }
            }

            for(int i=0; i<dx.length; i++){
                int xNew = x + dx[i];
                int yNew = y + dy[i];

                if (xNew < 0 || xNew >= N || yNew < 0 || yNew >= N) continue;
                if(visited[xNew][yNew] || !turnOn[xNew][yNew]) continue;
                visited[xNew][yNew] = true;
                q.add(new int[]{xNew, yNew});
            }
        }
    }
}