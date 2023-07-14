import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int[][] input = new int[M][N];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) input[i][j] = Integer.parseInt(st.nextToken());
        }
        ArrayList<int[]> zeroIndex = zeroCount(input);
        int zeroNum = zeroIndex.size();
        int res = 0;
        for(int k=0; k<zeroNum; k++){ // 0의 후보 개수만큼 반복함.
            for(int x=k+1; x<zeroNum; x++){
                for(int y=x+1; y<zeroNum; y++){
                    int square = countVirusNum(input, zeroIndex.get(k), zeroIndex.get(x), zeroIndex.get(y));
                    if(square > res) res = square;
                }
            }
        }
        System.out.println(res);
    }

    static ArrayList<int[]> zeroCount(int[][] input){
        ArrayList<int[]> zeroIndex = new ArrayList<>();
        for(int i=0; i<input.length; i++){
            for(int j=0; j<input[i].length; j++){
                if(input[i][j] == 0) {
                    int[] tmp = {i, j};
                    zeroIndex.add(tmp);
                }
            }
        }
        return zeroIndex;
    }

    static int[][] copy(int[][] input){
        int[][] res = new int[input.length][input[0].length];
        for(int i=0; i<res.length; i++){
            for(int j=0; j<res[i].length; j++) res[i][j] = input[i][j];
        }
        return res;
    }

    static int countVirusNum(int[][] input, int[] x, int[] y, int[] z){
        int[][] copy_input = copy(input);
        copy_input[x[0]][x[1]] = 1;
        copy_input[y[0]][y[1]] = 1;
        copy_input[z[0]][z[1]] = 1;

        return BFS(copy_input);
    }

    static int BFS(int[][] input){
        boolean[][] visitied = new boolean[input.length][input[0].length];
        for(int i=0; i<input.length; i++){
            for(int j=0; j<input[i].length; j++){
                if(input[i][j] == 2) BFS(input, i, j, visitied);
            }
        }
        return zeroCount(input).size();
    }

    static void BFS(int[][] input, int i, int j, boolean[][] visited){
        if(i<0 || j<0 || i>=M || j >= N) return;
        if(input[i][j] == 1 || visited[i][j]) return;
        visited[i][j] = true;
        input[i][j] = 2;
        BFS(input, i-1, j, visited);
        BFS(input, i+1, j, visited);
        BFS(input, i, j-1, visited);
        BFS(input, i, j+1, visited);
    }
}