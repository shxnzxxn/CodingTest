import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] fourWay = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] input = new int[n][n];
        StringTokenizer st;

        // step 1 : 입력받기
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) input[i][j] = Integer.parseInt(st.nextToken());
        }

        boolean[][] visited = new boolean[n][n];
        int num = 0;
        ArrayList<int[]>[] tmp = new ArrayList[100000];

        // step 2 : 섬마다 다른 수 부여하기. 섬의 경계 찾기
        for(int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                if(!visited[i][j] && input[i][j] != 0) {
                    tmp[num] = BFSIsland(input, visited, i, j, num+1);
                    num++;
                }
            }
        }
        ArrayList<int[]>[] boundary = new ArrayList[num];

        for(int i=0; i< boundary.length; i++) {
            boundary[i] = tmp[i];
        }

        // step 3 : 경계에서 BFS를 하여 다리를 잇는 길이 구하기
        int res = 100000;
        for(int k=0; k<boundary.length; k++){
            for(int i=0; i<boundary[k].size(); i++){
                int[] index = boundary[k].get(i);
                int len = BFSBridge(input, index[0], index[1]);
                if(len < res) res = len;
            }
        }
        System.out.println(res);
    }

    private static int BFSBridge(int[][] input, int a, int b) {
        boolean[][] visited = new boolean[n][n];
        int[][] distance = new int[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, b});
        int num = input[a][b];
        visited[a][b] = true;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i< fourWay.length; i++){
                int xNew = x + fourWay[i][0];
                int yNew = y + fourWay[i][1];

                if(xNew >= 0 && xNew < n && yNew >= 0 && yNew < n){
                    if(!visited[xNew][yNew] && input[xNew][yNew] == 0){
                        distance[xNew][yNew] = distance[x][y]+1;
                        q.add(new int[]{xNew, yNew});
                        visited[xNew][yNew] = true;
                    }else if(input[xNew][yNew] != num && input[xNew][yNew] != 0){
                        return distance[x][y];
                    }
                }
            }
        }
        return 100000;
    }

    private static ArrayList<int[]> BFSIsland(int[][] input, boolean[][] visited, int a, int b, int num) {
        visited[a][b] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, b});
        input[a][b] = num;
        ArrayList<int[]> islandBoundary = new ArrayList<>();
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            boolean boundary = false;
            for(int i=0; i< fourWay.length; i++){
                int xNew = x + fourWay[i][0];
                int yNew = y + fourWay[i][1];
                if(xNew >= 0 && xNew < n && yNew >= 0 && yNew < n){
                    if(!visited[xNew][yNew] && input[xNew][yNew] != 0){
                        visited[xNew][yNew] = true;
                        q.add(new int[]{xNew, yNew});
                        input[xNew][yNew] = num;
                    }else if(input[xNew][yNew] == 0) boundary = true;
                }
            }
            if(boundary) islandBoundary.add(new int[]{x, y});

        }
        return islandBoundary;
    }

}