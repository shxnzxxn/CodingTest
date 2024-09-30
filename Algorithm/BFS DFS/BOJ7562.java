import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] eightWay = {{-2, -1}, {-1, -2}, {-2, 1}, {-1, 2}, {2, -1}, {1, -2}, {2, 1}, {1, 2}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int iter = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int k=0; k<iter; k++){
            int n = Integer.parseInt(br.readLine());
            int[][] chess = new int[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++) chess[i][j] = -1;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            if(startX == endX && startY == endY) {
                sb.append(0+"\n");
                continue;
            }

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{startX, startY});
            chess[startX][startY] = 0;

            while (!q.isEmpty()) {
                boolean reach = false;
                int res = 0;
                int[] tmp = q.poll();
                int x = tmp[0];
                int y = tmp[1];
                for(int i=0; i<eightWay.length; i++){
                    int xNew = x + eightWay[i][0];
                    int yNew = y + eightWay[i][1];

                    if(xNew >= 0 && xNew < n && yNew >= 0 && yNew <n){
                        if(chess[xNew][yNew] == -1){
                            chess[xNew][yNew] = chess[x][y] +1;
                            if(xNew == endX && yNew == endY) {
                                reach = true;
                                res = chess[xNew][yNew];
                                break;
                            }else{
                                q.add(new int[]{xNew, yNew});
                            }
                        }
                    }
                }
                if(reach){
                    sb.append(res+"\n");
                }
            }
        }
        System.out.println(sb);
    }
}