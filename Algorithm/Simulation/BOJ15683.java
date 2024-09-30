import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static int[][] input;
    static LinkedList<int[]> cctvList = new LinkedList<>();
    static int cannotSeeArea;
    static int[][][] one = {{{-1, 0}}, {{1, 0}}, {{0, -1}}, {{0, 1}}};
    static int[][][] two = { {{-1, 0}, {1, 0}}, {{0,-1}, {0, 1}}};
    static int[][][] three = { {{-1, 0}, {0,-1}}, {{0,-1}, {1, 0}}, {{1, 0}, {0, 1}}, {{0,1}, {-1, 0}}};
    static int[][][] four = { {{-1, 0}, {1, 0}, {0, 1}}, {{-1,0}, {1, 0}, {0, -1}}, {{-1, 0}, {0, 1}, {0, -1}}, {{1,0}, {0, 1}, {0, -1}}};
    static int[][][] five = { {{-1, 0}, {1, 0}, {0,-1}, {0, 1}}};
    static int[][][][] cctvWay = {one, two, three, four, five};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        cannotSeeArea = m*n;
        input = new int[m][n];
        visited = new boolean[m][n];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int x = Integer.parseInt(st.nextToken());
                if(x != 0 && x != 6) cctvList.add(new int[]{x, i, j});
                if(x != 0) { // 0이 아닌 곳은 애초에 처음부터 cnt 하지 않음.
                    cannotSeeArea--;
                    visited[i][j] = true;
                }
                input[i][j] = x;
            }
        }

//        System.out.println(cannotSeeArea);
        simulation(0, cannotSeeArea); // cctvList의 0번 째 중에서 0번째 카메라 구도를 방문해야합니다.
        System.out.println(cannotSeeArea);
    }

    private static void simulation(int a, int area) {
        if(a == cctvList.size()){
//            System.out.println(1);
            cannotSeeArea = Math.min(cannotSeeArea, area);
            if(cannotSeeArea == 0){
                System.out.println(0);
                System.exit(0);
            }
//            System.out.println(cannotSeeArea);
            return;
        }
//        System.out.println(a);
        int[] indices = cctvList.get(a);
        int num = indices[0];
        int x = indices[1];
        int y = indices[2];
        int[][][] obj = cctvWay[num-1]; // one, two, three, four, five 중 하나

        for(int i=0; i<obj.length; i++){ // 이거는 카메라 위치를 나타냄.
            int copyX = x;
            int copyY = y;
            boolean[][] copyVisited = new boolean[m][n];
            int pastArea = area;
            for(int l=0; l<m; l++) copyVisited[l] = visited[l].clone();

            for(int j=0; j<obj[i].length; j++){ // 이거는 카메라 위치를 고정하고 카메라가 해당하는 방향을 보면서 cnt하는 부분.
                int[] ints = obj[i][j];
                while(true){
                    int xNew = copyX+ints[0];
                    int yNew = copyY+ints[1];

                    if(xNew < 0 || xNew >= m || yNew < 0 || yNew >= n) break;
                    if(input[xNew][yNew]==6) break;
                    if(!visited[xNew][yNew]){
                        visited[xNew][yNew] = true;
                        area--;
                    }
                    copyX = xNew;
                    copyY = yNew;
                }
                copyX = x;
                copyY = y;
            }
            simulation(a+1, area);
            area = pastArea;
            for(int l=0; l<m; l++) visited[l] = copyVisited[l].clone();
        }
    }
}