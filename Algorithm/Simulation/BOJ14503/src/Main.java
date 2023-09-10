import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static int startX;
    static int startY;
    static int[][] input;
    static int cleanCnt = 0;
    static int startWay = 0;
    static boolean[][] cleaned;
    static int[][] fourWay = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        input = new int[m][n];
        cleaned = new boolean[m][n];

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        startWay = Integer.parseInt(st.nextToken());

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if(input[i][j] == 1) cleaned[i][j] = true;
            }
        }

        clean(startX, startY, startWay);
        System.out.println(cleanCnt);
    }

    private static void clean(int x, int y, int way) {
        if(!cleaned[x][y]) {
            cleanCnt++;
            cleaned[x][y] = true;
        }

        if(canCleanFourWay(x, y)){
            // 만약 네 방향 중 청소할 곳이 있다면, 반시계 방향으로 회전하면서 재귀를 부른다. (청소 안한 부분이 있을 때만)
            int i = way;
            for(int k=0; k<4; k++){
                i = (i-1)%4;
                if(i<0) i +=4;
                int xNew = x + fourWay[i][0];
                int yNew = y + fourWay[i][1];

                if(xNew < 0 || xNew >= m || yNew < 0 || yNew >= n) continue;
                if(!cleaned[xNew][yNew]) {
                    clean(xNew, yNew, i);
                    break;
                }
            }
        }else{
            // 만약 그렇지 않다면, 후진한다. 후진 못하면 종료.
            int xNew = x+fourWay[(way+2)%4][0];
            int yNew = y+fourWay[(way+2)%4][1];

            if(xNew < 0 || xNew >= m || yNew < 0 || yNew >= n) {
                System.out.println(cleanCnt);
                System.exit(0);
            }

            if(input[xNew][yNew] == 1){
                System.out.println(cleanCnt);
                System.exit(0);
            }

            clean(xNew, yNew, way);
        }
    }

    private static boolean canCleanFourWay(int x, int y) {
        for(int i=0; i<fourWay.length; i++){
            int xNew = x + fourWay[i][0];
            int yNew = y + fourWay[i][1];

            if(xNew < 0 || xNew >= m || yNew < 0 || yNew >= n) continue;
            if(!cleaned[xNew][yNew]) return true;
        }
        return false;
    }
}