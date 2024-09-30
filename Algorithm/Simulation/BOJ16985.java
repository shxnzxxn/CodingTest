import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
//    static int[][] input1 = new int[5][5];
//    static int[][] input2 = new int[5][5];
//    static int[][] input3 = new int[5][5];
//    static int[][] input4 = new int[5][5];
//    static int[][] input5 = new int[5][5];
    static int[][][] input = new int[5][5][5];
    static int[][][] res = new int[5][5][5];
    static int[] order = new int[5];
    static int[] spin = new int[5]; // 회전은 0, 1, 2, 3으로 나눈다. 좌측부터 0, 90, 180, 270도이다.
    static boolean[] orderVisited = new boolean[5];
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int time = 200;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int k=0; k<5; k++) {
            int[][] tmp = input[k];
            for(int i=0; i<5; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 5; j++) tmp[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 1. 백트래킹으로 순서를 먼저 정한다.
        // 2. 순서가 정해진 배열은 바로 회전 경우의 수를 백트래킹한다.
        // 3. 그 후, 회전 경우의 수까지 정해진 배열은 출구를 찾아가는 BFS를 진행한다.
        findOrderMiro(0);
        System.out.println(time==200?-1:time);
    }

    private static void findOrderMiro(int x) {
        if(x==5){ // 순서가 다 정해졌다면, 회전 경우의 수로 넘어가자.
            findSpinOrderMiro(0);
            return;
        }

        for(int i=0; i<5; i++){
            if(!orderVisited[i]){
                orderVisited[i] = true;
                order[x] = i;
                findOrderMiro(x+1);
                orderVisited[i] = false;
            }
        }
    }
    static int idx = 0;
    private static void findSpinOrderMiro(int x) {
        if(x==5){ // 회전 방향까지 정해졌다면, 주어진 배열을 회전시켜주자!
            idx++;
            spinMiro();

            // 회전시켰다면, BFS 시작!!
            if(res[0][0][0] == 1 && res[4][4][4]==1) BFS();
            return;
        }

        for(int i=0; i<4; i++){ // 회전은 중복 가능!
            spin[x] = i;
            findSpinOrderMiro(x+1);
        }
    }

    private static void BFS() {
//        System.out.println("-----------------------------------------");
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[5][5][5];
        q.add(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int z = tmp[0];
            int x = tmp[1];
            int y = tmp[2];
            int distance = tmp[3];

            if(x == 4 && y==4 && z ==4) {
                time = Math.min(time, distance);
//                System.out.println("true!!!!!!!!!!!!!!!!!!!!!!!!!");
//                System.out.println(time);
//                for(int i=0; i<5; i++){
//                    for(int j=0; j<5; j++) System.out.println(Arrays.toString(res[i][j]));
//                    System.out.println();
//                }
//                System.out.println("-----------------------------------------");
                return;
            }

            for(int i=0; i<dx.length; i++){
                int xNew = x+dx[i];
                int yNew = y+dy[i];
                int zNew = z+dz[i];

                if(xNew < 0 || xNew >= 5 || yNew < 0 || yNew >= 5 || zNew < 0 || zNew >= 5) continue;
                if(visited[zNew][xNew][yNew] || res[zNew][xNew][yNew] == 0) continue;
//                System.out.println("zNew = " + zNew);
//                System.out.println("xNew = " + xNew);
//                System.out.println("yNew = " + yNew);
//                System.out.println(res[zNew][xNew][yNew]);
                visited[zNew][xNew][yNew] = true;
                q.add(new int[]{zNew, xNew, yNew, distance+1});
            }

        }
//        System.out.println("-----------------------------------------");
    }

    private static void spinMiro() {
        // order에 있는 순서대로 회전을 시켜주자.
        // order에서 꺼낸 인덱스에 해당하는 배열을 spin 배열에 담겨져있는 방향에 맞게 회전한다.
        // 회전할 때는, 원래 배열이 손상가지 않게 복사하여 진행한다.
        // 다 회전하고 나면, 해당 배열을 res에 저장하고 종료한다.

        for(int i=0; i<5; i++){
            int orderIdx = order[i];
            int[][] tmp = new int[5][5];
            int spinAngle = spin[i];
//            for(int j=0; j>5)
            spinFlatMiro(input[orderIdx], tmp, spinAngle);
            res[i] = tmp;
        }
    }


    private static void spinFlatMiro(int[][] obj, int[][] res, int spinAngle) {
        // obj 배열을 spingAngle만큼 돌려서 res에 넣는다.

        if(spinAngle == 0){
            for(int i=0; i<5; i++) res[i] = obj[i].clone();
        }else if(spinAngle == 1){
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    res[i][j] = obj[5-j-1][i];
                }
            }
        }else if(spinAngle == 2){
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    res[i][j] = obj[5-i-1][5-j-1];
                }
            }
        }else{
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    res[i][j] = obj[j][5-i-1];
                }
            }
        }
    }
}