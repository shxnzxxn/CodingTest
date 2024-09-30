import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static int diceStartX;
    static int diceStartY;
    static int iter;
    static int[][] map;
    static int[][] rollWay = { {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int[] dice = {0, 0, 0, 0, 0, 0}; // left, right, front, back, floor, ceil 순서
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        diceStartX = Integer.parseInt(st.nextToken());
        diceStartY = Integer.parseInt(st.nextToken());
        iter = Integer.parseInt(st.nextToken());

        map = new int[m][n];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int k=0; k<iter; k++){
            int inst = Integer.parseInt(st.nextToken());

            int[] index = rollWay[inst-1];
            int xNew = diceStartX + index[0];
            int yNew = diceStartY + index[1];

            if(xNew < 0 || xNew >= m || yNew < 0 || yNew >= n) continue;

            if(inst == 1) rollRight();
            else if(inst == 2) rollLeft();
            else if(inst == 3) rollUp();
            else if(inst == 4) rollDown();

            copyNum(xNew, yNew);
            sb.append(dice[5]+"\n");

            diceStartX = xNew;
            diceStartY = yNew;
        }
        System.out.println(sb);
    }

    private static void copyNum(int xNew, int yNew) {
        if(map[xNew][yNew] == 0){
            map[xNew][yNew] = dice[4];
        }else{
            dice[4] = map[xNew][yNew];
            map[xNew][yNew] = 0;
        }
    }

    private static void rollRight() {
        // right(1) -> floor(4)
        // floor(4) -> left(0)
        // left(0) -> ceil(5)
        // ceil(5) -> right(1)

        int one = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[0];
        dice[0] = dice[4];
        dice[4] = one;
    }

    private static void rollLeft() {
        // right(1) -> ceil(5)
        // ceil(5) -> left(0)
        // left(0) -> floor(4)
        // floor(4) -> right(1)

        int one = dice[1];
        dice[1] = dice[4];
        dice[4] = dice[0];
        dice[0] = dice[5];
        dice[5] = one;
    }
    private static void rollUp() {
        // back(3) -> floor(4)
        // floor(4) -> front(2)
        // front(2) -> ceil(5)
        // ceil(5) -> back(3)

        int three = dice[3];
        dice[3] = dice[5];
        dice[5] = dice[2];
        dice[2] = dice[4];
        dice[4] = three;
    }
    private static void rollDown() {
        // back(3) -> ceil(5)
        // ceil(5) -> front(2)
        // front(2) -> floor(4)
        // floor(4) -> back(3)

        int three = dice[3];
        dice[3] = dice[4];
        dice[4] = dice[2];
        dice[2] = dice[5];
        dice[5] = three;
    }
}