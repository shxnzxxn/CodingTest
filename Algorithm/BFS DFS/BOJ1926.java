import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] mn = br.readLine().split(" ");
        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);
        Graph gp = new Graph(m, n);
        for(int i=0; i<m; i++){
            String[] tmp = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                int x = Integer.parseInt(tmp[j]);
                if(x==1) gp.addEdge(i, j);
            }
        }
        gp.BFS();
        System.out.println(gp.count);
        System.out.println(gp.square);
    }
}
class Graph{
    static int M;
    static int N;
    static int[][] obj;
    int count = 0;
    int square = 0;
    boolean[][] visited;
    Graph(int M, int N){
        this.M = M;
        this.N = N;
        obj = new int[M][N];
        visited = new boolean[M][N];
    }
    void addEdge(int a, int b){obj[a][b] = 1;}

    void BFS(){
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                int s = obj[i][j];
                if(s == 0 || visited[i][j] == true){
                    visited[i][j] = true;
                    continue;
                }
                visited[i][j] = true;
                count++;
                int square_tmp = 1;
                int[] it = find(i, j);
                if(it[0] == 1) square_tmp += BFS(i-1, j);
                if(it[1] == 1) square_tmp += BFS(i+1, j);
                if(it[2] == 1) square_tmp += BFS(i, j-1);
                if(it[3] == 1) square_tmp += BFS(i, j+1);
                if(this.square < square_tmp) square = square_tmp;
            }
        }
    }

    int BFS(int i, int j){
        if(visited[i][j] == true) return 0;
        visited[i][j] = true;
        int square_tmp = 1;
        int[] it = find(i, j);
        if(it[0] == 1) square_tmp += BFS(i-1, j);
        if(it[1] == 1) square_tmp += BFS(i+1, j);
        if(it[2] == 1) square_tmp += BFS(i, j-1);
        if(it[3] == 1) square_tmp += BFS(i, j+1);
        return square_tmp;
    }

    static int[] find(int i, int j){
        int[] res = {0, 0, 0, 0}; // 위 아래 왼 오
        if(i != 0 && obj[i-1][j] == 1) res[0] = 1;
        if(i+1 < M && obj[i+1][j] == 1) res[1] = 1;
        if(j != 0 && obj[i][j-1] == 1) res[2] = 1;
        if(j+1 < N && obj[i][j+1] == 1) res[3] = 1;
        return res;
    }
}