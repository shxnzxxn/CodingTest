import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static int[][] fourWay = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int[][] input = new int[m][n];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) input[i][j] = Integer.parseInt(st.nextToken());
        }
        int res = 0;
        int x = segment(input);
        while(x == 1){
            input = BFS(input);
            res++;
            x = segment(input);
        }
        if(x == 0) System.out.println(0);
        else System.out.println(res);

    }

    private static int[][] BFS(int[][] input) {
        int[][] res = new int[m][n];
        for(int i=0; i<m; i++) res[i] = input[i].clone();
        for(int x=1; x<m-1; x++){
            for(int y=1; y<n-1; y++){
                if(input[x][y] != 0) {
                    int zeroCount = 0;
                    for(int a=0; a<fourWay.length; a++){
                        int xNew = x + fourWay[a][0];
                        int yNew = y + fourWay[a][1];

                        if(input[xNew][yNew] == 0) zeroCount++;
                    }
                    res[x][y] = Math.max((res[x][y] - zeroCount), 0);
                }
            }
        }
        return res;
    }

    public static int segment(int[][] input){
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        for(int i=1; i<input.length-1; i++){
            for(int j=1; j<input[i].length-1; j++){
                if(!visited[i][j] && input[i][j] != 0){
                    count(input, visited, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public static void count(int[][] input, boolean[][] visited, int a, int b){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, b});
        visited[a][b] = true;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for(int i=0; i<fourWay.length; i++){
                int xNew = x + fourWay[i][0];
                int yNew = y + fourWay[i][1];

                if (input[xNew][yNew] != 0 && !visited[xNew][yNew]) {
                    visited[xNew][yNew] = true;
                    q.add(new int[]{xNew, yNew});
                }
            }
        }
    }
}