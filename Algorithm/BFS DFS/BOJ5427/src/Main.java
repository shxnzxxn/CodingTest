import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static int[][] fourWay = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int iter = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int k=0; k<iter; k++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            String[][] input = new String[m][n];
            int[] person = new int[2];
            Queue<int[]> fire = new LinkedList<>();
            for(int i=0; i<m; i++){
                input[i] = br.readLine().split("");
                for(int j=0; j<n; j++){
                    if(input[i][j].equals("@")){
                        person = new int[]{i, j};
                    }else if(input[i][j].equals("*")){
                        fire.add(new int[]{i, j});
                    }
                }
            }
            boolean[][] fireVisited = new boolean[m][n];
            int[][] fireDistance = new int[m][n];
            BFSFire(input, fire, fireVisited, fireDistance);
            boolean[][] personVisited = new boolean[m][n];
            int[][] personDistance = new int[m][n];
            int x = BFSPerson(input, person, personVisited, fireDistance, personDistance);

            if(x==-1) sb.append("IMPOSSIBLE\n");
            else sb.append(x+"\n");
        }
        System.out.println(sb);
    }

    private static void BFSFire(String[][] input, Queue<int[]> q, boolean[][] visited, int[][] distance) {
//        Queue<int[]> q = new LinkedList<>();

        while(!q.isEmpty()){
            int tmp[] = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for(int i=0; i<fourWay.length; i++){
                int xNew = x + fourWay[i][0];
                int yNew = y + fourWay[i][1];

                if(xNew >= 0 && xNew < m && yNew >=0 && yNew < n){
                    if(!visited[xNew][yNew] && input[xNew][yNew].equals(".")){
                        distance[xNew][yNew] = distance[x][y]+1;
                        q.add(new int[]{xNew, yNew});
                        visited[xNew][yNew] = true;
                    }
                }
            }
        }
    }


    private static int BFSPerson(String[][] input, int[] person, boolean[][] visited, int[][] fireDistance, int[][] personDistance) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{person[0], person[1]});
        visited[person[0]][person[1]] = true;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for(int i=0; i< fourWay.length; i++){
                int xNew = x + fourWay[i][0];
                int yNew = y + fourWay[i][1];

                if(xNew >= 0 && xNew < m && yNew >= 0 && yNew < n){
                    if((!visited[xNew][yNew] && personDistance[x][y]+1 < fireDistance[xNew][yNew] && input[xNew][yNew].equals("."))
                            || (!visited[xNew][yNew] && fireDistance[xNew][yNew] == 0 && input[xNew][yNew].equals("."))){
                        personDistance[xNew][yNew] = personDistance[x][y] +1;
                        q.add(new int[]{xNew, yNew});
                        visited[xNew][yNew] = true;
                    }
                }else{
                    return personDistance[x][y]+1;
                }
            }
        }
        return -1;
    }
}