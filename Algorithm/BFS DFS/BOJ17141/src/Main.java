import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static LinkedList<int[][]> comb;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] obj = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        int[][] distance = new int[N][N]; // 2인 좌표를 넣기
        LinkedList<int[]> q = new LinkedList<>();
        int[][] fourWay = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 위, 아래, 왼, 오

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                obj[i][j] = Integer.parseInt(st.nextToken());
                distance[i][j] = -1;
                if(obj[i][j] == 2) q.add(new int[]{i, j});
            }
        }
        boolean[] visited_tmp = new boolean[q.size()];
        comb = new LinkedList<>();
        combination(q, visited_tmp, 0, q.size(), M);
        int max = -1;
        for(int i=0; i<comb.size(); i++){ // 바이러스가 될 수 있는 경우의 수만큼 반복함.
            LinkedList<int[]> que = new LinkedList<>();
            for(int j=0; j<comb.get(i).length; j++){ // 감염시킬 바이러스를 선택함.
                int x = comb.get(i)[j][0];
                int y = comb.get(i)[j][1];
                que.add(new int[]{x, y});
                distance[x][y] = 0;
            }
            System.out.println("Before");
            printDistance(distance);
            System.out.println("After");
            while(!que.isEmpty()) { // 선택한 바이러스들에 대해서 감염되는 날짜를 계산함.
                int[] s = que.poll();
                int x = s[0];
                int y = s[1];

                visited[x][y] = true;

                for (int j = 0; j < fourWay.length; j++) {
                    int x_adj = x + fourWay[j][0];
                    int y_adj = y + fourWay[j][1];

                    if (x_adj < 0 || y_adj < 0 || x_adj >= N || y_adj >= N) continue;

                    if ((obj[x_adj][y_adj] == 0 && !visited[x_adj][y_adj]) ||
                            (visited[x_adj][y_adj] && distance[x_adj][y_adj] > distance[x][y] + 1)) {
                        distance[x_adj][y_adj] = distance[x][y] + 1;
                        que.add(new int[]{x_adj, y_adj});
                        visited[x_adj][y_adj] = true;
                    }
                }
            }
                printDistance(distance);
                System.out.println("-----------------------------");
                int max_tmp = -1;
                for(int k=0; k<distance.length; k++){
                    for(int m=0; m<distance[k].length; m++){
                        if(max_tmp == -1) max_tmp = distance[k][m];
                        else if(max_tmp != -1 && max_tmp < distance[k][m]) max_tmp = distance[k][m];
                    }
                }
                if(max == -1) max = max_tmp;
                else if(max != -1 && max > max_tmp) max = max_tmp;
            }
//        System.out.println(max);
        }

    // 조합 by 백트래킹
    static void combination(LinkedList<int[]> arr, boolean[] visited, int start, int n, int r) {
        if(r == 0) {
            print(arr, visited, n);
            return;
        }

        for(int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
    // 배열 출력
    static void print(LinkedList<int[]> arr, boolean[] visited, int n) {
        int[][] tmp = new int[M][2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if(visited[i]) {
                tmp[idx][0] = arr.get(i)[0];
                tmp[idx++][1] = arr.get(i)[1];
            }
        }
        comb.add(tmp);
    }
    static void printDistance(int[][] distance){
        for(int i=0; i<distance.length; i++){
            for(int j=0; j<distance[i].length; j++){
                System.out.print(distance[i][j]+" ");
            }
            System.out.println();
        }
    }
}