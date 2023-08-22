import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M;
    static int N;
    static int K;
    static int[][] input;
    static boolean[][] visited;
    static int cnt = 0;
    static ArrayList<Integer> area = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new int[M][N];
        visited = new boolean[M][N];

        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            int leftDownY = Integer.parseInt(st.nextToken());
            int leftDownX = M-Integer.parseInt(st.nextToken())-1;
            int rightUpY = Integer.parseInt(st.nextToken())-1;
            int rightUpX = M-Integer.parseInt(st.nextToken());
            fillOne(leftDownX, leftDownY, rightUpX, rightUpY);
        }

        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(!visited[i][j] && input[i][j]!=1) BFS(i, j);
            }
        }

        System.out.println(cnt);
        Collections.sort(area);
        for (Integer integer : area) {
            System.out.print(integer+" ");
        }
    }

    private static void BFS(int a, int b) {
        Queue<int[]> q = new LinkedList<>();
        visited[a][b] = true;
        q.add(new int[]{a, b});
        cnt++;
        int square = 1;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i<dx.length; i++){
                int xNew = x + dx[i];
                int yNew = y + dy[i];
                if(xNew >= 0 && xNew < M && yNew >= 0 && yNew < N){
                    if(!visited[xNew][yNew] && input[xNew][yNew] != 1){
                        visited[xNew][yNew] = true;
                        square++;
                        q.add(new int[]{xNew, yNew});
                    }
                }
            }
        }
        area.add(square);
    }

    private static void fillOne(int leftDownX, int leftDownY, int rightUpX, int rightUpY) {
        for(int i=rightUpX; i<=leftDownX; i++){
            for(int j=leftDownY; j<=rightUpY; j++) input[i][j] = 1;
        }
    }
}