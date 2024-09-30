import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int P;
    static String[][] input;
    static boolean[][] visited;
    static int[] playerPower;
    static Queue<int[]>[] q;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean iter = true;
    static int[] area;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        input = new String[M][N];
        playerPower = new int[P];
        q = new LinkedList[P];
        visited = new boolean[M][N];
        area = new int[P];

        for (int i = 0; i < P; i++) q[i] = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < P; i++) playerPower[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            input[i] = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                if (!input[i][j].equals(".") && !input[i][j].equals("#")) {
                    int p = Integer.parseInt(input[i][j]) - 1;
                    area[p]++;
                    visited[i][j] = true;
                    q[p].add(new int[]{i, j, 0});
                } else if (input[i][j].equals("#")) visited[i][j] = true;
            }
        }

        while (iter) {
            iter = false;
            for (int i = 0; i < P; i++) BFS(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<area.length; i++) sb.append(area[i]+" ");
        System.out.println(sb);
    }

    private static void BFS(int start) {
        Queue<int[]> nextQ = new LinkedList<>();
        while (!q[start].isEmpty()) {
            int[] tmp = q[start].poll();
            int x = tmp[0];
            int y = tmp[1];
            int cnt = tmp[2];

            if (cnt == playerPower[start]) {
                nextQ.add(new int[]{x, y, 0});
                continue;
            }

            for (int i = 0; i < dx.length; i++) {
                int xNew = x + dx[i];
                int yNew = y + dy[i];

                if (xNew >= 0 && xNew < M && yNew >= 0 && yNew < N) {
                    if (!visited[xNew][yNew]) {
                        iter = true;
                        visited[xNew][yNew] = true;
                        area[start]++;
                        q[start].add(new int[]{xNew, yNew, cnt+1});
                    }
                }
            }
        }
        q[start] = nextQ;
    }
}