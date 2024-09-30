import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static int l;
    static int[][] sixWay = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        int notCnt = 0;
        Queue<int[]> q = new LinkedList<>();
        int[][][] input = new int[m][n][l];
        int[][][] date = new int[m][n][l];
        for(int k=0; k<l; k++){
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    int x = Integer.parseInt(st.nextToken());
                    input[i][j][k] = x;
                    if(x == 1) {
                        q.add(new int[]{i, j, k});
                        date[i][j][k] = 0;
                    }
                    else if (x == 0) {
                        notCnt++;
                        date[i][j][k] = -1;
                    }else{
                        date[i][j][k] = -2;
                    }
                }
            }
        }

        int res = 0;
        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int z = tmp[2];

            for(int i=0; i< sixWay.length; i++){
                int xNew = x + sixWay[i][0];
                int yNew = y + sixWay[i][1];
                int zNew = z + sixWay[i][2];
                if(xNew >= 0 && xNew < m && yNew >= 0 && yNew < n && zNew >= 0 && zNew < l){
                    if(date[xNew][yNew][zNew] == -1){
                        q.add(new int[]{xNew, yNew, zNew});
                        date[xNew][yNew][zNew] = date[x][y][z]+1;
                        if(res < date[xNew][yNew][zNew]) res = date[xNew][yNew][zNew];
                        notCnt--;
                    }
                }
            }
        }
        if(notCnt != 0) System.out.println(-1);
        else System.out.println(res);

    }
}