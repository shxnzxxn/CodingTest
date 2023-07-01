import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        String[][] input = new String[m][];
        int res = 32;
        for (int i = 0; i < m; i++) input[i] = br.readLine().split("");
        String[] go_obj = {"B", "W", "B", "W", "B", "W", "B", "W"};
        String[] back_obj = {"W", "B", "W", "B", "W", "B", "W", "B"};

        for (int i = 0; i < m - 8 + 1; i++) {
            for (int j = 0; j < n - 8 + 1; j++) { // 여기까지는 제일 좋은 8*8 정사각형을 찾는 for loop이다.
                int go_cnt = 0;
                int back_cnt = 0;
                for (int x = i; x < i + 8; x++) { // 8*8의 정사각형 내에서 색칠해야하는 횟수를 구해준다.
                    for (int y = j; y < j+8; y++) {
                        // 하지만 8*8의 첫번째가 제일 좋다는 보장을 못한다.
                        // 즉, 첫번째껄 색칠하는 경우가 더 좋을지도 모른다는 뜻이다.
                        // 맨 첫번 째 칸이 잘 색칠되었다고 가정하는 경우
                        if ((x - i) % 2 == 0 && !input[x][y].equals(go_obj[y - j])) go_cnt++;
                        if ((x - i) % 2 == 1 && !input[x][y].equals(back_obj[y - j])) go_cnt++;
                        // 맨 첫번 째 칸을 다시 색칠하는 경우
                        if ((x - i) % 2 == 0 && !input[x][y].equals(back_obj[y - j])) back_cnt++;
                        if ((x - i) % 2 == 1 && !input[x][y].equals(go_obj[y - j])) back_cnt++;
                    }
                }
                int cnt = Math.min(go_cnt, back_cnt);
                if(cnt < res) res = cnt;
            }
        }
        System.out.println(res);
    }
}