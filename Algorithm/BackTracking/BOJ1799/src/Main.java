import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static LinkedList<int[]>[][] input = new LinkedList[2][20]; // 색은 총 2가지이고, / 대각선은 모두 같다고 생각하면 20개가 max이다.(한 변의 길이가 최대 10), 마지막 2는 좌표 저장!
    static boolean[][] visited = new boolean[2][20];
    static int[] res = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<input.length; i++){
            for(int j=0; j<input[i].length; j++){
                input[i][j] = new LinkedList<>();
            }
        }


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(st.nextToken());
                if(x==1) input[(i+j+1)%2][n+i-j-1].add(new int[]{i, j});
            }
        }

        BackTracking(0, 0, 0);
        BackTracking(0, 0, 1);

        System.out.println(res[0]+res[1]);
    }

    private static void BackTracking(int idx, int cnt, int color) {
        if(idx == 2*n){
            res[color] = Math.max(res[color], cnt);
            return;
        }

        for (int[] ints : input[color][idx]) {
            int x = ints[0];
            int y = ints[1];

            if(visited[color][x+y]) continue; // \ 대각선은 여기서 처리하는 느낌...?
            visited[color][x+y] = true;
            BackTracking(idx+1, cnt+1, color);
            visited[color][x+y] = false;
        }

        BackTracking(idx+1, cnt, color);
    }


}