import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] fourWay = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        String[][] input = new String[n][n];
        for(int i=0; i<n; i++){
            input[i] = br.readLine().split("");
//            for(int j=0; j<n; j++) input[i][j] = st.nextToken();
        }

        boolean[][] visitedYes = new boolean[n][n];
        boolean[][] visitedNo = new boolean[n][n];
        int yes = 0;
        int no = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visitedYes[i][j]){
                    BFSYes(input, visitedYes, i, j);
                    yes++;
                }
                if(!visitedNo[i][j]){
                    BFSNo(input, visitedNo, i, j);
                    no++;
                }
            }
        }
        System.out.println(no+" "+yes);
    }
    static void BFSNo(String[][] input, boolean[][] visited, int i, int j){
        String obj = input[i][j]; // 얘랑 같아야함!
        Queue<int[]> q = new LinkedList<>();
        visited[i][j] = true;
        q.add(new int[]{i, j});

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for(int k=0; k< fourWay.length; k++){
                int xNew = x+fourWay[k][0];
                int yNew = y+fourWay[k][1];
                if(xNew >= 0 && xNew < n && yNew >= 0 && yNew < n){
                    if((input[xNew][yNew].equals(obj))&& !visited[xNew][yNew]){
                        q.add(new int[]{xNew, yNew});
                        visited[xNew][yNew] = true;
                    }
                }
            }
        }
    }
    static void BFSYes(String[][] input, boolean[][] visited, int i, int j){
        boolean isBlue = (input[i][j].equals("B"))? true:false; // 얘랑 같아야함!
        Queue<int[]> q = new LinkedList<>();
        visited[i][j] = true;
        q.add(new int[]{i, j});

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for(int k=0; k< fourWay.length; k++){
                int xNew = x+fourWay[k][0];
                int yNew = y+fourWay[k][1];
                if(xNew >= 0 && xNew < n && yNew >= 0 && yNew < n){
                    if((isBlue && input[xNew][yNew].equals("B")) || (!isBlue && !input[xNew][yNew].equals("B"))){
                        if(!visited[xNew][yNew]) {
                            q.add(new int[]{xNew, yNew});
                            visited[xNew][yNew] = true;
                        }
                    }
                }
            }
        }
    }
}