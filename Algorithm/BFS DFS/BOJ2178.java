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
    static int[][] fourWay = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        String[][] input = new String[m][n];
        int[][] distance = new int[m][n];
        for(int i=0; i<m; i++) {
            input[i] = br.readLine().split("");
        }
        boolean[][] visited = new boolean[m][n];
        BFS(input, visited, 0, 0, distance);
        System.out.println(distance[m-1][n-1]);
    }

    static void BFS(String[][] input, boolean[][] visited, int a, int b, int[][] distance){
        visited[a][b] = true;
        Queue<int[]> q = new LinkedList<>();
        distance[a][b] = 1;
        q.add(new int[]{a, b});
        while(!q.isEmpty()){
            int[] obj = q.poll();
            int x = obj[0];
            int y = obj[1];
            for(int i=0; i< fourWay.length; i++){
                int newX = x+ fourWay[i][0];
                int newY = y+ fourWay[i][1];
                if(newX >= 0 && newX < m && newY >= 0 && newY < n){
                    if(input[newX][newY].equals("1") && !visited[newX][newY]) {
                        q.add(new int[]{newX, newY});
                        distance[newX][newY] = distance[x][y]+1;
                        visited[newX][newY] = true;
                    }
                }
            }
        }

    }
}